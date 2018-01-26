<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.javaex.vo.UserVo" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div id="header">
			<a href="main"><h1>MySite</h1></a>
			<ul>
			
			<c:if test="${authUser==null}">
			<!-- 로그인 전 -->
				<li><a href="user?a=loginform">로그인</a></li>
				<li><a href="user?a=joinform">회원가입</a></li>
			</c:if>
			
			
			<c:if test="${authUser!=null}">
			<li><a href="user?a=modifyform">회원정보수정</a></li>
				<li><a href="user?a=logout">로그아웃</a></li> 
				<li> ${authUser.name}님 안녕하세요^^;</li>
			</c:if>
			
			
			
			</ul>
		</div> <!-- /header -->