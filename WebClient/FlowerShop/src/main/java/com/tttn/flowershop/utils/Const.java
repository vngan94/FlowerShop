package com.tttn.flowershop.utils;

public class Const {

  public static final int idNoExist = -1;
  public static class Regex {
    public static final String regPhone = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
    public static final String regEmail="^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

  }
  public static class User {
    public static final String adminRole = "Admin";
    public static final String employeeRole = "Employee";
    public static final String customerRole = "Customer";
    public static final int idAdminRole = 1;
    public static final int idEmployeeRole = 2;
    public static final int idCustomerRole = 3;
    public static final int idNoExistUser = -1;
  }
  public static class Flower {
    public static final String basePath="C:\\Users\\LENOVO\\Desktop\\ThucTap\\Project\\WebClient\\FlowerShop\\src\\main\\webapp\\resources\\images\\flower";
    public static final int idNoExistFlower = -1;
    public static final int idExistFlower = 1;
    public static final int deleteFalse = -1;
    
  }
  
  public static class Transaction {
	    public static final int cancelFalse = -1;
	    public static final int acceptFalse = -1;
	    
	  }

  public static class PaymentType {
    public static final int GiftVoucherId = 1;
    public static final int LoyaltyPointId = 2;
    public static final int PaypalId = 3;
  }
}
