<%@page import="Domain.Instructor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<div class="middle">
    <h2>View Teams</h2>
    <%@include file="messages.jsp" %>
 <% Instructor instructor = (Instructor) session.getAttribute("user");%>