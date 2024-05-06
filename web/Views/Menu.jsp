<%-- 
    Document   : Menu
    Created on : Feb 24, 2024, 5:03:25 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-md navbar-light bg-light sticky-top">
    <div class="container">
        <a class="navbar-brand" title="TiemGiayNam" href="home">
            <img class="logo" style="width: 55px; height: 55px; border-radius: 5px; " src="images/logopng.png" alt="alt"/>
        </a>


        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
            <ul class="navbar-nav m-auto">

                <c:if test="${sessionScope.acc.role.roleID == 1}">
                    <li class="nav-item">
                        <a class="nav-link" href="managerproduct">Manager Product</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="managerorder">Manager Order</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="manageraccount">Manager Account</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="statistic">Statistic</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.acc != null}">
                    <li class="nav-item">
                        <a href="#deleteEmployeeModal" class="nav-link" data-toggle="modal" title="My profile"> <span>Hello ${sessionScope.acc.lastName}</span></a>

                    </li>    
                    <li class="nav-item">
                        <a class="nav-link" href="logout">Logout</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.acc == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="login">Login</a>
                    </li>
                </c:if>
            </ul>

            <form action="search" method="post" class="form-inline my-2 my-lg-0">
                <div class="input-group input-group-sm">
                    <input name="search" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-secondary btn-number">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
                <a class="btn btn-success btn-sm ml-3" href="show">
                    <i class="fa fa-shopping-cart"></i> Cart
                    
                    <span class="badge badge-light">${requestScope.size}</span>
                    
                </a>
            </form>
        </div>
    </div>
</nav>

<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img class="d-block w-100" style="width: 1000px; height: 1000px" src="./images/banner/banner0.jpg" alt="First slide">
        </div>
        <div class="carousel-item">
            <img class="d-block w-100" style="width: 1000px; height: 1000px" src="./images/banner/banneraf1.jpg" alt="Second slide">
        </div>
        <div class="carousel-item">
            <img class="d-block w-100" style="width: 1000px; height: 1074px" src="./images/banner/banner3.jpg" alt="Third slide">
        </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>

<div id="deleteEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <c:set var="d" value="${sessionScope.acc}"/>
            <div class="modal-header">						
                <h4 class="modal-title">My Profile</h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div class="modal-body">	
                <span>User name: ${d.userName} </span><br>
                <span>Full name: ${d.firstName} ${d.lastName}</span><br>
                <span>Address:   ${d.address}, ${d.ward}, ${d.district}, ${d.province}.</span><br>
                <span>Phone number:   ${d.phoneNumber}</span><br>
            </div>
            <div class="modal-footer">
                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
            </div>
        </div>
    </div>
</div>


