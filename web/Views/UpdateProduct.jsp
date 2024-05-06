<%-- 
    Document   : UpdateProduct
    Created on : Mar 2, 2024, 4:51:11 PM
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
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Update <b>Product</b></h2>
                        </div>
                        <div class="col-sm-6">

                        </div>
                    </div>
                </div>
                
                <form action="updateproduct" method="POST">
                    
                    <div class="modal-header">						
                        <h4 class="modal-title">Update information of Product</h4>
                        
                    </div>
                    <div class="modal-body">	
                        
                        <div class="form-group">
                            <label>Product ID</label>
                            <input type="text" name="id" class="form-control" value="${edit.getProductID()}"  readonly="">
                        </div>
                        <div class="form-group">
                            <label>Product name</label>
                            <input type="text" name="name" class="form-control" value="${edit.productName}" required>
                        </div>
                        <div class="form-group">
                                <label>Brand   </label>
                                <select name="brand" class="form-select" aria-label="Default select example" required>
                                    <c:forEach items="${allBrand}" var="b">
                                        <option value="${b.brandID}" >${b.brandName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Category</label>
                                <select name="category" class="form-select" aria-label="Default select example" required>
                                    <c:forEach items="${allCategory}" var="c">
                                        <option value="${c.categoryID}">${c.categoryName}</option>
                                    </c:forEach>
                                    
                                </select>
                            </div>
                        <div class="form-group">
                            <label>Image</label>
                            <input name="image" value="${edit.image}" type="text" class="form-control" required>
                        </div>
                        <div class="form-group">
                            
                            <label>Price</label>
                            ${requestScope.priceError}
                            <input name="price" value="${edit.price}" type="number" step="10000" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Quantity</label>
                            ${requestScope.quantityError}
                            <input name="quantity" value="${edit.quantity}" type="number" class="form-control" required>
                        </div>					
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" class="btn btn-info" value="Save">
                    </div>
                </form>
            </div>
        </div>
        <script src="js/manager.js" type="text/javascript"></script>                                
    </body>
</html>
