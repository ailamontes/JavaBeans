<%-- 
    Document   : login
    Created on : May 4, 2023, 2:07:42 PM
    Author     : Aila-School
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Login</h1>

    <form action="LoginServlet" method ="post">
        <label for="username">Email:</label>
        <input type="text" id="username" name="username" required>
        <br>
        <br>
        <label for="password" class="password-toggle">Password:</label>
          <input type="password" id="password" name="password" required>
          <br>
          <br>
          <br>
        <button type="submit">Login</button>
      </form>
</body>
</html>
