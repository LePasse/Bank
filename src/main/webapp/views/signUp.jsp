<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="<%= request.getContextPath() %>" method="post">
    <table>
        <tr>
            <td>Email</td>
            <td><input type="email" name="email" required/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" required/></td>
        </tr>
        <tr>
             <td>Name</td>
             <td><input type="name" name="name" required/></td>
        </tr>
        <tr>
             <td>Surname</td>
             <td><input type="surname" name="surname" required/></td>
        </tr>
    </table>
    <input type="submit" value="Submit" />
</form>
</body>
</html>