$(function(){
	
	$('.td-op .add-cart').click(function(){
		var goods = getGoodsInfo($(this).closest('.goods-detail'));
		$.get('/store/cart/add.do', goods, function(result){
			if (result == STATUS.SUCCESS){
				alert('添加成功');
			}else{
				alert('服务器忙，请稍后重试');
			}
		});
	});
	
	$('.add-comment, .append-comment').click(function(){
		var top = $(this).offset().top;
        var left = $(this).offset().left;
        var $div = $('#comment-div');
        var orderId = $(this).closest('.row').find('.order-id em:eq(0)').html();
		var goodsId = $(this).closest('.goods-detail').attr('id');

        $div.css('left', left - 90);
        $div.css('top', top);
        $div.attr('order-id', orderId);
        $div.attr('goods-id', goodsId);
        $div.show();
        
        if ($(this).hasClass('add-comment')){
            $div.addClass('add');
        }
        if ($(this).hasClass('append-comment')){
            $div.addClass('append');
        }
	});
	
	$('#discard').click(function(){
		var $div = $('#comment-div');
		$('#user-comment').val('');
        $div.removeClass('add');
        $div.removeClass('append');
        $div.hide();
	});
	
	$('#submit').click(function(){
		var $div = $('#comment-div');
		var orderId = $div.attr('order-id');
		var goodsId = $div.attr('goods-id');
		var username = $('#siteNav a:eq(0)').html();
		var comment = $('#user-comment').val();
        if ($div.hasClass('add')){
    		$.post('/store/order/comment.do',{
	    			orderId:orderId,
	    			goodsId:goodsId,
	    			username:username,
	    			comment:comment
    		    }, function(result){
    				if (result == STATUS.SUCCESS){
    					hideCommentFrame();
    					alert('评论成功');
    					window.location.reload();
    				}else{
    					alert('服务器忙，请稍后重试');
    				}
    		});
        }
        if ($div.hasClass('append')){
        	$.post('/store/order/appendComment.do',{
    			orderId:orderId,
    			goodsId:goodsId,
    			username:username,
    			comment:comment
		    }, function(result){
				if (result == STATUS.SUCCESS){
					hideCommentFrame();
					alert('评论成功');
					window.location.reload();
				}else{
					alert('服务器忙，请稍后重试');
				}
		});
        }
	});
})
function getGoodsInfo($td){
	var goods = {};
	goods.id = $td.attr('id');
	goods.name  = $td.find('.introduce')[0].innerText;
	goods.price  = $td.find('.td-price label')[1].innerText;
	return goods;
}
function hideCommentFrame(){
	var $div = $('#comment-div');
	$div.val('');
	$div.hide();
}