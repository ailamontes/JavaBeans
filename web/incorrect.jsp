<%-- 
    Document   : incorrect
    Created on : Feb 17, 2023, 9:53:21 PM
    Author     : Aila-School
--%>
<%@page import="controller.Database"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" />
        <title>Incorrect Email/Password</title>
    </head>
    <body>
        <div class="center">
            <fieldset>
        <%
            int counter = Integer.parseInt(request.getAttribute("limit").toString());
            if(counter < 3){
        %>
            <h2><font color='red'>You have entered an Incorrect Username/Password</font></h2>
            <form action="index.jsp" method="post">                  
                <button type="submit" value="tryagain" class="button">Try Again</button>
            </form>
                
        <%
            } else {
        %>
            <h2><font color='red'>Sorry, you have reached the limit of 3 tries, good bye!</font></h2>
            <p><font color='red'>You may now close the window, Thank you!</font></p>
        <%
            }
        %>
            </fieldset>
        </div>
    </body>
</html>
