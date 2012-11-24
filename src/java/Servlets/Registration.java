/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Domain.Student;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
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
@WebServlet(name = "Registration", urlPatterns = {"/Registration"})
public class Registration extends HttpServlet {

    
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("OPSPU");
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
        ArrayList<String> success = new ArrayList<String>();

        String submit = request.getParameter("submit");
            //Check if form not submitted
        if (submit == null) {
            request.getRequestDispatcher("createaccount.jsp").forward(request, response);
        } 
        // Form is submitted
        else {
            String userID = request.getParameter("userID");
            String password = request.getParameter("password");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String courses = request.getParameter("courses");
            String studyProgram = request.getParameter("studyProgram");
            String type = request.getParameter("type"); //student or instructor 

            // Check for nulls and defaults
            if (userID == null || userID.length() <= 0) {
                errors.add("You must enter a username.");
            }
            if (password == null || password.length() <= 6) {
                errors.add("You must enter a password with a minimum length of 6");
            }
            
            if (firstName == null || firstName.length() <= 0) {
                errors.add("You must enter a first name.");
            }
            
            if (lastName == null || lastName.length() <= 0) {
                errors.add("You must enter a last name.");
            }
            
            if (email == null || email.length() <= 0) {
                errors.add("You must enter an email.");
            } else {
                if (!email.matches("^[_A-Za-z0-9-]+@[A-Za-z0-9]+.[A-Za-z]+$")) {
                    errors.add("You must enter a valid email.");
                }
            }
            if(courses == null || courses.length() <= 0){
                errors.add("You must enter a course.");
            }
            if(studyProgram == null || studyProgram.length() <= 0){
                errors.add("You must enter a study program.");
            }
            
            /*
             * if (type == null || type.length() <= 0) {
             * errors.add("You must select a type of account.");
             * } else if (!type.equals("student") && !type.equals("instructor")) {
             * errors.add("You must select a valid type of account.");
             * }
             */
            // Send out errors
            if (errors.size() > 0) {
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("Register.jsp").forward(request, response);
                return;
            }
            
            //Information in form is valid and verify userID doesn't already exist
            
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT u FROM User u WHERE u.userID = :userID");
            query.setParameter("userID", userID);
            query.setMaxResults(1);
            Collection<Student> queryResults = query.getResultList();
            em.close();
            
            if (queryResults.size() > 0) {
                errors.add("There is already a user with the user id that was entered.");
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("Register.jsp").forward(request, response);
                return;
            }
            
            // Create a new student account
            if (type.equals("student")) {
                em = emf.createEntityManager();
                try {
                    em.getTransaction().begin();
                    Student student = new Student();
                    student.setEmail(email);
                    student.setFirstName(firstName);
                    student.setLastName(lastName);
                    student.setPassword(password);
                    student.setUserID(Long.parseLong(userID));
                    em.persist(student);
                    em.getTransaction().commit();
                } catch (Exception e) {
                    if (e instanceof NumberFormatException){
                        errors.add("User id must be an integer.");
                    }
                    em.getTransaction().rollback();
                } finally {
                    em.close();
                }
            }
            //Create new instructor account
            else{
                em = emf.createEntityManager();
                try {
                    em.getTransaction().begin();
                    Student student = new Student();
                    student.setEmail(email);
                    student.setFirstName(firstName);
                    student.setLastName(lastName);
                    student.setPassword(password);
                    student.setUserID(Long.parseLong(userID));
                    em.persist(student);
                    em.getTransaction().commit();
                } catch (Exception e) {
                    if (e instanceof NumberFormatException){
                        errors.add("User id must be an integer.");
                    }
                    em.getTransaction().rollback();
                } finally {
                    em.close();
                }
            }
            
            // Show success message
            success.add("Account created successfully.");
            request.setAttribute("success", success);
            request.getRequestDispatcher("Home.jsp").forward(request, response);
        }
    }
    
        
        

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
