<%-- 
    Document   : searchrice
    Created on : Nov 4, 2020, 1:59:31 PM
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
        <table style="border: 1px solid black; width: .7">
            <thead style="text-align: center; font-weight: bold; background-color: activeborder">
                <tr>
                    <td>STT</td>
                    <td>Tên gạo</td>
                    <td>Đơn giá (đ/kg)</td>
                    <td>Thành tiền (đ)</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="rice" items="${sessionScope.RICES}" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${rice.riceName}</td>
                        <td>
                            <fmt:formatNumber pattern="###,###,###">
                                ${rice.price}
                            </fmt:formatNumber>
                        </td>
                        <td>
                            <fmt:formatNumber pattern="###,###,###">
                                ${rice.price * sessionScope.RICENUMBER}
                            </fmt:formatNumber>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>

