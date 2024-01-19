<%-- 
    Author: sbadhan Siddhesh Badhan
    Last Modified: 2/10/2023
    
    It lists the count of each recorded answer.
    If no results have been provided, it displays a different message.
--%>

<%@page import="java.util.TreeMap"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MCQ Clicker</title>
    </head>
    
        <h1>Distributed Systems Class Clicker</h1>
        Created by Siddhesh Badhan <br><br>
        <form action="getClicker" method="GET">
            

        <!-- Print the results dependent on whether the user has answered any questions-->
            <% Map<String, Integer> Answers = (TreeMap<String, Integer>)request.getAttribute("Answers"); %>

            <% if (Answers == null || (Answers.get("A") == 0 && Answers.get("B") == 0 && Answers.get("C") == 0 && Answers.get("D") == 0)) { %>
                There are currently no results
            <% } else { %>
                The results from the survey are as follows <br><br>

                <% for(Map.Entry<String, Integer> e : Answers.entrySet()) { %>
                    <%= e.getKey()%> : <%=e.getValue()%> <br><br>
                <% } %>
        
            <% } %>
        </form>
</html>
