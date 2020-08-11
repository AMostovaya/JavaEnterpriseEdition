<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 8/11/2020
  Time: 10:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="ru.geekbrains.persist.CartRepository" %>
<%@ page import="ru.geekbrains.persist.Cart" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>

<html lang="en">

<head>
    <%@include file="fragments/header.jsp" %>
    <title>Cart</title>
</head>

<body>

<%@include file="fragments/sidebar.jsp"%>

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
                        <td>
                            <c:url value="/edit" var="cartEditUrl">
                                <c:param name="id" value="${ct.id}"/>
                            </c:url>
                            <a class="btn btn-success" href="${cartEditUrl}"><i class="fas fa-edit"></i></a>
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

<%@include file="fragments/footer.jsp"%>

</html>
