/**
 * Created by luxuanren on 2016/12/25.
 */
$(function() {

	$('#deal').attr({'disabled':'disabled'});
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
	        $('#deal').removeAttr('disabled');
	     }else {
	        $('.ck').prop('checked',false);
	        $('#deal').attr({'disabled':'disabled'});
	     }
		 $('#goods-sum').html($('.ck:checked').length);
		 updateTotal();
	});
	$('.ck').click(function(){
		var sum = parseFloat($(this).closest('.item').find('.th-sum label:eq(1)').html());
		var total = parseFloat($('#sum').html());
		if ($(this).prop('checked') == true){
			$('#deal').removeAttr('disabled');
			total += sum;
		}else{
			if ($('.ck:checked').length == 0 ){
				$('#deal').attr({'disabled':'disabled'});
			}
			total -= sum;
		}
		$('#goods-sum').html($('.ck:checked').length);
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
})
function updateToDb($item, goodsId, num, originNum){
	$.get('/store/cart/update.do',{
		goodsId:goodsId,
		num:num
	}, function(data){
		if (data == STATUS.SUCCESS){
			var $parent = $item.closest('.item');
			var price = $parent.find('.th-price label:eq(1)').html();
			$parent.find('.th-sum label:eq(1)').html(changeTwoDecimal(price).toFixed(2) * num);
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
}