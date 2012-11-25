<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="Domain.Student"%>
<%@page import="Domain.CourseSection"%>
<%@page import="Domain.Team"%>

<%@include file="header.jsp" %>
<div class="middle-full">
    
    <% Student student = (Student) session.getAttribute("user");%>
    <% CourseSection course = (CourseSection) session.getAttribute("courseID");%>
    <% if (student != null) {%>
    <% Team team = student.getTeamLiaisonOf(course); %>
    <% session.setAttribute("teamID", team.getId()); %>
    <% List<Student> applicants = team.getApplicants(); %> 
    <% for (Student applicant : applicants) {%>
        <dl>
            <dt><a href="/AcceptNewStudents" onclick="<% session.setAttribute("userID", applicant.getUserID()); %>">Student Number: </a></dt><dd><% applicant.getUserID(); %></dd>
            <dt>First Name: </dt><dd><% applicant.getFirstName(); %></dd>
            <dt>Surname: </dt><dd><% applicant.getLastName(); %></dd>
            <dt>Study Program: </dt><dd><% applicant.getStudyProgram(); %></dd>
            <dt>Email: </dt><dd><% applicant.getEmail(); %></dd>    
        </dl>
       
</div>
<%@include file="footer.jsp" %>
