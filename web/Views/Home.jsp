<%-- 
    Document   : Home
    Created on : Feb 24, 2024, 1:57:07 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tiem Giay nam</title>
        <!--        Boostrapv4-->
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


        <!--        Boostrapv5-->
        <!--        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
                
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
                
                <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
                
        -->
        <link href="./css/style.css" rel="stylesheet" />
        <style>
            .pagination .page-item.active .page-link {
                background-color: lemonchiffon;
                color: #0397d6;
            }
        </style>
    </head>
    <body>

        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="container">
                <div class="row">
                    <div class="col">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">

<!--                                <li class="breadcrumb-item"><a href="home">Home</a></li>
                                <li class="breadcrumb-item"><a href="#">aaa</a></li>
                                <li class="breadcrumb-item active" aria-current="detail">Sub-category</li>-->

                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="card bg-light mb-3">
                            <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i><a style="text-decoration: none; color: whitesmoke" href="bestsell">  Best Selling</a></div>
                        </div>
                        <div class="card bg-light mb-3">
                            <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories </div>
                            <ul class="list-group category_block">
                            <c:forEach items="${allCategory}" var="c">
                                <li class="list-group-item text-white ${tag == c.categoryID ?"active":""}"  ><a style="text-decoration: none;" href="category?cid=${c.categoryID}">${c.categoryName}</a></li>
                                </c:forEach>

                        </ul>
                    </div>
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-primary text-white text-uppercase"> <i class="fa fa-list"></i>  Brand</div>
                        <ul class="list-group category_block">
                            <c:forEach items="${allBrand}" var="b">
                                <li class="list-group-item text-white ${brand == b.brandID ?"active":""}"><a style="text-decoration: none;" href="brand?bid=${b.brandID}">${b.brandName}</a></li>
                                </c:forEach>
                        </ul>
                    </div>
                </div>


                <div class="col-sm-9">
                    <c:set value="${requestScope.page}" var="page"/>


                    <div class="row">

                        <c:forEach items="${allProduct}" var="p">
                            <div class="col-12 col-md-6 col-lg-4">
                                <div class="card" >
                                    <img class="card-img-top" style="width: 253px; height: 200px" src="${p.image}" alt="TiemGiayNam">
                                    <div class="card-body">
                                        <h4 class="card-title show_txt"><a href="detail?pid=${p.productID}&bid=${p.brand.brandID}&cid=${p.category.categoryID}" title="View Product">${p.productName}</a></h4>
                                        <p class="card-text show_txt">${p.productName}</p>
                                        <div class="row">
                                            <c:if test="${p.quantity == 0}">
                                                <div class="col">
                                                    <p class="btn btn-danger btn-block">Hết hàng !</p>
                                                </div>
                                            </c:if>
                                            <div class="col">
                                                <p class="btn btn-success btn-block">${p.price} VNĐ</p>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <c:forEach items="${allCategoryProduct}" var="p">
                            <div class="col-12 col-md-6 col-lg-4">
                                <div class="card" >
                                    <img class="card-img-top" style="width: 253px; height: 200px" src="${p.image}" alt="TiemGiayNam">
                                    <div class="card-body">
                                        <h4 class="card-title show_txt"><a href="detail?pid=${p.productID}&bid=${p.brand.brandID}&cid=${p.category.categoryID}" title="View Product">${p.productName}</a></h4>
                                        <p class="card-text show_txt">${p.productName}</p>
                                        <div class="row">
                                            <c:if test="${p.quantity == 0 }">
                                                <div class="col">
                                                    <p class="btn btn-danger btn-block">Hết hàng !</p>
                                                </div>
                                            </c:if>
                                            <div class="col">
                                                <p class="btn btn-success btn-block">${p.price} VNĐ</p>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <c:forEach items="${allProductBrand}" var="p">
                            <div class="col-12 col-md-6 col-lg-4">
                                <div class="card" >
                                    <img class="card-img-top" style="width: 253px; height: 200px" src="${p.image}" alt="TiemGiayNam">
                                    <div class="card-body">
                                        <h4 class="card-title show_txt"><a href="detail?pid=${p.productID}&bid=${p.brand.brandID}&cid=${p.category.categoryID}" title="View Product">${p.productName}</a></h4>
                                        <p class="card-text show_txt">${p.productName}</p>
                                        <div class="row">
                                            <c:if test="${p.quantity == 0 }">
                                                <div class="col">
                                                    <p class="btn btn-danger btn-block">Hết hàng !</p>
                                                </div>
                                            </c:if>
                                            <div class="col">
                                                <p class="btn btn-success btn-block">${p.price} VNĐ</p>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <c:set var="list" value="${requestScope.searchList}"/>

                        <c:if test="${(list!=null) && (list.size() > 0)}">     
                            <c:forEach items="${searchList}" var="p">
                                <div class="col-12 col-md-6 col-lg-4">
                                    <div class="card" >
                                        <img class="card-img-top" style="width: 253px; height: 200px" src="${p.image}" alt="TiemGiayNam">
                                        <div class="card-body">
                                            <h4 class="card-title show_txt"><a href="detail?pid=${p.productID}&bid=${p.brand.brandID}&cid=${p.category.categoryID}" title="View Product">${p.productName}</a></h4>
                                            <p class="card-text show_txt">${p.productName}</p>
                                            <div class="row">
                                                <c:if test="${p.quantity == 0 }">
                                                    <div class="col">
                                                        <p class="btn btn-danger btn-block">Hết hàng !</p>
                                                    </div>
                                                </c:if>
                                                <div class="col">
                                                    <p class="btn btn-success btn-block">${p.price} VNĐ</p>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                        <c:forEach items="${listBestSell}" var="p">
                            <div class="col-12 col-md-6 col-lg-4">
                                <div class="card" >
                                    <img class="card-img-top" style="width: 253px; height: 200px" src="${p.image}" alt="TiemGiayNam">
                                    <div class="card-body">
                                        <h4 class="card-title show_txt"><a href="detail?pid=${p.productID}&bid=${p.brand.brandID}&cid=${p.category.categoryID}" title="View Product">${p.productName}</a></h4>
                                        <p class="card-text show_txt">${p.productName}</p>
                                        <div class="row">
                                            <c:if test="${p.quantity == 0 }">
                                                <div class="col">
                                                    <p class="btn btn-danger btn-block">Hết hàng !</p>
                                                </div>
                                            </c:if>
                                            <div class="col">
                                                <p class="btn btn-success btn-block">${p.price} VNĐ</p>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>


                </div>

                <c:forEach begin="${1}" end="${requestScope.number}" var="i">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <li class="page-item active"><a class="page-link ${i==page?'active':''}" href="home?page=${i}">${i}</a></li>
                        </ul>
                    </nav>
                </c:forEach>


            </div>

        </div>

        <!-- Footer -->
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>
