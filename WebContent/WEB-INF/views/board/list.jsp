<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/board.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>


		<div id="content">
			<div id="board">
				<form id="search_form" action="board?a=list" method="post">
					<input type="text" id="kwd" name="searchword" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>


					<c:forEach items="${list }" var="vo">
						<tr>
							<td>${vo.no }</td>
							<td><a href="board?a=view&no=${vo.no }&count=${vo.count}">${vo.title }</a></td>
							<td>${vo.name }</td>
							<td>${vo.count }</td>
							<td>${vo.date }</td>

							<c:if test="${vo.userNo==authUser.no }">
								<td><a href="board?a=delete&no=${vo.no }" class="del">삭제</a></td>
							</c:if>
						</tr>
					</c:forEach>





				</table>
				<div class="pager">
					<ul>
						<c:if test="${paging.getCur_Page()!=1}">
							<li><a href="board?a=list&page=${paging.getCur_Page()-1}">◀</a></li>
						</c:if>

						<c:forEach var="i" begin="${paging.getPage_Start()}"
							end="${paging.getPage_End()}">

							<c:choose>
								<c:when test="${i==paging.getCur_Page()}">
									<li class="selected"><a href="board?a=list&page=${i}">
											${i} </a></li>
								</c:when>

								<c:otherwise>
									<li><a href="board?a=list&page=${i}"> ${i} </a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						

						<li><a href="board?a=list&page=${paging.getCur_Page()+1}">▶</a></li>
					</ul>
				</div>

				<c:if test="${authUser!=null }">
					<div class="bottom">
						<a href="board?a=writeform" id="new-book">글쓰기</a>
					</div>
				</c:if>
			</div>
		</div>

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

	</div>
</body>
</html>