/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Domain.CourseSection;
import Domain.Student;
import Domain.Team;
import Util.QueryHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@WebServlet(name = "CreateTeam", urlPatterns = {"/CreateTeam"})
public class CreateTeam extends HttpServlet {

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
        List<String> errors = new ArrayList<String>();
        List<String> success = new ArrayList<String>();
        
        String courseID = (String) session.getAttribute("courseID");
        CourseSection course = QueryHelper.searchCourseSection(courseID);
        Student student = (Student)session.getAttribute("user");
        
        List<Team> teams = course.getTeams();
        for(Team t : teams) {
            //check if student is already liaison of a team in this course section and return an error
            if(t.getLiaison().equals(student)) {
                errors.add("You are already the liaison of a team in this course.");
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("CreateTeam.jsp").forward(request, response);
                return;
            }
            //check if student is already a member of a team in this course section and return an error
            List<Student> members = t.getMembers();
            if(members.contains(student)) {
                errors.add("You are already a member of a team in this course.");
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("CreateTeam.jsp").forward(request, response);
                return;
            }
            //check if student has already applied to a team in this course section and remove the student from its applicant list
            if(t.removeApplicant(student)) {
                student.removeTeamAppliedTo(t);
                QueryHelper.merge(t);
            }
            
        }
        
        Team team = new Team();
        team.setTeamName(request.getParameter("name"));
        team.setCreationDate(new Date(System.currentTimeMillis()));
        
        //check minimum
        if(course.getMinStudents() <= 1) {
            team.setStatus(true);
        }
        else
            team.setStatus(false);
        
        team.setMinExempt(false);
        team.setMaxExempt(false);
        
        team.setLiaison(student);
        student.addTeamLiaisonOf(team);
        team.setCourseSection(course);
        course.addTeam(team);
        
        QueryHelper.persist(team);
        QueryHelper.merge(student);
        QueryHelper.merge(course);
        
        success.add("Team " + team.getTeamName()+ " added to course " + course.getCourseName());
        request.setAttribute("success", success);
        request.getRequestDispatcher("AcceptStudents.jsp").forward(request, response);
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
