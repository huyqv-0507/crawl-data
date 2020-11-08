<%-- 
    Document   : donate
    Created on : Oct 21, 2020, 4:20:51 PM
    Author     : Huy Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Điền thông tin</h1>
        Chọn tỉnh thành: 
        <form action="ProcessServlet">
            <select name="selectedProvince">
                <c:forEach var="province" items="${PROVINCES}" varStatus="counter">
                    <option value="${counter.index}">${province.provinceName}</option>
                </c:forEach>
            </select>
            <p>Nhập số <strong>hộ</strong> dân bị ảnh hưởng</p>
            <input type="text" name="txtNumberDamaged"><br/>
            
            <input type="submit" value="Xem" name="btnAction">
            
            <p style="color: red">${requestScope.MESSAGE}</p>
            
        </form>
        <br/>
        <a href="home.jsp">Về trang chủ</a>
    </body>
</html>
