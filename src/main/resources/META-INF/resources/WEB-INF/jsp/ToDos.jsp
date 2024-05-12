<%@ include file="Common/header.jspf" %>
<div class="container">
    <%@ include file="Common/navigation.jspf" %>
<h1>Your todos </h1>
<table class="table">
    <thead>
    <tr>
        <th>ID</th>

        <th>Description</th>
        <th>TargetDate</th>
        <th>IsDone?</th>


    </tr>
    </thead>
    <tbody>
    <c:forEach items="${todos}" var="todo">
    <tr>
        <td>${todo.id}</td>

        <td>${todo.description}</td>
        <td>${todo.targetDate}</td>
        <c:if test="${not todo.done}"><td>In Progress</td><</c:if>
        <c:if test="${todo.done}"><td>Completed</td><</c:if>
        <td><a href="update-todo?id=${todo.id}" class="btn btn-warning">Update</a></td>
        <td><a href="delete-todo?id=${todo.id}" class="btn btn-danger">Delete</a></td>
        <c:if test="${not todo.done}">
            <td><a href="MarkDone-todo?id=${todo.id}" class="btn btn-success">Mark as done</a></td>
        </c:if>
    </tr>
    </c:forEach>

    </tbody>
</table>
    <a href="add-todo" class=" btn btn-outline-success">Add Todo</a>

</div>
<%@ include file="Common/footer.jspf" %>