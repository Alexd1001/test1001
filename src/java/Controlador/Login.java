/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Entidades.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adavila
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public boolean comparar(EntityManager em,HttpServletRequest request ) throws IOException
    {   
        
        boolean flag=false;
        em.getTransaction().begin();     
        
        
        Cliente up = em.find(Cliente.class, Integer.parseInt(request.getParameter("documentoid")));
        if(up!=null)
        {
            if(up.getDocumentoid()==Integer.parseInt(request.getParameter("documentoid")))
            {
                flag=true;
            }
        
        }
        
        
        
        
        
        
        
        /*boolean flag=false;
        Query query = em.createQuery("SELECT p FROM Cliente p", Cliente.class);
        List<Cliente> lista = query.getResultList();
        
        
        for (Cliente p : lista) {
        if(request.getParameter("documentoid").equals(p.getDocumentoid())){
            flag=true;
        break;    
        }   
        }
        return flag;*/
        return flag;
   }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankApplicationPU");
        EntityManager em = emf.createEntityManager();
        
        if(comparar(em, request)){
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
        for(Cookie cookie : cookies){
        if(cookie.getName().equals("name")) 
            cookie.setValue(request.getParameter("documentoid"));
            response.addCookie(cookie);

        }
    }
            else{
        Cookie nombre = new  Cookie("name", request.getParameter("documentoid"));
        nombre.setMaxAge(60*60*24*7);
        response.addCookie(nombre);
            }
    
      response.setContentType("text/html");
      String site = new String("transacciones.html");

      response.setStatus(response.SC_MOVED_TEMPORARILY);
      response.setHeader("Location", site); 
       }
        
        else{
        
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        
        
        }
        
        
        /**/
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
