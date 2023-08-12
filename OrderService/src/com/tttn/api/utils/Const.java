package com.tttn.api.utils;

public class Const {
    public static final String regPhone = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
    public static final String regEmail="^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    static class Role {
        public static final String adminRole = "Admin";
        public static final String employeeRole = "Employee";
        public static final String customerRole = "Customer";
        public static final int idAdminRole = 1;
        public static final int idEmployeeRole = 2;
        public static final int idCustomerRole = 3;
    }
}
