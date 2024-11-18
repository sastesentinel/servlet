import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/Practical5_city"})
public class Practical5_city extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // HTML form for input
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>City Lookup</title>");
            out.println("</head>");
            out.println("<body>");
            
            //making a form to enter the city name to be searched
            out.println("<h1>Enter City Name to Get Details</h1>");
            out.println("<form action='Practical5_city' method='POST'>");
            out.println("City Name: <input type='text' name='Cityname'><br>");
            out.println("<input type='submit' value='Submit'>");
            out.println("</form>");

            // Get the input parameter from the name
            String cityname = request.getParameter("Cityname");

            // Check if a city name has been submitted
            if (cityname != null && !cityname.isEmpty()) {
                out.println("<h2>City Details for: " + cityname + "</h2>");
                out.println("<table border=2><tr><td><b>City Name</b></td><td><b>Pincode</b></td><td><b>Population</b></td></tr>");
                
                String query = "SELECT * FROM citydetails WHERE cityname LIKE '%" + cityname + "%'";
                sqldb.connect();  
                
                ResultSet r = sqldb.fetchdata(query);
                while (r.next()) {
                    String name = r.getString("cityname");
                    String pin = r.getString("pincode");
                    int population = r.getInt("population");
                    out.println("<tr><td>" + name + "</td><td>" + pin + "</td><td>" + population + "</td></tr>");
                }
                
                out.println("</table>");
                sqldb.connclose();  // Close the connection
            }

            out.println("</body>");
            out.println("</html>");
        } catch (SQLException ex) {
            Logger.getLogger(Practical5_city.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
