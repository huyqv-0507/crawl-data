<%-- 
    Document   : result
    Created on : Nov 3, 2020, 5:08:44 PM
    Author     : Huy Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            td {
                border: 1px solid black
            }
        </style>
    </head>
    <body>
        <a href="check.jsp">Quay lại</a>
        <h1>Kết quả</h1>
        <i>(Kết quả chỉ mang tính chất tham khảo).</i><br/>
        <strong>1. Tỷ lệ số hộ dân bị ảnh hưởng bởi bão:</strong> <span style="color: blue">${sessionScope.DAMAGED_PERCENT}</span>
        <br/>
        <strong>2. Mặt hàng thuốc cần thiết sau bão, lũ:</strong>
        <br/>
        <c:forEach var="cateName" items="${sessionScope.CATEGORYNAME}">
            <p>- ${cateName}</p>
        </c:forEach>
        <a href="detail.jsp">Xem chi tiết</a>
        <br/>
        <br/>
        <strong>3. Số lượng mì cần thiết trong 7 ngày (3 gói/ngày):</strong>
        <span> ${sessionScope.NOODLENUM} thùng</span>
        <br/>
        <iframe src="searchnoodle.jsp" width="700" height="400"></iframe>
        <br/>
        <strong>3. Số lượng gạo cần thiết trong 7 ngày (300g/người/ngày):</strong>
        <span> ${sessionScope.RICENUM} kg</span>
        <br/>
        <iframe src="searchrice.jsp" width="700" height="400"></iframe>
        <br/>
        
    </body>
</html>
