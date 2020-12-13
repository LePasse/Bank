<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Card creation</title>
</head>
<body>
<form action="<%= request.getContextPath() %>" method="post">
    <table>
        <tr>
            <td>Enter card name</td>
            <td><input type="cardname" name="cardname" required/></td>
        </tr>
    </table>
    <input type="submit" value="Submit" />
</form>
</body>
</html>