/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Domain.CourseSection;
import Domain.Instructor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
//import java.util.Date;
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
@WebServlet(name = "SetupParameters", urlPatterns = {"/SetupParameters"})
public class SetupParameters extends HttpServlet {

    
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
        Instructor instructor = (Instructor)session.getAttribute("user");
        
        ArrayList<String> errors = new ArrayList<String>();
        ArrayList<String> success = new ArrayList<String>();
        
        String submit = request.getParameter("submit");
        //Check if form not submitted
        if (submit == null) {
            request.getRequestDispatcher("SetupParameters.jsp").forward(request, response);
        }
        // Form is submitted
        else {
            String semester = request.getParameter("semester");
            String section = request.getParameter("section");
            String courseName = request.getParameter("courseName");
            String description = request.getParameter("description");
            String maxStudents = request.getParameter("maxStudents");
            String minStudents = request.getParameter("minStudents");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            
            
            // Check for nulls and defaults
            if (semester == null || semester.length() <= 0) {
                errors.add("You must enter a semester.");
            }
            if (section == null || section.length() <= 0) {
                errors.add("You must enter a section.");
            }
            
            if (courseName == null || courseName.length() <= 0) {
                errors.add("You must enter a course name.");
            }
            
            if (description == null || description.length() <= 0) {
                errors.add("You must enter a description.");
            }
            
            if (maxStudents == null || maxStudents.length() <= 0) {
                errors.add("You must enter the maximum amount of students.");
            }
            
            if(minStudents == null || minStudents.length() <= 0){
                errors.add("You must enter the minumum amount of students.");
            }
            
            if(startDate == null || startDate.length() <= 0){
                errors.add("You must enter a start date.");
            }
            
            if(endDate == null || endDate.length() <= 0){
                errors.add("You must enter a end date.");
            }
            
            
            // Send out errors
            if (errors.size() > 0) {
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("SetupParameters.jsp").forward(request, response);
            }
            
            /*
            //Information in form is valid and verify semester doesn't already exist
            
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT c FROM CourseSection c WHERE c.couseID = :courseID");
            query.setParameter("courseID", courseID);
            query.setMaxResults(1);
            Collection<CourseSection> queryResults = query.getResultList();
            em.close();
            
            if (queryResults.size() > 0) {
                errors.add("There is already a course section with the same course id that was entered.");
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("SetupParameters.jsp").forward(request, response);
            }
            */
            
            // Create a new course section
            EntityManager em = emf.createEntityManager();
            em = emf.createEntityManager();
            try {
                em.getTransaction().begin();
                CourseSection courseSection = new CourseSection();
                courseSection.setSemester(semester);
                courseSection.setSection(section);
                courseSection.setCourseName(courseName);
                courseSection.setDescription(description);
                courseSection.setMaxStudents(Integer.parseInt(maxStudents));
                courseSection.setMinStudents(Integer.parseInt(minStudents));
                courseSection.setStartDate(startDate);
                courseSection.setEndDate(endDate);
                
                instructor.addCourseSection(courseSection);
                
                em.merge(instructor);
                em.persist(courseSection);
                em.getTransaction().commit();
            } catch (Exception e) {
                if (e instanceof NumberFormatException){
                    errors.add("Max and Min Student fields must be integers.");
                }
                em.getTransaction().rollback();
            } finally {
                em.close();
            }
            
        }
        
        // Show success message
        success.add("Course section created successfully.");
        request.setAttribute("success", success);
        request.getRequestDispatcher("InstructorPage.jsp").forward(request, response);
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
