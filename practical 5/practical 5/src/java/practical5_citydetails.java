/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CityServlet")
public class practical5_citydetails extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String cityName = request.getParameter("cityName");

        sqldb.connect();
        
        String cityDetails = "";

        try {
            String query = "SELECT * FROM city WHERE name = ?";
            PreparedStatement stmt = sqldb.conn.prepareStatement(query);
            stmt.setString(1, cityName);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cityDetails = "<tr><td>" + rs.getString("name") + "</td>" +
                              "<td>" + rs.getInt("population") + "</td>" +
                              "<td>" + rs.getString("country") + "</td></tr>";
            } else {
                cityDetails = "<tr><td colspan='3'>City not found</td></tr>";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            cityDetails = "<tr><td colspan='3'>Error occurred while fetching city details.</td></tr>";
        } finally {
            sqldb.connclose();
        }

        out.println("<html>");
        out.println("<head><title>City Details</title></head>");
        out.println("<body>");
        out.println("<h1>Search City Details</h1>");
        out.println("<form method='POST' action='CityServlet'>");
        out.println("Enter City Name: <input type='text' name='cityName' />");
        out.println("<input type='submit' value='Search' />");
        out.println("</form>");
        out.println("<h2>City Information</h2>");
        
        // Table for displaying the city details
        out.println("<table border='1'>");
        out.println("<tr><th>City</th><th>Population</th><th>Country</th></tr>");
        out.println(cityDetails); // Display the city details in a table row
        out.println("</table>");

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}