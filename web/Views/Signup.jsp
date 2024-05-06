<%-- 
    Document   : Signup
    Created on : Feb 28, 2024, 12:12:45 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <!--Fontawesome CDN-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

        <title>Sign Up</title>
        <style>
            body {
                margin: 70px;
                padding: 0;
                background-image: url('https://wallpapercave.com/wp/wp9123736.jpg');
                background-size: cover;
                background-repeat: space;
                background-position: center;

            }

            .container {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 30px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
                background-color: transparent;
                opacity: rgba(0, 0, 0, 0.5);

            }

            .form-register {
                flex:0.5;
                padding: 20px;
                background-color: #f5f5f5;
                border: 3px solid bisque;
                border-radius: 10px;
            }

            .form-register h2 {
                margin-top: 0;
                margin-bottom: 35px;
                text-align: center;
            }

            .form-register input[type="text"],
            .form-register input[type="password"],
            .form-register select {
                width: 100%;
                padding: 10px;
                margin-bottom: 15px;
                box-sizing: border-box;
            }

            .form-register input[type="submit"] {
                width: 100%;
                padding: 10px;
                background-color: #4CAF50;
                color: white;
                border: none;
            }

            .form-register .location-select {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 15px;
                border-radius: 12px;
                border-color: greenyellow;
            }

            .form-register .location-select select {
                flex: 1;
                margin-right: 10px;
                border-radius: 12px;
                border-color: greenyellow;
            }

            .form-register input {
                border-radius: 12px;
                border-color: greenyellow;
            }
            .form-register .back {
                border-radius: 12px;
                border-color: greenyellow;
                margin-top: 25px;
            }

        </style>
    </head>
    <body>
        <div class="container">

            <div class="form-register">
                <h2 >Register</h2>    
                <img class="logo" title="TiemGiayNam" style="width: 75px; height: 75px; border-radius: 5px; margin-left: 220px; margin-bottom: 30px;margin-top: 0px" src="images/logopng.png" alt="alt"/>
                <form id="signupForm" method="POST" action="signup">
                    <div class="location-select">
                        <select id="province"  onchange="myProvince()" name="province">
                            <c:if test="${globalP != null}">
                                <option name="province"  value="${globalP}" >${globalP}</option>
                            </c:if>

                            <c:forEach  items="${requestScope.province}" var="p">
                                <option  name="province" value="${p.province}" >${p.province}</option>
                            </c:forEach>                        
                        </select>
                        <select id="district" onchange="myDistrict()" name="district">
                            <c:if test="${globalD != null}">
                                <option   name="district" value="${globalD}" >${globalD}</option>
                            </c:if>
                            <c:forEach  items="${requestScope.district}" var="d">
                                <option name="district" value="${d.district}" onchange="getDistrict()">${d.district}</option>
                            </c:forEach>
                        </select>
                        <select id="ward" onchange="myWard()" name="ward">
                            <c:if test="${globalW != null}">
                                <option name="ward"  value="${globalW}" >${globalW}</option>
                            </c:if>
                            <c:forEach  items="${requestScope.ward}" var="d">
                                <option name="ward" value="${d.ward}" onchange="getWard()">${d.ward}</option>
                            </c:forEach>

                        </select>
                    </div>
                    <input type="text" name="firstname" placeholder="First name" required>
                    <input type="text" name="lastname" placeholder="Last name" required>
                    <span id="username-error" style="color: red;">
                        ${duplicate}
                    </span>                     
                    <input type="text" name="username" placeholder="User name" required>
                    <input type="password" name="password" placeholder="Password" required>
                    <input type="text" name="address" placeholder="Address" required>
                    
                    <input type="number" name="phonenumber" placeholder="Phone" required>
                    <span id="phonenumber-error" style="color: red;">
                        ${wrong}
                    </span>
                    <input  type="submit"  value="Register">

                </form>
                <button class="back"><a href="login" style="text-decoration: none"> Back to login</a></button>
            </div>

        </div>
    </body>
    <script>

        function myProvince() {
            var p = document.getElementById("province").value;
            window.location = `signup?province=` + p;
        }
        function getDistrict() {
            document.getElementById("province").innerHTML = ` <c:forEach items="${requestScope.district}" var="d"> 
                        <option id="district" value="${d.district}">${d.district}</option>
                
        </c:forEach> `;
        }
        function myDistrict() {
            var p = document.getElementById("province").value;
            var d = document.getElementById("district").value;
            window.location = `signup?province=` + p + `&district=` + d;
        }
        function getWard() {
            document.getElementById("district").innerHTML = ` <c:forEach items="${requestScope.ward}" var="d"> 
                        <option id="ward" value="${d.ward}">${d.ward}</option>
                
        </c:forEach> `;
        }
        function myWard() {
            var p = document.getElementById("province").value;
            var d = document.getElementById("district").value;
            var w = document.getElementById("ward").value;
            window.location = `signup?province=` + p + `&district=` + d + `&ward=` + w;
        }
    </script>
</html>
