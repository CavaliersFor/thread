/**
* @include "core.js"
*/

Core.Validator={};

/**
 * 错误信息显示在元素后面
 * @type 
 */
Core.Validator.Options1 = {
	errorElement : 'span',
	errorClass:'error1',
 	errorPlacement: function (error, element) { //指定错误信息位置
		var box = element.parent();
		//console.log(error);
		error.appendTo(box);
		box.find(this.errorElement + '.success').remove();	
	},
 	success: function (label) {
 		var me = this;
 		label.html('&nbsp;').addClass('success').removeClass(me.errorClass);
	}
};

/**
 * 错误信息浮动显示
 * @type 
 */
Core.Validator.Options2 = {
	errorElement : 'div',
	errorClass:'error2',
 	errorPlacement: function (error, element) { //指定错误信息位置	 		
 		var me = this; 		
 		var errorId = error.attr('id');
 		
 		//var errorText = error.html();
		//console.log(errorText);
		var errorObj = $('#'+errorId);
		//console.log(errorObj.size());
		if(errorObj.size()>0){
			errorObj.remove();	
		} 		
		var elementIdOrName = element.attr('id');
		if(!elementIdOrName){
			elementIdOrName = element.attr('name');
		}		
		if(error.html()){
			if(element.is(':focus')){
				var eOffset = element.offset();
				error.css({position:'absolute',left: eOffset.left , top: eOffset.top + element.outerHeight()+1}).appendTo('body');
			}
			//if($('.'+me.errorClass+'[for="'+elementIdOrName+'"]').size() == 0){		
			var dom = element.get(0);
			var tagName = dom.tagName;							
			if(tagName == 'SELECT'){
				error.html('该数据项必须选择');
				var selecterror = $(me.errorElement + '.select'+me.errorClass+'[for="'+elementIdOrName+'"]');
				if(selecterror.size()==0){
				 	selecterror =$('<'+me.errorElement +' for="'+elementIdOrName+'" class="select'+me.errorClass+'" ></'+me.errorElement +'>')	;
				 	element.css('margin-right','3px').css('float','left').after(selecterror);
				 	
				 	//bindMouseEvent(selecterror);
				 	bindMouseEvent(element,selecterror);
				}
			}else{
				if(dom.type=="checkbox"){
					element = element.parent();
					element.addClass('checkbox'+me.errorClass).addClass(errorId);
				}
				bindMouseEvent(element);
			}
		}	
		function bindMouseEvent(element,selecterror){
			errorObj = $('#'+errorId);
			element.one('mouseover',mouseover).bind('mouseout',function(){
				errorObj.hide();
			});
			
			if(selecterror){
				selecterror.one('mouseover',mouseover).bind('mouseout',function(){
					errorObj.hide();
				});
			}			
		};
		
		function mouseover(){
			var eOffset = element.offset();
			if($('#'+errorId).size() == 0){
				error.css({position:'absolute'}).appendTo('body');
				errorObj = $('#'+errorId);
			}
			errorObj.html(error.html()).show().css({left: eOffset.left , top: eOffset.top + element.outerHeight()+1});	
		}
	},
 	success: function (label) { 
 		var me = this;
 		
 		var errorId = label.attr('id'); 		
 		var error = $('#' + errorId);
 	
		//var elementIdOrName = label.attr('for');
 		var elementIdOrName = errorId.replace('-error',''); 	
 		error.remove();
		var selecterror = $(me.errorElement + '.select'+me.errorClass+'[for="'+elementIdOrName+'"]');		
		if(selecterror.size()>0){
		 	selecterror.remove();
		}else{
			//console.log($(me.errorElement + '.'+me.errorClass+'[for="'+elementIdOrName+'"]'));
			//$(me.errorElement + '.'+me.errorClass+'[for="'+elementIdOrName+'"]').remove();
			var checkbox_box = $('.' + elementIdOrName + '-error');		
			//console.log(checkbox_box);
			if(checkbox_box.size()>0){
				checkbox_box.removeClass('checkbox'+me.errorClass);
				checkbox_box.unbind('mouseover mouseout');
			}else{
				var element = $('[name="'+elementIdOrName+'"]');
				if(element.size() == 0){
					element = $('#'+elementIdOrName);
				}
				element.unbind('mouseover mouseout');
			}	
		}
	}
	
};

////默认错误信息浮动
//if($.validator){	
//	$.validator.setDefaults(Core.Validator.Options2);
//}

//自定义校验规则
// 验证值必须大于特定值(不能等于)
//<input class="a b" greaterThan="0"/>
//<input class="{greaterThan:0}"/>
jQuery.validator.addMethod("greaterThan", function(value, element, param) {
    return value > param;
}, $.validator.format("该数据项必须大于{0}"));

//只能包括中文字、英文字母、数字和下划线
 jQuery.validator.addMethod("stringCheck", function(value, element) {
     return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
 }, "只能包括中文字、英文字母、数字和下划线");

 // 中文字两个字节
 jQuery.validator.addMethod("byteRangeLength", function(value, element, param) {
     var length = value.length;
     for(var i = 0; i < value.length; i++){
         if(value.charCodeAt(i) > 127){
         length++;
        }
    }
    return this.optional(element) || ( length >= param[0] && length <= param[1] );
 }, "请确保输入的值在{1}-{2}个字节之间(一个中文字算2个字节)");

 // 身份证号码验证
 jQuery.validator.addMethod("idCardNo", function(value, element) {
     //return this.optional(element) || isIdCardNo(value);
 	var length = value.length;
 	var reg = /^\d{15}(\d{2}(\d|[A-Z]|[a-z]))?$/;
 	return this.optional(element) ||(length > 0 && reg.test(value));;
 }, "身份证号码不正确");

 // 手机号码验证
 jQuery.validator.addMethod("mobile", function(value, element) {
     var length = value.length;
     var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
     return this.optional(element) || (length == 11 && reg.test(value));
 }, "手机号码不正确");

 // 电话号码验证
 jQuery.validator.addMethod("phone", function(value, element) {
     var reg = /^([0-9]{2}-)?(0?[0-9]{2,3}\-)?[1-9]?[0-9]{7}$/;
     return this.optional(element) || (reg.test(value));
 }, "电话号码格式不正确");

 // 联系电话(手机/电话皆可)验证
 jQuery.validator.addMethod("phoneOrMobile", function(value,element) {
     var length = value.length;
     var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
     var tel = /^([0-9]{2}-)?(0?[0-9]{2,3}\-)?[1-9]?[0-9]{7}$/;
     return this.optional(element) || (tel.test(value) || mobile.test(value));

 }, "联系电话不正确");

 // 邮政编码验证
 jQuery.validator.addMethod("postCode", function(value, element) {
    var reg = /^[0-9]{6}$/;
    return this.optional(element) || (reg.test(value));
 }, "邮政编码格式不正确");

  //附件
 jQuery.validator.addMethod("accessory", function(value, element) {
    var reg = /\.(doc|docx|xls|xlsx|ppt|pdf|txt|rar|zip|jpg|jpeg|gif|bmp|png)$/i;
    return this.optional(element) || (reg.test(value));
 }, "禁止上传该格式的附件");

  //图片
 jQuery.validator.addMethod("image", function(value, element) {
    var reg = /\.(jpg|jpeg|gif|png)$/i;
    return this.optional(element) || (reg.test(value));
 }, "请上传正确的图片文件,格式为:(jpg|jpeg|gif|png)");

  //正则
 jQuery.validator.addMethod("regex", function(value, element, param) {
    return this.optional(element) || (param.test(value));
 }, "格式不正确");

  //中文姓名
 jQuery.validator.addMethod("username", function(value, element) {
    var reg = /^[\u4e00-\u9fa5]{2,4}$/;
    return this.optional(element) || (reg.test(value));
 }, "格式不正确,必须是由2-4位中文组成");

  //企业名称
 jQuery.validator.addMethod("companyname", function(value, element) {
    var reg = /^[a-zA-Z0-9_\u4e00-\u9fa5]{4,40}$/;
    return this.optional(element) || (reg.test(value));
 }, "格式不正确,至少包含4个中文字符");

  //只能数字和英文字母
 jQuery.validator.addMethod("onlyEnglishAndNumber", function(value, element) {
     return this.optional(element) || /^([A-Z]|[a-z]|[\d])*$/.test(value);
 }, "格式不正确,不能含有中文或非法字符");

   //只能英文
 jQuery.validator.addMethod("onlyEnglish", function(value, element) {
 	 var iftrue = (/^([A-Za-z]*)\.*\ *[A-Za-z]*$/.test(value));
     return this.optional(element) || iftrue;
 }, "格式不正确,不能含有中文或数字");

  //只能数字
 jQuery.validator.addMethod("onlyNumber", function(value, element) {
     return this.optional(element) || /^([\d])*$/.test(value);
 }, "只能是数字");
 
  //支付宝
 jQuery.validator.addMethod("alipay", function(value, element) {
 	var f1 = $.validator.methods["mobile"];
 	var f2 = $.validator.methods["email"];
 	value = value.replace(/\r/g, "");
 	return this.optional(element) || f1.call(this, value, element) || f2.call(this, value, element)
     //return this.optional(element) || $.validator.m this.email(value, element) || this.mobile(value, element);
 }, "支付宝格式不正确");
 
   //邮箱或者手机号
 jQuery.validator.addMethod("emailOrMobile", function(value, element) {
 	var f1 = $.validator.methods["mobile"];
 	var f2 = $.validator.methods["email"];
 	value = value.replace(/\r/g, "");
 	return this.optional(element) || f1.call(this, value, element) || f2.call(this, value, element)
     //return this.optional(element) || $.validator.m this.email(value, element) || this.mobile(value, element);
 }, "账号必须为手机号或邮箱！");
 
   //QQ
 jQuery.validator.addMethod("qq", function(value, element) {
 	var f1 = $.validator.methods["digits"];
 	var f2 = $.validator.methods["email"];
 	value = value.replace(/\r/g, "");
 	return this.optional(element) || f1.call(this, value, element) || f2.call(this, value, element)
     //return this.optional(element) || $.validator.m this.email(value, element) || this.mobile(value, element);
 }, "QQ格式不正确");

    //MSN
 jQuery.validator.addMethod("msn", function(value, element) {
 	var f1 = $.validator.methods["email"];
 	value = value.replace(/\r/g, "");
 	return this.optional(element) || f1.call(this, value, element)
     //return this.optional(element) || $.validator.m this.email(value, element) || this.mobile(value, element);
 }, "MSN格式不正确");

 jQuery.validator.addMethod("goodsPrice", function(value, element) {
		theregex = /^(\d+)([.]\d{1,2})?$/;
	    return (parseFloat(value) > parseFloat('0') && theregex.test(value));
	},'必须为大于0的数字,且只能保留两位小数');
	
jQuery.validator.addMethod("regionCode", function(value, element) {
		theregex = /[0-9]{6}/;
	    return (parseFloat(value) > parseFloat('0') && theregex.test(value));
	},'地区代码不正确,正确的代码为6为整数');
// 密码
jQuery.validator.addMethod("pwdRegexzs", function(value, element) {
			regex = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;
			return this.optional(element) || (regex.test(value));
		}, '密码需为6-20个字符,由字母、数字和符号组成。');
		
//正整数校验规则
jQuery.validator.addMethod("pwdRegex", function(value, element) {
			regex = /^\S{6,20}$/;
			return this.optional(element) || (regex.test(value));
		}, '密码需为6-20个字符,由字母、数字和符号组成。');
		
// 复杂密码
jQuery.validator.addMethod("pwdComplexRegex", function(value, element) {
	        var iftrue = ((value.match(/[\d]/) && value.match(/[A-Za-z]/) && value.match(/[~!@#$%^&*()_+.;'<>,^]/)) || (value.match(/[\d]/) && value.match(/[A-Za-z]/)) || (value.match(/[\d]/)  && value.match(/[~!@#$%^&*()_+.;'<>,^]/)))
			return this.optional(element) || iftrue;
		}, '密码需为6-20个字符,由字母、数字和符号组成。');


// 网址
jQuery.validator.addMethod("website", function(value, element) {
			regex = new RegExp('^[A-Za-z]+://[A-Za-z0-9-_]+\\.[A-Za-z0-9-_%&\?\/.=]+$'); 
			return this.optional(element) || (regex.test(value));
		}, '请输入正确的网址。');		
		
// 邮箱验证
jQuery.validator.addMethod("vEmail", function(value, element) {
	regex = new RegExp('^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+'); 
	return this.optional(element) || (regex.test(value));
}, '请输入正确的邮箱格式。');		
		
//正整数校验规则
jQuery.validator.addMethod("positiveinteger", function(value, element) {
   var aint=parseInt(value);	
    return aint>0&& (aint+"")==value;   
  }, "请输入一个正整数");   