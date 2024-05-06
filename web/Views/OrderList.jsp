<%-- 
    Document   : OrderList
    Created on : Mar 8, 2024, 6:56:26 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order List</title>
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
                                                    
                                                </tr>
                                            </thead>
                                            <tbody>

                                                <c:forEach items="${requestScope.cart}" var="i">
                                                    
                                                    <tr>

                                                        <th scope="row">
                                                            <div class="p-2">
                                                                <img src="${i.product.image}" alt="" width="70" class="img-fluid rounded shadow-sm">
                                                                <div class="ml-3 d-inline-block align-middle">
                                                                    <h5 class="mb-0"> <p class="text-dark d-inline-block">${i.product.productName}</p></h5><span class="text-muted font-weight-normal font-italic"></span>
                                                                </div>
                                                            </div>
                                                        </th>
                                                        <td class="align-middle"><strong>${i.size}</strong></td>
                                                        <td class="align-middle"><strong><fmt:formatNumber pattern="##.#" value="${i.price}"/></strong></td>
                                                        <td class="align-middle">
                                                            
                                                            <strong>${i.quantity}</strong>
                                                            
                                                        </td>
                                                        <td class="align-middle"><strong><fmt:formatNumber pattern="##.#" value="${i.price * i.quantity}"/></strong></td>

                                                        <td class="align-middle">
<!--                                                            <form action="process" method="post">
                                                                <input type="hidden" name="id" value="${i.product.productID}"/>
                                                                <input type="hidden" name="size" value="${i.size}"/>
                                                                <input class="btn btn-danger" type="submit" value="Delete"/>
                                                                  <button type="submit" class="">Delete</button>
                                                            </form>-->

                                                        </td>

                                                    </tr> 
                                                </c:forEach>


                                        </tbody>

                                    </table>

                                </div>
                                <!-- End -->
                            </div>
                        </div>

                        <div class="row py-5 p-4 bg-white rounded shadow-sm">
                            <div class="col-lg-6">
                            </div>
                                <div class="col-lg-6">

                                    <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">
                                        
                                    </div>


                            </div>
                            
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
