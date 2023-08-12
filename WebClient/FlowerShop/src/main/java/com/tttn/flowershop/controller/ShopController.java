package com.tttn.flowershop.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tttn.flowershop.model.*;
import com.tttn.flowershop.service.*;
import com.tttn.flowershop.utils.Const;
import com.tttn.flowershop.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/shop")
public class ShopController {

  @Autowired
  private JavaMailSender javaMailSender;
  private int idGiftVoucher = Const.idNoExist;
  // Mapping jsp
  @RequestMapping("main")
  public String index(ModelMap model) {
    List<Flower> list = FlowerService.getListFlower();
    model.addAttribute("flowers", list);
    return "shop/main";
  }

  @RequestMapping("search")
  public String index1(ModelMap model, HttpServletRequest request) {

    String text = request.getParameter("search");

    List<Flower> list = FlowerService.getFlowerByKey(text);
    model.addAttribute("flowers", list);
    return "shop/main";
  }


  // cart
  @RequestMapping(value = "cart", method = RequestMethod.GET)
  public String cart(ModelMap model, HttpServletRequest request) throws Exception {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("userLogin");
    if (user == null) {
      return "redirect:/pages/error";
    }
    List<ShopCart> listCartUpdate = (List<ShopCart>) session.getAttribute("cartUpdate");
    List<ShopCart> list = listCartUpdate != null ? listCartUpdate : CartService.getCartsByUserId(user.getId());
    if (list.size() == 0)
      return "redirect:/pages/error";
    // đồng bộ cập nhật giỏ hàng
    for (ShopCart cart : list) {
      Flower flo = FlowerService.getFlowerById(cart.getIdFlower());
      cart.setFlower(flo);
      if (cart.getAmount().compareTo(flo.getPriceAfterDiscount()) != 0) {
        // update lại giá trong db khi hoa thay đổi giá
        cart.setAmount(flo.getPriceAfterDiscount());
        CartService.update(cart);
      }
    }

    model.addAttribute("subTotal", ShopService.subTotal(list));
    model.addAttribute("ship", ShopService.ship(list));
    model.addAttribute("carts", list);
    return "shop/cart";
  }

  @RequestMapping(value = "cart/insert/{floId}", method = RequestMethod.GET)
  public String cartInsert(ModelMap model, @PathVariable("floId") int floId, HttpServletRequest request) throws Exception {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("userLogin");
    // check số lượng sản phẩm trong kho 
   int storage = StorageService.getStorageByFlower(floId);
    if(storage == 0) {
    		
    		 return "redirect:/shop/product_detail/{floId}";
    }
    // số lượng khi add vào giỏ hàng
    int sl;
    if (request.getParameter("qtt") == null) sl = 1;
    else sl = Integer.parseInt(request.getParameter("qtt"));
    System.out.println("check" + sl);
    if (sl > storage) {
    	
    	 return "redirect:/shop/product_detail/{floId}";
    }
    // xử lý trùng cart
    ShopCart cartCopy = CartService.getCartByUserFlo(user.getId(), floId);
    if (cartCopy != null) {
      cartCopy.setQuantity(cartCopy.getQuantity() + sl);
      System.out.println(sl + " - " + cartCopy.getQuantity());
      CartService.update(cartCopy);
    } else {
      Flower flo = FlowerService.getFlowerById(floId);
      if (flo.getDiscount() == null) flo.setDiscount(0);
      //thêm vào db - chú ý tiền sau khi đã giảm giá
      ShopCart cart = new ShopCart(0, user.getId(), flo.getId(), sl, flo.getPriceAfterDiscount(), false);
      CartService.create(cart);
    }
    return "redirect:/shop/cart";
  }

  @RequestMapping(value = "cart/update", method = RequestMethod.GET)
  public String cartUpdate(ModelMap model, HttpServletRequest request) throws Exception {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("userLogin");

    List<ShopCart> list = CartService.getCartsByUserId(user.getId());
    for (ShopCart item : list) {
      //System.out.println(request.getParameter("qtt" + item.getId()));
      int sl = Integer.parseInt(request.getParameter("qtt" + item.getId()));
      if (sl != item.getQuantity()) {
        item.setQuantity(sl);
        CartService.update(item);
      }
      if (!String.valueOf(request.getParameter("selected" + item.getId())).equals("on")) {
        item.seiIsSelected(false);
      }
    }
    session.setAttribute("cartUpdate", list);
    return "redirect:/shop/cart";
  }

  @RequestMapping(value = "cart/delete/{id}", method = RequestMethod.GET)
  public String cartDelete(ModelMap model, @PathVariable("id") int id, HttpServletRequest request) throws Exception {

    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("userLogin");
    // check delete true user
    ShopCart cart = CartService.getCartById(id);
    if (cart.getUserID() != user.getId()) {
      
      return "pages/error404";
    }

    cart.setIsDeleted(true);
    CartService.update(cart);
    return "redirect:/shop/cart";
  }


  // product_detail
  @RequestMapping(value = "product_detail/{floId}", method = RequestMethod.GET)
  public String product_detail(ModelMap model, @PathVariable("floId") int floId) {
  
    Flower flo = FlowerService.getFlowerById(floId);
    model.addAttribute("flower", flo);
    
    model.addAttribute("remain", StorageService.getStorageByFlower(floId));
    
    return "shop/product_detail";
  }

  //checkout
  @RequestMapping(value = "checkout", method = RequestMethod.GET)
  public String checkout(ModelMap model, HttpServletRequest request) throws Exception {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("userLogin");

    List<ShopCart> listCartUpdate = (List<ShopCart>) session.getAttribute("cartUpdate");
    List<ShopCart> list = listCartUpdate != null ? listCartUpdate : CartService.getCartsByUserId(user.getId());
    if (list.size() == 0)
      return "redirect:/pages/error";
    list.forEach(temp -> temp.setFlower(FlowerService.getFlowerById(temp.getIdFlower())));
    list = list.stream().filter(ShopCart::getIsSelected).collect(Collectors.toList());

    for (ShopCart cart : list) {
      if (cart.getFlower().getQuantity() < cart.getQuantity())
      {
        model.addAttribute("checkQuantity", false);
        model.addAttribute("checkQttDialog", cart.getFlower().getName() + " chỉ còn lại "+ cart.getFlower().getQuantity() +" sản phẩm. Vui lòng cập nhật lại giỏ hàng.");
        return cart(model,request);
      }
    }

    // check hết hàng -> không cho check out hàng đó
    

    model.addAttribute("carts",list);

    model.addAttribute("subTotal", ShopService.subTotal(list));
    model.addAttribute("ship", ShopService.ship(list));
    model.addAttribute("detailTransaction", new Transaction());

    String message = "Do you want to ship to a different address? Edit form below.";
    model.addAttribute("message", message);
    return "shop/checkout";
  }

  @RequestMapping(value = "payment", method = RequestMethod.POST)
  public String checkout1(ModelMap model, HttpServletRequest request) {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("userLogin");

    long usePoint =  0L;
    long giftVoucherAmount = 0L;
    long onlinePaymentAmount = 0L;
    String giftVoucherInput;


    if (request.getParameter("usepointModal") != null && !request.getParameter("usepointModal").equals("0")
    && !request.getParameter("usepointModal").trim().isEmpty()){
      usePoint = Long.parseLong(request.getParameter("usepointModal"));
    }

    if (request.getParameter("giftvoucherModal") != null &&
            (!request.getParameter("giftvoucherModal").equals("0") && !request.getParameter("giftvoucherModal").trim().isEmpty()))
    {
    
      boolean isHaveValue = true;

      if (isHaveValue){
        String[] code = request.getParameter("giftvoucherModal").split("_");
        // xử lý check PMH từ db
        GiftVoucher giftVoucher = new GiftVoucher();
        try {
        	 
          giftVoucher = OrderPaymentService.getGiftVoucherByKey(code[1]);
          
        } catch (Exception e){
          e.printStackTrace();
          giftVoucher = null;
        }
        if ( giftVoucher != null){
          if (!giftVoucher.getIsUsed()){
        	  System.out.println("idGiftVoucher " + giftVoucher.getId());
            idGiftVoucher = giftVoucher.getId();
            giftVoucherAmount = giftVoucher.getValue().longValue();
            
          }
          else {
            model.addAttribute("addPMH", false);
            model.addAttribute("messagePMH", "Phiếu mua hàng đã được sử dụng");
          }
        } else {
          model.addAttribute("addPMH", false);
          model.addAttribute("messagePMH", "Phiếu mua hàng không tồn tại. Vui lòng thử lại");
        }
      }
    }

   

    List<ShopCart> listCartUpdate = (List<ShopCart>) session.getAttribute("cartUpdate");
    List<ShopCart> list = listCartUpdate != null ? listCartUpdate : CartService.getCartsByUserId(user.getId());
    list.forEach(temp -> temp.setFlower(FlowerService.getFlowerById(temp.getIdFlower())));
    list = list.stream().filter(ShopCart::getIsSelected).collect(Collectors.toList());
    model.addAttribute("carts", list.stream().filter(ShopCart::getIsSelected).collect(Collectors.toList()));

    model.addAttribute("subTotal", ShopService.subTotal(list));
    model.addAttribute("ship", ShopService.ship(list));
    model.addAttribute("detailTransaction", new Transaction());

    BigDecimal totalAmount = ShopService.subTotal(list).add(ShopService.ship(list));
    BigDecimal totalAmountNotIncludeGiftVoucher = BigDecimal.valueOf(usePoint ).multiply(BigDecimal.valueOf(-1));
    if (totalAmount.add(totalAmountNotIncludeGiftVoucher).compareTo(BigDecimal.valueOf(giftVoucherAmount)) < 0) {
      giftVoucherAmount = totalAmount.add(totalAmountNotIncludeGiftVoucher).longValue();
    }
    model.addAttribute("loyaltyPoint", usePoint);
    model.addAttribute("giftVoucher", giftVoucherAmount);
    model.addAttribute("onlinePayment", onlinePaymentAmount);

    String message = "Do you want to ship to a different address? Edit form below.";
    model.addAttribute("message", message);
    return "shop/checkout";
  }

  @RequestMapping(value = "checkout", method = RequestMethod.POST)
  public String checkoutSave(ModelMap model, HttpServletRequest request, @ModelAttribute("detailTransaction") Transaction trans,
                             BindingResult errors) throws Exception{
    // test các rằng buộc
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("userLogin");
    List<ShopCart> listCartUpdate = (List<ShopCart>) session.getAttribute("cartUpdate");
    List<ShopCart> list = listCartUpdate != null ? listCartUpdate : CartService.getCartsByUserId(user.getId());
    list.forEach(temp -> temp.setFlower(FlowerService.getFlowerById(temp.getIdFlower())));
    list = list.stream().filter(ShopCart::getIsSelected).collect(Collectors.toList());

    Matcher matcherEmail = Global.patternEmail.matcher(trans.getRecipientEmail().trim());
    Matcher matcherPhone = Global.patternPhone.matcher(trans.getRecipientPhone().trim());
    if (trans.getRecipientName().trim().length() == 0) {
      errors.rejectValue("recipientName", "trans", "RecipientName cannot be blank");
    }
    if (trans.getRecipientAddress().trim().length() == 0) {
      errors.rejectValue("recipientAddress", "trans", "RecipientAddress cannot be blank");
    }
    if (!matcherEmail.matches()) {
      errors.rejectValue("recipientEmail", "trans", "RecipientEmail is invalidate");
    }
    if (!matcherPhone.matches()) {
      errors.rejectValue("recipientPhone", "trans", "RecipientPhone is invalidate");
    }

    if (errors.hasErrors()) {
      model.addAttribute("carts", list.stream().filter(ShopCart::getIsSelected).collect(Collectors.toList()));
      model.addAttribute("subTotal", ShopService.subTotal(list));
      model.addAttribute("ship", ShopService.ship(list));
      String message = "Do you want to ship to a different address? Edit form below.";
      model.addAttribute("message", message);
    } else {
    	
      // lưu vào bảng Trans + lưu vào bảng Order
      
      long usePoint =  0L;
      long giftVoucherAmount = 0L;
      long onlinePaymentAmount = 0L;
      BigDecimal totalAmount = ShopService.subTotal(list).add(ShopService.ship(list));

      AtomicBoolean isError = new AtomicBoolean(false);
      boolean isUsedPoint = false;
      boolean isUsedGiftVoucher = false;
      boolean isUsedOnlPayment = false;

      if (list.size() != 0) {
        trans.setStatus(false);
        trans.setUserID(user.getId());
        trans.setAmount(totalAmount);
        
        
       
        	
          int idTrans = OrderPaymentService.createTransaction(trans);
       
//          int idPayTrans = PaymentTransactionService.createPaymentTransaction();
          if(idTrans !=  Const.idNoExist) {
          list.forEach(cart -> {
            Order order = new Order(cart.getIdFlower(), cart.getQuantity(), cart.getAmount(), idTrans);
				try {
					int idOrder = Const.idNoExist;
					idOrder = OrderPaymentService.createOrder(order);
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
          });
          }
          
        
        
        if (idTrans > 0){
          // Cập nhật trạng thái giỏ hàng
          list.forEach(temp -> {
            temp.setIsOrdered(true);
            try {
              CartService.update(temp);
              // cập nhật số lượng tồn -> update Storage
              OrderPaymentService.addStorage(new Storage(temp.getIdFlower(), temp.getQuantity(), "",temp.getFlower().getPrice()));
             
            } catch (Exception e) {
              isError.set(true);
              throw new RuntimeException(e);
            }
            // xoas cartupdate session
            if (listCartUpdate != null) {
              session.removeAttribute("cartUpdate");
            }
          });

          // CẬP NHẬT THANH TOÁN LOCAL
          // Cập nhật điểm + update PMH
          if (request.getParameter("usepointDisplay") != null && !request.getParameter("usepointDisplay").equals("0")
                  && !request.getParameter("usepointDisplay").trim().isEmpty()){
            usePoint = Long.parseLong(request.getParameter("usepointDisplay"));
            user.setLoyaltyPoint(user.getLoyaltyPoint() - usePoint);
            try {
              UserService.updateUser(user);
              isUsedPoint = true;
            } catch (Exception e){
              e.printStackTrace();
              usePoint = 0;
            }
          }
         
          if (request.getParameter("giftvoucherDisplay") != null &&
                  (!request.getParameter("giftvoucherDisplay").equals("0") && !request.getParameter("giftvoucherDisplay").trim().isEmpty())) {
            giftVoucherAmount = Long.parseLong(request.getParameter("giftvoucherDisplay"));
            try{
              GiftVoucher giftVoucher = OrderPaymentService.getGiftVoucherByKey(String.valueOf(idGiftVoucher));
              giftVoucher.setIsUsed(true);
             
              // gọi api cập nhật PMH
              OrderPaymentService.updateGiftVoucher(giftVoucher);
            }catch (Exception e){
              e.printStackTrace();
              giftVoucherAmount = 0;
            }
          }
          BigDecimal cashVND = totalAmount.add(BigDecimal.valueOf(giftVoucherAmount).multiply(BigDecimal.valueOf(-1)))
                  .add(BigDecimal.valueOf(usePoint).multiply(BigDecimal.valueOf(-1)))
                  .add(BigDecimal.valueOf(onlinePaymentAmount).multiply(BigDecimal.valueOf(-1)));
          // Cập nhật trạng thái bảng Payment VỚI CÁC CỘT CHÌA RA LẠI (HẬU KIỂM SAU KHI LƯU DB) -> api cập nhật 3 bảng
          
          if (!isError.get()){
            // khi xác nhận thành công thì mới có cơ chế cộng điểm + xét PMH tặng
            try{
            	
              long point = user.getLoyaltyPoint() + totalAmount.multiply(new BigDecimal(0.01)).longValue();
              
              long valuePMH = totalAmount.divide(new BigDecimal(10)).longValue();
              point = 1000L * (point / 1000L);
              
              user.setLoyaltyPoint(point);
              
              UserService.updateUser(user);
              valuePMH = 50000L * (valuePMH / 50000L);
              
              if ( valuePMH >= 50000L){
                String codePMH = UUID.randomUUID().toString().replaceAll("_", "").substring(0,6).toUpperCase(Locale.ROOT);
                OrderPaymentService.addGiftVoucher(new GiftVoucher(codePMH, new BigDecimal(valuePMH)));
                String from = "FLOWER_SHOP";
                String subject = "GIFT VOUCHER";
                String body = "Bạn đã được tặng 1 phiếu mua hàng trị giá " + valuePMH + " với mã PMH: " + idTrans + "_" + codePMH ;
                new MailService(javaMailSender).sendEmail(from, user.getEmail().trim(), subject, body);
                
              }
            } catch (Exception e){
              e.printStackTrace();
            }
            
            PaymentTransaction paymentTransaction = new PaymentTransaction();
            paymentTransaction.setIdTrans(idTrans);
            
            paymentTransaction.setIdGiftVoucher(idGiftVoucher == Const.idNoExist ? 0 :idGiftVoucher );
            paymentTransaction.setPointUsed(BigDecimal.valueOf(usePoint));
            paymentTransaction.setIdPaymentType(Integer.parseInt(request.getParameter("rad")));
            paymentTransaction.setAmount(cashVND);
            
            PaymentTransactionService.create(paymentTransaction);

          }
        }
      }

      String message = "";
      if (isError.get()) message = "Thao tác không thành công";
      else {
        message = "Thao tác thành công";
      }
      model.addAttribute("message", message);


    }
    return "shop/checkout";
  }

  @Autowired
  ModelController mc;

  @ModelAttribute("listCarts")
  public List<ShopCart> dem(HttpServletRequest request) {
    return mc.dem(request);
  }

  @ModelAttribute("sizelistCarts")
  public int size(HttpServletRequest request) {
    return mc.size(request);
  }

  @ModelAttribute("totalCarts")
  public BigDecimal total(HttpServletRequest request) {
    return mc.total(request);
  }

  // show feature product

  @ModelAttribute("TopFlower")
  public List<FlowerTwo> arrange() {
    List<FlowerTwo> list = new ArrayList<FlowerTwo>();

    List<Flower> listFlo = mc.arrange(12);

    for (int i = 0; i < listFlo.size() / 2; i++) {
      Flower f1 = listFlo.get(i);
      Flower f2 = listFlo.get(i + 6);
      list.add(new FlowerTwo(f1, f2));
    }
    return list;
  }
}
