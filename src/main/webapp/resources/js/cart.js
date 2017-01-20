/**
 * Created by luxuanren on 2016/12/25.
 */
$(function() {

	updateDealBtn();
	$('.cut').each(function(){
		if ($(this).next().val() == 1){
			// remove hover effect
			$(this).removeClass('cut');
		}
		$(this).hover(function(){
			if ($(this).next().val() > 1){
				$(this).addClass('cut-hover');
			}
		},function(){
			$(this).removeClass('cut-hover');
		});
	});
	$('#ck-all').click(function(){
		 if ($(this).prop('checked') == true){
	        $('.ck').prop('checked',true);
	     }else {
	        $('.ck').prop('checked',false);
	        $('#deal').attr({'disabled':'disabled'});
	     }
		 $('#goods-sum').html($('.ck:checked').length);
		 updateDealBtn();
		 updateTotal();
	});
	$('.ck').click(function(){
		var sum = parseFloat($(this).closest('.item').find('.th-sum label:eq(1)').html());
		var total = parseFloat($('#sum').html());
		if ($(this).prop('checked') == true){
			$('#deal').removeAttr('disabled');
			$('#deal').css('background','#FF4400');
			total += sum;
		}else{
			if ($('.ck:checked').length == 0 ){
				$('#deal').attr({'disabled':'disabled'});
			}
			total -= sum;
		}
		$('#goods-sum').html($('.ck:checked').length);
		updateDealBtn();
		$('#sum').html(changeTwoDecimal(total).toFixed(2));
	});
	$('.add').click(function(){
		var $parent = $(this).parent();
		var num = parseInt($parent.find('input:eq(0)').val());
		$parent.find('input:eq(0)').attr('value',++num);
		var goodsId = $(this).closest('.item').attr('id');
		updateToDb($parent, goodsId, num, num - 1)
		if ( num > 1){
			$parent.find('.cut:eq(0)').addClass('cut');
		}
	});
	
	$('.cut').click(function(){
		var $parent = $(this).parent();
		if ( $parent.find('.amount:eq(0)').val() == 1){
			return;
		}
		var num = parseInt($parent.find('input:eq(0)').val());
		$parent.find('input:eq(0)').attr('value', --num);
		var goodsId = $(this).closest('.item').attr('id');
		updateToDb($parent, goodsId, num, num + 1)
		if ( $parent.find('.amount:eq(0)').val() == 1){
			$parent.find('.cut:eq(0)').removeClass('cut');
		}
	});
	$('.delete').click(function(){
		var $item = $(this).closest('.item');
		var goodsId = $item.attr('id');
		$.get('/store/cart/delete.do',{
			goodsId:goodsId
		}, function(data){
			if (data == STATUS.SUCCESS){
				$item.remove();
				updateTotal();
			}else{
				alert('服务器忙，请稍后重试。');
			}
		});
	})
	$('.mark').click(function(){
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
	$('#deal').click(function(){
		var tradeList = '[';
		$('.ck:checked').each(function(index){
			var tradeItem = {};
			var $parent = $(this).closest('.item');
			tradeItem.goodsId = $parent.attr('id');
			tradeItem.price = $parent.find('.th-price label:eq(1)').html();
			tradeItem.tradeNum = $parent.find('.th-amount input:eq(0)').val();
			tradeItem.sum = $parent.find('.th-sum label:eq(1)').html();
			tradeList += JSON.stringify(tradeItem) + ',';
		});
		tradeList = tradeList.replace(/,$/,']');
		$.get('/store/order/deal.do',{
			json:tradeList
		}, function(result){
			if (result == STATUS.SUCCESS){
				$('.ck:checked').each(function(){
					$(this).closest('.item').remove();
				})
				updateTotal();
				alert('交易成功');
			}else if (result == STATUS.INSUFFICIENT){
				alert('库存不足，请重新下单。');
			}else if ( result == STATUS.UN_LOGIN){
				window.location.href = "/store/login.do?target=/user/cart.do";
			}else{
				alert('服务器忙，请稍后重试。');
			}
		})
	});
})
function updateToDb($item, goodsId, num, originNum){
	$.get('/store/cart/update.do',{
		goodsId:goodsId,
		num:num
	}, function(data){
		if (data == STATUS.SUCCESS){
			var $parent = $item.closest('.item');
			var price = $parent.find('.th-price label:eq(1)').html();
			var sum = changeTwoDecimal(price)* num;
			$parent.find('.th-sum label:eq(1)').html(sum.toFixed(2) );
			updateTotal();
		}else{
			$parent.find('.amount:eq(0)').val(originNum);
			alert('服务器忙，请稍后重试。');
		}
	} );
}
function updateTotal(){
	var sum = 0.00;
	var num;
	$('.ck:checked').each(function(){
		num = parseFloat($(this).closest('.item').find('.th-sum label:eq(1)').html());
		sum += num;
	});
	$('#sum').html(sum.toFixed(2));
	$('#goods-sum').html($('.ck:checked').length);
}
function updateDealBtn(){
	if ($('.ck:checked').length > 0){
		$('#deal').removeAttr('disabled');
		$('#deal').css('background','#FF4400');
		$('#deal').addClass('deal-hover');
	}else{
		$('#deal').attr({'disabled':'disabled'});
		$('#deal').css('background','#6C6C6C');
		$('#deal').removeClass('deal-hover');
	}
}