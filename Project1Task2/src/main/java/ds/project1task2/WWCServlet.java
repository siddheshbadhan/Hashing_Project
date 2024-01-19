/**
 * Author: sbadhan Siddhesh Badhan
 * Last Modified: 02/10/2023
 This Servlet class acts as the controller in the program, receiving the country name from the user and
 passing it to the Model for information retrieval. The Model returns the information back to the Servlet,
 which creates an instance of the Model and utilizes its methods to extract the information.
 After obtaining the information from the Model, the Servlet forwards it to the output.jsp (the View)
 to be displayed on an HTML page along with a "Continue" button for the user to retrieve information
 for another country.
 */

package ds.project1task2;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "findStateInfo", value = "/find-state-info")
public class WWCServlet extends HttpServlet {

    /***
     * This servlet will reply to HTTP GET requests via this doGet method
     * @param request Represents the request from HttpServletRequest
     * @param response Represents the response from HttpServletRequest
     * @throws IOException Exception during IO operations
     * @throws ServletException Represents servlet exceptions
     */
    // Source: CMU 95702 Spring 2023 Lab2-InterestingPicture Code
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        WWCModel model = new WWCModel();

        // Gets and stores the country selected by the user
        String country = request.getParameter("country");

        String nickName;
        String topScorers;
        String capital;
        String flagEmoji;
        String flagImage;

        //Scrape and store the name of the country
        // Set attributes for output.jsp file

        request.setAttribute("country", country);

        //nickname
        nickName = model.getNickName(country);
        request.setAttribute("nickName", nickName);

        //top scorer
        topScorers = model.getTopScorer(country);
        request.setAttribute("topScorer", topScorers);

        //capital city
        capital = model.getCapital(country);
        request.setAttribute("capital", capital);

        //flag emoji
        flagEmoji = model.getFlagEmoji(country);
        request.setAttribute("flagEmoji", flagEmoji);

        //flag
        flagImage = model.getFlag(country);
        request.setAttribute("flagImage", flagImage);

        RequestDispatcher view = request.getRequestDispatcher("output.jsp");

        // Forwards HttpServletRequest request, HttpServletResponse response to the View
        view.forward(request, response);
    }
}
