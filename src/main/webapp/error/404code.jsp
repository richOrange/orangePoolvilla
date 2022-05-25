<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404오류페이지</title>
</head>
<body>
<div class="row">
    <div class="col-md-12 page-404">
        <div class="number font-red"></div>
        <div class="details">
            <h3>404 - 잘못된 요청입니다.</h3>
            <p>
                <a href="${pageContext.request.contextPath}/all/homeController" > [HOME] </a>   
            </p>
        </div>
    </div>
</div>
</body>
</html>