<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="<%= request.getContextPath() %>" method="post">
    <table>
        <tr>
            <td>Card number</td>
            <td><input type="number" name="number" required/></td>
        </tr>
        <tr>
            <td>Amount</td>
            <td><input type="amount" name="amount" required/></td>
        </tr>
    </table>
    <input type="submit" value="Submit" />
</form>
</body>
</html>
