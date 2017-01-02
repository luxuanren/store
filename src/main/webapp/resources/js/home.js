/**
 * Created by exuanlu on 9/27/2016.
 */
$(function() {

	initPage();
})

function initPage() {
	
	var $user = $('#user');
	var name = new String($user.html());
	if (name.length == 0) {
		$user.html("请登录");
		$('#link').attr("href","/store/login.do");
	} else {
		$('#user').toggleClass('unlogin');
	}
	// initialize userBar
	$('#cart,#mark').click(function(){
		$(this).removeClass('highlight');
	})
	$('#order,#cart,#mark').click(function(){
		if($('#user').attr('userId').length == 0){
			var target = $(this).find('a:first').attr('href');
			window.location.href = "/store/login.do?target=" + target;
			return false;
		}
	})
	
	// initialize add button
	$('#content label[name=amount][value=0]').each(function(){
		$(this).parent().parent().find('.add').attr("disabled", "disabled");
	})
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
		
	})
	
}
function getGoodsInfo($td){
	var goods = {};
	goods.id = $td.parent().attr('id');
	goods.name  = $td.find('label[name=name]')[0].innerText;
	goods.title  = $td.find('label[name=title]')[0].innerText;
	goods.price  = $td.find('label[name=price]')[0].innerText;
	return goods;
}
