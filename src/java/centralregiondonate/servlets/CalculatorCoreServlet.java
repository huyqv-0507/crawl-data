/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralregiondonate.servlets;

import centralregiondonate.dtos.TblMedicine;
import centralregiondonate.dtos.TblProvince;
import centralregiondonate.dtos.TblRice;
import centralregiondonate.helpers.CalculatorHelper;
import centralregiondonate.helpers.ValidatorHelper;
import centralregiondonate.services.GaoSachGiaBaoService;
import centralregiondonate.services.PharmacityService;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Huy Nguyen
 */
@WebServlet(name = "CalculatorCoreServlet", urlPatterns = {"/CalculatorCoreServlet"})
public class CalculatorCoreServlet extends HttpServlet {

    private final String INVALID_PAGE = "invalid.jsp";
    private final String RESULT_PAGE = "result.jsp";
    private final String CHECK_PAGE = "check.jsp";

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
        String url = INVALID_PAGE;
        String msg = "";
        try {
            String selectedProvince = request.getParameter("selectedProvince");

            ServletContext context = this.getServletContext();
            List<TblProvince> provinces = (List<TblProvince>) context.getAttribute("PROVINCES");
            long totalPopulation = provinces.get(Integer.parseInt(selectedProvince)).getPopulation();

            String txtNumberDamaged = request.getParameter("txtNumberDamaged");
            float numberDamaged = (float) Long.parseLong(txtNumberDamaged);

            float numberDamagedPerson = numberDamaged * 4;

            if (numberDamagedPerson > totalPopulation) {
                msg = "Số hộ dân vượt quá tổng. Vui lòng thử lại";
                url = CHECK_PAGE;
            } else {
                HttpSession session = request.getSession();
                String percent = CalculatorHelper.calculatePercentDamaged(numberDamagedPerson, totalPopulation);
                session.setAttribute("DAMAGED_PERCENT", percent);

                List<TblMedicine> painFevers = PharmacityService.fetchPainFevers();
                session.setAttribute("PAIN_FEVER", painFevers);

                List<TblMedicine> coldCoughs = PharmacityService.fetchColdCoughs();
                session.setAttribute("COLD_COUGH", coldCoughs);

                List<TblMedicine> dermatologies = PharmacityService.fetchDermatologies();
                session.setAttribute("DERMATOLOGY", dermatologies);

                List<TblMedicine> degestives = PharmacityService.fetchDegestives();
                session.setAttribute("DEGESTIVE", degestives);

                List<TblMedicine> vitamins = PharmacityService.fetchVitamins();
                session.setAttribute("VITAMIN", vitamins);

                List<String> categories = new ArrayList<>();
                categories.add(painFevers.get(0).getCategoryName());
                categories.add(coldCoughs.get(0).getCategoryName());
                categories.add(dermatologies.get(0).getCategoryName());
                categories.add(degestives.get(0).getCategoryName());
                categories.add(vitamins.get(0).getCategoryName());

                session.setAttribute("CATEGORYNAME", categories);

                float noodleNum = numberDamagedPerson * 3 * 7 / 30;
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                session.setAttribute("NOODLENUM", decimalFormat.format(noodleNum));

                session.setAttribute("NOODLENUMBER", noodleNum);

                float riceNum = numberDamagedPerson * 300 * 7 / 1000;
                session.setAttribute("RICENUM", decimalFormat.format(riceNum));
                session.setAttribute("RICENUMBER", riceNum);

                List<TblRice> rices = GaoSachGiaBaoService.fetchRices();
                session.setAttribute("RICES", rices);
                
                url = RESULT_PAGE;
            }
            request.setAttribute("MESSAGE", msg);

        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
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
