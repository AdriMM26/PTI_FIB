package mypackage;

import java.io.*;
import java.io.File;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class CarRentalNew extends HttpServlet {

  int cont = 0;

  public void doGet(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    
    /* Get form parameters */
    String nombre = req.getParameter("name");
    String carType = req.getParameter("sub_model_vehicle");
    String co2 = req.getParameter("co2_rating");
    String days = req.getParameter("dies_lloguer");
    String vehicles = req.getParameter("num_vehicles");
    String discount = req.getParameter("descompte");
    
    /* Check if rentals.json exists. Create it otherwise */
    File file = new File("rentals.json");
    file.createNewFile();
    cont ++;

    /* Write form parameters to rentals.json */
    FileWriter fileWriter = new FileWriter("rentals.json", true);
    fileWriter.write("{\n");
    fileWriter.write("  \"Rating\": \"" + co2 + "\",\n");
    fileWriter.write("  \"Engine\": \"" + carType + "\",\n");
    fileWriter.write("  \"Number of days\": \"" + days + "\",\n");
    fileWriter.write("  \"Number of units\": \"" + vehicles + "\",\n");
    fileWriter.write("  \"Discount\": \"" + discount + "\"\n");
    fileWriter.write("}\n");
    fileWriter.close();

    /* Print form parameters */
    out.println("<html>");
    out.println("<head>");
    out.println("<title>New Rental</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>New Rental</h1>");
    out.println("<div id='response'></div>");
    out.println("<script>");out.println("document.getElementById('response').innerHTML = 'C02 Rating: " + co2 + "';");
    out.println("document.getElementById('response').innerHTML += '<br>Engine: " + carType + "';");
    out.println("document.getElementById('response').innerHTML += '<br>Number of days: " + days + "';");
    out.println("document.getElementById('response').innerHTML += '<br>Number of units: " + vehicles + "';");
    out.println("document.getElementById('response').innerHTML += '<br>Discount: " + discount + "';");
    out.println("</script>");
    out.println("</body>");
    out.println("</html>");
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    doGet(req, res);
  }
  
}
