<%-- 
    Author: dlesser Daniel Lesser
    Last Modified: 2/2/2019
    
    This is the output display page for the program.  It accesses the output
of the program (string, hash selection, encrypted strings in hexadecimal and
base 64) from the ComputeHashes.Java file.  Those values are displayed on-screen.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <!-- Code below displays the output to the user-->
        <h1>The Hash value of the entered text is: </h1>
        
        Name of Hash: <%= request.getAttribute("opString")%><br>
        Hash Value: <%= request.getAttribute("opHash")%><br>
        Hexadecimal Value: <%= request.getAttribute("opHex")%><br>
        Base 64 Value: <%= request.getAttribute("opBase64")%><br>
        
        
    </body>
</html>
