package com.tttn.flowershop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"com.tttn.flowershop.controller"})
public class FlowerShopApplication{

  public static void main(String[] args) {
    SpringApplication.run(FlowerShopApplication.class, args);
  }

}