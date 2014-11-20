package Controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Entidades.Cliente;
import Entidades.Cuenta;
import Entidades.Transacciones;
import Modelo.Clientes;
import Modelo.CuentaAhorros;
import Modelo.CuentaCte;
import Modelo.Cuentas;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adavila
 */
@WebServlet(urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void save(Clientes object,HttpServletRequest request)
    {
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
     
    public void create(EntityManager em, HttpServletRequest request, Clientes p)
    {   Transacciones q = new Transacciones();
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
        q.setSaldo(p.getCuenta().getSaldo());
        
        em.getTransaction().begin(); 
        em.persist(t);
        em.persist(h);
        em.persist(q);
        em.getTransaction().commit();        
     
        
    }

     public boolean comparar(EntityManager em,HttpServletResponse response,HttpServletRequest request ) throws IOException
    {   
        boolean flag=false;
        Query query = em.createQuery("SELECT p FROM Cliente p ", Cliente.class);
        List<Cliente> lista = query.getResultList();
        
        
        for (Cliente p : lista) {
        if(request.getParameter("documentoid").equals(p.getDocumentoid())){
            flag=true;
        break;    
        }   
        }
        return flag;
   }
    /* public void delete(EntityManager em,int accnumber)
    {
         em.getTransaction().begin();
         Cuentas y = em.find(Cuentas.class, accnumber);
         if (y != null) 
             em.remove(y);
         em.getTransaction().commit();
    }
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankApplicationPU");
        EntityManager em = emf.createEntityManager();
        Clientes p = new Clientes();
        Cuentas m = new CuentaAhorros();
        
        /*if(comparar(em, response, request)){ 
            response.setContentType("text/html");
      String site = new String("transacciones.html");

      response.setStatus(response.SC_MOVED_TEMPORARILY);
      response.setHeader("Location", site); 
       }
        
        else{
       request.setAttribute("errorMessage", "Invalid user or password");
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
            
            
      
        }*/
        save(p, request);
        create(em, request,  p);
        response.setContentType("text/html");
      String site = new String("transacciones.html");

      response.setStatus(response.SC_MOVED_TEMPORARILY);
      response.setHeader("Location", site); 
        
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
