<%-- 
    Document   : detail
    Created on : Nov 4, 2020, 1:37:45 PM
    Author     : Huy Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail</title>
         <style>
            td {
                border: 1px solid black
            }
        </style>
    </head>
    <body>
        <h1>Bảng thuốc chi tiết</h1>
        <a href="result.jsp">Quay lại</a>
        <c:forEach var="cateName" items="${sessionScope.CATEGORYNAME}" varStatus="counter">
            <p><strong>- ${cateName}</strong></p>
            <table style="border: 1px solid black; width: .7">
                <thead style="text-align: center; font-weight: bold; background-color: activeborder">
                    <tr>
                        <td>STT</td>
                        <td>Tên thuốc</td>
                        <td>Giá</td>
                        <td>Đóng gói</td>
                        <td>Mô tả</td>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${counter.count == 1}">
                        <c:forEach var="medicine" items="${sessionScope.PAIN_FEVER}" varStatus="counter">
                            <tr>
                                <td width="26">${counter.count}</td>
                                <td width="250">${medicine.medicineName}</td>
                                <td width="40">${medicine.price}</td>
                                <td width="40">${medicine.packing}</td>
                                <td width="500">${medicine.description}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${counter.count == 2}">
                        <c:forEach var="medicine" items="${sessionScope.COLD_COUGH}" varStatus="counter">
                            <tr>
                                <td width="26">${counter.count}</td>
                                <td width="250">${medicine.medicineName}</td>
                                <td width="40">${medicine.price}</td>
                                <td width="40">${medicine.packing}</td>
                                <td width="500">${medicine.description}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${counter.count == 3}">
                        <c:forEach var="medicine" items="${sessionScope.DERMATOLOGY}" varStatus="counter">
                            <tr>
                                <td width="26">${counter.count}</td>
                                <td width="250">${medicine.medicineName}</td>
                                <td width="40">${medicine.price}</td>
                                <td width="40">${medicine.packing}</td>
                                <td width="500">${medicine.description}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${counter.count == 4}">
                        <c:forEach var="medicine" items="${sessionScope.DEGESTIVE}" varStatus="counter">
                            <tr>
                                <td width="26">${counter.count}</td>
                                <td width="250">${medicine.medicineName}</td>
                                <td width="40">${medicine.price}</td>
                                <td width="40">${medicine.packing}</td>
                                <td width="500">${medicine.description}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${counter.count == 5}">
                        <c:forEach var="medicine" items="${sessionScope.VITAMIN}" varStatus="counter">
                            <tr>
                                <td width="26">${counter.count}</td>
                                <td width="250">${medicine.medicineName}</td>
                                <td width="40">${medicine.price}</td>
                                <td width="40">${medicine.packing}</td>
                                <td width="500">${medicine.description}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        </c:forEach>
            
    </body>
</html>
