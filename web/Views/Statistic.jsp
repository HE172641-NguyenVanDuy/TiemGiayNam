<%-- 
    Document   : Statistic
    Created on : Mar 10, 2024, 10:43:18 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Statistic Page</title>
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
                            <h2><b>Statistical</b></h2>
                        </div>
                        <div class="col-sm-6">

                        </div>
                    </div>

                </div>
                <ul>
                    <li>
                        <div style="font-size: 25px;" onclick="showTotalRevenue()">Total revenue:  <p style="text-decoration: underline; cursor: pointer; display: inline-block" id="totalRevenueValue"> click to show revenue</p>  
                        </div> 
                    </li> 
                    <li>
                        <div><a href="#" data-toggle="modal" data-target="#categoryQuanModal">Purchase quantity for each category</a></div>
                    </li>
                    <li>    
                        <div><a href="#" data-toggle="modal" data-target="#userInProvinceModal">User in each province</a></div>
                    </li>
                    <li>  
                        <div><a href="#sizeQuantity" data-toggle="modal" data-target="#sizeQuantityModal">Size Vs. Quantity</a></div>
                    </li>
                    <li>    
                        <div><a href="#" data-toggle="modal" data-target="#quantityBrand">Brand Vs. Quantity</a></div>
                    </li>
                </ul>

                <button class="btn btn-secondary"><a href="home" style="text-decoration: none"> Back to home</a></button>
            </div>
            
        </div>

        <div id="categoryQuanModal" class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div id="columnchart_values" style="width: 900px; height: 300px;"></div>
                </div>
            </div>
        </div>

        <div id="userInProvinceModal" class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div id="piechart" style="width: 700px; height: 300px;"></div>
                </div>
            </div>
        </div>

        <div id="sizeQuantityModal" class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div id="chart_div" style="width: 900px; height: 500px;"></div>
                </div>
            </div>
        </div>
        
        <div id="quantityBrand" class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div id="piechartV2" style="width: 700px; height: 300px;"></div>
                </div>
            </div>
        </div>
    </body>
</html>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script  type="text/javascript">

                            function showTotalRevenue() {
                                var totalRevenue = "${requestScope.totalRevenue} VNĐ";
                                document.getElementById("totalRevenueValue").innerHTML = totalRevenue;
                            }



                            google.charts.load("current", {packages: ['corechart']});
                            google.charts.setOnLoadCallback(drawChart1);
                            function drawChart1() {
                                var data = google.visualization.arrayToDataTable([
                                    ["Element", "Density", {role: "style"}],
    <c:forEach items="${requestScope.categoryQuan}" var="c">
                                    ['${c.categoryName}', ${c.quantityOrder}, "#b87333"],
    </c:forEach>
                                ]);
                                var view = new google.visualization.DataView(data);
                                view.setColumns([0, 1,
                                    {calc: "stringify",
                                        sourceColumn: 1,
                                        type: "string",
                                        role: "annotation"},
                                    2]);
                                var options = {
                                    title: "Purchase quantity for each category",
                                    width: 600,
                                    height: 400,
                                    bar: {groupWidth: "95%"},
                                    legend: {position: "none"},
                                };
                                var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
                                chart.draw(view, options);
                            }

                            google.charts.load('current', {'packages': ['corechart']});
                            google.charts.setOnLoadCallback(drawChart2);

                            function drawChart2() {

                                var data = google.visualization.arrayToDataTable([
                                    ['Province', Number],
    <c:forEach items="${requestScope.quantityLocate}" var="c">
                                    ['${c.key}', ${c.value}],
    </c:forEach>
                                ]);

                                var options = {
                                    title: 'Size vs. quantity comparison'
                                };

                                var chart = new google.visualization.PieChart(document.getElementById('piechart'));

                                chart.draw(data, options);
                            }

                            google.charts.load('current', {'packages': ['corechart']});
                            google.charts.setOnLoadCallback(drawChart3);

                            function drawChart3() {
                                var data = google.visualization.arrayToDataTable([
                                    ['Size', 'Number'],
    <c:forEach items="${requestScope.quantitySize}" var="c">
                                    ['${c.key}', ${c.value}],
    </c:forEach>


                                ]);

                                var options = {
                                    title: 'Size vs. quantity comparison',
                                    hAxis: {title: 'Size'},
                                    vAxis: {title: 'Quantity'},
                                    legend: 'none'
                                };

                                var chart = new google.visualization.ScatterChart(document.getElementById('chart_div'));

                                chart.draw(data, options);
                            }

                            function drawChart4() {

                                var data = google.visualization.arrayToDataTable([
                                    ['Brand', Number],
    <c:forEach items="${requestScope.quantityBrand}" var="c">
                                    ['${c.key}', ${c.value}],
    </c:forEach>
                                ]);

                                var options = {
                                    title: 'Brand vs. quantity comparison'
                                };

                                var chart = new google.visualization.PieChart(document.getElementById('piechartV2'));

                                chart.draw(data, options);
                            }
                            google.charts.load('current', {'packages': ['corechart']});
                            google.charts.setOnLoadCallback(drawChart4);
</script>
