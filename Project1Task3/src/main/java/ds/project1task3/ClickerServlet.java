/*
    Author: sbadhan Siddhesh Badhan
    Last Modified: 2/10/2023
    
    Servlet file that invokes methods of Model file on the user selections,
    provided from prompt.jsp. The information is then passed to be displayed to the user.
 */
package ds.project1task3;

import java.io.IOException;
import java.util.Map;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ClickerServlet",
        urlPatterns = {"/getClicker", "/getResults"})
public class ClickerServlet extends HttpServlet {

    ClickerModel cmodel = null;

    //initiating the servlet
    @Override
    public void init() {
        cmodel = new ClickerModel();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     * 
     * The doGet method handles the HTTP GET requests.
     * It checks the URL the user is trying to go to and displays either getResults.jsp or prompt.jsp accordingly.
     * It also clears the results stored in getResults.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // retrieve the servlet path from the request
        String servletPath = request.getServletPath().toLowerCase();

        // check if the servlet path contains "/getresults"
        if (servletPath.contains("/getresults")) {
            // set the answers as an attribute in the request
            request.setAttribute("Answers", cmodel.answer);
            // get the request dispatcher for the "getResults.jsp" page
            RequestDispatcher view = request.getRequestDispatcher("getResults.jsp");
            // forward the request and response to the "getResults.jsp" page
            view.forward(request, response);
            // reset the answers by replacing all values with 0
            cmodel.answer.replaceAll((k, v) -> 0);
        }

        // get the request dispatcher for the "prompt.jsp" page
        RequestDispatcher view = request.getRequestDispatcher("prompt.jsp");
        // forward the request and response to the "prompt.jsp" page
        view.forward(request, response);
    }


    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
     * The doPost method handles the HTTP POST requests.
     * It receives the answer from the user and updates the answer count in the Map stored in the Model file.
     * It then sends the user to the output.jsp page.
     * It also checks if the device used by the user is mobile or not.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // flag which sets true if the device is mobile
        boolean isMobile;
        String nxtView;

        // getting the value of the "stage" parameter from the request
        String ans = request.getParameter("stage");

        // getting the user-agent header from the request
        String userType = request.getHeader("User-Agent");

        // checking if the user-agent header is not null and if it contains either "Android" or "iPhone"
        if (userType != null && ((userType.contains("Android")) || (userType.contains("iPhone")))) {
            // setting the isMobile flag to true
            isMobile = true;
            // setting the "doctype" attribute to the WAPFORUM doctype
            request.setAttribute("doctype", "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.2//EN\" \"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd\">");
        } else {
            // setting the isMobile flag to false
            isMobile = false;
            // setting the "mobile" attribute to false
            request.setAttribute("mobile", "false");
            // setting the "doctype" attribute to the HTML 4.01 Transitional doctype
            request.setAttribute("doctype", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        }

        // model to do the search and choose the result view
        // adding the answer to the cmodel and getting the result in a map
        Map<String, Integer> Answers = cmodel.addAnswer(ans);
        // setting the "Answers" attribute to the result map
        request.setAttribute("Answers", Answers);
        // setting the "answer" attribute to the ans value
        request.setAttribute("answer", ans);

        // setting the value of nxtView to "output.jsp"
        nxtView = "output.jsp";

        // getting the RequestDispatcher for the next view
        RequestDispatcher view = request.getRequestDispatcher(nxtView);
        // forwarding the request and response to the next view
        view.forward(request, response);

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
