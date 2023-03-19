<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>這邊顯示所有使用者</title>

<style>
table, tr, td {
	border: 0.5px solid black;
}

img {
	width: 100px
}
</style>
</head>

<body>
	<table>
		<thead>
			<tr>
				<td>ID</td>
				<td>姓名</td>
				<td>信箱</td>
				<td>照片</td>
				<td>刪除</td>
				<td>修改</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${mList }" var="m">
				<tr>
					<td>${m.mID}</td>
					<td>${m.memberDetail.name}</td>
					<td>${m.email}</td>
					<td><img src=" ${m.memberDetail.photo}"></td>

					<td><a href="DeleteMemberByID.do?mID=${m.mID}"><button>刪除</button></a>
					</td>
					<td><a href="GetUpdatePage.do?mID=${m.mID}"><button>更新</button></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>





</body>

</html>