<%-- 
    Document   : ManagerProduct
    Created on : Feb 29, 2024, 9:40:46 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Manage Page</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            img{
                width: 250px;
                height: 140px;
            }

            .pagination .page-item.active .page-link {
                background-color: lemonchiffon;
                color: #0397d6;
            }

        </style>
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
                            <h2>Manage <b>Product</b></h2>
                        </div>
                        <div class="col-sm-6">
                            <table >
                                <tr>
                                    <th>
                                        <a href="#addEmployeeModal3"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Category</span></a>
                                    </th>
                                    <th>
                                        <a href="#addEmployeeModal"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Product</span></a>
                                    </th>
                                </tr>
 
                            </table>
                        </div>
                    </div>
                </div>

                <table class="table table-striped table-hover">
                    <c:set value="${requestScope.page}" var="page"/>
                    <thead>
                        <tr>
                            <th></th>
                            <th>ProductID</th>
                            <th>Name</th>
                            <th>Brand</th>
                            <th>Category</th>
                            <th>Image</th>
                            <th>Price</th>
                            <th>Quantity</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach items="${allProduct}" var="p">
                            <tr>
                                <td>
                                    <span class="custom-checkbox">
                                        <input type="checkbox" id="checkbox1" name="options[]" value="1">
                                        <label for="checkbox1"></label>
                                    </span>
                                </td>
                                <td>${p.productID}</td>
                                <td>${p.productName}</td>
                                <td>${p.brand.brandName}</td>
                                <td>${p.category.categoryName}</td>
                                <td>
                                    <img src="${p.image}">
                                </td>
                                <td>${p.price}VNĐ</td>
                                <td>${p.quantity}</td>
                                <td>
                                    <a href="loaddata?pid=${p.productID}" id="edit"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
<!--                                    <a href="#" onclick="doDelete('${p.productID}')" class="delete" data-toggle="tooltip" title="Delete"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>-->
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>

                </table>
                <c:forEach begin="${1}" end="${requestScope.number}" var="i">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <li class="page-item active"><a class="page-link ${i==page?"active":""}" href="managerproduct?page=${i}">${i}</a></li>
                        </ul>
                    </nav>
                </c:forEach>
                <button class="btn btn-secondary"><a href="home" style="text-decoration: none"> Back to home</a></button>

            </div>

        </div>
        <!-- add product -->
        <div id="addEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <!-- form product -->
                    <form action="addproduct" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Add Product</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Product Name</label>
                                <input name="name" type="text" class="form-control" required>
                            </div>
                            <div class="form-group text-center">
                                <label>Brand   </label>
                                <select name="brand" class="form-select" aria-label="Default select example" required>
                                    <c:forEach items="${allBrand}" var="b">
                                        <option value="${b.brandID}" >${b.brandName}</option>
                                    </c:forEach>

                                </select>
                            </div>
                            <div class="form-group text-center">
                                <label>Category</label>
                                <select name="category" class="form-select" aria-label="Default select example" required>
                                    <c:forEach items="${allCategory}" var="c">
                                        <option value="${c.categoryID}">${c.categoryName}</option>
                                    </c:forEach>

                                </select>
                            </div>
                            <div class="form-group">
                                <label>Image</label>
                                <input name="image" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input name="price" type="number" step="10000" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Quantity</label>
                                <input name="quantity" type="number" class="form-control" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Add">
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Add new category -->
        <div id="addEmployeeModal3" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content text-center">
                    <!-- form category -->
                    <form action="addcategory" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Add Category</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Category Name</label>
                                <input name="name" type="text" class="form-control" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Add Category">
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Add new brand -->
        <div id="addEmployeeModal2" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content text-center">
                    <!-- form category -->
                    <form action="addbrand" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Add Brand</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Name</label>
                                <input name="name" type="text" class="form-control" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Add">
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Edit Modal HTML -->
        <div id="editEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="updateproduct" >
                        <c:set var="u" value="${requestScope.productUpdate}"/>
                        <div class="modal-header">						
                            <h4 class="modal-title">Update information of Product</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">	
                            <div class="form-group">
                                <label>Product ID</label>
                                <input type="text" class="form-control" value="${u.productID}"  readonly="">
                            </div>
                            <div class="form-group">
                                <label>Product name</label>
                                <input type="text" class="form-control" value="${u.productName}" required>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input type="email" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Address</label>
                                <textarea class="form-control" required></textarea>
                            </div>
                            <div class="form-group">
                                <label>Phone</label>
                                <input type="text" class="form-control" required>
                            </div>					
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-info" value="Save">
                        </div>

                    </form>
                </div>
            </div>
        </div>

        
        <script src="js/manager.js" type="text/javascript">

        </script>
        <script>
            function doDelete(productID) {
                if (confirm("Do you want to delete this product?")) {
                    window.location = "deleteproduct?pid=" + productID;
                }
            }
            function edit(p) {
                var p = document.getElementById("edit").value;
                window.location = "updateproduct?pid=" + p;
            }

        </script>

    </body>
</html>
