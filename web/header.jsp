<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Team Management System</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
    <body>
        <div id="wrap">
            <div id="top"></div>
            <div id="content">
                <div class="header">
                    <h1><a href="?s=home">TMS: Team Management System</a></h1>
                    <h2>by the AntiPatterns</h2>
                </div>
                <div class="topmenu">
                    <a href="?s=home">Home</a> �
                    <% if(session.getAttribute("user") != null) { %><a href="?s=account">My Account</a> �<% } %>
                    <% if(session.getAttribute("user") == null) { %><a href="?s=login">Login</a><% } %>
                    <% if(session.getAttribute("user") != null) { %><a href="?s=logout">Logout</a><% } %>
                </div>
                