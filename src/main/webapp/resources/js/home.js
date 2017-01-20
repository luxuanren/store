/**
 * Created by exuanlu on 9/27/2016.
 */
$(function() {

	var $user = $('#user');
	var name = new String($user.html());
	if (name.length == 0) {
		$user.html("请登录");
		$('#user').attr("href","/store/login.do");
	} else {
		$('#user').toggleClass('unlogin');
	}

	// initialize add button
	$('.add').click(function(){
		if($('#user').attr('userId').length == 0){
			window.location.href = "/store/login.do";
			return false;
		}else{
			var goods = getGoodsInfo($(this).parent().parent());
			$.get('/store/cart/add.do', goods, function(data){
				if (data == STATUS.UN_LOGIN){
					window.location.href = "/store/login.do";
				}else{
					alert('添加成功！');
				}
			})
		}
	});
	
	$('.add-mark').click(function(){
		var $item = $(this).closest('.item');
		var goodsId = $item.attr('id');
		$.get('/store/mark/add.do',{
			goodsId:goodsId
		}, function(data){
			if (data == STATUS.SUCCESS){
				alert('添加成功');
			}else{
				alert('服务器忙，请稍后重试。');
			}
		});
	});
	$('.delete-mark').click(function(){
		var $item = $(this).closest('.item');
		var goodsId = $item.attr('id');
		$.get('/store/mark/delete.do',{
			goodsId:goodsId
		}, function(data){
			if (data == STATUS.SUCCESS){
				$item.remove();
				alert('删除成功');
			}else{
				alert('服务器忙，请稍后重试。');
			}
		});
	});
	
	$('label[name=amount]').each(function(){
		if ( $(this).attr('value') == 0){
			$parent = $(this).closest('.display');
			$parent.css('background','#E8E8E8');
			var addBtn = $parent.find('.add:eq(0)');
			addBtn.removeClass('add').addClass('add-disable').unbind('click');
		}
	});
})

function getGoodsInfo($td){
	var goods = {};
	goods.id = $td.parent().attr('id');
	goods.name  = $td.find('.goods-name')[0].innerText;
	goods.title  = $td.find('label[name=title]')[0].innerText;
	goods.price  = $td.find('label[name=price]')[0].innerText;
	return goods;
}
