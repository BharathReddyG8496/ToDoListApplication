<%@ include file="Common/header.jspf" %>
   <div class="container">
      <%@ include file="Common/navigation.jspf" %>
      <h1>Enter Your todo details here</h1>
      <hr>
      <form:form method="post" modelAttribute="toDoClass">

         <form:input type="hidden" path="done"/>

<%--         <fieldset>--%>
<%--         <form:label path="username">Enter username </form:label>--%>
         <form:input type="hidden" path="username" value="${Username}" />
<%--         <form:errors path="username"/>--%>
<%--         </fieldset>--%>
         <br>
         <br>
         <fieldset>
         <form:label path="targetDate">Enter Target Date </form:label>
         <form:input type="date" path="targetDate" placeholder="Text only"/>
         <form:errors path="targetDate"/>
         </fieldset>
         <br>
         <br>
         <fieldset>
         <form:label path="description">Enter Description </form:label>
         <form:input type="text" path="description" placeholder="Text only"/>
         <form:errors path="description"/>
         </fieldset>
         <input type="submit" class="btn btn-success" cssClass="mt-3">

      </form:form>

   </div>
<%@ include file="Common/footer.jspf" %>

