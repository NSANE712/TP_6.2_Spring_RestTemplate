<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>Edit Employee</h1>
<form:form method="POST" action="/editsave" modelAttribute="empVo">
    <table >
        <tr>
            <td></td>
            <td><form:hidden path="id" /></td>
        </tr>
        <tr>
            <td>Name : </td>
            <td><form:input path="firstName" /></td>
        </tr>
        <tr>
            <td>Salary :</td>
            <td><form:input path="salaire" /></td>
        </tr>
        <tr>
            <td>Fonction :</td>
            <td><form:input path="fonction" /></td>
        </tr>
        <tr>
            <td> </td>
            <td><input type="submit" value="Edit Save" /></td>
        </tr>
    </table>
</form:form>