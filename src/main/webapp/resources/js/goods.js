$(function () {
	$('#add-btn').click(function(){
		var goods = getGoodsInfo($('#goods-info'));
		$.post('/store/cart/add.do', goods, function(result){
			if (result == STATUS.SUCCESS){
				alert('添加成功');
			}else{
				alert('服务器忙，请稍后重试');
			}
		});

	});
	$('#mark-btn').click(function(){
		var goodsId = $('#goods-info').attr('goodsId');
		$.post('/store/mark/add.do', {
			goodsId:goodsId
		}, function(result){
			if (result == STATUS.SUCCESS){
				alert('添加成功');
			}else{
				alert('服务器忙，请稍后重试');
			}
		});

	});
});

function getGoodsInfo($td){
	var goods = {};
	goods.id = $td.attr('goodsId');
	goods.name  = $td.find('#name label')[0].innerText;
	goods.price  = $td.find('#price em')[0].innerText;
	return goods;
}