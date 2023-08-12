package com.tttn.flowershop.controller;

import com.tttn.flowershop.model.Flower;
import com.tttn.flowershop.model.FlowerTwo;
import com.tttn.flowershop.model.ShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

	@Autowired
	ModelController mc;
  @GetMapping("/")
  public String index1(Model model) {
    model.addAttribute("message", "Welcome to Simple Solution home page!");
    return "index";
  }

  @GetMapping("/home/index")
  public String index(Model model) {
	  
    return "home/index";
  }

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
