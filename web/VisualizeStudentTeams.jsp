<%@page import="java.util.List"%>
<%@page import="Domain.Instructor"%>
<%@page import="Domain.CourseSection"%>
<%@page import="Domain.Team" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<div class="middle">
    <h2>View Teams</h2>
    <%@include file="messages.jsp" %>
    <% Instructor instructor = (Instructor) session.getAttribute("user");%>
    <% List<CourseSection> coursesTaught = instructor.getCoursesTaught();%>
    <% if (coursesTaught.size() <= 0) {%>
    <p>You do not teach any courses.</p>
    <% } else {%>
    <% for (CourseSection course : coursesTaught) {%>
    <h2> Course: <% course.getCourseName(); %> </h2>
    <% List<Team> teams = course.getTeamsPartOfCourse();%>
    <% for (Team team : teams) {%>
    
    
</div>
<%@include file="footer.jsp" %>