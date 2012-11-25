<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="Domain.Student"%>
<%@page import="Domain.CourseSection"%>
<%@page import="Domain.Team"%>

<%@include file="header.jsp" %>
<div class="middle-full">
    <h2>Instructor Page</h2>
    <%@include file="messages.jsp" %>
    
    <% Student student = (Student) session.getAttribute("user");%>    
    
    <% if(student != null ) { %>
    <% if(student.getTeamsLiaisonOf() != null ) { %>
    <p><a href="/AcceptNewStudents">Accept New Student</a></p>
    <% } } %>
      
    
    <p><a href="/CreateTeam">Create Team</a></p>

    
</div>
<%@include file="footer.jsp" %>