/**
* @include "../admin.js"
 */

jQuery(function(){
	var cookieAccount = Core.CookieUtil.readCookie('adminLogin');
	if(cookieAccount){
		$('#txtAccount').val(cookieAccount);
	}
	
	var cookiePass = Core.CookieUtil.readCookie('adminPass');
	if(cookiePass){
		$('#txtPwd').val(cookiePass);
	}
	
	var btnSubmit = $('#btnSubmit');
	var formLogin = $('#formLogin');
	
	initValidateCode();
	
	formLogin.validate({		
		submitHandler:function(f){//form jquery验证通过之后提交之前执行 
			btnSubmit.text('登录中...').attr('disabled',true);
			var formData  = Core.FormUtils.getFormValues(formLogin);	
			
			var pwd = $('#txtPwd').val();
			if(cookiePass){
				
			}else{
				pwd = Core.DigestUtils.sha1(pwd);
			}
			
			console.log('pwd',pwd);
			
			var random = new Date().getTime();
			formData.random = random;
			
			var loginSign = Core.DigestUtils.md5(
				Core.DigestUtils.md5(
					formData.account + pwd
				) + random
			);
			
			formData.sign = loginSign;
					
			AncunApiClient.postForm('login',{
				data:formData,
				success:function(response){
					//var data = response.data;
					//Core.CookieUtil.createCookie('demo_tk',data.accessid);	
					if(jQuery("#rememberMe").hasClass('checked')){
						Core.CookieUtil.createCookie('adminLogin',formData.account);
						Core.CookieUtil.createCookie('adminPass',pwd);
					}
					location.href = '/trade/list';
				},
				fail:function(code,msg){

					//  ,_2060501("登录失败，用户名密码错误")
					//	,_2060502("登录失败，账户被锁定")
					//	,_2060503("登录失败，账户被冻结")
					//	,_2060504("初始登录，请重置密码")
					//	,_2060505:验证码错误(连续访问没带验证码)
					//	,_2060506("登录失败大于3次")
					// 1000122：验证码不正确
					//code="506";
					;
					btnSubmit.text('登 录').removeAttr('disabled');
					if( code=="2060502" || code=="2060503"){
						//Core.PopUtil.warn(msg + "！");
						jQuery.alert({
							type: 'error',
							text: msg,
							time: 3000
						});
						window.location.reload();
						//$("tr_validateCode").show();
					}else if(code=="2060505"){
						//loadValidateCode();
						//Core.PopUtil.warn(msg + "！");
						window.location.reload();
					}else if(code=="1000122"){
						//loadValidateCode();
						//Core.PopUtil.warn(msg + "！");
						jQuery.message({
							type: 'error',
							text: msg,
							time: 3000
						});
						window.location.reload();
					}else if(code=="2060504"){
						openWinUpdatePwd(formData["account"]);
					}else if(code=="2060501"){
						//Core.PopUtil.warn(msg + "！");
						jQuery.message({
							type: 'error',
							text: msg,
							time: 3000
						});
						window.location.reload();
					}else if(code=="2060506"){
						//Core.PopUtil.warn(msg + "！");
//						$("#txtPwd").css.style.marginBottom="0px";
						document.getElementById("txtPwd").style.marginBottom="";
						$("#tr_validateCode").show();
					}else{
						//Core.PopUtil.warn(msg + "！");
						jQuery.message({
							type: 'error',
							text: msg,
							time: 3000
						});
						window.location.reload();
					}

				},
				complete:function(response, status){
					//console.log(response);
				}
			});
		}
	});
});

var _validateCodeImgUrl=App.getApiUrl('/register/captcha?tmp=');
function initValidateCode(){
	_validateCodeImg = $('#codeSrc');
	loadValidateCode();							
	_validateCodeImg.click(function(){
		loadValidateCode();
	});
}
function loadValidateCode(){
	_validateCodeImg.attr('src',_validateCodeImgUrl+new Date().getTime());
}

//修改密码
var _winUpdatePwd,_formUpdatePwd;
function openWinUpdatePwd(userAccount){
	var html =  '<form class="winFormBox"><table><tr>' +
		//'<th>旧密码：</th><td>' +
		//'<input name="pwd"  class="required {rangelength:[6,20]}" type="password" value="" /><em class="requiredTag">*</em>' +
		//'</td></tr><tr>' +
		'<th>新密码：</th><td>' +
		'<input name="newPwd"  id="newPwd" class="required {rangelength:[6,20]}" type="password" value=""/><em class="requiredTag">*</em>' +
		'</td></tr><tr>' +
		'<th>确认密码：</th><td>' +
		'<input name="confimPwd"  class="required {rangelength:[6,20]}" type="password" value="" equalTo="#newPwd"/><em class="requiredTag">*</em>' +
		'</td></tr><tr><th></th><td>' +
		'<input type="button" value="提 交" class="btn mt5" onclick="updatePwd(\''+userAccount+'\');"/>' +
		'</td></tr></table></form>'

	//if(!_winUpdatePwd){
	_winUpdatePwd = new Boxy(html,{
		width:350,
		height:180,
		unloadOnHide : false,
		title : '修改密码',
		afterShow: function(){
			if(!_formUpdatePwd){
				_formUpdatePwd = this.boxy.find('form');
				App.initCls(_formUpdatePwd);
				_formUpdatePwd.validate();
			}
			_formUpdatePwd.resetForm().find('font.success').remove();
		}
	});
	//}
	_winUpdatePwd.show();
}

function updatePwd(userAccount){
	var regx=/^[0-9a-zA-Z]*$/;
	var pwd=$("#pwd").val();
	if(!regx.test(pwd)){
		Core.PopUtil.warn("密码由数字字母组成！");
		return;
	}

	$.ajax({
		url : "updatePwdByFirstLogin",
		type : 'post',
		data:{
			newPwd:$("#newPwd").val(),
			userAccount:userAccount
		},
		success : function(result) {
			if (App.isSubmitSuccess(result)) {
				Core.PopUtil.alert('操作成功,请登录！', {
					autohide : true,
					callback : function() {
						Core.UrlUtil.redirect("login");
					}
				});
			}
		}
	});

}
$.extend({
	message : function(config){
            var $tpl = null;
           var conf = {
           text: config.text
           };
          switch(config.type) {
            case 'error':
              conf.icon = 'icon-warnfill';
              conf.style = 'z-danger';
              break;
            case 'info':
              conf.icon = 'icon-infomsg';
              conf.style = 'z-primary';
              break;
            default :
              conf.icon = 'icon-successfill';
              conf.style = 'z-success';
              break;
          }
          var len = $('body').find('.alert').length;
          if(len==1) {
            $('.alert').remove();
            clearTimeout(timer);
          }
          var strnbsp = " ";//空格
           $tpl = $('<div class="alert">'
                    +'<div class="alert-inner">'
                      +'<i class="'+conf.icon+''+strnbsp+''+conf.style+'"></i>'
                      +'<span class="alert-text">'
                        +conf.text
                      +'</span>'
                    +'</div>'
                  +'</div>');
            $('body').append($tpl);
            $tpl.css({
            marginLeft: -$tpl.outerWidth() / 2
            }).addClass('in');
            console.log($tpl.outerWidth());
            timer = setTimeout(function() {
              if(!$tpl) {
                return;
              }
              $tpl.removeClass('in');
              setTimeout(function() {
                $tpl.remove();
                $tpl = null;
              }, 300);
            }, config.time || 1500);
	}
})
