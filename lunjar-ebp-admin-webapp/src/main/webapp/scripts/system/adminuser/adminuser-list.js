/**
* @include "../../admin.js"
 */

jQuery(function($) {	
	$('.resetPwd').click(resetPwd);
	$('.delete').click(remove);
	$('.updateStatus').click(updateStatus);
});

function resetPwd(){	
	var selected = getSelectedRow(this);
	var $this = $(this);
	if(selected.userId){		
		var text=selected.userName;
		Core.PopUtil.confirm('确定重置用户 <b style="color:#ff6600;">'+text+'</b> 密码?',function(){
			Core.appendLoadingIcon($this);
			$.post(
				'resetPwd' + App.ajaxActionSuffix,
				{id:selected.userId,userName:text},
				function(v){
					Core.removeLoadingIcon($this);
					if(App.isSubmitSuccess(v)){
						Core.PopUtil.alert(
							'用户 <b style="color:#ff6600;">'+text+'</b> 密码重置成功!'
							,{autohide:true}
						);	
					}
				}
			);			
		});
	}	
}

function remove(){
	var selected = getSelectedRow(this);
	if(selected.userId){
		var text=selected.userName;	
		var tr = selected.tr;
		Core.PopUtil.confirm('确定删除用户 <b style="color:#ff6600;">'+text+'</b> ?',function(){			
			tr.html('<td colspan="10" style="text-align:left;"><div class="common_loading">系统正在处理，请稍侯......</div></td>')
			$.post(
				'delete' + App.ajaxActionSuffix,
				{id:selected.userId,userName:text},
				function(v){					
					if(App.isSubmitSuccess(v)){
						tr.find('td').html('<div class="msg-success">删除成功!</div>');
						setTimeout(function(){
							tr.find('div').slideUp('normal',function(){tr.remove();});
						},500);					
					}
				}
			);				
		});
	}	
}

function updateStatus(){
	var selected = getSelectedRow(this);
	var $this = $(this);
	if(selected.userId){
		var opname = $this.text();
		var text=selected.userName;		
		Core.PopUtil.confirm('确定'+opname+'用户 <b style="color:#ff6600;">'+text+'</b> ?',function(){
			var tr = selected.tr;
			var statusHidden = tr.find('input[name=status]');
			Core.appendLoadingIcon($this);
			$.post(
				'updateStatus' + App.ajaxActionSuffix,
				{id:selected.userId,userName:text,status:statusHidden.val()},
				function(v){
					Core.removeLoadingIcon($this);
					if(App.isSubmitSuccess(v)){
						//Core.PopUtil.alert('用户 <b style="color:#ff6600;">'+text+'</b> '+opname+'成功!',{
							//autohide:true,
							//callback:function(){
							var status = UserStatus['_' + statusHidden.val()];							
							tr.find('.statusText span').text(status.text).css('color',status.color);
							
							statusHidden.val(status.afterOperateValue);
							$this.text(status.afterOperateText);
						//}});	
					}
				}
			);				
		});
	}
}

var UserStatus = {
	_1:{value:'1',text:'正常',color:'green',afterOperateValue:'2',afterOperateText:'冻结'},
	_2:{value:'2',text:'冻结',color:'#ff6600',afterOperateValue:'1',afterOperateText:'解冻'}
}

function getSelectedRow(e){
	var tr = $(e).parents('tr');
	//console.log(tr);
	var userId = tr.find('input[name=userId]').val();
	var userName = tr.find('.userName').text();
	
	return {
		tr:tr,
		userId:userId,
		userName:userName
	}
}