<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 8/12/2020
  Time: 1:09 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="ru.geekbrains.persist.OrderRepository" %>
<%@ page import="ru.geekbrains.persist.Order" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>

<html lang="en">

<head>
    <%@include file="fragments/header.jsp" %>
    <title>Order</title>
</head>

<body>

<%@include file="fragments/sidebar.jsp" %>

<div class="container">
    <div class="row py-2">
        <div class="col-12">
            <table class="table table-bordered my-2">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">idProduct</th>
                    <th scope="col">Qty</th>
                    <th scope="col">Price</th>
                    <th scope="col">Purhase date</th>
                    <th scope="col">Client</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="ct" items="${requestScope.cart}">
                    <tr>
                        <th scope="row">
                            <c:out value="${ct.id}"/>
                        </th>
                        <td>
                            <c:out value="${ct.idProduct}"/>
                        </td>
                        <td>
                            <c:out value="${ct.qty}"/>
                        </td>
                        <td>
                                <c:out value="${ct.price}"/>
                        </td>
                        <td>
                            <c:out value="${ct.datePurchase}"/>
                        </td>
                        <td>
                            <c:out value="${ct.client}"/>
                        </td>

                        <td>
                            <c:url value="/edit" var="orderEditUrl">
                                <c:param name="id" value="${ct.id}"/>
                            </c:url>
                            <a class="btn btn-success" href="${orderEditUrl}"><i class="fas fa-edit"></i></a>
                            <a class="btn btn-danger" href="#"><i class="far fa-trash-alt"></i></a>
                        </td>
                    </tr>

                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>

</body>

<%@include file="fragments/footer.jsp" %>

</html>

