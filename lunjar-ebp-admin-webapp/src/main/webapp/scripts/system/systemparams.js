/**
* @include "../admin.js"
* @include "D:/works/workspace/ancun-bps-webui/_component/ancun/core.js"
 */
 
jQuery(function(){

	$('a.linkEdit').click(function(){
		var me = $(this);
		me.hide();
		
		var tr = me.parents('tr');
		
		tr.find('span.spanText').hide();		
		tr.find('input.inputValue').show();		
		tr.find('input.btnSave').show();	
		tr.find('input.btnCancel').show();	
		
	});
	
	$('input.btnSave').click(function(){
		var me = $(this);		
		
		var tr = me.parents('tr');
		
		var data = Core.FormUtils.getFormValues(tr);
		Core.appendLoadingIcon(me);
		$.post('update' +App.ajaxActionSuffix,data,function(v){
			Core.removeLoadingIcon(me);
			if(App.isSubmitSuccess(v)){
				Core.PopUtil.success('修改成功！');
				
				var inputs = tr.find('input.inputValue');
				inputs.hide();
				me.hide();	
				tr.find('input.btnCancel').hide();	
				tr.find('span.spanText').show();
				tr.find('a.linkEdit').show();
				
				$.each(inputs,function(){
					var me = $(this);
					me.prev().text(me.val());
				});
			}
		});	
	});
	
	$('input.btnCancel').click(function(){
		var me = $(this);
		me.hide();
		
		var tr = me.parents('tr');
		
		tr.find('input.inputValue').hide();
		tr.find('input.btnSave').hide();
		tr.find('span.spanText').show();		
		tr.find('a.linkEdit').show();
	});
});