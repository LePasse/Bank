<%@ page import="entity.Member" %>
<%@ page import="dao.sql.MemberDAOSQL" %>
<%@ page import="service.UserService" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Crew" %>
<%@ page import="service.CrewService" %>
<%@ page import="entity.Airport" %>
<%@ page import="service.AirportService" %>
<%@ page import="entity.Flight" %>
<%@ page import="service.FlightService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) request.getSession().getAttribute("user");
%>
<html>
<head>
    <title>main.jsp</title>
</head>
<body>
<h1>Bank</h1>
<%
    // Header
    if (user == null) {
        out.print("<p>\n" +
                "        <a href=\"signin\">Sign In</a>\n" +
                "    </p>\n" +
                "    <p>\n" +
                "        <a href=\"signup\">Sign Up</a>\n" +
                "    </p>");
    } else {
        out.print("<p>" + user.email + "</p>");
        out.print("<form action=\"" + request.getContextPath() + "\" method=\"post\">\n" +
                " <input type=\"hidden\" name=\"action\" value=\"logout\" hidden/>\n" +
                " <input type=\"submit\" value=\"Log Out\"/>\n" +
                "    </form>");
    }
%>
</body>
</html>
