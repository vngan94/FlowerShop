<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
Hello world abc
<%
    String url= "home/index";
//    String url= "admin/flower";
    /* String url="admin/flower.htm"; */
    response.sendRedirect(url);
%>
</body>
</html>