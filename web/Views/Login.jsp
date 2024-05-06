<%-- 
    Document   : Login
    Created on : Feb 19, 2024, 9:06:35 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <!--Fontawesome CDN-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

        <title>Login</title>
        <style>
            html,body{

                background-image: url('https://img.freepik.com/premium-vector/red-sneakers-seamless-pattern_193606-267.jpg');
                background-size: cover;
                background-repeat: no-repeat;
                height: 100%;
                font-family: 'Numans', sans-serif;

            }
            h3{

            }
        </style>
    </head>
    <body>
        <section class="vh-100" >
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                        <div class="card shadow-2-strong" style="border-radius: 1rem; background-color: bisque; border: none;">
                            <div class="card-body p-5  text-muted">
                                <h3 class="mb-5 text-center">Sign in</h3>      
                                <div class="container" >           
                                        <img class="logo" title="TiemGiayNam" style="width: 75px; height: 75px; border-radius: 5px; margin-left: 120px; margin-bottom: 30px;margin-top: 0px" src="images/logopng.png" alt="alt"/>
                                </div>
                                <c:set var="cookie" value="${pageContext.request.cookies}"/>
                                <form method="POST" action="login">
                                    <span id="username-error" style="color: red;">
                                        ${requestScope.notice}
                                    </span> 
                                    <div class="form-outline mb-4">
<!--                                        <label class="form-label" >Username</label>-->
                                        <input type="text" id="username" name="username" placeholder="Username" class="form-control form-control-lg" value="${cookie.uCookie.value}" required/>  
                                    </div>

                                    <div class="form-outline mb-4">
<!--                                        <label class="form-label" for="typePasswordX-2">Password</label>-->
                                        <input type="password" id="password" name="password" placeholder="Password" class="form-control form-control-lg" value="${cookie.pCookie.value}" required/>

                                    </div>

                                    <!-- Checkbox -->
                                    <div class="form-check d-flex justify-content-start mb-4">
                                        <input class="form-check-input" name="remember" type="checkbox" "${(cookie.rCookie!=null?'checked':'')}" id="remember" />
                                        <label class="form-check-label" > Remember password </label>
                                    </div>
                                    <input class="btn btn-primary btn-lg btn-block" type="submit" value="Login">

                                </form>

                                <div class="form-outline mb-4">
                                    Not a member?<a style="text-decoration: none;" href="signup">  Sign Up</a>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
