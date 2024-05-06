<%-- 
    Document   : Cart
    Created on : Mar 3, 2024, 5:59:35 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
        <!--        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">-->

        <link
            rel="stylesheet"
            href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
            />
        <link
            href="https://use.fontawesome.com/releases/v5.0.4/css/all.css"
            rel="stylesheet"
            />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

        <link href="./css/style.css" rel="stylesheet" />
    </head>

    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="shopping-cart">
                <div class="px-4 px-lg-0">

                    <div class="pb-5">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

                                    <!-- Shopping cart table -->
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th scope="col" class="bg-light">
                                                        <div class="p-2 px-3">Product</div>
                                                    </th>
                                                    <th scope="col" class="bg-light">
                                                        <div class="p-2">Size</div>
                                                    </th>
                                                    <th scope="col" class="bg-light">
                                                        <div class="py-2">Price</div>
                                                    </th>
                                                    <th scope="col" class="bg-light">
                                                        <div class="py-2">Quantity</div>
                                                    </th>
                                                    <th scope="col" class="bg-light">
                                                        <div class="py-2">Total Money</div>
                                                    </th>
                                                    <th scope="col" class="bg-light">
                                                        <div class="py-2 ">Delete</div>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:set var="c" value="${requestScope.cart}"/>    
                                            <c:if test="${sessionScope.acc != null}">
                                                <c:if test="${(c.items != null)}">    
                                                    <c:forEach items="${c.items}" var="i">
                                                        <tr>

                                                            <th scope="row">
                                                                <div class="p-2">
                                                                    <img src="${i.product.image}" alt="" width="70" class="img-fluid rounded shadow-sm">
                                                                    <div class="ml-3 d-inline-block align-middle">
                                                                        <h5 class="mb-0"> <a href="detail?pid=${i.product.productID}&bid=${i.product.brand.brandID}&cid=${i.product.category.categoryID}" class="text-dark d-inline-block">${i.product.productName}</a></h5><span class="text-muted font-weight-normal font-italic"></span>
                                                                    </div>
                                                                </div>
                                                            </th>
                                                            <td class="align-middle"><strong>${i.size}</strong></td>
                                                            <td class="align-middle"><strong><fmt:formatNumber pattern="##.#" value="${i.price}"/></strong></td>
                                                            <td class="align-middle">
                                                                <a href="process?num=-1&id=${i.product.productID}&size=${i.size}"><button class="btnSub">-</button></a> 
                                                                <strong>${i.quantity}</strong>
                                                                <a href="process?num=1&id=${i.product.productID}&size=${i.size}"><button class="btnAdd">+</button></a>
                                                            </td>
                                                            <td class="align-middle"><strong><fmt:formatNumber pattern="##.#" value="${i.price * i.quantity}"/></strong></td>

                                                            <td class="align-middle">
                                                                <form action="process" method="post">
                                                                    <input type="hidden" name="id" value="${i.product.productID}"/>
                                                                    <input type="hidden" name="size" value="${i.size}"/>
                                                                    <input class="btn btn-danger" type="submit" value="Delete"/>
                                                                    <!--  <button type="submit" class="">Delete</button>-->
                                                                </form>

                                                            </td>

                                                        </tr> 
                                                    </c:forEach>
                                                </c:if>    
                                            </c:if>            
                                        </tbody>

                                    </table>

                                </div>
                                <!-- End -->
                            </div>
                        </div>
                        <div class="row py-5 p-4 bg-white rounded shadow-sm">
                            <div class="col-lg-6">
                               
                            </div>
                            <c:set var="o" value="${requestScope.cart}"/> 
                            <c:if test="${sessionScope.acc.getRole().getRoleID() == 2}">
                                <div class="col-lg-6">

                                    <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Payment</div>
                                    <div class="p-4">
                                        <ul class="list-unstyled mb-4">
                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-dark">Total money</strong><strong style="color: red; font-family: unset">${o.totalMoney}  VNĐ</strong></li>

                                            <h5 class="font-weight-bold"></h5>
                                            </li>
                                        </ul>
                                        <!--                                        <form action="order">
                                                                                    
                                                                                </form>-->
                                        <c:if test="${o.getItems().size() != 0}">
                                            <a href="order" class="btn btn-dark rounded-pill py-2 btn-block">Order</a>
                                        </c:if>

                                    </div>
                                </c:if>
                            </div>
                            <button class="btn-block btn-dark rounded-pill py-2 btn-block font-weight-bold"><a class="btn-block btn-dark rounded-pill py-2 btn-block font-weight-bold" style="text-decoration: none" href="home">Click me to continue shopping</a></button>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>

</html>
