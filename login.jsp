<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<div class="middle-full">
    <h2>Login</h2>
    <%@include file="messages.jsp" %>
    <form>
        <dl>
            <input type="hidden" name="s" value="login" />
            <dt>Username</dt>
            <dd><input type="text" value="" name="userID" /></dd>
            <dt>Password</dt>
            <dd><input type="password" value="" name="password" /></dd>
            <dt>Login Type</dt>
            <dd>
            <INPUT TYPE="radio" NAME="type" VALUE="student" >
             Student
            <BR>
            <INPUT TYPE="radio" NAME="type" VALUE="instructor">
             Instructor
            <BR>
            </dd>
            <dt></dt>
            <dd><input type="submit" value="Login" name="submit" /></dd>
        </dl>
    </form>
    <p><a href="?s=createaccount">New member?</a></p>
</div>
<%@include file="footer.jsp" %>
