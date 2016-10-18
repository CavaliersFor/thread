/**
 * 
* @include "D:/works/workspace/ancun-bps-webui/_component/ancun/core.js"
*/

// 控制错误信息的样式
if ($.validator) {
	$.validator.setDefaults(Core.Validator.Options2);
}

App.getApiUrl=function(functionUrl){
	return App.getCurrentSystemUrl(functionUrl) ;
};

var Config={}

jQuery(function(){
	//if(!_isNotInitCls){
		//初始化所有表单样式
		App.initCls($('body'));
	//}
});
