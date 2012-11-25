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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michael
 */
@WebServlet(name = "AcceptNewStudents", urlPatterns = {"/AcceptNewStudents"})
public class AcceptNewStudents extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        List<String> errors = new ArrayList<String>();
        List<String> success = new ArrayList<String>();
        String studentID = request.getParameter("userID");
        String teamID = request.getParameter("teamID");
        
        Student student = QueryHelper.searchStudent(studentID);
        //check if student exists     
        if(student == null) {
            errors.add("There is no student with this user id");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("AcceptNewStudents.jsp").forward(request, response);
            return;
        }
        Team team = QueryHelper.searchTeam(teamID);
        //check if team exists
        if(student == null) {
            errors.add("There is no team with this team id");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("AcceptNewStudents.jsp").forward(request, response);
            return;
        }
        //check if team is full
        if(team.getMembers().size() + 1 >= team.getCourseSection().getMaxStudents() && !team.getMaxExempt()) {
            errors.add("This team is full");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("AcceptNewStudents.jsp").forward(request, response);
            return;
        }
        
        student.getTeamsAppliedTo().remove(team);
        student.getTeamsMemberOf().add(team);
        team.getApplicants().remove(student);
        team.getMembers().add(student);
        
        //update the status of the team
        if(team.getMembers().size() + 1 > team.getCourseSection().getMaxStudents()
                || team.getMembers().size() + 1 < team.getCourseSection().getMinStudents()) {
            team.setStatus(false);
        }
        
        //remove this student from applicant list for all teams in this team's course section
        List<Team> teamsList = team.getCourseSection().getTeams();
        for(Team t : teamsList) {
            if(t.removeApplicant(student)) {
                student.removeTeamAppliedTo(t);
                QueryHelper.merge(t);
            }    
        }
        
        QueryHelper.merge(student);
        QueryHelper.merge(team);
        
        success.add("Student " + student.getFirstName() + " " + student.getLastName() + " successfully added to team.");
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
