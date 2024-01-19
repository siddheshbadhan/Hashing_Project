<%-- 
    Author: dlesser Daniel Lesser
    Last Modified: 2/2/2019
    
    This is the starting page for the program.  Run the program on index.jsp
to start.  A welcome screen is shown to the user, with a text box and radio
button for input.  The information is passed to ComputeHashes.Java GET method 
once the user hits the submit button.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project 1 Task 1 </title>
    </head>
    <body>
        <h1>Welcome!!! </h1>
        Created by Siddhesh Badhan <br>
        <h2> Enter Text and choose the hash function</h2>

        <!--
        Code below creates areas for user to input string and select from
        a set of two radio buttons.  That information is passed to the
        ComputeHashes.java servlet once the submit button is hit.
        -->
        <form action="ComputeHashes" method="GET">
            Enter text<br>
            <input type="text" name="inputString">
            <br>
            Select type of encryption<br><br>
            <input type="radio" name="InputEncryption" value="MD5" checked> MD5<br>
            <input type="radio" name="InputEncryption" value="SHA-256"> SHA-256<br>
            <br>
            <input type="submit" value="Submit">                    

        </form>

    </body>
</html>
