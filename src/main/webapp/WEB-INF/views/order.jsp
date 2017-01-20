<%@ page import="java.util.HashMap"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <link rel="stylesheet" href="${res}/css/order.css">
    <script src="${res}/js/jquery-3.1.0.min.js"></script>
    <script src="${res}/js/common.js"></script>
	<script src="${res}/js/order.js"></script>
</head>
<body>
    <div id="siteNav-bar">
        <div id="siteNav">
            <div><a href="/store/user/information.do">${username}</a></div>
            <div><a id="order" href="${path}/user/order.do">我的订单</a></div>
            <div><a href="/store/user/mark.do">收藏夹</a></div>
            <div><a href="/store/user/cart.do">购物车</a></div>
            <div><a href="/store/home.do">商城首页</a></div>
        </div>
    </div>
    <div id="frame">
        <div id="searchBar">
            <form action="/store/order/search.do">
                <input id="search" type="text">
                <input type="button" value="订单搜索">
            </form>
        </div>
        <div id="orders">
            <div id="head">
                <div class="cell td-goods">
                    <label>宝贝</label>
                </div>
                <div class="cell td-price">
                    <label>单价</label>
                </div>
                <div class="cell td-amount">
                    <label>数量</label>
                </div>
                <div class="cell td-sum">
                    <label>总金额</label>
                </div>
                <div class="cell td-status">
                    <label>交易状态</label>
                </div>
                <div class="cell td-op">
                    <label>操作</label>
                </div>
            </div>
            <div id="content">
            	<c:forEach var="order" items="${orderList}">
	                <div class="row">
	                    <div class="order-detail">
	                        <span class="time"><em>${order.date }</em></span>
	                        <span class="order-id">订单号: <em>${order.id }</em></span>
	                    </div>
	                    <c:forEach var="item" items="${order.tradeList.tradeList}">
		               		<div id="${item.goodsId }" class="goods-detail">
		                        <div class="cell td-goods">
		                            <div class="container">
		                                <a class="introduce" href="/store/search/goods.do?goodsId=${item.goods.id}">${item.goods.name}</a>
		                            </div>
		                        </div>
		                        <div class="cell td-price">
		                            <div class="container">
		                                <div>
		                                    <label class="price">￥</label>
		                                    <label class="price">${item.price }</label>
		                                </div>
		                            </div>
		                        </div>
		                        <div class="cell td-amount">
		                            <div class="container">
		                                <label>${item.tradeNum }</label>
		                            </div>
		                        </div>
		                        <div class="cell td-sum">
		                            <div class="container">
		                                <div>
		                                    <label class="price">￥</label>
		                                    <label class="price">${item.sum }</label>
		                                </div>
		                            </div>
		                        </div>
		                        <div class="cell td-status">
		                            <div class="container">
		                                <label>交易成功</label>
		                            </div>
		                        </div>
		                        <div class="cell td-op">
		                            <div class="container">
		                            	<c:choose>
		                            		<c:when test="${item.evaluate == true }">
		                            			<c:choose>
			                            		    <c:when test="${item.appendEvaluation == true }">
			                            		        <label>已评价</label>
			                            		    </c:when>
			                            		    <c:when test="${item.appendEvaluation == false }">
			                            		        <a class="append-comment">追加评价</a>
			                            		    </c:when>
		                            		    </c:choose>
		                            		</c:when>
		                            	</c:choose>
		                            	<c:choose>
		                            		<c:when test="${item.evaluate == false }">
		                            		    <a class="add-comment">评价</a>
		                            		</c:when>
		                            	</c:choose>
		                                <a class="add-cart">加入购物车</a>
		                            </div>
		                        </div>
		                    </div>
		                </c:forEach>
                	</div>
                </c:forEach>
            </div>
        </div>
        <div id="comment-div">
        	<div id="comment-info">
        		<textArea id="user-comment"></textArea>
        	</div>
        	<div id="comment-op">
        		<input id="discard" type="button" value="取消">
        		<input id="submit" type="button" value="提交">
        	</div>
        </div>
</body>
</html>