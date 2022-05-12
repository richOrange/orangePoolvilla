<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Title -->
    <title>OrangePoolvilla:suppliesList</title>

    <!-- Favicon -->
    <link rel="icon" href="${pageContext.request.contextPath}/template/img/core-img/favicon.ico">

    <!-- Stylesheet -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/style.css">

</head>

	<!-- jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    
<body>
    <!-- Preloader -->
    <div id="preloader">
        <div class="loader"></div>
    </div>
    <!-- /Preloader -->

    <!-- Header Area Start -->
        <div id="includeHeader"> <!-- Insert your file here --></div>
	<!-- Header Area End -->

    <!-- Breadcrumb Area Start -->
    <div class="breadcrumb-area bg-img bg-overlay jarallax" style="background-image: url(${pageContext.request.contextPath}/template/img/bg-img/17.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="breadcrumb-content text-center">
                        <div class="breadcrumb-post-content">
                            <h2 class="post-title">Supplies List</h2>
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
                                <h5>Supplies List</h5>
                            </div>
                        </blockquote>
         
					        <div>
								<table class = "table table-hover">
									<thead>
										<tr>
											<th>Number</th>
											<th>Supplies Name</th>
											<th>Update Date</th>
											<th>&nbsp;</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<c:forEach var = "s" items = "${ list }">
													<tr>
														<td>${ s.suppliesNo }</td>
														<td>${ s.suppliesName }</td>
														<td>${ s.updateDate }</td> 
														<td><a href="${pageContext.request.contextPath}/host/deleteSuppliesController?suppliesNo=${ s.suppliesNo }" class = "btn btn-outline-secondary btn-sm">삭제</a></td>
													</tr>
											</c:forEach>
										</tr>
									</tbody>
								</table>
							</div>
                    </div>
                    <!-- Leave A Reply -->
                    <div class="roberto-contact-form mt-80 mb-100">
                        <h2>Enter</h2>

                        <!-- Form -->
                        <form action="${pageContext.request.contextPath}/host/suppliesController" method="post">
                            <div class="row">
                                <div class="col-12">
                                    <input type="text" name="suppliesName" class="form-control mb-30" placeholder="Please enter the supplies">
                                </div>
                                <div class="col-12">
                                    <button type="submit" class="btn roberto-btn btn-3 mt-15">Save</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
</body>

<script>
        $("#includeHeader").load('${pageContext.request.contextPath}/includeController');
</script>

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
    
</html>