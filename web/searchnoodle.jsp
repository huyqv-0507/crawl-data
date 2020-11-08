<%-- 
    Document   : searchnoodle
    Created on : Nov 4, 2020, 1:59:18 PM
    Author     : Huy Nguyen
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            td {
                border: 1px solid black;
            }
        </style>
    </head>
    <body>
        <form action="SearchServlet">
            Nhập tên mì <br/>
            <input type="text" name="txtSearchNoodle">
            <br/>
            <input type="submit" value="Seach" name="btnSearchNoodle">
        </form>
        Tổng tiền: 
        <fmt:formatNumber pattern="###,###,###">
            <c:out value="${sessionScope.SELECTED_NOODLE}"/>
        </fmt:formatNumber>
        <table style="border: 1px solid black; width: .7">
            <thead style="text-align: center; font-weight: bold; background-color: activeborder">
                <tr>
                    <td>STT</td>
                    <td>Tên mì</td>
                    <td>Đơn giá (đ/thùng)</td>
                    <td>Thành tiền (đ)</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="noodle" items="${sessionScope.NOODLES}" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${noodle.noodleName}</td>
                        <td>
                            <fmt:formatNumber pattern="###,###,###">
                                ${noodle.price}
                            </fmt:formatNumber>
                        </td>
                        <td>
                            <fmt:formatNumber pattern="###,###,###">
                                ${noodle.price * sessionScope.NOODLENUMBER}
                            </fmt:formatNumber>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
