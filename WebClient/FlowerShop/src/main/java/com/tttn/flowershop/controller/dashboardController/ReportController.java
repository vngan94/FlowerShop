package com.tttn.flowershop.controller.dashboardController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tttn.flowershop.model.Flower;
import com.tttn.flowershop.model.GetAllTransactionByDate;
import com.tttn.flowershop.model.Order;
import com.tttn.flowershop.model.Transaction;
import com.tttn.flowershop.service.FlowerService;
import com.tttn.flowershop.service.OrderService;
import com.tttn.flowershop.service.PaymentTransactionService;
import com.tttn.flowershop.service.TransactionService;


@Controller
@RequestMapping("/admin/report")
public class ReportController {
	@RequestMapping(value = "", method = RequestMethod.GET) 
	public String home(ModelMap model, HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		User user = (User) session.getAttribute("userLogin");
//		
//		if (user == null || user.getIdRole() == Const.User.idCustomerRole) {
//			return "pages/error404";
//		}
		return "admin/report";
	}
	@RequestMapping(value= "", params = "btnReport")
	public String searchFlower(HttpServletRequest request, ModelMap model) throws Exception {
		 BigDecimal amount = BigDecimal.ZERO;
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		
		
		List<Order> listOrder = new ArrayList<>();
		listOrder = OrderService.getOrderTransReport(from, to);
		listOrder.forEach(o -> o.setFlower(FlowerService.getFlowerById(o.getIdFlower())));
		PagedListHolder pagedListHolder = new PagedListHolder(listOrder);
		List<Transaction> listTrans = TransactionService.getAllTransReport(from, to);
		
		for(Transaction t : listTrans) {
			System.out.println(PaymentTransactionService.getPaymentTransactionByIdTrans(t.getId()).getAmount());
			amount = amount.add(PaymentTransactionService.getPaymentTransactionByIdTrans(t.getId()).getAmount()) ;
			
			
		}
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(2);
		pagedListHolder.setPageSize(10);
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		model.addAttribute("amount", amount);
	

		return "admin/report";
	}

}
