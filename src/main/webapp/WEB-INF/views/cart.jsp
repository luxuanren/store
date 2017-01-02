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
<title>商城-我的购物车</title>
<link rel="stylesheet" href="${res}/css/cart.css">
<script src="${res}/js/jquery-3.1.0.min.js"></script>
<script src="${res}/js/common.js"></script>
<script src="${res}/js/cart.js"></script>
</head>
<body>
	<div id="siteNav-bar">
		<div id="siteNav">
			<div>
				<a href="/store/user/information.do">${username}</a>
			</div>
			<div>
				<a href="/store/user/mark.do">收藏夹</a>
			</div>
			<div>
				<a href="/store/user/cart.do">购物车</a>
			</div>
		</div>
	</div>
	<div id="frame">
		<div id="head">
			<h1>商城</h1>
			<h3>购物车</h3>
			<div id="search">
				<form action="/store/user/cart/search.do">
					<input id="search-info" type="text" /> <input id="search-btn"
						type="submit" value="搜&nbsp;索" />
				</form>
			</div>
		</div>
		<div id="cartContent">
			<div id="cart-th">
				<div class="th th-ck">
					<div>
						<input id="ck-all" type="checkbox"> <label
							for="ck-all">全选</label>
					</div>
				</div>
				<div class="th th-item">
					<div>
						<label>商品信息</label>
					</div>
				</div>
				<div class="th th-info">
					<div>
						<label>信息</label>
					</div>
				</div>
				<div class="th th-price">
					<div>
						<label>单价</label>
					</div>
				</div>
				<div class="th th-amount">
					<div>
						<label>数量</label>
					</div>
				</div>
				<div class="th th-sum">
					<div>
						<label>金额</label>
					</div>
				</div>
				<div class="th th-op">
					<div>
						<label>操作</label>
					</div>
				</div>
			</div>
			<div id="cart-tr">
				<c:forEach var="item" items="${list}">
					<div id="${item.goods.id }" class="item">
						<div class="tr th-ck">
							<div class="container">
								<input type="checkbox" class="ck">
							</div>
						</div>
						<div class="tr th-item">
							<div class="container title">
								<label>${item.goods.name }</label>
							</div>
						</div>
						<div class="tr th-info">
							<div class="container detail">
								<label>${item.goods.detail }</label>
							</div>
						</div>
						<div class="tr th-price">
							<div class="container">
								<div>
									<label class="price">￥</label> <label class="price">${item.goods.price }</label>
								</div>
							</div>
						</div>
						<div class="tr th-amount">
							<div class="container alter">
								<div class="alter-div">
									<a class="cut highlight-hover">-</a>
									<input class="amount" type="number" min="1" value="${item.num }"/>
									<a class="add">+</a>
								</div>
							</div>
						</div>
						<div class="tr th-sum">
							<div class="container">
								<div>
									<label class="price">￥</label>
									<label class="price sum">${item.sum }</label> 
								</div>
							</div>
						</div>
						<div class="tr th-op">
							<div class="container op-list">
								<div>
									<a href="/store/user/mark.do">收藏</a>
									<a href="/store/cart/delete.do">删除</a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<div id="summary">
		<span>已选商品&nbsp;<em id="goods-sum" class="price">0</em>&nbsp;件
		</span> <span>共计&nbsp;:&nbsp;<em id="sum" class="price">0.00</em></span> <a
			id="deal">结&nbsp;算</a>
	</div>
	<div id="padding-div"></div>
</body>
</html>
