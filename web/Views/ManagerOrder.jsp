<%-- 
    Document   : ManagerOrder
    Created on : Mar 12, 2024, 5:46:39 PM
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
                            <h2>Manage <b>Order  </b> -- order success --</h2>
                        </div>
                        <div class="col-sm-6">

                           
                                    <th>
                                        <a href="pendinglist"  class="btn btn-success"><i class="material-icons">&#xE147;</i> <span>List Pending</span></a>
                                    </th>
                               
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

                        <c:forEach items="${allOrder}" var="o">
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
                                    <a href="detailorder?oid=${o.orderID}"    class="info" data-toggle="modal">
                                        <i class="material-icons" data-toggle="tooltip" title="Order detail">&#xf107;</i></a>
                                    <form action="managerorder" method="post">
                                        <input hidden name="orderid" value="${o.orderID}"/>
                                        <input type="submit" value="Cancel Order"/>
                                    </form> 
<!--                                    <form action="managerorder" method="post">
                                        <input hidden name="deleteid" value="${o.orderID}"/>
                                        <input type="submit" value="Delete Order"/>
                                    </form> -->
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>

                </table>
                <button class="btn btn-secondary"><a href="home" style="text-decoration: none"> Back to home</a></button>

            </div>

        </div>


        <!--        informationfade            -->
        <!--        <div id="informationOrder" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                             form category 
        
                            <table class="table table-striped table-hover">
        
                                <thead>
                                    <tr>
                                                                    <th></th>
                                        <th>OrderID</th>
                                        <th>ProductID</th>
                                        <th>Price</th>
                                        <th>Size</th>
                                        <th>Quantity</th>
                                        <th>Delivery address</th>
                                    </tr>
                                </thead>
                                <tbody>
        
        <c:forEach items="${requestScope.orderDetailByOrderID}" var="od">
            <tr>
                <td>${od.order.orderID}</td>
                <td>${od.product.productID}</td>
                <td>${od.price}VNĐ</td>	
                <td>${od.size}</td>
                <td>${od.quantity}</td>
                <td>${od.deliveryAddress}</td>
                <td>
                    <a href="managerorder?oid=${o.orderID}" id="info"  class="info" data-toggle="modal">
                        <i class="material-icons" data-toggle="tooltip" title="Info">&#xf107;</i></a>
                    <a href="#" onclick="doDelete('${p.productID}')" class="delete" data-toggle="tooltip" title="Delete"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                </td>
            </tr>

        </c:forEach>
    </tbody>

</table>

</div>
</div>
</div>-->



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
    </body>
</html>
