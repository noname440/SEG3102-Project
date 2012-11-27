/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Domain.CourseSection;
import Domain.Instructor;
import Util.QueryHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

/**
 *
 * @author Michael
 */
@WebServlet(name = "SetupParameters", urlPatterns = {"/SetupParameters"})
public class SetupParameters extends HttpServlet {
    @Resource 
    private UserTransaction utx; 
    
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
            
            if(startDate == null){
                errors.add("You must enter a start date.");
            }
            
            if(endDate == null){
                errors.add("You must enter a end date.");
            }
            
            
            // Send out errors
            if (errors.size() > 0) {
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("SetupParameters.jsp").forward(request, response);
            }
            
            // Create a new course section
            try {
                CourseSection courseSection = new CourseSection();
                courseSection.setSemester(semester);
                courseSection.setSection(section);
                courseSection.setCourseName(courseName);
                //add course code
                courseSection.setDescription(description);
                courseSection.setMaxStudents(Integer.parseInt(maxStudents));
                courseSection.setMinStudents(Integer.parseInt(minStudents));
                courseSection.setStartDate(startDate);
                courseSection.setEndDate(endDate);
                
                instructor.addCourseSection(courseSection);
                
                QueryHelper.persist(courseSection,utx);
            } catch (Exception e) {
                if (e instanceof NumberFormatException){
                    errors.add("Max and Min Student fields must be integers.");
                }
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
