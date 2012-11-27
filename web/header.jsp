<%@page import="Domain.TMSUser" %>
<%@page import="Domain.Student" %>
<%@page import="Domain.Instructor" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">  
    <head>
        <title>Team Management System</title>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" type="text/css" href="styles/style.css" media="screen" />
        <script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
    </head>
    <body>
        <div id="wrap">
            <div id="top"></div>
            <div id="content">
                <div class="header">
                    <h1><a href="index.jsp">TMS: Team Management System</a></h1>
                    <h2>by the AntiPatterns</h2>
                </div>
                <div class="topmenu">
                    <a href="index.jsp">Home</a> ·
                    <% TMSUser user = (TMSUser) session.getAttribute("user"); %>
                    <% if(user == null) { %><a href="Login">Login ·</a><% } %>
                    <% if(user == null) { %><a href="Registration">Register</a><% } %>
                    <% if(user != null && user instanceof Student) { %><a href="StudentPage.jsp">Student Page ·</a><% } %>
                    <% if(user != null && user instanceof Instructor) { %><a href="InstructorPage.jsp">Instructor Page ·</a><% } %>
                    <% if(user != null) { %><a href="Logout">Logout</a><% } %>
                </div>
                
                