<%@page import="java.util.ArrayList"%>
<% ArrayList<String> errors = (ArrayList<String>) request.getAttribute("errors");%>
<% if (errors != null && !errors.isEmpty()) {%>
<div class="message_error">
    <ul>
        <% for (String message : errors) {%>
        <li><%= message%></li>
        <% }%>
    </ul>
</div>
<% }%>

<% ArrayList<String> notices = (ArrayList<String>) request.getAttribute("notices");%>
<% if (notices != null && !notices.isEmpty()) {%>
<div class="message_warning">
    <ul>
        <% for (String message : notices) {%>
        <li><%= message%></li>
        <% }%>
    </ul>
</div>
<% }%>


<% ArrayList<String> success = (ArrayList<String>) request.getAttribute("success");%>
<% if (success != null && !success.isEmpty()) {%>
<div class="message_success">
    <ul>
        <% for (String message : success) {%>
        <li><%= message%></li>
        <% }%>
    </ul>
</div>
<% }%>
