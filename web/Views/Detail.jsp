<%-- 
    Document   : Detail
    Created on : Feb 24, 2024, 3:43:14 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail Product</title>
        <!--        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
                <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
                <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>-->

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

        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <style>
            .gallery-wrap .img-big-wrap img {
                height: 450px;
                width: auto;
                display: inline-block;
                cursor: zoom-in;
            }


            .gallery-wrap .img-small-wrap .item-gallery {
                width: 60px;
                height: 60px;
                border: 1px solid #ddd;
                margin: 7px 2px;
                display: inline-block;
                overflow: hidden;
            }

            .gallery-wrap .img-small-wrap {
                text-align: center;
            }
            .gallery-wrap .img-small-wrap img {
                max-width: 100%;
                max-height: 100%;
                object-fit: cover;
                border-radius: 4px;
                cursor: zoom-in;
            }
            .img-big-wrap img{
                width: 100% !important;
                height: auto !important;
            }
        </style>
    </head>
    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="container">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="card bg-light mb-3">
                            <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
                            <ul class="list-group category_block">
                            <c:forEach items="${allCategory}" var="c">
                                <li class="list-group-item text-white ${tag == c.categoryID?"active":""}"><a href="category?cid=${c.categoryID}">${c.categoryName}</a></li>
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
                    <div class="container">
                        <div class="card">
                            <div class="row">
                                <c:set value="${requestScope.detail}" var="d"/>
                                <aside class="col-sm-5 border-right">
                                    <article class="gallery-wrap"> 
                                        <div class="img-big-wrap">
                                            <div><a href="#"><img src="${requestScope.detail.getImage()}"></a></div>
                                        </div> <!-- slider-product.// -->
                                        <div class="img-small-wrap">
                                        </div> <!-- slider-nav.// -->
                                    </article> <!-- gallery-wrap .end// -->
                                </aside>
                                <aside class="col-sm-7">
                                    <article class="card-body p-5">
                                        <h3 class="title mb-3">${d.productName}</h3>

                                        <p class="price-detail-wrap"> 
                                            <span class="price h3 text-warning"> 
                                                <span class="currency"></span><span class="num">${d.price}VNĐ</span>
                                            </span> 
                                        </p> <!-- price-detail-wrap .// -->
                                        <dl class="item-property">
                                            <dt style="font-weight: bold;font-size: 20px">Description</dt>
                                            <li>Thương hiệu:  ${name.brandName}</li>
                                            <li>Dòng giày:  ${categoryProduct.getCategoryName()}</li>
                                        </dl>
                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-5">

                                                <form action="buy" method="POST">
                                                    <input type="number" name="pid" hidden value="${d.productID}"/>
                                                    <dl class="param param-inline">
                                                        <c:if test="${requestScope.notice != null}">
                                                            ${requestScope.notice}
                                                        </c:if>
                                                        <c:if test="${requestScope.notice == null}">
                                                            <dt><span class="text-qty">Số lượng:</span></dt>
                                                            <dd>

                                                                <input type="number" name="quantity" min="1" step="1" value="1" required/>

                                                            </dd>
                                                        </c:if>

                                                    </dl>  <!-- item-property .// -->
                                                    <dl class="param param-inline">
                                                        <dt>Size: </dt>
                                                        <dd>
                                                            <select name="size" class="form-control form-control-sm" style="width:70px;">
                                                                <c:forEach items="${sizeProduct}" var="s">
                                                                    <option> ${s.size.sizeID} </option>
                                                                </c:forEach>
                                                            </select>
                                                        </dd>
                                                    </dl>
                                                    <c:if test="${sessionScope.acc.role.roleID == 2}">
                                                        <c:if test="${requestScope.detail.quantity > 0}">
                                                            <c:if test="${requestScope.notice == null}">
                                                        <input type="submit"  class="btn btn-lg btn-primary text-uppercase" value="Buy Now"/>
                                                            </c:if>
                                                        </c:if>
                                                        
<!--                                                        <input type="submit"  class="btn btn-lg btn-primary text-uppercase" value="Buy Now"/>-->
                                                        
                                                    </c:if>
                                                    <c:if test="${sessionScope.acc == null}">
                                                        <a href="login" style="text-decoration: none;" type="submit" class="btn btn-lg btn-primary text-uppercase">Buy Now</a>
                                                        <!--                                                    <input type="submit"  class="btn btn-lg btn-primary text-uppercase" value="Buy Now"/>-->
                                                    </c:if>
                                                    <!--                                                    <input type="submit" class="btn btn-lg btn-outline-primary text-uppercase fas fa-shopping-cart" value="Add to cart"/>-->
                                                </form>
                                            </div> <!-- col.// -->

                                        </div> <!-- row.// -->
                                        <hr>


                                    </article> <!-- card-body.// -->
                                </aside> <!-- col.// -->
                            </div> <!-- row.// -->
                        </div> <!-- card.// -->
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer -->
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>

</html>
<script type="text/javascript">
    function buy(id) {
        var q = document.detail.quantity.value;
        var s = document.detail.size.value;
        document.detail.action = "buy?pid=" + id + "&quantity=" + q + "&size=" + s;
        document.detail.submit();
    }
</script>
