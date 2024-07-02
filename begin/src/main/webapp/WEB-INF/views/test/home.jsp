<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home 출력</title>
<link rel="stylesheet" type="text/css"
	href="../css/bootstrap.min_4.5.0.css">
<script src="../js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="../js/bootstrap.min_4.5.0.js" type="text/javascript"></script>
</head>
<body>
	<div class="container p-3 my-3 border">
		<h1>Hello Spring Boot(안녕 스프링부트)</h1>
		<p> ${test} 살아도 사는게 아니래~ 초라해 보이고 ~   </p>
		<P>The time on the server is ${serverTime}.</P>
	</div>
</body>
</html>