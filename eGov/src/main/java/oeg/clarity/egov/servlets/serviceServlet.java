
package oeg.clarity.egov.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oeg.clarity.egov.tramites.Tramite;
import oeg.clarity.egov.tramites.TramiteManager;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author admin
 */
public class serviceServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
                String serviceid = request.getParameter("serviceid");
                //out.println(serviceid);
                System.out.println("Serving HTML for general players");
                ClassLoader classLoader = serviceServlet.class.getClassLoader();
                String html = IOUtils.toString(classLoader.getResourceAsStream("service.html"), "UTF-8");
                String body = html;
                Tramite tramite = TramiteManager.getTramite(serviceid);
                if (tramite==null)
                    tramite=new Tramite();
            
                String titulo = URLDecoder.decode(tramite.titulo, "UTF-8");
                body = body.replace("<!--TEMPLATE_TITLE1-->","<h3>"+titulo+"</h3>" );
                body = body.replace("<!--TEMPLATE_TITLE2-->", "<p>" + tramite.descabre+"</p>");
                
                String ht="";
                if (!tramite.presencialProceso.isEmpty())
                {
                    ht="Proceso presencial: " + tramite.presencialProceso;
                }
                body = body.replace("<!--TEMPLATE_BODY-->", ht);
                

            response.getWriter().println(body);
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }catch(Exception e)
        {
            System.out.println("ERror "+ e.getMessage());
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
