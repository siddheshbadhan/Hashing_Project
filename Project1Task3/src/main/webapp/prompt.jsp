<%-- 
    Author: sbadhan Siddhesh Badhan
    Last Modified: 2/10/2023
    
    The starting screen of the program where user is prompted to selected an option.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Class Clicker</title>
    </head>
    <body>
        <h1>Distributed Systems Class Clicker</h1>
        Created by Siddhesh Badhan <br><br>
        Submit your answer to the current question:
        
        <form action="getClicker" method="POST">
        
            <label for="stage"></label>
            <br>
            <input type="radio" name="stage" value="A">A<br>
            <input type="radio" name="stage" value="B">B<br>
            <input type="radio" name="stage" value="C">C<br>
            <input type="radio" name="stage" value="D">D<br>
            <br>
            <input type="submit" value="Submit"> 
        </form>
    </body>
</html>
