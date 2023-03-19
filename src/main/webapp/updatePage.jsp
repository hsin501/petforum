<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改頁面</title>
</head>
<body>

	<form action="update.do" method="post" enctype="multipart/form-data">
		<div>
			姓名: <input type="text" value="${member.memberDetail.name }" name="mName">
		</div>
		<div>
			年齡: <input type="number" value="${member.memberDetail.age }" name="mAge">
		</div>
		<div>
			大頭貼: <img alt="" src="${member.memberDetail.photo }"> <input
				type="file" name="mFile">
		</div>

		<button>送出</button>
	</form>
</body>
</html>