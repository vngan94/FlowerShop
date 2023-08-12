package com.tttn.flowershop.controller;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tttn.flowershop.model.Flower;
import com.tttn.flowershop.model.ShopCart;
import com.tttn.flowershop.model.User;
import com.tttn.flowershop.service.CartService;
import com.tttn.flowershop.service.FlowerService;
import com.tttn.flowershop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class ModelController {

  @ModelAttribute("listCarts")
  public List<ShopCart> dem(HttpServletRequest request) {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("userLogin");
    if (user != null) {
      List<ShopCart> listCartUpdate = (List<ShopCart>) session.getAttribute("cartUpdate");
      List<ShopCart> list = listCartUpdate != null ?  listCartUpdate : CartService.getCartsByUserId(user.getId());
      for (ShopCart cart : list) {
        Flower flo = FlowerService.getFlowerById(cart.getIdFlower());
        cart.setFlower(flo);
        if (cart.getAmount().compareTo(flo.getPriceAfterDiscount()) != 0){
          // update lại giá trong db khi hoa thay đổi giá
          cart.setAmount(flo.getPriceAfterDiscount());
          try {
            CartService.update(cart);
          } catch (Exception e){
            e.printStackTrace();
          }
        }
      }
      return list;
    }
    return null;
  }

  @ModelAttribute("sizelistCarts")
  public int size(HttpServletRequest request) {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("userLogin");
    if (user != null) {
      List<ShopCart> listCartUpdate = (List<ShopCart>) session.getAttribute("cartUpdate");
      List<ShopCart> list = listCartUpdate != null ?  listCartUpdate : CartService.getCartsByUserId(user.getId());
      return list.size();
    }
    return 0;
  }

  @ModelAttribute("totalCarts")
  public BigDecimal total(HttpServletRequest request) {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("userLogin");
    if (user != null) {
      List<ShopCart> listCartUpdate = (List<ShopCart>) session.getAttribute("cartUpdate");
      List<ShopCart> list = listCartUpdate != null ?  listCartUpdate : CartService.getCartsByUserId(user.getId());
      return ShopService.subTotal(list.stream().filter(ShopCart::getIsSelected).collect(Collectors.toList()));
    }
    return null;
  }

  // model cho feature product Top sản phẩm gia cao nhat
  @ModelAttribute("TopFlower")
  public List<Flower> arrange(int top) {
	  
    List<Flower> list = FlowerService.getListFlower();
    
    return list.stream().sorted(Comparator.comparing(Flower::getPriceAfterDiscount).reversed()).limit(top).collect(Collectors.toList());
  }

}
