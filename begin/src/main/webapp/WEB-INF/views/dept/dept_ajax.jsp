<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 관리</title>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min_4.5.0.css">
<script src="./js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="./js/bootstrap.min_4.5.0.js" type="text/javascript"></script>
<style type="text/css">
.btn-group-custom {
	display: flex;
	justify-content: center;
	margin-bottom: 20px;
	flex-wrap: wrap;
}

.btn-group-custom .btn {
	margin: 5px;
	min-width: 120px;
}

.custom-table-wrapper {
	overflow-x: auto;
	width: 100%;
}

table.custom-table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
}

table.custom-table th, table.custom-table td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: center;
	vertical-align: middle;
}

table.custom-table th {
	background-color: #f2f2f2;
	font-weight: bold;
}

.form-container {
    display: none;
    margin-top: 20px;
}

.form-container.active {
    display: block;
}

.form-group label {
	font-weight: bold;
}
</style>
</head>
<body>
	<div class="container p-3 my-3 border">
		<div class="btn-group-custom">
			<button id="selectAllBtn" class="btn btn-outline-secondary">부서 목록</button>
			<button id="selectBtn" class="btn btn-outline-secondary">부서 상세 보기</button>
			<button id="insertBtn" class="btn btn-outline-secondary">부서 입력</button>
			<button id="updateBtn" class="btn btn-outline-secondary">부서 수정</button>
			<button id="deleteBtn" class="btn btn-outline-secondary">부서 삭제</button>
		</div>
		<div id="result" class="custom-table-wrapper">
			<table id="resultTable" class="custom-table">
				<thead>
					<tr>
						<th>부서 번호</th>
						<th>부서 이름</th>
						<th>부서 지역</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>

		<div id="formContainer" class="form-container">
			<form id="actionForm">
				<div class="form-group">
					<label for="deptNo">부서 번호</label>
					<input type="text" class="form-control" id="deptNo" name="deptno">
				</div>
				<div class="form-group">
					<label for="dname">부서 이름</label>
					<input type="text" class="form-control" id="dname" name="dname">
				</div>
				<div class="form-group">
					<label for="loc">부서 지역</label>
					<input type="text" class="form-control" id="loc" name="loc">
				</div>
				<button type="button" class="btn btn-outline-info" id="submitActionForm">저장</button>
			</form>
		</div>
	</div>

	<script type="text/javascript">
		// 콜백 함수로 문서가 완전히 로드되고 DOM이 준비된 후에 실행된다.
		$(function() {
			var currentAction = '';
			
			// 전체 부서 목록을 로드한다.
			function loadData() {
				$.ajax({
					url: './selectAll', // 모든 부서 정보를 가져오는 API 엔드포인트다.
					method: 'GET',
					success: function(data) {
						console.log("json 데이터 확인 : ", data); // 데이터를 테이블에 표시한다.
						displayTable(data);
					},
					error: function(error) {
						alert('부서 목록에 데이터가 없습니다.');
					}
				});
			}

			// 부서 목록 버튼 클릭 시 부서 목록을 로드한다.
			$('#selectAllBtn').click(function() {
				loadData();
			});

			// 공통 입력 폼을 표시하고 currentAction을 설정한다.
			function showForm(action) {
				currentAction = action;
				$('#formContainer').addClass('active');
			}

			$('#selectBtn').click(function() {
				showForm('select');
				$('#dname').parent().hide();
				$('#loc').parent().hide();
			});

			$('#insertBtn').click(function() {
				showForm('insert');
				$('#dname').parent().show();
				$('#loc').parent().show();
			});

			$('#updateBtn').click(function() {
				showForm('update');
				$('#dname').parent().show();
				$('#loc').parent().show();
			});

			$('#deleteBtn').click(function() {
				showForm('delete');
				$('#dname').parent().hide();
				$('#loc').parent().hide();
			});

			$('#submitActionForm').click(function() {
				var deptno = $('#deptNo').val();
				var dname = $('#dname').val();
				var loc = $('#loc').val();
				
				// 공통 유효성 검사
				if (!deptno || isNaN(deptno) || deptno.length > 2) {
					alert("부서 번호를 올바르게 입력하세요.");
					return;
				}
				
				if ((currentAction === 'insert' || currentAction === 'update') && (!dname || dname.length < 2 || dname.length > 14)) {
					alert("부서 이름을 올바르게 입력하세요.");
					return;
				}
				
				if ((currentAction === 'insert' || currentAction === 'update') && (!loc || loc.length < 2 || loc.length > 14)) {
					alert("부서 지역을 올바르게 입력하세요.");
					return;
				}

				if (currentAction === 'select') {
					$.ajax({
						url: './select/' + deptno,
						method: 'GET',
						success: function(data) {
							displayTable([data]);
						},
						error: function(error) {
							alert('부서 번호가 존재하지 않습니다.');
						}
					});
				} else if (currentAction === 'insert') {
					$.ajax({
						url: './insert',
						method: 'POST',
						contentType: 'application/json',
						data: JSON.stringify({
							deptno: deptno,
							dname: dname,
							loc: loc
						}),
						success: function(data) {
							alert("부서를 등록하였습니다.");
							loadData();
						},
						error: function(error) {
							alert('입력할 부서 번호가 존재합니다.');
						}
					});
				} else if (currentAction === 'update') {
					$.ajax({
						url: './update',
						method: 'PUT',
						contentType: 'application/json',
						data: JSON.stringify({
							deptno: deptno,
							dname: dname,
							loc: loc
						}),
						success: function(data) {
							alert("부서를 수정하였습니다.");
							loadData();
						},
						error: function(error) {
							alert('수정할 부서 번호가 존재하지 않습니다.');
						}
					});
				} else if (currentAction === 'delete') {
					$.ajax({
						url: './delete/' + deptno,
						method: 'DELETE',
						success: function(data) {
							alert("부서를 삭제하였습니다.");
							loadData();
						},
						error: function(error) {
							alert('삭제할 부서 번호가 존재하지 않습니다.');
						}
					});
				}
			});

			// 테이블에 데이터를 표시한다.
			function displayTable(data) {
				var tableBody = $('#resultTable tbody');
				tableBody.empty();
				data.forEach(function(row) {
					var rowHtml = '<tr>' +
						'<td>' + row.deptno + '</td>' +
						'<td>' + row.dname + '</td>' +
						'<td>' + row.loc + '</td>' +
						'</tr>';
					// 새로운 데이터를 추가한다.
					tableBody.append(rowHtml);
				});
			}

			// 페이지 로드 시 초기 데이터로 로드한다.
			loadData();
		});
	</script>
</body>
</html>
