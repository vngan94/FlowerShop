package com.tttn.flowershop.controller.dashboardController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tttn.flowershop.model.Flower;
import com.tttn.flowershop.model.Order;
import com.tttn.flowershop.model.PaymentTransaction;
import com.tttn.flowershop.model.Storage;
import com.tttn.flowershop.model.Transaction;
import com.tttn.flowershop.model.User;
import com.tttn.flowershop.service.FlowerService;
import com.tttn.flowershop.service.OrderPaymentService;
import com.tttn.flowershop.service.OrderService;
import com.tttn.flowershop.service.PaymentTransactionService;
import com.tttn.flowershop.service.StorageService;
import com.tttn.flowershop.service.TransactionService;
import com.tttn.flowershop.utils.Const;
import com.tttn.flowershop.utils.Global;

@Controller
@RequestMapping("/admin/transaction")
public class ManageTransController {
	private static List<Transaction> transList = new ArrayList<>();

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(ModelMap model, HttpServletRequest request) throws Exception {
//		HttpSession session = request.getSession();
//		User user = (User) session.getAttribute("userLogin");
//		
//		if (user == null || user.getIdRole() == Const.User.idCustomerRole) {
//			return "pages/error404";
//		}

		transList = TransactionService.getAllTransaction();
		for(Transaction trans: transList) {
			trans.setPaymentTransaction(PaymentTransactionService.getPaymentTransactionByIdTrans(trans.getId()));
			
		}
		PagedListHolder pagedListHolder = new PagedListHolder(transList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(2);
		pagedListHolder.setPageSize(10);
		model.addAttribute("pagedListHolder", pagedListHolder);
//		model.addAttribute("role", Global.getRole(session));
		model.addAttribute("flowerEdit", new Flower());
		return "admin/transaction";
	}

	@RequestMapping(value = "", params = "btnsearch2")
	public String searchFlower(HttpServletRequest request, ModelMap model) throws Exception {

		String text = request.getParameter("searchInput");
		if (!text.equals("")) {
			List<Transaction> transactions = transList.stream().filter(temp -> temp.getPhone().equals(text))
					.collect(Collectors.toList());
			PagedListHolder pagedListHolder = new PagedListHolder(transactions);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(2);
			pagedListHolder.setPageSize(5);
			model.addAttribute("pagedListHolder", pagedListHolder);
			return "admin/transaction";
		} else {
			return home(model, request);
		}
	}
	
	@RequestMapping(value="accept/{id}", method=RequestMethod.POST) 
	public String accept(ModelMap model,HttpServletRequest request, @PathVariable String id) throws Exception {
		
		model.addAttribute("accept", TransactionService.acceptTransaction(Integer.parseInt(id)) != Const.Transaction.acceptFalse);

		return home(model, request);
	}
	
	@RequestMapping(value="cancel/{id}", method=RequestMethod.POST) 
	public String cancel(ModelMap model,HttpServletRequest request, @PathVariable String id) throws Exception {
		if (TransactionService.cancelTransaction(Integer.parseInt(id)) != Const.Transaction.cancelFalse) {
			model.addAttribute("cancel", true);
			
			// cap nhat lai so luong trong kho
			List<Order> listOrder = OrderService.getOrderByIdTrans(Integer.parseInt(id));
			Flower f = new Flower();
			for(Order o : listOrder) {
				OrderPaymentService.addStorage_CancelOrder(new Storage(o.getIdFlower(), o.getQuantity(), "", BigDecimal.ZERO));
			}
			
			
			
		}
		
		
		return home(model, request);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET) 
	public String getTransactionById (ModelMap model,HttpServletRequest request, @PathVariable String id,  @ModelAttribute("transDetail") Transaction transDetail) throws Exception {
		List<Order> listOrder = OrderService.getOrderByIdTrans(Integer.parseInt(id));
		
		Flower f = new Flower();
		for(Order o : listOrder) {
			
			f = FlowerService.getFlowerById(o.getIdFlower());
			o.setFlower(f);
		}
		transDetail = TransactionService.getTransactionById(Integer.parseInt(id));
		PaymentTransaction paymentTransaction = PaymentTransactionService.getPaymentTransactionByIdTrans(Integer.parseInt(id));
		model.addAttribute("formDetail" , true);
		
		model.addAttribute("transDetail",transDetail);
		model.addAttribute("listOrder", listOrder);
		model.addAttribute("paymentTransaction", paymentTransaction);
		
		
		
		return home(model, request);
	}
	
}
