<%-- 
    Document   : ManagerAccount
    Created on : Mar 13, 2024, 12:37:41 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Page</title>
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
                            <h2>Manage <b> Account</b></h2>
                        </div>
                        <div class="col-sm-6">
<!--
                           
                                    <th>
                                        <a href="pendinglist"  class="btn btn-success"><i class="material-icons">&#xE147;</i> <span>List Pending</span></a>
                                    </th>-->
                               
                        </div>
                    </div>
                </div>

                <table class="table table-striped table-hover">

                    <thead>
                        <tr>
                            <!--                            <th></th>-->
                            <th>User name</th>
                            <th>Full name</th>
                            <th>Address</th>
                            <th>Role</th>
                            
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach items="${allAccount}" var="o">
                            <tr>
                                <td>${o.getUserName()}</td>	
                                <td>${o.firstName} ${o.lastName}</td>
                                <td>${o.province} ${o.district} ${o.ward} ${o.address}</td>
                                <td>${o.role.role}</td>
                                <td>
                                    
                                    <form action="manageraccount" method="post">
                                        <select name="role">
                                            <c:forEach items="${allRole}" var="r">
                                                <option value="${r.roleID}">${r.role}</option>
                                            </c:forEach>
                                        </select>
                                        <input hidden name="username" value="${o.getUserName()}"/>
                                        <input type="submit" value="Update"/>
                                    </form> 
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>

                </table>
                <button class="btn btn-secondary"><a href="home" style="text-decoration: none"> Back to home</a></button>

            </div>

        </div>
    </body>
</html>
