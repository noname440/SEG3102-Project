<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<div class="middle-full">
    <h2>Login</h2>
    <%@include file="messages.jsp" %>
    <form>
        <dl>
            <dt>Team Name</dt>
            <dd><input type="text" value="" name="name" /></dd>
            <dt></dt>
            <dd><input type="submit" value="Login" name="submit" /></dd>
        </dl>
    </form>
</div>
<%@include file="footer.jsp" %>
