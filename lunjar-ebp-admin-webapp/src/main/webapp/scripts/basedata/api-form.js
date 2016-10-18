var _backUrl = 'listb'; 
var _form;
var _btnSubmit;
jQuery(function($) {	
	
	_form = $('.formBox form');
	_btnSubmit = _form.find(':submit')
	
	
	
		_form.validate({
			ignore : ':hidden',//不校验隐藏元素
			
		submitHandler:function(f){//form jquery验证通过之后提交之前执行 
			
			var apiId = $("#apiId").val(); 
			
			$('input[type=submit]',$(f)).attr('disabled','disabled');			
			
			var url = apiId ? 'update' : 'add'
			var apiType=$("#apiType option:selected").val();
			var apiReqEncoding=$("#apiReqEncoding option:selected").val();
			var apiWay=$("#apiWay option:selected").val();
			var isPay=$("#isPay option:selected").val();
			var paramType=$("#paramType option:selected").val();
			var isNull=$("#isNull option:selected").val();
			_btnSubmit.text('处理中...').attr('disabled',true);
			$(f).ajaxSubmit({
				url:url,
				type:'POST',
				data:{'apiType':apiType,
					'apiReqEncoding':apiReqEncoding,
					'apiWay':apiWay,
					'isPay':isPay,
					'paramType':paramType,
					'isNull':isNull
					},
				success:function(result){
					callback(apiId,result)
					callback1(id,result)
				}
			});
		}		
	});
});

$(function(){
	$("#btnAdd").click(function(){
		$('table').append(
				' <tr>'+
					'<td>'+
				'<input name="paramName" id="paramName" size="30"   class="paramName" type="text" value="$!{apiParam.paramName}"/>'+
					'</td>'+
					'<td>'+
						'<select id="paramType" >'+
						'<option value="1">字符</option>'+
						'<option value="2">对象</option>'+
						'</select >	'+
					'</td>'+
					'<td>'+
						'<select id="isNull" >'+
						'<option value="1">必填</option>'+
						'<option value="2">不必</option>'+
						'</select >'+	
					'</td>'+
					'<td><input name="paramDefalut" id="paramDefalut" size="30"   class="paramDefalut" type="text" value="$!{apiParam.paramDefalut}"/></td>'+
					'<td>'+
						'<input name="paramDescription" id="paramDescription" size="30"   class="paramDescription" type="text" value="$!{apiParam.paramDescription}"/>'+
					'</td>'+
				'</tr>');
	});
});

/**
 * 
 * @param  result {object} 			 
 * 			<li>title {string}</li>
 * 			<li>type {string} success,error</li> 
 */
function callback(apiId,result){
	_btnSubmit.text('提交').removeAttr('disabled');
	if(App.isSubmitSuccess(result)){
		var msg = null;
		var autohide = true;
		if(apiId){		
			msg = "修改成功！";
			
		}else{ 	
	
			msg = "添加成功！";
			
		}
			
		Core.PopUtil.alert(msg,{autohide:autohide,callback:function(){
			Core.UrlUtil.redirect(_backUrl);
		}});
	}
} 
function callback2(id,result){
	_btnSubmit.text('提交').removeAttr('disabled');
	if(App.isSubmitSuccess(result)){
		var msg = null;
		var autohide = true;
		if(id){		
			msg = "修改成功！";
			
		}else{ 	
	
			msg = "添加成功！";
			
		}
			
		Core.PopUtil.alert(msg,{autohide:autohide,callback:function(){
			Core.UrlUtil.redirect(_backUrl);
		}});
	}
} 
