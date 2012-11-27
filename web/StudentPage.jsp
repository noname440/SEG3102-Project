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
    <h2> Course: <%= course.getCourseID()%> -   <%= course.getCourseName()%> </h2>
    <% if (student.getTeamLiaisonOf(course) != null) {%>
    <p><a href="AcceptNewStudents" onclick="<% session.setAttribute("course", course); %>">Accept New Students</a></p>
    <% } %>
    <p><a href="JoinTeam" onclick="<% session.setAttribute("course", course); %>">Join a Team</a></p>  
    <p><a href="CreateTeam" onclick="<% session.setAttribute("course", course); %>">Create Team</a></p>
    <% } } %>
     
   
</div>
<%@include file="footer.jsp" %>