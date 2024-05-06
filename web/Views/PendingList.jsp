<%-- 
    Document   : PendingList
    Created on : Mar 12, 2024, 8:46:35 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pending List</title>
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
                            <h2>Pending List</h2>
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

                    <thead>
                        <tr>
                            <!--                            <th></th>-->
                            <th>OrderID</th>
                            <th>User  name</th>
                            <th>Total money</th>
                            <th>Order date</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach items="${allPendingList}" var="o">
                            <tr>
                                
                                <td>${o.orderID}</td>
                                <td>${o.getUserName()}</td>	
                                <td>${o.totalMoney}VNĐ</td>
                                <td>${o.orderDate}</td>
                                <c:if test="${o.status == 1}">
                                    <td>Success </td>
                                </c:if>
                                <c:if test="${o.status != 1}">
                                    <td>Pending </td>
                                </c:if>
                                <td>
                                    <form action="pendinglist" method="post">

                                        <input hidden name="orderid" value="${o.orderID}"/>
                                        <input type="submit" value="confirm"/>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <button class="btn btn-secondary"><a href="managerorder" style="text-decoration: none"> Back manager order</a></button>

            </div>

        </div>
    </body>
</html>
