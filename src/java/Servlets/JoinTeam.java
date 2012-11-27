/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Domain.Student;
import Domain.Team;
import Util.QueryHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "JoinTeam", urlPatterns = {"/JoinTeam"})
public class JoinTeam extends HttpServlet {
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
        List<String> errors = new ArrayList<String>();
        List<String> success = new ArrayList<String>();
        
        Long teamID = (Long) session.getAttribute("teamID");
        Team team = QueryHelper.searchTeam(teamID);
        Student student = (Student)session.getAttribute("user");
        
        
         String submit = request.getParameter("submit");
            //Check if form not submitted
        if (submit == null) {
            request.getRequestDispatcher("JoinTeam.jsp").forward(request, response);
        } 
        // Form is submitted
        else {
        //check if this student is a member or liaison of another team
        List<Team> teamsList = team.getCourseSection().getTeams();
        for(Team t : teamsList) {
            if(t.getLiaison().equals(student)) {
                errors.add("You are already the liaison of a team in this course.");
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("StudentPage.jsp").forward(request, response);
                return;
            }
            List<Student> members = t.getMembers();
            if(members.contains(student)) {
                errors.add("You are already a member of a team in this course.");
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("StudentPage.jsp").forward(request, response);
                return;
            }
        }
        
        student.addTeamAppliedTo(team);
        team.addApplicant(student);
        
        QueryHelper.merge(student,utx);
        QueryHelper.merge(team,utx);
        
        success.add("Successfully applied to team " + team.getTeamName());
        request.setAttribute("success", success);
        request.getRequestDispatcher("JoinTeam.jsp").forward(request, response);        
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
