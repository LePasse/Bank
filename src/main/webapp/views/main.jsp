<%@ page import="entity.User" %>
<%@ page import="entity.Card" %>
<%@ page import="DAO.sql.UserDAOSQL" %>
<%@ page import="service.UserService" %>
<%@ page import="java.util.List" %>
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
                "        <a href=\"signIn\">Sign In</a>\n" +
                "    </p>\n" +
                "    <p>\n" +
                "        <a href=\"signUp\">Sign Up</a>\n" +
                "    </p>");
    } else {
        out.print("<p>" + user.email + "</p>");
        out.print("<form action=\"" + request.getContextPath() + "\" method=\"post\">\n" +
                " <input type=\"hidden\" name=\"action\" value=\"logout\" hidden/>\n" +
                " <input type=\"submit\" value=\"Log Out\"/>\n" +
                "    </form>");
    }
%>
<h1>Cards</h1>
<%
    //Cards
    if (user != null){
        List<Card> cards = user.cards;
        for (Card card : cards) {
            out.print("<p>Card " + card.name + " (" + card.date + ")</p>");
            out.print("<p>Balance " + card.account.balance +".</p>");
        }
    }
%>

</body>
</html>