<%--Author: sbadhan Siddhesh Badhan
 * Last Modified: 02/10/2023
 * --%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <title>WWC</title>
  </head>
  <body>
    <h1>Country: <%= request.getAttribute("country")%></h1>

    <h2>Nickname: <%= request.getAttribute("nickName")%></h2>
    <h3>www.topendsports.com/sport/soccer/team-nicknames-women.htm</h3>

    <h2>Capital City: <%= request.getAttribute("capital")%></h2>
    <h3>www.restcountries.com</h3>

    <h2>Top Scorers: <%= request.getAttribute("topScorer")%></h2>
    <h3>www.espn.com/soccer/stats/_/league/FIFA.WWC/season/2019/view/scoring</h3>

    <h2>Flag:</h2>
    <img src="<%= request.getAttribute("flagImage")%>" width="200" height="150">
    <h3>/www.cia.gov/the-world-factbook/countries/</h3>

    <h2>Flag Emoji:</h2>
    <img src="<%= request.getAttribute("flagEmoji")%>" width="200" height="150">
    <h3>www.cdn.jsdelivr.net/npm/country-flag-emoji-json@2.0.0/dist/your-country-here.svg</h3>
    <a href = "./index.jsp">
      <button>Continue</button>
    </a>
  </body>
</html>