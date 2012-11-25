/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Domain.Student;
import Domain.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Michael
 */
@WebServlet(name = "Login", urlPatterns = {"/Login", "/"})
public class Login extends HttpServlet {

    
    //EntityManagerFactory emf = Persistence.createEntityManagerFactory("OPSPU");
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        
        HttpSession session = request.getSession(true);

        ArrayList<String> errors = new ArrayList<String>();
        
        String submit = request.getParameter("submit");

        // Check if form not submitted
        if(submit == null) {
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } 
        // Form has been submitted
        else {
            String userID = request.getParameter("userID");
            String password = request.getParameter("password");
            String type = request.getParameter("type");

            // Check for nulls and defaults
            if(userID == null || userID.length() <= 0) {
                errors.add("You must enter a username.");
            } 

            if(password == null || password.length() <= 6) {
                errors.add("You must enter a password with a minimum of 6 characters.");
            } 

            if(type == null || type.length() <= 0) {
                errors.add("You must select a type of login.");
            } else if(!type.equals("student") && !type.equals("instructor")) {
                errors.add("You must select a valid type of login.");
            }

            // Send out errors
            if(errors.size() > 0) {
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("Login.jsp").forward(request, response);
                return;
            }

            //Form information is valid
            // Check the database for valid login
            /*
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT s FROM User s WHERE s.userID = :userID AND s.password = :password");
            
            query.setParameter("userID", userID);
            query.setParameter("password", password);
            List<User> queryResults = (List<User>)query.getResultList();

            em.close();
           
            
            if(queryResults.size() <= 0) {
                errors.add("Invalid username or password. Please try again.");
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("Login.jsp").forward(request, response);
                return;
            } 
            else if(queryResults.get(0) instanceof Student){
                request.setAttribute("errors", errors);
                session.setAttribute("user", queryResults.iterator().next());
                request.getRequestDispatcher("StudentPage.jsp").forward(request, response);
            }
            else {                      
                session.setAttribute("user", queryResults.iterator().next());
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("InstructorPage.jsp").forward(request, response);
            }
              */
          }
    }

    
   /* public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    * */
    
    
    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
