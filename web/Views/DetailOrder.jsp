<%-- 
    Document   : DetailOrder
    Created on : Mar 12, 2024, 8:09:02 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail Order</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <a class="navbar-brand" title="TiemGiayNam" href="home">
                <img class="logo" style="width: 55px; height: 55px; border-radius: 5px; " src="images/logopng.png" alt="alt"/>
            </a>
        </div>

        <div class="container">

            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Order <b>detail</b></h2>
                        </div>
                        <div class="col-sm-6">
                            <table >
                                <tr>

                                </tr>
                                <tr>


                                </tr>
                            </table>
                        </div>
                    </div>
                </div>

                <table class="table table-striped table-hover">
                    <table class="table table-striped table-hover">

                        <thead>
                            <tr>
                                <th>OrderID</th>
                                <th>Product Name</th>
                                <th>Price</th>
                                <th>Size</th>
                                <th>Quantity</th>
                                <th>Delivery address</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach items="${requestScope.orderDetailByOrderID}" var="od">
                                
                                <tr>
                                    <td>${od.getOrder().getOrderID()}</td>
                                    <td>${od.product.productName}</td>
                                    <td>${od.price}VNĐ</td>	
                                    <td>${od.size}</td>
                                    <td>${od.quantity}</td>
                                    <td>${od.deliveryAddress}</td>
                                </tr>

                            </c:forEach>
                        </tbody>

                    </table>
                <button class="btn btn-secondary"><a href="managerorder" style="text-decoration: none"> Back manager order</a></button>
            </div>
        </div>
    </body>
</html>
