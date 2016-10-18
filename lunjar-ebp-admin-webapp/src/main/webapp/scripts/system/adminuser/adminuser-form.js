/**
* @include "../../admin.js"
 */

var _backUrl = 'listb'; //在vm中赋值,默认listb
var _form;
var _btnSubmit;
jQuery(function($) {	
	var msgAccount = "2-20位英文字母、数字、中文或下划线 ";
	_form = $('.formBox form');
	_btnSubmit = _form.find(':submit')
	
	
	//如果是修改的话账号和工号不可编辑
	if($("#userId").val()){
		$('#account').attr("disabled","true");
		$('#workNo').attr("disabled","true");
	}
	_form.validate({
		ignore : ':hidden',//不校验隐藏元素
		rules:{	
			'account':{
				remote: {
				    url: "account-check", 
				    type: "get",
				    data: {
				        'id': function() {
				            return $("#userId").val();
				        }
				    }
				}
			},
			'workNo':{
				remote: {
				    url: "workno-check" +App.ajaxActionSuffix, 
				    type: "get",
				    data: {
				        'id': function() {
				            return $("#userId").val();
				        }
				    }
				}
			}
		},
		messages:{
			'account':{remote:'该账号已经存在,请使用其他账号',account:msgAccount,rangelength:msgAccount},
			'workNo':{remote:'该工号已经存在,请使用其他工号'}	
		},
		submitHandler:function(f){//form jquery验证通过之后提交之前执行 			
			var userId = $("#userId").val();
			
			$('input[type=submit]',$(f)).attr('disabled','disabled');			
			
			var url = userId ? 'update' : 'add'
			_btnSubmit.text('处理中...').attr('disabled',true);
			$(f).ajaxSubmit({
				url:url,
				type:'POST',
				success:function(result){
					callback(userId,result)
				}
			});
		}		
	});
});

/**
 * 
 * @param  result {object} 			 
 * 			<li>title {string}</li>
 * 			<li>type {string} success,error</li> 
 */
function callback(userId,result){
	_btnSubmit.text('提交').removeAttr('disabled');
	if(App.isSubmitSuccess(result)){
		var msg = null;
		var autohide = true;
		if(userId){		
			msg = "修改成功！";
			//autohide = true;
		}else{
//			msg = '<span style="color:green;font-size:14px;font-weight:bold;">添加成功!</span> 您可以' +
//				'<ul style="margin-top:5px;"><li> - <a href="listb.htm">返回列表</a></li>' +
//				'<li> - <a href="add.htm">继续添加</a></li>' +
//				'<li> - <a href="d'+result.data+'.htm">查看添加的用户</a></li></ul>';	
			msg = "添加成功！";
			
		}
			
		Core.PopUtil.alert(msg,{autohide:autohide,callback:function(){
			Core.UrlUtil.redirect(_backUrl);
		}});
	}
} 

