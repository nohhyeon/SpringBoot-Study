<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link rel="stylesheet" type="text/css"
	href="./css/bootstrap.min_4.5.0.css">
<script src="./js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="./js/bootstrap.min_4.5.0.js" type="text/javascript"></script>
</head>
<body>
	<div class="container p-3 my-3 border">관리자 아이디 : ${admin_id}</div>
	<div class="container p-3 my-3 border">
		<%--세션 범위인 sessionScope를 생략해도 되지만 권장하지 않는다.--%>
		관리자 이름 : ${sessionScope.admin_name}
	</div>
</body>
</html>