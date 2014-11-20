/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Entidades.Cliente;
import Entidades.Cuenta;
import Entidades.Transacciones;
import Modelo.Clientes;
import Modelo.CuentaAhorros;
import Modelo.CuentaCte;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mao
 */
@WebServlet(name = "Registro", urlPatterns = {"/Registro"})
public class Registro extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public void save(HttpServletRequest request, Clientes object)
    {    
        //Clientes object = new Clientes();
         Enumeration en = request.getParameterNames();
             
         
             while (en.hasMoreElements()) 
             {
                 String paramName = (String) en.nextElement();
                
              
                  switch (paramName) {
                 case "nombre":
                      object.setNombre(request.getParameter(paramName));
                     break;
                 case "apellido":
                      object.setApellido(request.getParameter(paramName));
                     break;
                 case "documentoid":
                     object.setDocumentoId(Integer.parseInt(request.getParameter(paramName)));
                     break;
                 case "saldo":
                     object.setCuenta(new CuentaAhorros());
                     //object.getCuenta().setSaldo(Integer.parseInt(request.getParameter(paramName)));
                     object.getCuenta().setSaldo(87123);
                     break;
                 case "telefono":
                     object.setTelefono(Integer.parseInt(request.getParameter(paramName)));
                     break;
                 case "direccion":
                     object.setDireccion(request.getParameter(paramName));
                     break;      
                }
            }
             
    }
    
    
     
    public boolean create(EntityManager em, HttpServletRequest request, Clientes p)
    {   
        Cliente up = em.find(Cliente.class, Integer.parseInt(request.getParameter("documentoid")));
        boolean flag=false;
        if(up==null)
        {
        //Clientes p = new Clientes();
        Transacciones q = new Transacciones();
        Cuenta h = new Cuenta();
        
        
        Cliente t = new Cliente();          
        t.setNombre(p.getNombre());
        t.setApellido(p.getApellido());
        t.setDireccion(p.getDireccion());
        t.setTelefono(p.getTelefono());
        t.setDocumentoid(p.getDocumentoId());
        
        h.setDocumentoId(t);
        h.setTipocuenta(Integer.parseInt(request.getParameter("tipocuenta")));
        
        q.setCuentaId(h);
        p.setCuenta(new CuentaCte());
        q.setSaldo(Integer.parseInt(request.getParameter("saldo")));
        
        em.getTransaction().begin(); 
        em.persist(t);
        em.persist(h);
        em.persist(q);
        em.getTransaction().commit();        
        flag=true;
        }
        return flag;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankApplicationPU");
        EntityManager em = emf.createEntityManager();
        Clientes p = new Clientes();
        save(request,p);
        if(create(em,request,p))
        {
        Cookie nombre = new  Cookie("name", request.getParameter("documentoid"));
        nombre.setMaxAge(60*60*24*7);
        response.addCookie(nombre);
        }
        
        String userName= null;
        Cookie[] cookies = request.getCookies();
    if(cookies !=null){
        for(Cookie cookie : cookies){
        if(cookie.getName().equals("name")) {
            userName = cookie.getValue();
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Registro</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Registro at " + userName + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        }
        }
    }
        
    else{
        
        
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Registro</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Registro at " + "perico" + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    }// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
