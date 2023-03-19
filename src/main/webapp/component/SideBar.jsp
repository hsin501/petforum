<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav id="sidebarMenu"
	class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
	<div class="position-sticky pt-3 sidebar-sticky">
		<ul class="nav flex-column">
			<c:if test="${LoginMember != null }">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="#"> <span data-feather="home"
						class="align-text-bottom"></span> 寵物管理AB
				</a></li>
			</c:if>

			<li class="nav-item"><a class="nav-link active"
				aria-current="page" href="#"> <span data-feather="home"
					class="align-text-bottom"></span> 會員管理
			</a></li>
		</ul>



	</div>
</nav>