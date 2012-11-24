<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<div class="middle-full">
    <h2>Create Account</h2>
    <%@include file="messages.jsp" %>
    <form>
        <dl>
            <input type="hidden" name="s" value="createaccount" />
        <dt>User ID</dt><dd><input type="text" value="" name="username" /></dd>
        <dt>Password</dt><dd><input type="password" value="" name="password" /></dd>
        <dt>Given name</dt><dd><input type="text" value="" name="firstName" /></dd>
        <dt>Last name</dt><dd><input type="text" value="" name="lastName" /></dd>
        <dt>Email</dt><dd><input type="text" value="" name="email" /></dd>
        <dt>Account type</dt> <dd>
            <INPUT TYPE="radio" NAME="type" VALUE="student" >
             Student
            <BR>
            <INPUT TYPE="radio" NAME="type" VALUE="instructor">
             Instructor
            <BR>
            </dd>
        <% String type = request.getParameter("type"); %>  
        <% if((type!=null)&&(type.trim().equals("student"))) { %>  
            <jsp:include page="RegisterStudent.jsp" />  
        <% } %>  
  
        <dt></dt><dd><input type="submit" value="Create account" name="submit" /></dd>
        </dl>
    </form>
    <p><a href="?s=login">Already a member?</a></p>
</div>
<%@include file="footer.jsp" %>
