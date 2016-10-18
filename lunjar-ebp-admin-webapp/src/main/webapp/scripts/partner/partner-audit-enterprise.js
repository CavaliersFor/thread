jQuery(function(){

	var reasonTxt = $('#reason');
	$('#pass').click(function(){
		reasonTxt.attr('disabled','disabled');
		reasonTxt.hide();
	})
	$('#unpass').click(function(){
		reasonTxt.removeAttr('disabled');
		reasonTxt.show();
	})
	$('#pass').click();
});