<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>寵物清單</title>
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
				<td>照片</td>
			</tr>
		</thead>

		<tbody>

			<c:forEach items="${petList }" var="p">
				<tr>
					<td>${p.pID }</td>
					<td>${p.name }</td>
					<td><img src="GetPetPhotoByID.do?pID=${p.pID }"></td>
				</tr>

			</c:forEach>


		</tbody>


	</table>



</body>
</html>