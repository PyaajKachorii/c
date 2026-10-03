<%-- 
    Document   : index
    Created on : 3 Oct, 2026, 7:45:09 PM
    Author     : KEVIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Registration</title>
    </head>
    <body>
        <h1>Hello World ! !</h1>
        <br><br>
        <form method="post" action="StudentClient002">
            Name: <input type="text" name="name" required><br><br>
            Roll No: <input type="text" name="rollNo" required><br><br>
            Address: <textarea name="address" rows="2" columns="3"></textarea><br><br>
            Gender: 
            <select name="gender" required>
                <option value = "">--Select-Gender--</option>
                <option value = "">Male</option>
                <option value = ""> Female</option>
            </select><br><br><br>
            <input type="submit" value="Click to Submit">
        </form>
    </body>
</html>
