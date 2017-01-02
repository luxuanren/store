<%@ include file="/WEB-INF/include/taglib.jsp"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎光临书城</title>
<link rel="stylesheet" href="${res}/css/home.css">
<script src="${res}/js/jquery-3.1.0.min.js"></script>
<script src="${res}/js/common.js"></script>
<script src="${res}/js/home.js"></script>
</head>
<body>
	<div id="userBar">
		<div id="nav">
			<a id="user" class="unlogin" userId="${user.id}" href="${path}/user/information.do">${user.username}</a>
			<a id="order" href="${path}/user/order.do" target="_blank">我的订单</a>
			<a id="cart" href="${path}/user/cart.do" target="_blank">购物车</a>
			<a id="mark" href="${path}/user/mark.do" target="_blank">我的收藏</a>
		</div>
	</div>
	<div id="frame">
		<div id="searchBar">
			<form action="<%=response.encodeRedirectURL("/store/search/keyword.do")%>">
				<input id="input" name="keyword" type="text"> <input
					id="doSearch" type="submit" value="搜索">
			</form>
		</div>

		<div id="content">
			<table>
				<c:forEach var="item" items="${list}">

					<td id="${item.id}">
						<div class="display">
							<div>
								<label name="name">${item.name}</label>
							</div>
							<div>
								<label name="title">${item.title}</label>
							</div>
							<div class="price">
								￥<label name="price"><fmt:formatNumber
										value="${item.price}" pattern=".00" /></label>
							</div>
							<div>
								库存&nbsp;<label name="amount" value="${item.inventory}">${item.inventory}</label>
							</div>
							<div>
							    <label class="mark">关注</label>
								<label class="add">加入购物车</label>
							</div>
						</div>
					</td>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>