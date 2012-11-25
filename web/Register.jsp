<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<div class="middle-full">
    <h2>Create Account</h2>
    <%@include file="messages.jsp" %>
    <form>
        <dl>
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
            <dt>Study Program</dt><dd><select name="studyProgram">
            <option value="arts">Arts</option>
            <option value="science">Science</option>
            <option value="engineering">Engineering</option>
            </select></dd>
            
            <dt>Courses</dt><dd><select multiple="multiple" name="courses">
            <option value="seg3102">SEG 3102</option>
            <option value="seg4910">SEG 4910 </option>
            <option value="csi3105">CSI 3105 </option>
            </select></dd>
            
        <% } %>  
  
        <dt></dt><dd><input type="submit" value="Create account" name="submit" /></dd>
        </dl>
    </form>
    <p><a href="?s=login">Already a member?</a></p>
</div>
<%@include file="footer.jsp" %>
