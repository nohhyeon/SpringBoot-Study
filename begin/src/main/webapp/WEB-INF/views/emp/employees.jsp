<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 관리</title>
<link rel="stylesheet" type="text/css"
	href="/resources/css/bootstrap.min_4.5.0.css">
<script src="/resources/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="/resources/js/bootstrap.min_4.5.0.js"
	type="text/javascript"></script>
<style type="text/css">
.search-container {
	display: flex;
	align-items: center;
	margin-bottom: 20px;
}

.search-input {
	padding: 10px;
	font-size: 16px;
	margin-left: 10px;
	border: 1px solid #ddd;
	border-radius: 4px;
}

.search-button {
	background-color: #4CAF50;
	color: white;
	padding: 10px 20px;
	font-size: 16px;
	border: none;
	cursor: pointer;
	border-radius: 4px;
	margin-left: 10px;
}

.search-button:hover {
	background-color: #45a049;
}

.list-button {
	background-color: #007bff;
	color: white;
	padding: 10px 20px;
	font-size: 16px;
	border: none;
	cursor: pointer;
	border-radius: 4px;
	margin-left: 10px;
}

.list-button:hover {
	background-color: #0069d9;
}

.pagination {
	display: inline-block;
}

.pagination li {
	display: inline;
}

.pagination a {
	color: black;
	float: left;
	padding: 8px 16px;
	text-decoration: none;
	transition: background-color .3s;
	border: 1px solid #ddd;
	margin: 0 0px;
}

.pagination a.active {
	background-color: #4CAF50;
	color: white;
	border: 1px solid #4CAF50;
}

.pagination a:hover:not(.active) {
	background-color: #ddd;
}

.pagination .page-item {
	display: inline-block;
}

.alert {
	max-width: 500px;
	margin: 10px auto;
	padding: 5px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #856404;
	background-color: #fff3cd;
	border-color: #ffeeba;
	text-align: center;
}
</style>
<script type="text/javascript">
	// 폼 유효성을 검사한다.
	function validateForm() {
		var searchFilter = $('select[name="searchFilter"]').val();
		var searchQuery = $('input[name="searchQuery"]').val().trim();
		var alertDiv = $('#alert');
		if (searchFilter === "deptno" || searchFilter === "empno") {
			if (searchQuery === "" || isNaN(searchQuery)) {
				alertDiv.text("숫자만 입력합니다.");
				alertDiv.show();
				return false;
			}
		} else if (searchFilter === "ename" || searchFilter === "job"
				|| searchFilter === "mgr") {
			if (searchQuery === "" || !isNaN(searchQuery)) {
				alertDiv.text("문자열을 입력하세요.");
				alertDiv.show();
				return false;
			}
		} else {
			if (searchQuery === "") {
				alertDiv.text("검색어를 입력하세요.");
				alertDiv.show();
				return false;
			}
		}
		alertDiv.hide();
		return true;
	}
	// 숫자 입력만 허용한다.
	function checkIfNumeric(event) {
		var searchFilter = $('select[name="searchFilter"]').val();
		if (searchFilter === "deptno" || searchFilter === "empno") {
			var key = event.key;
			if (!/^\d*$/.test(key)) {
				event.preventDefault();
			}
		}
	}
	// 필터에 따라 입력 필드의 플레이스홀더를 업데이트한다.
	function updatePlaceholder() {
		// 선택된 검색 필터의 값을 가져온다.
		var searchFilter = $('select[name="searchFilter"]').val(); // 검색 입력 필드 요소를 선택한다.
		var queryInput = $('input[name="searchQuery"]');
		if (searchFilter === "deptno" || searchFilter === "empno") {
			//검색 입력 필드의 플레이스홀더를 숫자 입력으로 설정한다.
			queryInput.attr("placeholder", "숫자 입력");
			//숫자만 입력할 수 있도록 keypress 이벤트 핸들러를 추가한다.
			queryInput.on('keypress', checkIfNumeric);
		} else {
			//그 외의 경우 검색 필터 값에 따라 플레이스홀더를 설정한다.
			switch (searchFilter) {
			case "ename":
				queryInput.attr("placeholder", "사원 이름 입력");
				break;
			case "job":
				queryInput.attr("placeholder", "직급 입력");
				break;
			case "mgr":
				queryInput.attr("placeholder", "관리자 입력");
				break;
			default:
				queryInput.attr("placeholder", "검색어 입력");
				break;
			}
			//숫자 입력 제한을 위한 keypress 이벤트 핸들러를 제거한다.
			queryInput.off('keypress', checkIfNumeric);
		}
	}
	// 문서가 로드될 때 플레이스홀더를 업데이트한다.
	$(function() {
		updatePlaceholder();
		// 필터 선택이 변경될 때 플레이스홀더를 업데이트한다.
		$('select[name="searchFilter"]').on('change', updatePlaceholder);
	});
</script>
</head>
<body>
	<div class="container p-3 my-3 border">
		<section id="search" class="py-4 mb-4 bg-light">
			<div class="container">
				<div class="row">
					<div class="col-md-6 ml-auto">
						<!-- 검색 폼-->
						<form name="search" action="/search" method="get"
							onsubmit="return validateForm()">
							<fieldset>
								<div class="input-group">
									<select name="searchFilter" class="form-control mr-sm-1"
										onchange="updatePlaceholder()">
										<option value="ename" ${param.searchFilter == 'ename' ? 'selected' : ''}>사원 이름</option>
										<option value="empno" ${param.searchFilter == 'empno' ? 'selected' : ''}>사원 번호</option>
										<option value="deptno" ${param.searchFilter == 'deptno' ? 'selected' : ''}>부서 번호</option>
										<option value="job" ${param.searchFilter == 'job' ? 'selected' : ''}>직급</option>
										<option value="mgr" ${param.searchFilter == 'mgr' ? 'selected' : ''}>관리자</option>
									</select> 
									<input type="search" name="searchQuery"
										class="form-control mr-sm-1" placeholder="검색어 입력"
										value="${param.searchQuery}">
									<button class="btn btn-outline-success" type="submit">검색</button>
									&nbsp; <a href="/search" class="btn btn-outline-info">목록 이동</a>
								</div>
							</fieldset>
						</form>
						<!-- 유효성 검사 알림을 위한 테두리 -->
						<div id="alert" class="alert" style="display: none;"></div>
					</div>
				</div>
			</div>
		</section>
		<div class="container">
			<!-- 직원 데이터가 없는 경우에 알림 메시지를 표시한다. -->
			<c:if test="${empty employees}">
				<div class="alert">검색 결과가 없습니다.</div>
			</c:if>
			<!-- 직원 데이터가 있는 경우에 테이블에 표시한다. -->
			<c:if test="${not empty employees}">
				<table class="table">
					<thead>
						<tr>
							<th>사원 번호</th>
							<th>사원 이름</th>
							<th>직급</th>
							<th>관리자</th>
							<th>입사일</th>
							<th>월급</th>
							<th>커미션</th>
							<th>부서 번호</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="employee" items="${employees}">
							<tr>
								<td>${employee.empno}</td>
								<td>${employee.ename}</td>
								<td>${employee.job}</td>
								<td>${employee.mgr}</td>
								<td>${employee.hiredate}</td>
								<td><fmt:formatNumber value="${employee.sal}" type="currency" /></td>
								<td><fmt:formatNumber value="${employee.comm}" type="currency" /></td>
								<td>${employee.deptno}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 페이징 처리를 설정한다. -->
				<nav class="d-flex justify-content-center">
					<ul class="pagination">
						<li class="page-item ${page == 1 ? 'disabled' : ''}">
							<a href="/search?page=${page - 1}&searchFilter=${param.searchFilter}&searchQuery=${param.searchQuery}" class="page-link">이전</a>
						</li>
						<c:forEach begin="1" end="${totalPages}" var="i">
							<li class="page-item ${i == page ? 'active' : ''}">
								<a href="/search?page=${i}&searchFilter=${param.searchFilter}&searchQuery=${param.searchQuery}" class="page-link">${i}</a>
							</li>
						</c:forEach>
						<li class="page-item ${page == totalPages ? 'disabled' : ''}">
							<a href="/search?page=${page + 1}&searchFilter=${param.searchFilter}&searchQuery=${param.searchQuery}" class="page-link">다음</a>
						</li>
					</ul>
				</nav>
			</c:if>
		</div>
	</div>
</body>
</html>
