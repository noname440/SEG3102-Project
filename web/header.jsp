<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">  
    <head>
        <title>Team Management System</title>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" type="text/css" href="../style.css" />
    </head>
    <body>
        <div id="wrap">
            <div id="top"></div>
            <div id="content">
                <div class="header">
                    <h1><a href="Home.jsp">TMS: Team Management System</a></h1>
                    <h2>by the AntiPatterns</h2>
                </div>
                <div class="topmenu">
                    <a href="Home.jsp">Home</a> ·
                    <% if(session.getAttribute("user") == null) { %><a href="/Login">Login</a><% } %>
                    <% if(session.getAttribute("user") != null) { %><a href="/Logout">Logout</a><% } %>
                </div>
                
                