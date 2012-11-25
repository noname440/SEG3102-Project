<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<div class="middle-full">
    <h2>Setup Parameters</h2>
    <%@include file="messages.jsp" %>
    <form>
        <dl>
            <dt>Semester</dt>
            <dd><input type="text" value="" name="semester" /></dd> 
            <dt>Course Name</dt>
            <dd><input type="text" value="" name="courseName" /></dd>
            <dt>Section</dt>
            <dd><input type="text" value="" name="section" /></dd>
            <dt>Description</dt>
            <dd><input type="text" value="" name="description" /></dd>
            <dt>Max Students</dt>
            <dd><input type="text" value="" name="maxStudents" /></dd>
            <dt>Min Students</dt>
            <dd><input type="text" value="" name="minStudents" /></dd>
            <dt>Start Date</dt>
            <dd><input type="Date" value="" name="startDate" /></dd>
            <dt>End Date</dt>
            <dd><input type="Date" value="" name="endDate" /></dd>            
            <dt></dt>
            <dd><input type="submit" value="Submit" name="submit" /></dd>
        </dl>
    </form>
</div>
<%@include file="footer.jsp" %>
