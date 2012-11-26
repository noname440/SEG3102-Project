<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="Domain.Student"%>
<%@page import="Domain.CourseSection"%>
<%@page import="Domain.Team"%>

<%@include file="header.jsp" %>
<div class="middle-full">
    <h2>Student Page</h2>
    <%@include file="messages.jsp" %>  
    
    <% Student student = (Student) session.getAttribute("user");%>
    <% if (student != null) {%>
    <% List<CourseSection> courses = student.getCourses();%>
    <% for (CourseSection course : courses) {%>
    <h2> Course: <% course.getCourseName(); %> </h2>
    <% if (student.getTeamLiaisonOf(course) != null) {%>
    <p><a href="AcceptNewStudents" onclick="<% session.setAttribute("courseID", course.getCourseID()); %>">Accept New Students</a></p>
    <% } %>
    <% Team teamApp = student.getTeamAppliedTo(course);%>
    <% Team teamMemb = student.getTeamMemberOf(course);%>
    <% if (teamApp == null && teamMemb == null) { %>
    <p><a href="JoinTeam" onclick="<% session.setAttribute("courseID", course.getCourseID()); %>">Join a Team</a></p>  
    <% } %>
    <p><a href="CreateTeam" onclick="<% session.setAttribute("courseID", course.getCourseID()); %>">Create Team</a></p>
    <% } } %>
     
   
</div>
<%@include file="footer.jsp" %>