<%-- 
    Document   : crawlpage
    Created on : Oct 19, 2020, 11:18:11 AM
    Author     : Huy Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Trang Crawl</h1>
        <a href="home.jsp">Về trang chủ</a>
        <form action="CrawlDataServlet" method="POST">
            <h3>Pharmacity</h3>
            website: https://www.pharmacity.vn/
            <input type="submit" value="Bắt đầu" name="btnPharmacity" />
            </br>
            <h3>Kế hoạch Việt</h3>
            website: https://kehoachviet.com/
            <input type="submit" value="Bắt đầu" name="btnKHV" />
            </br>
            <h3>Okfood</h3>
            website: https://okfood.vn/
            <input type="submit" value="Bắt đầu" name="btnOkfood" /></br>
            <h3>Gạo sạch Gia Bảo</h3>
            website: https://gaosachgiabao.net/
            <input type="submit" value="Bắt đầu" name="btnGaoSachGiaBao" />
        </form>
    </body>
</html>
