/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Domain.CourseSection;
import Domain.Instructor;
import Domain.Student;
import Domain.TMSUser;
import Util.QueryHelper;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "Registration", urlPatterns = {"/Registration"})
public class Registration extends HttpServlet {
    @Resource 
    private UserTransaction utx; 
    
    private static Boolean populated = false;
    
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

        
        if (populated == false){
            QueryHelper.populateDatabase(utx);
            populated = true;
        }
        String submit = request.getParameter("submit");
            //Check if form not submitted
        if (submit == null) {
            request.getRequestDispatcher("Registration.jsp").forward(request, response);
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
            if (userID == null) {
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
            if(type == null) {
                errors.add("You must enter a Account Type.");
            }
            else if(type.equals("student")){
                if(courses == null || courses.length() <= 0){
                    errors.add("You must enter a course.");
                }
                if(studyProgram == null || studyProgram.length() <= 0){
                    errors.add("You must enter a study program.");
                }
            }
            
            // Send out errors
            if (errors.size() > 0) {
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("Registration.jsp").forward(request, response);
                return;
            }
            
            //verify userID doesn't already exist

            TMSUser u = QueryHelper.searchUser(userID);
            if(u != null) {
                errors.add("There is already a user with the user id that was entered.");
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("Registration.jsp").forward(request, response);
                return;
            }

            
            CourseSection courseSection = QueryHelper.searchCourseSection(courses);
                    
            // Create a new student account
          
            
           if (type.equals("student")) {
                try {
                    Student student = new Student();
                    student.setEmail(email);
                    student.setFirstName(firstName);
                    student.setLastName(lastName);
                    student.setPassword(password);
                    student.setUserID(userID);
                    student.setStudyProgram(studyProgram);
                    
                    student.addCourse(courseSection);
                    courseSection.addStudent(student);
                    
                    QueryHelper.merge(courseSection,utx);
                    QueryHelper.persist(student,utx);
                    
                    
                } catch (Exception e) {
                    errors.add("Did not persists.");
                    if (e instanceof NumberFormatException){
                        errors.add("User id must be an integer.");
                    }
                    e.printStackTrace();
                }
            }
            //Create new instructor account
            else{
                try {
                    Instructor instructor = new Instructor();
                    instructor.setEmail(email);
                    instructor.setFirstName(firstName);
                    instructor.setLastName(lastName);
                    instructor.setPassword(password);
                    instructor.setUserID(userID);
                    
                    QueryHelper.persist(instructor,utx);
                } catch (Exception e) {
                    errors.add("Did not persists.");
                    if (e instanceof NumberFormatException){
                        errors.add("User id must be an integer.");
                    }
                }
            }
            
            // Show success message
            success.add("Account created successfully.");
            request.setAttribute("success", success);
            request.getRequestDispatcher("Login").forward(request, response);
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
