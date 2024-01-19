/**
* Author: sbadhan Siddhesh Badhan
* Date Modified: 02/10/2023
 * The Servlet file invokes the hashing methods of Model file on the user input that
 * is provided from index.jsp. It then passes that information to the output.jsp
 * file to be displayed back to the user.
*/
package ds.project1task1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "ComputeHashes", urlPatterns = {"/ComputeHashes"})
public class ComputeHashes extends HttpServlet {
    ComputeHashesModel hashesModel = null; // the model for this application

    //initiates the servlet by instantiating the model it will use.
    @Override
    public void init() {

        hashesModel = new ComputeHashesModel();
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
     * Reads in the user input to string values. Calls the encryption methods
     * from the Model.java file.  Returns user to output.jsp view page for
     * appropriate results to be displayed.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Default next view is the index.jsp
        String nextView = "index.jsp";

        // Get the input string from the request parameter
        String inputStr = request.getParameter("inputString");
        // Get the encryption type from the request parameter
        String encType = request.getParameter("InputEncryption");

        // If the input string is not empty, then the next view is output.jsp
        if (!inputStr.trim().isEmpty()) {
            nextView = "output.jsp";
        }

        // Initialize the output string array
        String [] outputStr = null;

        // If the encryption type is MD5, encrypt the input string using the MD5Encrypt method
        if (encType.equalsIgnoreCase("MD5")) {
            outputStr = hashesModel.MD5Encrypt(inputStr);
        }
        // If the encryption type is SHA-256, encrypt the input string using the SHA256Encrypt method
        else if (encType.equalsIgnoreCase("SHA-256")) {
            outputStr = hashesModel.SHA256Encrypt(inputStr);
        }

        // Set the attributes for the output model to retrieve
        request.setAttribute("opString", outputStr[0]);
        request.setAttribute("opHash", encType);
        request.setAttribute("opHex", outputStr[1]);
        request.setAttribute("opBase64", outputStr[2]);

        // Get the request dispatcher for the specified view and forward the request and response to the view
        RequestDispatcher view = request.getRequestDispatcher(nextView);
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
