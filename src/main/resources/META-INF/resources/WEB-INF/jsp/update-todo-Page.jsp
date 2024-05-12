<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
   <head>
      <meta charset="utf-8">
      <title>Update Your Todo</title>
      <link href="webjars\bootstrap\5.1.3\css\bootstrap.css" rel="stylesheet">

   </head>
   <body>
   <div class="container">
      <h1>Update Your todo here</h1>
      <hr>
      <form:form action="updated-todo" method="post" modelAttribute="uptodo">
         <form:input type="hidden" path="id"/>
         <form:input type="hidden" path="username"/>


         <span>Enter Target Date</span>
         <form:input type="date" path="targetDate"/>
         <form:errors path="targetDate"/>
         <br>
         <br>
         <span>Enter Description</span>
         <form:input type="text" path="desc" placeholder="Text only"/>
         <form:errors path="desc"/>

         <br>
         <br>
         <form:checkbox path="done"/>
         <input type="submit" class="btn btn-success">

      </form:form>

   </div>
   <script src="webjars\bootstrap\5.1.3\js\bootstrap.js"></script>
   <script src="webjars\jquery\3.6.0\jquery.js"></script>

   </body>
</html>