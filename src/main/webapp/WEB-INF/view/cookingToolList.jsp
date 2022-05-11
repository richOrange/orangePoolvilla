<%@page import="java.util.ArrayList"%>
<%@page import="vo.CookingTool"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%
	ArrayList<CookingTool> list = (ArrayList<CookingTool>)request.getAttribute("list");
%>
<head>
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Title -->
    <title>Roberto - Hotel &amp; Resort HTML Template Template</title>

    <!-- Favicon -->
    <link rel="icon" href="${pageContext.request.contextPath}/template/img/core-img/favicon.png">

    <!-- Stylesheet -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/style.css">

</head>

<body>
    <!-- Preloader -->
    <div id="preloader">
        <div class="loader"></div>
    </div>
    <!-- /Preloader -->

    <!-- Header Area Start -->
    <header class="header-area">
        <!-- Search Form -->
        <div class="search-form d-flex align-items-center">
            <div class="container">
                <form action="index.html" method="get">
                    <input type="search" name="search-form-input" id="searchFormInput" placeholder="Type your keyword ...">
                    <button type="submit"><i class="icon_search"></i></button>
                </form>
            </div>
        </div>

        <!-- Main Header Start -->
        <div class="main-header-area">
            <div class="classy-nav-container breakpoint-off">
                <div class="container">
                    <!-- Classy Menu -->
                    <nav class="classy-navbar justify-content-between" id="robertoNav">

                        <!-- Logo -->
                        <a class="nav-brand" href="${pageContext.request.contextPath}/template/index.html"><img src="${pageContext.request.contextPath}/template/img/core-img/logo.png" alt=""></a>

                        <!-- Navbar Toggler -->
                        <div class="classy-navbar-toggler">
                            <span class="navbarToggler"><span></span><span></span><span></span></span>
                        </div>

                        <!-- Menu -->
                        <div class="classy-menu">
                            <!-- Menu Close Button -->
                            <div class="classycloseIcon">
                                <div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
                            </div>
                            <!-- Nav Start -->
                            <div class="classynav">
                                <ul id="nav">
                                    <li class="active"><a href="${pageContext.request.contextPath}/template/index.html">Home</a></li>
                                    <li><a href="${pageContext.request.contextPath}/template/room.html">Rooms</a></li>
                                    <li><a href="${pageContext.request.contextPath}/template/about.html">About Us</a></li>
                                    <li><a href="${pageContext.request.contextPath}/template/#">Pages</a>
                                        <ul class="dropdown">
                                            <li><a href="${pageContext.request.contextPath}/template/index.html">- Home</a></li>
                                            <li><a href="${pageContext.request.contextPath}/template/room.html">- Rooms</a></li>
                                            <li><a href="${pageContext.request.contextPath}/template/single-room.html">- Single Rooms</a></li>
                                            <li><a href="${pageContext.request.contextPath}/template/about.html">- About Us</a></li>
                                            <li><a href="${pageContext.request.contextPath}/template/blog.html">- Blog</a></li>
                                            <li><a href="${pageContext.request.contextPath}/template/single-blog.html">- Single Blog</a></li>
                                            <li><a href="${pageContext.request.contextPath}/template/contact.html">- Contact</a></li>
                                            <li><a href="${pageContext.request.contextPath}/template/#">- Dropdown</a>
                                                <ul class="dropdown">
                                                    <li><a href="${pageContext.request.contextPath}/template/#">- Dropdown Item</a></li>
                                                    <li><a href="${pageContext.request.contextPath}/template/#">- Dropdown Item</a></li>
                                                    <li><a href="${pageContext.request.contextPath}/template/#">- Dropdown Item</a></li>
                                                    <li><a href="${pageContext.request.contextPath}/template/#">- Dropdown Item</a></li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </li>
                                    <li><a href="${pageContext.request.contextPath}/template/blog.html">News</a></li>
                                    <li><a href="${pageContext.request.contextPath}/template/contact.html">Contact</a></li>
                                </ul>

                                <!-- Search -->
                                <div class="search-btn ml-4">
                                    <i class="fa fa-search" aria-hidden="true"></i>
                                </div>

                                <!-- Book Now -->
                                <div class="book-now-btn ml-3 ml-lg-5">
                                    <a href="${pageContext.request.contextPath}/template/#">Book Now <i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
                                </div>
                            </div>
                            <!-- Nav End -->
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </header>
    <!-- Header Area End -->

    <!-- Breadcrumb Area Start -->
    <div class="breadcrumb-area bg-img bg-overlay jarallax" style="background-image: url(${pageContext.request.contextPath}/template/img/bg-img/17.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="breadcrumb-content text-center">
                        <div class="breadcrumb-post-content">
                            <h2 class="post-title">Cooking Tool List</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb Area End -->

    <!-- Blog Area Start -->
    <div class="roberto-news-area section-padding-100-0">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-lg-8">
                
                    <!-- Blog Details Text -->
                    <div class="blog-details-text">
                        <!-- Blockquote -->
                        <blockquote class="roberto-blockquote d-flex">
                            <div class="text">
                                <h5>Cooking Tool List</h5>
                            </div>
                        </blockquote>
         
					        <div>
								<table class = "table table-hover">
									<thead>
										<tr>
											<th>Number</th>
											<th>Cooking Tool Name</th>
											<th>Update Date</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<%
												for(CookingTool ck : list) {
											%>
													<tr>
														<td><%= ck.getCookingToolNo() %></td>
														<td><%= ck.getCookingToolName() %></td>
														<td><%= ck.getUpdateDate() %><td> 
														<td><a href="${pageContext.request.contextPath}/deleteCookingToolController?cookingToolNo=<%= ck.getCookingToolNo() %>" class = "btn btn-outline-secondary btn-sm">삭제</a></td>
													</tr>
											<%		
												}
											%>
										</tr>
									</tbody>
								</table>
							</div>
                    </div>
                    <!-- Leave A Reply -->
                    <div class="roberto-contact-form mt-80 mb-100">
                        <h2>Enter</h2>

                        <!-- Form -->
                        <form action="${pageContext.request.contextPath}/cookingToolController" method="post">
                            <div class="row">
                                <div class="col-12">
                                    <input type="text" name="cookingToolName" class="form-control mb-30" placeholder="Please enter the cooking tool">
                                </div>
                                <div class="col-12">
                                    <button type="submit" class="btn roberto-btn btn-3 mt-15">Save</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

    <!-- **** All JS Files ***** -->
    <!-- jQuery 2.2.4 -->
    <script src="${pageContext.request.contextPath}/template/js/jquery.min.js"></script>
    <!-- Popper -->
    <script src="${pageContext.request.contextPath}/template/js/popper.min.js"></script>
    <!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/template/js/bootstrap.min.js"></script>
    <!-- All Plugins -->
    <script src="${pageContext.request.contextPath}/template/js/roberto.bundle.js"></script>
    <!-- Active -->
    <script src="${pageContext.request.contextPath}/template/js/default-assets/active.js"></script>

</body>

</html>