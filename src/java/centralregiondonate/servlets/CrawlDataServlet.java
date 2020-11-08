/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralregiondonate.servlets;

import centralregiondonate.constants.URLConstant;
import centralregiondonate.services.GaoSachGiaBaoService;
import centralregiondonate.services.KeHoachVietService;
import centralregiondonate.services.OkfoodService;
import centralregiondonate.services.PharmacityService;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Huy Nguyen
 */
@WebServlet(name = "CrawlDataServlet", urlPatterns = {"/CrawlDataServlet"})
public class CrawlDataServlet extends HttpServlet {
    private static final String DEFAULT_URL = "crawlpage.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        if (request.getParameter("btnPharmacity") != null) {
            PharmacityService service = new PharmacityService();
            service.getMedicines(URLConstant.PHARMACITY_ROOT);
        }
        if (request.getParameter("btnGaoSachGiaBao") != null) {
            GaoSachGiaBaoService service = new GaoSachGiaBaoService();
            service.getRices(URLConstant.GSGB_ROOT);
        } 
        if (request.getParameter("btnOkfood") != null) {
            OkfoodService service = new OkfoodService();
            service.getNoodles(URLConstant.OKFOOD_ROOT);
        }
        if (request.getParameter("btnKHV") != null) {
            KeHoachVietService service = new KeHoachVietService();
            service.getProvices(URLConstant.KHV_ROOT);
        }
        RequestDispatcher rd = request.getRequestDispatcher(DEFAULT_URL);
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
