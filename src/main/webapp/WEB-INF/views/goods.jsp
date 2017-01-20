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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>商品详情</title>
    <link rel="stylesheet" href="${res}/css/goods.css">
    <script src="${res}/js/jquery-3.1.0.min.js"></script>
    <script src="${res}/js/common.js"></script>
	<script src="${res}/js/goods.js"></script>
</head>
<body>
	<div id="siteNav-bar">
        <div id="siteNav">
            <div><a href="/store/user/information.do">${username}</a></div>
            <div><a href="/store/user/order.do">我的订单</a></div>
            <div><a href="/store/user/mark.do">收藏夹</a></div>
            <div><a href="/store/user/cart.do">购物车</a></div>
        </div>
    </div>
    <div id="frame">
        <div id="content">
            <div id="goods-info" goodsId="${goods.id }">
                <div id="detail">
                    <label>${goods.detail }</label>
                </div>
                <div id="name">
                    <label>${goods.name }</label>
                </div>
                <div id="price">
                    <span>￥ <em>${goods.price }</em></span>
                </div>
            </div>
            <div id="op">
                <div id="inventory">
                    <span>库存 <em>${goods.inventory}</em> 件</span>
                </div>
                <div id="op-amount">
                    <div id="container">
                        <div id="alter-div">
                            <a id="cut">-</a>
                            <input id="amount" type="number" min="1" value="1"/>
                            <a id="add">+</a>
                        </div>
                    </div>
                </div>
                <div id="op-action">
                    <a id="mark-btn">加关注</a>
                    <a id="add-btn">加入购物车</a>
                </div>
            </div>
        </div>
        <div id="commence">
        	<c:forEach var="item" items="${list}">
        		<div class="item">
        			<div class="user-info">
                    	<label>${item.username }</label>
                	</div>
	                <div class="origin-commence">
	                    <div class="commence-time">
	                		<label>${item.evalTime }</label>
	                		<label>初次评价</label>
	                    </div>
	                    <div class="comment-item">
	                        <label>${item.info }</label>
	                    </div>
	                </div>
	                <c:choose>
		                <c:when test="${item.appendCommence == true }">
		                    <div class="append-commence">
	                    		<div class="commence-time">
			                        <label>${item.appendEvalTime }</label>
			                        <label>追加评论</label>
			                    </div>
			                    <div class="comment-item">
			                        <label>${item.appendInfo }</label>
			                    </div>
	                		</div>
		                </c:when>
		            </c:choose>
            	</div>
	        </c:forEach>
        </div>
    </div>
</body>
</html>