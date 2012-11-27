<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<div class="middle-full">
    <h2>Create Account</h2>
    <%@include file="messages.jsp" %>
    <form id="registerForm" >
        <dl>
        <dt>User ID</dt><dd><input type="text" value="" name="userID" /></dd>
        <dt>Password</dt><dd><input type="password" value="" name="password" /></dd>
        <dt>Given name</dt><dd><input type="text" value="" name="firstName" /></dd>
        <dt>Last name</dt><dd><input type="text" value="" name="lastName" /></dd>
        <dt>Email</dt><dd><input type="text" value="" name="email" /></dd>
        <dt>Account type</dt> <dd>
            <INPUT id="student" TYPE="radio" NAME="type" VALUE="student">
             Student
            <INPUT id="instructor" TYPE="radio" NAME="type" VALUE="instructor">
             Instructor
            </dd>
               
        <div id="studentPanel" style="display:none">    
            <dt>Study Program</dt><dd><select name="studyProgram">
            <option value="arts">Arts</option>
            <option value="science">Science</option>
            <option value="engineering">Engineering</option>
            </select></dd>
            
            <dt>Courses</dt><dd><select multiple="multiple" name="courses">
            <option value="SEG3102">SEG 3102</option>
            <option value="seg4910">SEG 4910 </option>
            <option value="csi3105">CSI 3105 </option>
            </select></dd>
            
        </div>   
        <script>
            $('#registerForm').change(function() {
                if ($('#student').attr('checked')) {
                    $('#studentPanel').show('slow');
                } else {
                    $('#studentPanel').hide('slow');
                }
            });
        </script>

  
        <dt></dt><dd><input type="submit" value="Create Account" name="submit" /></dd>
        </dl>
    </form>
    <p><a href="Login">Already a Member?</a></p>
</div>
<%@include file="footer.jsp" %>
