package oeg.clarity.egov.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oeg.clarity.egov.tramites.Tramite;
import oeg.clarity.egov.tramites.TramiteManager;

/**
 *
 * @author admin
 */
public class servicesServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Tramite> tramites = TramiteManager.getTramites();
        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            /*
            String json = "{\n"
                    + "  \"type\": \"Feature\",\n"
                    + "  \"geometry\": {\n"
                    + "    \"type\": \"Point\",\n"
                    + "    \"coordinates\": [41.656887, -0.878407]\n"
                    + "  },\n"
                    + "  \"properties\": {\n"
                    + "    \"name\": \"Basílica de El Pilar\"\n"
                    + "  }\n"
                    + "}";
            out.println(json);            
            if(true)
                return;*/
            
            //http://localhost:8080/eGov/services?current=1&rowCount=10&searchPhrase=Matri
            // services?current=1&rowCount=10
            String uri = request.getRequestURI();
   //         if (uri.equals("/services")) {
                //current=1&rowCount=10&sort[sender]=asc&searchPhrase=&id=b0df282a-0d67-40e5-8558-c9e93b7befed
                String offset = request.getParameter("current");
                String limit = request.getParameter("rowCount");
                String searchFrase = request.getParameter("searchPhrase");
                tramites = TramiteManager.filter(searchFrase);
                int current=1;
                int ilimit=2;
                current = Integer.parseInt(offset);
                ilimit = Integer.parseInt(limit);
                int total = tramites.size();
                ilimit = Math.min(total, ilimit);
                
                
           //     logger.info("Total" + total);
                System.out.println("Hay un total de " + total);
                int init = (current - 1) * ilimit;
                String s = "{\n"
                        + "  \"current\": " + current + ",\n"
                        + "  \"rowCount\": " + ilimit + ",\n"
                        + "  \"rows\": [\n";
                int conta = 0;

                for (int j=init;j<init+ilimit;j++) {
                    Tramite t = tramites.get(j);
                    String nombre = t.titulo;
                    nombre = URLDecoder.decode(nombre, "UTF-8");
//                    cp = cp.replace(" ", "+");
                    if (conta != 0) {
                        s += ",\n";
                    }
                    s += "    {\n"
                            + "      \"resource\": \"" + nombre + "\",\n"
                            + "      \"resourceurl\": \"service?serviceid=" + t.id + "\"\n"
                            + "    } ";
                    conta++;
                }

                s += "  ],\n"
                        + "  \"total\": " + total + "\n"
                        + "}    ";
                out.print(s);
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
       //     }
        } catch (Exception e) {

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
