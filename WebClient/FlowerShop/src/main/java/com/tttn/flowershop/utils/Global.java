package com.tttn.flowershop.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tttn.flowershop.model.User;

import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

public class Global {
  public static Pattern patternEmail = Pattern.compile(Const.Regex.regEmail);
  public static Pattern patternPhone = Pattern.compile(Const.Regex.regPhone);

  public static final Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().setPrettyPrinting().create();
  public static ObjectMapper objectMapper = new ObjectMapper();

  public static String getRole(HttpSession session){
    User user = (User) session.getAttribute("userLogin");
    return user.getIdRole() == Const.User.idCustomerRole ? Const.User.customerRole :
            (user.getIdRole() == Const.User.idEmployeeRole ? Const.User.employeeRole : Const.User.adminRole);
  }

}
