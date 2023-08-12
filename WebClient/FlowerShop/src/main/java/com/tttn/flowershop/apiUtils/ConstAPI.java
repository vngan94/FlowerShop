package com.tttn.flowershop.apiUtils;

public class ConstAPI {

  // sau này chung 1 gateway
  public static boolean isGateway = true;
  public static String URL_API = "http://localhost:8085/";
  public static String URL_API_Order = "http://localhost:8081/";
  public static String URL_API_Payment = "http://localhost:8082/";
  public static String URL_API_Product = "http://localhost:8083/";
  public static String Service_API_Order = "OrderAPI/";
  public static String Service_API_Payment = "PaymentAPI/";
  public static String Service_API_Product = "ProductAPI/";
  public static String URL_ver = "api/v1/";
  public static String accessToken ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJEb2FuQ3VvbmdEYWkiLCJuYW1lIjoiSERWIEdyb3VwIiwiaWF0IjoyNTAxMjAyMjA5MTE3MTE5MDB9.2VaeS_V11otO0TX6P1w9eIPQQKtlNHbGfUoS55AzkGg";

  //Function API Order
  public class APIOrder {
    public static final String Checklogin = "CheckLogin";
    public static final String GetUserByKey = "GetUserByKey";
    public static final String AddUser = "PostAccount";
    public static final String UpdateUser = "UpdateAccount";

    public static final String GetCartsByUserId = "GetCartsByUserId";
    public static final String GetCartByUserFlo = "GetCartByUserFlo";
    public static final String GetCartById = "GetCartById";
    public static final String CreateCart = "CreateCart";
    public static final String UpdateCart = "UpdateCart";
    public static final String PostTransaction = "PostTransaction";
    
    public static final String GetAllTransaction = "GetAllTransaction";
    public static final String AcceptTransaction = "AcceptTransaction";
    public static final String CancelTransaction = "CancelTransaction";
    public static final String GetTransactionById = "GetTransactionById";
    
    public static final String GetOrderByIdTrans = "GetOrderByIdTrans";
    public static final String PostOrder = "PostOrder";
	public static final String GetOrderTransReport = "GetAllOrderReport";
	public static final String GetAllTransReport = "GetAllTransByDate";
  }

  //Function API Product
  public class APIProduct {
    public static final String GetALLColor = "GetAllColor";
    public static final String GetAllCategory = "GetAllCategory";
    public static final String GetAllFlower = "GetAllFlower";
    public static final String PostFlower = "PostFlower";
    public static final String UpdateFlower = "UpdateFlower";
    public static final String BlockAndUnblockFlower = "BlockAndUnblockFlower";
    public static final String GetFlowerById = "GetFlowerById";
    public static final String GetListFlowerByKey = "GetListFlowerByKey";
    public static final String AddStorage = "AddStorage";
    
    public static final String GetStorageByFlower = "GetStorageByFlower";
    public static final String AddStorage_CancelOrder = "AddStorage_CancelOrder";
	
    

  }
  
  // Function API Transaction


  public class APIPayment {
    public static final String GetGiftVoucherByKey = "GetGiftVoucherByKey";
    public static final String UpdateGiftVoucher = "UpdateGiftVoucher";
    public static final String PostGiftVoucher = "PostGiftVoucher";
    public static final String GetAllCategory = "GetAllCategory";
    public static final String GetAllFlower = "GetAllFlower";
    public static final String UpdateFlower = "UpdateFlower";
    public static final String GetFlowerById = "GetFlowerById";
    public static final String GetListFlowerByKey = "GetListFlowerByKey";
    
    public static final String getPaymentTransactionByIdTrans = "GetPaymentTransactionByIdTrans";
    public static final String PostPaymentTransaction = "PostPaymentTransaction";

  }


  public static String getURL(String serviceName){
    String url = isGateway ? URL_API : (
            serviceName.trim().equals(Service_API_Order) ? URL_API_Order : (
                    serviceName.trim().equals(Service_API_Product) ? URL_API_Product : URL_API_Payment
            )
    );
    return url + serviceName + URL_ver;
//    return "http://localhost:8081/api/v1/po";
  }
}
