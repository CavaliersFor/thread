/**
 * Core
 */
var Core = {
 	/**
 	 * 获取文件的后缀名,包括".",比如  getFileExtension('aa.txt')='.txt',
 	 * @param {String} fileName
 	 */
 	getFileExtension:function(fileName){
 		var returnV = '';
 		var tmps = fileName.split('.'); 		
 		if(tmps.length > 1){
 			returnV = '.' + tmps[tmps.length-1];
 		}
 		return returnV;
 	},
	
	/**
	 * 键盘回车触发
	 * @param {} fn
	 */
	keyEnterSubmit : function (fn) {
		var e = Core.getEvent();
		if (e.keyCode == 13) {
			fn.call();
		}
	},			
	showImageWithMaxSize:function(imgJQueryObject,src,maxSize){				
		imgJQueryObject.attr('style','');
		imgJQueryObject.attr('src',src);
		//imgJQueryObject.attr('width','');	
		imgJQueryObject.hide();
		
		var scale;
		setTimeout(function() {            
            var w = imgJQueryObject.outerWidth();	
			var h = imgJQueryObject.outerHeight();
			var w1,h1;
			if(w >= h){				
				w1 = maxSize;
				scale = w / w1;
				h1 = h / scale;
			}else{				
				h1 = maxSize;
				scale = h / h1;
				w1 = w / scale;
			}
			imgJQueryObject.width(w1);
			imgJQueryObject.height(h1);
			imgJQueryObject.fadeIn(200);
        }, 200);
        return scale;
	},	
	/**
	 * 格式化数字
	 * @param {Number} value 数字
	 * @param {Number} pos 小数点后 位数
	 */
	formatNumber:function(value,pos){
		///alert(pos)
		if(pos == null || pos == undefined){pos = 2}
		var returnV = 0;
		if(value){			
			returnV = parseFloat(value).toFixed(pos);
			returnV = parseFloat(returnV).toLocaleString();
		}
		return returnV;
	},
	/**
	 * 格式化数字函数句柄
	 * @param {} pos
	 */
	getformatNumberHandler:function (pos){
		return function(v){
			return Core.formatNumber(v,pos);			
		}
	},
	/**
	 * 格式化日期
	 * @param {Object} date
	 * @param {String} format
	 * @return {String}
	 */
	formatDate:function(date,format){			
		var returnV = '';			
		if(date){
			if(typeof(date) == 'string'){			
  				//date = date.replace(/(\d{4})-(\d{2})-(\d{2})T(.*)?\.(.*)/, "$1/$2/$3 $4");							
				date = new Date(date);
			}else if(typeof(date) == 'number'){
				date = new Date(date);			
			}			
			//alert(date.time);
			//console.log(date);
			if(date.time){
			    var time = parseInt(date.time);
			    date = new Date(time);
			}
			returnV  = date.format(format);
		}
		return returnV;
	}
	/**
	 * 返回格式化日期句柄
	 * @param {String} format
	 * @return {function}
	 */
	,getFormatDateFunction:function(format){
		return function(v){
			return Core.formatDate(v,format);			
		}
	},
	/** 
     * java String hashCode 的实现 
     * @param strKey 
     * @return intValue 
     */  
    hashCode : function (strKey) {  
        var hash = 0;       
        if(strKey && Core.StringUtils.trim(strKey) != '') {
            for (var i = 0; i < strKey.length; i++) {
                hash = hash * 31 + strKey.charCodeAt(i);  
                hash = Core.intValue(hash);  
            }  
            //console.log(hash);
        }  
        return hash;  
    }, 
    /** 
     * 将js页面的number类型转换为java的int类型 
     * @param num 
     * @return intValue 
     */  
    intValue : function(num) {  
        var MAX_VALUE = 0x7fffffff;  
        var MIN_VALUE = -0x80000000;  
        if(num > MAX_VALUE || num < MIN_VALUE) {  
            return num &= 0xFFFFFFFF;  
        }  
        return num;  
    } ,
    getBodyHeight:function(){
		return document.documentElement.clientHeight;
	},
	/**
	 * 在按钮或链接前后加上转圈圈的图标
	 * @param {jQuery object} o
	 * @param {String} location 可选值 before|after 默认before
	 */
	appendLoadingIcon:function(o,location){
		location = location ? location : 'before';
		var html = '<div class="loading-small">&nbsp;</div>';
		var loading;
		if(location.toLowerCase()=='after'){
			var next = o.next();
			if(next.is('.loading-small')){
				loading = next;
				loading.show();
			}else{
				loading = jQuery(html);
				o.after(loading);
			}
		}else{
			var prev = o.prev();
			if(prev.is('.loading-small')){
				loading = prev;
				loading.show();
			}else{
				loading = jQuery(html);
				o.before(loading);
			}
		}
		
		o.attr('disabled',true);
		
		return loading;
	},
	/**
	 * 
	 * @param {jQuery object} o
	 * @param {String} location 可选值 before|after
	 */
	removeLoadingIcon:function(o,location){
		location = location ? location : 'before';
		
		o.removeAttr('disabled');
		
		if(location.toLowerCase()=='after'){
			var next = o.next();
			if(next.is('.loading-small')){
				next.hide();
			}
		}else{
			var prev = o.prev();
			if(prev.is('.loading-small')){
				prev.hide();
			}
		}
	},
	/**
	 * 将a标签disabled
	 * @param {} link
	 * @return {}
	 */
	disableLink:function(link){
		link.attr('disabled',true);
		var href = link.attr('href');
		link.removeAttr('href');
		return href;
	},
	unDisabledLink:function(link,href){
		link.removeAttr('disabled');
		link.attr('href',href);
	}
	//,
//	/**
//	 * 渲染图片
//	 * @param params {object}
//	 * picBoxSize<br>
//	 * imagePath<br>
//	 * imageHoverPath<br>
//	 * clickUrl<br>
//	 * target<br>
//	 * noimageText<br> 
//	 * imageSizeEqualsBoxSize {boolean}<br>
//	 * picBox
//	 */
//	renderImageBox : function (params){
//		var picBox = params.picBox;
//		var divImageBox = $('<div class="imagebox"/>');	
//		var divImageBoxDiv = $('<div/>');	
//		divImageBox.append
//		divImageBox.appendTo(picBox);
//		
////		var returnHtml = '<div style="position:absolute;top:50%;margin-top:'+params.picBoxSize[1]/2+'px;background-color:#f7f7f7;border:solid 1px #dedede;width:'+params.picBoxSize[0] + 'px;height:'+params.picBoxSize[1]+'px" align="center">';
////		if(!params.imagePath){
////			returnHtml += (params.noimageText ? params.noimageText :'暂无图片');
////		}else{
////			if(params.clickUrl){
////				returnHtml += '<a href="'+params.clickUrl+'" target="'+params.target+'">';
////			}
////			returnHtml += '<img src="'+params.imagePath +'"/>';	
////			if(params.clickUrl){returnHtml += '</a>';}
////		}
////		returnHtml += '</div>';
////		picBox.html(returnHtml);
//	}
};

Core.StringUtils = {
	/**
	 * 去掉字符串两边的空格
	 * @param {String} str
	 * @return {String}
	 */
	trim:function(str){
		if(str == undefined || str == null)
			return '';

		 return str.replace(/(^\s*)|(\s*$)/g, '');
	},
	/**	
	 * 把html标记去除
	 * @param {String} detail
	 */
	removeHtml : function(html){
		var _val = $("<div>" + html + "</div>").text();
 		return _val;
	},
	/**	
	 * 截取字符串
	 * @param {String} detail
	 */
	substring : function(str,start,length){	
 		var length = str.length > 200 ? 200 : str.length;
 		str = str.substring(start, length);
 		return str;
	},
	/**
	 * 见\n转换成br，将空格转换层nbsp;nbsp;
	 * @param {String} str
	 * @return {String}
	 */
	convertBlankAndEnterToBrAndNbsp:function(str){
		if(str){
			return str.replace(/\n/g,'<br/>').replace(/  /g,'&nbsp;&nbsp;');
		}else{
			return '';
		}
	}	
};

/**
 * 提示框工具
 * @type 
 */
Core.PopUtil = {
	/**
	 * loading 信息
	 * @param {Object} parameter
	 * 		msg {String} 默认 系统正在处理,请稍候......
	 * 		width {int} 默认 250
	 * @return {Boxy}
	 */
	loading: function(msg,parameter){
		if(!msg){msg='请稍候...';}
		
		parameter = jQuery.extend({
			width:200,
			modal:true,
			transparent:true
		},parameter);
		
		return new Boxy('<div class="common_loading3"></div>',parameter);
		//<div class="common_loading3_msg">'+msg +'</div>
	},

	/**
	 * @param {String} msg
	 * @param {Object} parameter
	 * 			<li>type{String} success/error/warn 默认success </li>	
	 * 			<li>width{int} 默认380</li>
	 * 			<li>autohide{bool} 是否自动关闭 默认false</li>
	 * 			<li>timeout{int} 自动关闭的延迟时间 默认700毫秒	</li>
	 * 			<li>callback{function} 关闭之后的回调</li>
	 * 			<li>showtitle</li>
	 */
	alert: function(msg,parameter){
		
		if(!msg){msg = '操作成功!';}	
		
		parameter = jQuery.extend({
			width:400,
			modal:true,
			timeout:1200,
			show:false,
			type:'success'
		},parameter);

		var renderHtml = '<div class="common_msg2" >' +
					'<div class="icons '+parameter.type+'"></div><div class="textWrap"><div class="text '+parameter.type+'">'+msg +'</div></div></div>';

		if(parameter.autohide){
			var b = new Boxy(renderHtml,parameter);
			b.show(400);
			setTimeout(function(){
				b.hide(parameter.callback);
			},parameter.timeout + 400);
		}else{
			Boxy.alert(renderHtml,function(){			
				if(parameter.callback){
					parameter.callback.call(window);
				}
			},parameter);
		}
	},	
	success:function(msg,callback,parameter){
		parameter = jQuery.extend({
			callback:callback,
			type: 'success',
			autohide:true
		},parameter);	
		Core.PopUtil.alert(msg,parameter);
	},
	/**
	 * 警告
	 * @param {String} msg
	 * @param {Function} callback
	 * @param {Object} parameter
	 */
	warn:function(msg,callback,parameter){
		parameter = jQuery.extend({
			callback:callback,
			type: 'warn',
			autohide:false
		},parameter);	
		Core.PopUtil.alert(msg,parameter);
	},
	/**
	 * 
	 * @param {} msg
	 * @param {} callback
	 * @param {} parameter
	 */
	error:function(msg,callback,parameter){
		parameter = jQuery.extend({
			callback:callback,
			type: 'error',
			autohide:false
		},parameter);	
		Core.PopUtil.alert(msg,parameter);
	},
	/**
	 * @param {String} msg
	 * @param {Object} parameter
	 * 	 		<li>width{int} 默认 400 </li>
	 * 			<li>msg{String}</li>
	 * 
	 * @param {Function} fn
	 */
	confirm: function(msg,fn,parameter){
		if(!parameter){parameter = {}}
		if(!parameter.width){parameter.width='400'}
		
		var renderHtml = '<div class="common_msg2">' +
					'<div class="icons icon_question"></div><div class="textWrap"><div class="text">'+msg +'</div></div></div>';
		
		Boxy.confirm(renderHtml,fn,parameter);
	},

    /*
    *  By dema 修改密码
    * */
    changePw: function(msg,fn,parameter){
        if(!parameter){parameter = {}}
        if(!parameter.width){parameter.width='550'}

        var renderHtml =
            '<div class="wrap">'+
                '<div class="content qrcode">'+
                    '<p class="phone">'+
                        '<label class="fl">新 &nbsp;&nbsp;密 &nbsp;&nbsp;码 :</label>'+
                        '<input  name="new_password" type="password" class="text fl" style="margin-left:30px;"/>'+
						' &nbsp;&nbsp;<span></span>'+
                    '</p>'+
                    '<p class="qrcode">'+
                        '<label class="fl">再 次 输 入 :</label>'+
                        '<input  type="password" class="text fl" name="re_password"  style="margin-left: 28px;"/>'+
						' &nbsp;&nbsp;<span></span>'+
                    '</p>'+
                    '</div>'+
                '</div>'+
            '</div>';

        Boxy.confirm(renderHtml,fn,parameter);
    },
	
	/**
	 * 
	 * @param {Object} parameter
	 * 	 		<li>width{int} 默认 400 </li>
	 * 			<li>msg{String}</li>
	 * 
	 * @param {Function} fn
	 */
	ask:function(question,buttons,fn,parameter){		
		parameter = jQuery.extend({
    		title:'请选择',
    		width:'350'
    	},parameter || {});
		
		var renderHtml = '<div class="common_msg2">' +
					'<div class="icons icon_question"></div><div class="textWrap"><div class="text">'+question +'</div></div></div>';
			
		Boxy.ask(renderHtml,buttons,fn,parameter);
	}
};

/**
 * 弹出窗口
 * @param {String or Object} contextBox 弹出窗口内容容器
 * @param {Object} params 
 * 		<li>title{String} 标题</li>
 *		<li>width{int} 宽度 默认500</li>
 *		<li>height{int} 高度默认300</li>
 *   	<li>answers{Array} 底部按钮 .eg  answers:['提交','关闭']</li>
 *   	<li>callback{function} 底部按钮点击回调 .eg: </li>
 *      <pre> 
 *     	callback:function(btn){
 *				if(btn=='关闭'){
 *				}else{
 *				}						
 *			}
 *		</pre> 
 */
Core.Window = function(contextBox,params){
	//if(!params) {params = {};}
	params = jQuery.extend({
		title : '弹出窗口',		
		modal : true,
		unloadOnHide : false,
		hideAfterCallback : false,
		title : '弹出窗口',
		width: 500,
		height : 300
	}, params);
	
	var contextBoxObj;
	if(typeof(contextBox) == 'string'){
		contextBoxObj = $('#' + contextBox);
		//var tmp = $('#' + contextBox);
		//contextBoxObj = tmp.html();
		//tmp.remove();
	}else{
		contextBoxObj = contextBox;		
	}	
	contextBoxObj.show();
	
	this.win = new Boxy(contextBoxObj,params);
};
Core.Window.prototype = {
	/**打开窗口*/
	open:function(){
		this.win.show();
	},
	show:function(){
		this.win.show();
	},
	/**关闭窗口*/
	hide:function(fn){
		//console.log(this.win);
		this.win.hide(fn);
		//console.log(this.win);
	},
	/**设置标题*/
	setTitle:function(title){
		this.win.setTitle(title);
	},
	/**getBoxy*/
	getBoxy:function(){
		return this.win.boxy;
	}
};

//
//Core.WindowUtils = {
//	open:function(contextBox,params){
//		var contextBoxObj;
//		var winId;
//		if(typeof(contextBox) == 'string'){
//			//var tmp = $('#' + contextBox);
//			winId = contextBox;
//			contextBoxObj = $('#' + contextBox);
//			//tmp.remove();
//		}else{
//			contextBoxObj = contextBox;
//			winId = contextBox.attr('id');
//			if(!winId){
//				winId = 'boxy-' + new Date().getTime();
//			}
//		}	
//		contextBoxObj.show();
//		var win = Core.WindowUtils._windows[winId];		
//		if(!win){
//			params = jQuery.extend({
//				modal:true,
//				unloadOnHide:false,
//				title:'弹出窗口',
//				width:500,
//				height:300,
//				id:winName
//			}, params);
//			
//			//console.log(html);
//			var win = new  Boxy(contextBoxObj,params);			
//			Core.WindowUtils._windows[winId] = win;
//		}
//		win.show();
//		return win;		
//	},
//	/**关闭窗口*/
//	hide:function(win,fn){
//		if(win){
//			win.hide(fn);
//		}
//	},
//	/**设置标题*/
//	setTitle:function(win,title){
//		if(win){
//			win.setTitle(title);
//		}
//	},
//	_windows:{}
//};

/**
 * 数据校验类
 * @type 
 */
Core.Validate = {
	isEmpty : function(str){
		var returnV;

		if(!str || jQuery.trim(str) == ''){
			returnV = true;
		}else{
			returnV = false;
		}

		return returnV;
	},

	/**
	 * 功能：判断是否为整数
	 * @param {String} objStr
	 * @return {Boolen}
	 */
	isInt : function (objStr){
	    var returnV;
		if(!objStr || objStr == ''){
			returnV = true;
		}

	    var reg=/(^-?|^\+?)\d+$/;
	    var r = objStr.match(reg);
	    return r != null;
	},


	/**
	 * 功能：判断是否为浮点数
	 * @param {} objStr
	 * @return {}
	 */
	 isDouble : function(objStr){
		var returnV;

		if(objStr == ""){
			returnV = true;
		}

	   	var reg=/^[0-9]+[\.[0-9]+]*$/;
	    var r = objStr.match(reg);
	
	    return r != null;
	},

	/**
	 * 判断是的是数字
	 * @param {} objStr
	 * @return {}
	 */
	 isNumber : function(objStr){
		return Validate.isDouble(objStr) || Validate.isInt(objStr);
	},


	/**
	 * 判断是否日期
	 * @param {} str
	 * @return {Boolean}
	 */
	 isDate:function(str){
	    if(str == "")
			return true;

	    //var re	= new RegExp("^([0-9]{1,2})[./]{1}([0-9]{1,2})[./]{1}([0-9]{4})$");
	    var re = new RegExp("^([0-9]{4})[-]{1}([0-9]{1,2})[-]{1}([0-9]{1,2})$");
	    var ar;
	    var res = true;

	    if ((ar = re.exec(str)) != null)
	    {
	        var i;

	        i = parseFloat(ar[2]);

	        if (i <= 0 || i > 12)
	            res = false;

	        i = parseFloat(ar[3]);

	        if (i <= 0 || i > 31)
	            res = false;
	    }
	    else{
	        res = false;
	    }

	    return res;
	},

	/**
	 * 判断是否电子邮件
	 * @param {} str
	 * @return {Boolean}
	 */
	 isEmail:function(str){
		if(str == "")
			return true;
		str = jQuery.trim(str);
		var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		return reg.exec(str) != null;

	},

	/**
	 *判断是否电话号码
	 * @param {} str
	 * @return {Boolean}
	 */
	 isPhone:function(str){
		if(str == ""){
			return true;
		}
		str = jQuery.trim(str);
		var reg = /^([0-9]{2}-)?(0?[0-9]{2,3}\-)?[1-9]?[0-9]{7}$/;
		return reg.exec(str) != null;
	},

	/**
	 *
	 * @param {} str
	 * @return {Boolean}
	 */
	 isMobile:function(str){
		if(str == ""){
			return true;
		}
		str = jQuery.trim(str);
		//var reg=/^[0-9]{11}/;
		var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
		
		return reg.exec(str) != null;
	},

	/**
	 *
	 * @param {} str
	 * @return {Boolean}
	 */
	 isPostcode:function(str){
		if(str == ""){
			return true;
		}
		str = jQuery.trim(str);
		var reg = /^[0-9]{6}$/;
		return reg.exec(str) != null;
	},

	/**
	 * 是否组织机构代码
	 * @param {} str
	 * @return {Boolean}
	 */
	isOrgCode : function(str){
		if(str == ""){
			return true;
		}
		str = jQuery.trim(str);
		var reg = /^[A-Za-z0-9_\-\[\]\(\)]*$/;
		return reg.exec(str) != null;
	},
	/**
	 * 校验上传文件大小的方法
	 * 
	 * @author huangrh , checkFileSize : function (file){ if(file == null ||
	 *         file == ''|| file.indexOf(":\\")== -1){ alert("不能上传空文件!"); return
	 *         true; } var fso,f; fso = new
	 *         ActiveXObject("Scripting.FileSystemObject"); f =
	 *         fso.GetFile(file); if(f.size > appConstants.UPLOAD_FILE_MAXSIZE){
	 *         alert("上传文件的大小不能超过" + appConstants.UPLOAD_FILE_MAXSIZE + "Bytes
	 *         (1M)!\n\n您现在上传的文件大小为：" + f.size + "Bytes,请更换!"); return true; }
	 *         return false; }
	 */	
	
	/**
	 * 校验上传的文件(根据文件全路径fileName)是否与所选文件类型checkType一致
	 */	
	checkFileType : function(fileName, checkType) {
		var last = fileName.lastIndexOf(".");
		var lastname = fileName.substring(last, fileName.length);
		var array = new Array;
		if (checkType == "1") {
			// 图片文件格式 jpg,gif,bmp,tif,png,svg
			array[0] = ".jpg";
			array[1] = ".gif";
			array[2] = ".bmp";
		} else if (checkType == "2") {
			// Flash文件格式 swf,jpg,mp3,png,gif
			array[0] = ".swf";
		} else if (checkType == "3") {
			// 视频文件格式 rm,rmvb,mpeg1-4,mov,mtv,dat,wmv,avi,3gp,amv,dmv
			array[0] = ".rm";
			array[1] = ".rmvb";
			array[2] = ".wmv";
		}
		for (var i = 0; i < array.length; i++) {
			if (lastname.toLowerCase() == array[i])
				return true;
		}
		return false;
	}
	/**
	 * 校验上传文件(含图片、文档等类型)大小的统一方法
	 */
	,
	checkFileSize : function(file, size) {
		if (file == null || file == ''){
			return false;
		}
		var limit = 1024 * size;
		var img = new Image();
		img.dynsrc = file;
		if (img.fileSize > limit) {
			alert("上传文件的大小不能超过" + size + "K,请更换!");
			return true;
		}
		return false;
	}		
	/**
	 * 校验上传的文件是否正确
	 */
	,
	fileIsExit : function(fileName) {
		return (fileName.indexOf(':\\') < 1)
			
	}
};

/**
 * URL工具类
 * @type 
 */
Core.UrlUtil = {
	/**
	 * 刷新页面
	 */	
	refresh : function() {
		var url = location.href.toString();
		location.href = url;
	},

	/**
	 * 转向页面
	 * @param {String} url
	 */	
	redirect: function(url) {
		location.href = url;
	},

	/**
	 * 格式化链接和参数
	 * @param {String} url
	 * @param {String} p
	 * @return {String}
	 */	
	format : function(url, p) {
		if(p){
			if (url.indexOf("?") == -1) {
				return url + "?" + p;
			} else {
				return url + "&" + p;
			}
		}else{
			return url;
		}
	},
	
	/**
	 * 重构URL中Query 
	 * @param {String} queryKey
	 * @param {String} queryValue
	 * @return {String}
	 */
	rebuildUrlQuery : function (queryKey, queryValue) {
		var query = document.location.search;
		if (query != "") {
			query = query.substring(1);
			var querys = query.split("&");
			query = "";
			for (var i = 0; i < querys.length; i++) {
				var tmps = querys[i].split("=");
				if (tmps.length > 1) {
					if (tmps[0] != queryKey) {
						query += "&" + tmps[0] + "=" + tmps[1];
					}
				}
			}
		}
		query += "&" + queryKey + "=" + queryValue;
		if (query != "") {
			query = query.substring(1);
		}
		return query;
	},
	
	/**
	 * 获取URL中去掉query的部分
	 */
	getUrlWithoutQuery : function(){
		var urlAll = document.location.href;
		
		var tmps =  urlAll.split("?");
		
		return tmps[0];
	},
	
	/**
	 * 将URL中GET请求的参数，组装成对象
	 */
	getParames : function(search){
		var returnO = {};
		var search = search ? search : location.search;
		
		if(search.charAt(0)=='?'){
			search = search.substring(1);
		}
	
		var params = search.split('&');
		
		var paramsLength = params.length;
		var param;
		for(var i = 0; i < params.length; i ++){
			param = params[i];		
			var tmp = param.split('=');
			if(tmp.length == 1){
				returnO[tmp[0]] = null;
			}else{					
				returnO[tmp[0]] = tmp[1];
			}
		}
		
		return returnO;
	},
	
	getUrlQueryFromForm : function(formId){
		var formItems = jQuery('input[type=text],input[type=hidden],select',jQuery('#' + formId));
		
		var query = '';
		jQuery.each(formItems,function(){
			if(jQuery.trim(this.value)!= '' && jQuery.trim(this.name)!= ''){
				//query += '&' + this.name + '=' + encodeURI(encodeURI(this.value.replace(' ','+')));
				query += '&' + this.name + '=' + this.value;
			}
		});
		
		if(query!=''){
			query = query.substring(1);
		}
	
		return query;
	},
	
	redirectQueryFromForm : function(url,formId){
		var url = this.format(url,this.getUrlQueryFromForm(formId));
		this.redirect(url);
		return false;
	}
};

Core.IdCardUtil = {
/**
	 * 身份证中取生日
	 * 
	 * @param {String} idCart 身份证号
	 */	
	changeToBirth : function(idCart) {
		var idStr = "";
		var birthStr = "";
		if (null != idCart && idCart != '') {
			idStr = idCart.length == 18 ? idCart.substring(0, 17) : idCart
					.substring(0, 6)
					+ "19" + idCart.substring(6, 16);
			birthStr = idStr.substring(6, 10) + "-" + idStr.substring(10, 12)
					+ "-" + idStr.substring(12, 14);
		}
		return birthStr;
	}
	/**
	 * 身份证中籍贯
	 * 
	 * @param {String} idCart 身份证号
	 */
	,
	changeToArea : function(idCart) {
		var idStr = "";
		var birthStr = "";
		if (null != idCart && idCart != '') {
			birthStr = idCart.substring(0, 6);

		}
		return birthStr;
	}

	/**
	 * 身份证中取性别 其中1为男、2为女
	 * 
	 * @param {String} idCart 身份证号
	 */
	,
	changeToSex : function(idCart) {
		var sex = "";
		if (null != idCart && idCart != '') {
			sex = idCart.length == 18 ? idCart.substring(16, 17) : idCart.substring(14, 15);
		}
		if (parseInt(sex) % 2 == 1){
			sex = 1;
		}else{
			sex = 0;
		}
		return sex;
	}
};

Core.CookieUtil = {
	/**
	 * 
	 * @param {String} name 
	 * @param {String} value
	 * @param {Integet} days  过期天数
	 * @param {String} Tdom 主机
	 */
	createCookie : function(name, value, days, Tdom) {
		var Tdom = (Tdom) ? Tdom : "/";
		if (!days) {
			days=30;
		}
		var date = new Date();
		//alert(date.toGMTString());
		//alert(days * 24 * 60 * 60 * 1000);
		//alert(date.getTime() + (days * 24 * 60 * 60 * 1000));
		date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));	
		document.cookie = name + "=" + escape (value) + "; expires=" + date.toGMTString() + "; path=" + Tdom;	
	},
	
	/**
	 * 
	 * @param {String} name
	 * @return {String}
	 */
	readCookie : function(name) {
		/*var nameEQ = name + "=";
		var ca = document.cookie.split(';');
		for (var i = 0; i < ca.length; i++) {
			var c = ca[i];
			while (c.charAt(0) == ' ') {
				c = c.substring(1, c.length);
			}
			if (c.indexOf(nameEQ) == 0) {
				return c.substring(nameEQ.length, c.length);
			}
		}*/
		var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
		return (arr != null)? unescape(arr[2]) : null;
	}
};

Core.FormUtils = {
	/**
	 * 获取表单值
	 * @param {Object} formId 或 form Jquery对象
	 * @return {Object}
	 */
	getFormValues : function(form){
		//console.log(typeof(form));
		var formObj;
		if(typeof(form) == 'string'){
			formObj = $('#' + form);
		}else{
			formObj = form;
		}	
		var values = {};
		var formItems = formObj.find(':text,textarea,select,:hidden,:password');
		$.each(formItems,function(){
			if(this.name){
				var v = $(this).val();
				if(v){
					values[this.name] = v;
				}
			}
		});

		var formCheckBoxs = formObj.find(':checkbox');
		$.each(formCheckBoxs,function(){
			if(this.checked){
				if(values[this.name]){
					values[this.name] += ',' + this.value;
				}else{
					values[this.name] = this.value;
				}
			}
		});

		var formradios = formObj.find(':radio');
		$.each(formradios,function(){
			if(this.checked){
				values[this.name] = this.value;
			}
		});

		return values;
	},

	/**
	 * 设置表单值
	 * @param {Object} formId 或 form Jquery对象
	 * @param {Object} values 表单数据 必须
	 */
	setFormValues:function(form,values){
		var formObj;
		if(typeof(form) == 'string'){
			formObj = $('#' + form);
		}else{
			formObj = form;
		}
		
		for(var field in values){
			var item = form.find(':text,input[type=hidden],textarea,select').filter('[name=' + field + ']');
			if(item.length>0){			
				item.val(values[field]);
			}else{				
				var radios = form.find(':radio[name=' + field + ']');
				if(radios.length>0){
					radios.removeAttr('checked');
					//console.log(radios.filter('[value='+values[field]+']').length);
					var selected = radios.filter('[value='+values[field]+']');
					if(selected.length>0){
						selected.get(0).checked=true;
					}
				}else{
					var checkbox = form.find(':checkbox[name=' + field + ']');
					if(checkbox.length>0){
						var val = values[field];
						if(typeof(val)=='string'){
							val = val.split(',');
						}
						//console.log(field);
						//console.log(val);
					
						$.each(val,function(){
							var selected = checkbox.filter('[value='+this+']');
							if(selected.length>0){
								selected.get(0).checked=true;
							}
						});
					
					}
				}
			}			
		}		
	},

	/**
	 * 重置表单
	 * @param {} form
	 */
	resetForm:function(form,isAboutHidden){
		var formObj;
		if(typeof(form) == 'string'){
			formObj = $('#' + form);
		}else{
			formObj = form;
		}	
		
		var formDom = formObj.get(0);	
		
		if(formDom.tagName.toLowerCase() != 'form'){
			var form =  $(formDom).find('form');
			if(form.size() > 0){
				formDom = form.get(0);
			}else{
				formDom = null;
			}
		}
		
		if(formDom){
			formDom.reset();
			$('select',formObj).val('');
			//$('radio',formObj).val('');
		}
	}
};

Core.DigestUtils = {
	md5 : function (string) { 
	    function RotateLeft(lValue, iShiftBits) {
	        return (lValue<<iShiftBits) | (lValue>>>(32-iShiftBits));
	    }	 
	    function AddUnsigned(lX,lY) {
	        var lX4,lY4,lX8,lY8,lResult;
	        lX8 = (lX & 0x80000000);
	        lY8 = (lY & 0x80000000);
	        lX4 = (lX & 0x40000000);
	        lY4 = (lY & 0x40000000);
	        lResult = (lX & 0x3FFFFFFF)+(lY & 0x3FFFFFFF);
	        if (lX4 & lY4) {
	            return (lResult ^ 0x80000000 ^ lX8 ^ lY8);
	        }
	        if (lX4 | lY4) {
	            if (lResult & 0x40000000) {
	                return (lResult ^ 0xC0000000 ^ lX8 ^ lY8);
	            } else {
	                return (lResult ^ 0x40000000 ^ lX8 ^ lY8);
	            }
	        } else {
	            return (lResult ^ lX8 ^ lY8);
	        }
	    }	 
		function F(x,y,z) { return (x & y) | ((~x) & z); }
		function G(x,y,z) { return (x & z) | (y & (~z)); }
		function H(x,y,z) { return (x ^ y ^ z); }
		function I(x,y,z) { return (y ^ (x | (~z))); }	 
	    function FF(a,b,c,d,x,s,ac) {
	        a = AddUnsigned(a, AddUnsigned(AddUnsigned(F(b, c, d), x), ac));
	        return AddUnsigned(RotateLeft(a, s), b);
	    };	 
	    function GG(a,b,c,d,x,s,ac) {
	        a = AddUnsigned(a, AddUnsigned(AddUnsigned(G(b, c, d), x), ac));
	        return AddUnsigned(RotateLeft(a, s), b);
	    };	 
	    function HH(a,b,c,d,x,s,ac) {
	        a = AddUnsigned(a, AddUnsigned(AddUnsigned(H(b, c, d), x), ac));
	        return AddUnsigned(RotateLeft(a, s), b);
	    };	 
	    function II(a,b,c,d,x,s,ac) {
	        a = AddUnsigned(a, AddUnsigned(AddUnsigned(I(b, c, d), x), ac));
	        return AddUnsigned(RotateLeft(a, s), b);
	    };	 
	    function ConvertToWordArray(string) {
	        var lWordCount;
	        var lMessageLength = string.length;
	        var lNumberOfWords_temp1=lMessageLength + 8;
	        var lNumberOfWords_temp2=(lNumberOfWords_temp1-(lNumberOfWords_temp1 % 64))/64;
	        var lNumberOfWords = (lNumberOfWords_temp2+1)*16;
	        var lWordArray=Array(lNumberOfWords-1);
	        var lBytePosition = 0;
	        var lByteCount = 0;
	        while ( lByteCount < lMessageLength ) {
	            lWordCount = (lByteCount-(lByteCount % 4))/4;
	            lBytePosition = (lByteCount % 4)*8;
	            lWordArray[lWordCount] = (lWordArray[lWordCount] | (string.charCodeAt(lByteCount)<<lBytePosition));
	            lByteCount++;
	        }
	        lWordCount = (lByteCount-(lByteCount % 4))/4;
	        lBytePosition = (lByteCount % 4)*8;
	        lWordArray[lWordCount] = lWordArray[lWordCount] | (0x80<<lBytePosition);
	        lWordArray[lNumberOfWords-2] = lMessageLength<<3;
	        lWordArray[lNumberOfWords-1] = lMessageLength>>>29;
	        return lWordArray;
	    };	 
	    function WordToHex(lValue) {
	        var WordToHexValue="",WordToHexValue_temp="",lByte,lCount;
	        for (lCount = 0;lCount<=3;lCount++) {
	            lByte = (lValue>>>(lCount*8)) & 255;
	            WordToHexValue_temp = "0" + lByte.toString(16);
	            WordToHexValue = WordToHexValue + WordToHexValue_temp.substr(WordToHexValue_temp.length-2,2);
	        }
	        return WordToHexValue;
	    };	 
	    function Utf8Encode(string) {
	        string = string.replace(/\r\n/g,"\n");
	        var utftext = "";
	 
	        for (var n = 0; n < string.length; n++) {	 
	            var c = string.charCodeAt(n);	 
	            if (c < 128) {
	                utftext += String.fromCharCode(c);
	            }
	            else if((c > 127) && (c < 2048)) {
	                utftext += String.fromCharCode((c >> 6) | 192);
	                utftext += String.fromCharCode((c & 63) | 128);
	            }
	            else {
	                utftext += String.fromCharCode((c >> 12) | 224);
	                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
	                utftext += String.fromCharCode((c & 63) | 128);
	            }	 
	        }	 
	        return utftext;
	    };
	 
	    var x=Array();
	    var k,AA,BB,CC,DD,a,b,c,d;
	    var S11=7, S12=12, S13=17, S14=22;
	    var S21=5, S22=9 , S23=14, S24=20;
	    var S31=4, S32=11, S33=16, S34=23;
	    var S41=6, S42=10, S43=15, S44=21;
	 
	    string = Utf8Encode(string);	 
	    x = ConvertToWordArray(string);	 
	    a = 0x67452301; b = 0xEFCDAB89; c = 0x98BADCFE; d = 0x10325476;	 
	    for (k=0;k<x.length;k+=16) {
	        AA=a; BB=b; CC=c; DD=d;
	        a=FF(a,b,c,d,x[k+0], S11,0xD76AA478);
	        d=FF(d,a,b,c,x[k+1], S12,0xE8C7B756);
	        c=FF(c,d,a,b,x[k+2], S13,0x242070DB);
	        b=FF(b,c,d,a,x[k+3], S14,0xC1BDCEEE);
	        a=FF(a,b,c,d,x[k+4], S11,0xF57C0FAF);
	        d=FF(d,a,b,c,x[k+5], S12,0x4787C62A);
	        c=FF(c,d,a,b,x[k+6], S13,0xA8304613);
	        b=FF(b,c,d,a,x[k+7], S14,0xFD469501);
	        a=FF(a,b,c,d,x[k+8], S11,0x698098D8);
	        d=FF(d,a,b,c,x[k+9], S12,0x8B44F7AF);
	        c=FF(c,d,a,b,x[k+10],S13,0xFFFF5BB1);
	        b=FF(b,c,d,a,x[k+11],S14,0x895CD7BE);
	        a=FF(a,b,c,d,x[k+12],S11,0x6B901122);
	        d=FF(d,a,b,c,x[k+13],S12,0xFD987193);
	        c=FF(c,d,a,b,x[k+14],S13,0xA679438E);
	        b=FF(b,c,d,a,x[k+15],S14,0x49B40821);
	        a=GG(a,b,c,d,x[k+1], S21,0xF61E2562);
	        d=GG(d,a,b,c,x[k+6], S22,0xC040B340);
	        c=GG(c,d,a,b,x[k+11],S23,0x265E5A51);
	        b=GG(b,c,d,a,x[k+0], S24,0xE9B6C7AA);
	        a=GG(a,b,c,d,x[k+5], S21,0xD62F105D);
	        d=GG(d,a,b,c,x[k+10],S22,0x2441453);
	        c=GG(c,d,a,b,x[k+15],S23,0xD8A1E681);
	        b=GG(b,c,d,a,x[k+4], S24,0xE7D3FBC8);
	        a=GG(a,b,c,d,x[k+9], S21,0x21E1CDE6);
	        d=GG(d,a,b,c,x[k+14],S22,0xC33707D6);
	        c=GG(c,d,a,b,x[k+3], S23,0xF4D50D87);
	        b=GG(b,c,d,a,x[k+8], S24,0x455A14ED);
	        a=GG(a,b,c,d,x[k+13],S21,0xA9E3E905);
	        d=GG(d,a,b,c,x[k+2], S22,0xFCEFA3F8);
	        c=GG(c,d,a,b,x[k+7], S23,0x676F02D9);
	        b=GG(b,c,d,a,x[k+12],S24,0x8D2A4C8A);
	        a=HH(a,b,c,d,x[k+5], S31,0xFFFA3942);
	        d=HH(d,a,b,c,x[k+8], S32,0x8771F681);
	        c=HH(c,d,a,b,x[k+11],S33,0x6D9D6122);
	        b=HH(b,c,d,a,x[k+14],S34,0xFDE5380C);
	        a=HH(a,b,c,d,x[k+1], S31,0xA4BEEA44);
	        d=HH(d,a,b,c,x[k+4], S32,0x4BDECFA9);
	        c=HH(c,d,a,b,x[k+7], S33,0xF6BB4B60);
	        b=HH(b,c,d,a,x[k+10],S34,0xBEBFBC70);
	        a=HH(a,b,c,d,x[k+13],S31,0x289B7EC6);
	        d=HH(d,a,b,c,x[k+0], S32,0xEAA127FA);
	        c=HH(c,d,a,b,x[k+3], S33,0xD4EF3085);
	        b=HH(b,c,d,a,x[k+6], S34,0x4881D05);
	        a=HH(a,b,c,d,x[k+9], S31,0xD9D4D039);
	        d=HH(d,a,b,c,x[k+12],S32,0xE6DB99E5);
	        c=HH(c,d,a,b,x[k+15],S33,0x1FA27CF8);
	        b=HH(b,c,d,a,x[k+2], S34,0xC4AC5665);
	        a=II(a,b,c,d,x[k+0], S41,0xF4292244);
	        d=II(d,a,b,c,x[k+7], S42,0x432AFF97);
	        c=II(c,d,a,b,x[k+14],S43,0xAB9423A7);
	        b=II(b,c,d,a,x[k+5], S44,0xFC93A039);
	        a=II(a,b,c,d,x[k+12],S41,0x655B59C3);
	        d=II(d,a,b,c,x[k+3], S42,0x8F0CCC92);
	        c=II(c,d,a,b,x[k+10],S43,0xFFEFF47D);
	        b=II(b,c,d,a,x[k+1], S44,0x85845DD1);
	        a=II(a,b,c,d,x[k+8], S41,0x6FA87E4F);
	        d=II(d,a,b,c,x[k+15],S42,0xFE2CE6E0);
	        c=II(c,d,a,b,x[k+6], S43,0xA3014314);
	        b=II(b,c,d,a,x[k+13],S44,0x4E0811A1);
	        a=II(a,b,c,d,x[k+4], S41,0xF7537E82);
	        d=II(d,a,b,c,x[k+11],S42,0xBD3AF235);
	        c=II(c,d,a,b,x[k+2], S43,0x2AD7D2BB);
	        b=II(b,c,d,a,x[k+9], S44,0xEB86D391);
	        a=AddUnsigned(a,AA);
	        b=AddUnsigned(b,BB);
	        c=AddUnsigned(c,CC);
	        d=AddUnsigned(d,DD);
	    }
	 
	    var temp = WordToHex(a)+WordToHex(b)+WordToHex(c)+WordToHex(d);	 
	    return temp.toLowerCase();
	},
	sha1:function(s){		
		var hexcase = 0;  /* hex output format. 0 - lowercase; 1 - uppercase        */
		var b64pad  = ""; /* base-64 pad character. "=" for strict RFC compliance   */
		var chrsz   = 8;  /* bits per input character. 8 - ASCII; 16 - Unicode      */
	
		function hex_sha1(s){return binb2hex(core_sha1(str2binb(s),s.length * chrsz));}
		function b64_sha1(s){return binb2b64(core_sha1(str2binb(s),s.length * chrsz));}
		function str_sha1(s){return binb2str(core_sha1(str2binb(s),s.length * chrsz));}
		function hex_hmac_sha1(key, data){ return binb2hex(core_hmac_sha1(key, data));}
		function b64_hmac_sha1(key, data){ return binb2b64(core_hmac_sha1(key, data));}
		function str_hmac_sha1(key, data){ return binb2str(core_hmac_sha1(key, data));}	
		/*
		 * Calculate the SHA-1 of an array of big-endian words, and a bit length
		 */
		function core_sha1(x, len)
		{
		  /* append padding */
		  x[len >> 5] |= 0x80 << (24 - len % 32);
		  x[((len + 64 >> 9) << 4) + 15] = len;
		
		  var w = Array(80);
		  var a =  1732584193;
		  var b = -271733879;
		  var c = -1732584194;
		  var d =  271733878;
		  var e = -1009589776;
		
		  for(var i = 0; i < x.length; i += 16)
		  {
		    var olda = a;
		    var oldb = b;
		    var oldc = c;
		    var oldd = d;
		    var olde = e;
		
		    for(var j = 0; j < 80; j++)
		    {
		      if(j < 16) w[j] = x[i + j];
		      else w[j] = rol(w[j-3] ^ w[j-8] ^ w[j-14] ^ w[j-16], 1);
		      var t = safe_add(safe_add(rol(a, 5), sha1_ft(j, b, c, d)),
		                       safe_add(safe_add(e, w[j]), sha1_kt(j)));
		      e = d;
		      d = c;
		      c = rol(b, 30);
		      b = a;
		      a = t;
		    }
		
		    a = safe_add(a, olda);
		    b = safe_add(b, oldb);
		    c = safe_add(c, oldc);
		    d = safe_add(d, oldd);
		    e = safe_add(e, olde);
		  }
		  return Array(a, b, c, d, e);
		
		}
		
		/*
		 * Perform the appropriate triplet combination function for the current
		 * iteration
		 */
		function sha1_ft(t, b, c, d)
		{
		  if(t < 20) return (b & c) | ((~b) & d);
		  if(t < 40) return b ^ c ^ d;
		  if(t < 60) return (b & c) | (b & d) | (c & d);
		  return b ^ c ^ d;
		}
		
		/*
		 * Determine the appropriate additive constant for the current iteration
		 */
		function sha1_kt(t)
		{
		  return (t < 20) ?  1518500249 : (t < 40) ?  1859775393 :
		         (t < 60) ? -1894007588 : -899497514;
		}
		
		/*
		 * Calculate the HMAC-SHA1 of a key and some data
		 */
		function core_hmac_sha1(key, data)
		{
		  var bkey = str2binb(key);
		  if(bkey.length > 16) bkey = core_sha1(bkey, key.length * chrsz);
		
		  var ipad = Array(16), opad = Array(16);
		  for(var i = 0; i < 16; i++)
		  {
		    ipad[i] = bkey[i] ^ 0x36363636;
		    opad[i] = bkey[i] ^ 0x5C5C5C5C;
		  }
		
		  var hash = core_sha1(ipad.concat(str2binb(data)), 512 + data.length * chrsz);
		  return core_sha1(opad.concat(hash), 512 + 160);
		}
		
		/*
		 * Add integers, wrapping at 2^32. This uses 16-bit operations internally
		 * to work around bugs in some JS interpreters.
		 */
		function safe_add(x, y)
		{
		  var lsw = (x & 0xFFFF) + (y & 0xFFFF);
		  var msw = (x >> 16) + (y >> 16) + (lsw >> 16);
		  return (msw << 16) | (lsw & 0xFFFF);
		}
		
		/*
		 * Bitwise rotate a 32-bit number to the left.
		 */
		function rol(num, cnt)
		{
		  return (num << cnt) | (num >>> (32 - cnt));
		}
		
		/*
		 * Convert an 8-bit or 16-bit string to an array of big-endian words
		 * In 8-bit function, characters >255 have their hi-byte silently ignored.
		 */
		function str2binb(str)
		{
		  var bin = Array();
		  var mask = (1 << chrsz) - 1;
		  for(var i = 0; i < str.length * chrsz; i += chrsz)
		    bin[i>>5] |= (str.charCodeAt(i / chrsz) & mask) << (32 - chrsz - i%32);
		  return bin;
		}
		
		/*
		 * Convert an array of big-endian words to a string
		 */
		function binb2str(bin)
		{
		  var str = "";
		  var mask = (1 << chrsz) - 1;
		  for(var i = 0; i < bin.length * 32; i += chrsz)
		    str += String.fromCharCode((bin[i>>5] >>> (32 - chrsz - i%32)) & mask);
		  return str;
		}
		
		/*
		 * Convert an array of big-endian words to a hex string.
		 */
		function binb2hex(binarray)
		{
		  var hex_tab = hexcase ? "0123456789ABCDEF" : "0123456789abcdef";
		  var str = "";
		  for(var i = 0; i < binarray.length * 4; i++)
		  {
		    str += hex_tab.charAt((binarray[i>>2] >> ((3 - i%4)*8+4)) & 0xF) +
		           hex_tab.charAt((binarray[i>>2] >> ((3 - i%4)*8  )) & 0xF);
		  }
		  return str;
		}
		
		/*
		 * Convert an array of big-endian words to a base-64 string
		 */
		function binb2b64(binarray)
		{
		  var tab = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
		  var str = "";
		  for(var i = 0; i < binarray.length * 4; i += 3)
		  {
		    var triplet = (((binarray[i   >> 2] >> 8 * (3 -  i   %4)) & 0xFF) << 16)
		                | (((binarray[i+1 >> 2] >> 8 * (3 - (i+1)%4)) & 0xFF) << 8 )
		                |  ((binarray[i+2 >> 2] >> 8 * (3 - (i+2)%4)) & 0xFF);
		    for(var j = 0; j < 4; j++)
		    {
		      if(i * 8 + j * 6 > binarray.length * 32) str += b64pad;
		      else str += tab.charAt((triplet >> 6*(3-j)) & 0x3F);
		    }
		  }
		  return str;
		}
		return hex_sha1(s);
	},
	sha256 : function(s){
		var chrsz   = 8;
		var hexcase = 0;
	 
		function safe_add (x, y) {
			var lsw = (x & 0xFFFF) + (y & 0xFFFF);
			var msw = (x >> 16) + (y >> 16) + (lsw >> 16);
			return (msw << 16) | (lsw & 0xFFFF);
		}
	 
		function S (X, n) { return ( X >>> n ) | (X << (32 - n)); }
		function R (X, n) { return ( X >>> n ); }
		function Ch(x, y, z) { return ((x & y) ^ ((~x) & z)); }
		function Maj(x, y, z) { return ((x & y) ^ (x & z) ^ (y & z)); }
		function Sigma0256(x) { return (S(x, 2) ^ S(x, 13) ^ S(x, 22)); }
		function Sigma1256(x) { return (S(x, 6) ^ S(x, 11) ^ S(x, 25)); }
		function Gamma0256(x) { return (S(x, 7) ^ S(x, 18) ^ R(x, 3)); }
		function Gamma1256(x) { return (S(x, 17) ^ S(x, 19) ^ R(x, 10)); }
	 
		function core_sha256 (m, l) {
			var K = new Array(0x428A2F98, 0x71374491, 0xB5C0FBCF, 0xE9B5DBA5, 0x3956C25B, 0x59F111F1, 0x923F82A4, 0xAB1C5ED5, 0xD807AA98, 0x12835B01, 0x243185BE, 0x550C7DC3, 0x72BE5D74, 0x80DEB1FE, 0x9BDC06A7, 0xC19BF174, 0xE49B69C1, 0xEFBE4786, 0xFC19DC6, 0x240CA1CC, 0x2DE92C6F, 0x4A7484AA, 0x5CB0A9DC, 0x76F988DA, 0x983E5152, 0xA831C66D, 0xB00327C8, 0xBF597FC7, 0xC6E00BF3, 0xD5A79147, 0x6CA6351, 0x14292967, 0x27B70A85, 0x2E1B2138, 0x4D2C6DFC, 0x53380D13, 0x650A7354, 0x766A0ABB, 0x81C2C92E, 0x92722C85, 0xA2BFE8A1, 0xA81A664B, 0xC24B8B70, 0xC76C51A3, 0xD192E819, 0xD6990624, 0xF40E3585, 0x106AA070, 0x19A4C116, 0x1E376C08, 0x2748774C, 0x34B0BCB5, 0x391C0CB3, 0x4ED8AA4A, 0x5B9CCA4F, 0x682E6FF3, 0x748F82EE, 0x78A5636F, 0x84C87814, 0x8CC70208, 0x90BEFFFA, 0xA4506CEB, 0xBEF9A3F7, 0xC67178F2);
			var HASH = new Array(0x6A09E667, 0xBB67AE85, 0x3C6EF372, 0xA54FF53A, 0x510E527F, 0x9B05688C, 0x1F83D9AB, 0x5BE0CD19);
			var W = new Array(64);
			var a, b, c, d, e, f, g, h, i, j;
			var T1, T2;
	 
			m[l >> 5] |= 0x80 << (24 - l % 32);
			m[((l + 64 >> 9) << 4) + 15] = l;
	 
			for ( var i = 0; i<m.length; i+=16 ) {
				a = HASH[0];
				b = HASH[1];
				c = HASH[2];
				d = HASH[3];
				e = HASH[4];
				f = HASH[5];
				g = HASH[6];
				h = HASH[7];
	 
				for ( var j = 0; j<64; j++) {
					if (j < 16) W[j] = m[j + i];
					else W[j] = safe_add(safe_add(safe_add(Gamma1256(W[j - 2]), W[j - 7]), Gamma0256(W[j - 15])), W[j - 16]);
	 
					T1 = safe_add(safe_add(safe_add(safe_add(h, Sigma1256(e)), Ch(e, f, g)), K[j]), W[j]);
					T2 = safe_add(Sigma0256(a), Maj(a, b, c));
	 
					h = g;
					g = f;
					f = e;
					e = safe_add(d, T1);
					d = c;
					c = b;
					b = a;
					a = safe_add(T1, T2);
				}
	 
				HASH[0] = safe_add(a, HASH[0]);
				HASH[1] = safe_add(b, HASH[1]);
				HASH[2] = safe_add(c, HASH[2]);
				HASH[3] = safe_add(d, HASH[3]);
				HASH[4] = safe_add(e, HASH[4]);
				HASH[5] = safe_add(f, HASH[5]);
				HASH[6] = safe_add(g, HASH[6]);
				HASH[7] = safe_add(h, HASH[7]);
			}
			return HASH;
		}
	 
		function str2binb (str) {
			var bin = Array();
			var mask = (1 << chrsz) - 1;
			for(var i = 0; i < str.length * chrsz; i += chrsz) {
				bin[i>>5] |= (str.charCodeAt(i / chrsz) & mask) << (24 - i%32);
			}
			return bin;
		}
	 
		function Utf8Encode(string) {
			string = string.replace(/\r\n/g,"\n");
			var utftext = "";
	 
			for (var n = 0; n < string.length; n++) {
	 
				var c = string.charCodeAt(n);
	 
				if (c < 128) {
					utftext += String.fromCharCode(c);
				}
				else if((c > 127) && (c < 2048)) {
					utftext += String.fromCharCode((c >> 6) | 192);
					utftext += String.fromCharCode((c & 63) | 128);
				}
				else {
					utftext += String.fromCharCode((c >> 12) | 224);
					utftext += String.fromCharCode(((c >> 6) & 63) | 128);
					utftext += String.fromCharCode((c & 63) | 128);
				}
	 
			}
	 
			return utftext;
		}
	 
		function binb2hex (binarray) {
			var hex_tab = hexcase ? "0123456789ABCDEF" : "0123456789abcdef";
			var str = "";
			for(var i = 0; i < binarray.length * 4; i++) {
				str += hex_tab.charAt((binarray[i>>2] >> ((3 - i%4)*8+4)) & 0xF) +
				hex_tab.charAt((binarray[i>>2] >> ((3 - i%4)*8  )) & 0xF);
			}
			return str;
		}
	 
		s = Utf8Encode(s);
		return binb2hex(core_sha256(str2binb(s), s.length * chrsz));
	}
};

Core.RegionUtil = {
	_fullCodePath:null,
	getCodeFullPath:function(code) {
		Core.RegionUtil._fullCodePath = '/' + code;
		
		inner(code);
		//console.log(Core.RegionUtil._fullCodePath);
		return Core.RegionUtil._fullCodePath;
		
		function inner(code){
			var pcode = Core.RegionUtil.getParentCode(code);	
			//console.log(pcode);
			if (pcode && pcode != '') {
				Core.RegionUtil._fullCodePath = '/' + pcode + Core.RegionUtil._fullCodePath;			
				inner(pcode);
			}
		}
	},
	getParentCode : function(code) {
		var returnV = null;
		if (code.length > 6) {
			code = code.substring(0,6);			
		}
		if (code.length < 6) {
			for(var i = 0; i < (6-code.length) ; i ++){
				code += '0';
			}			
		}
		
		if (code.substring(2)=="0000") {
			returnV = "";
		} else if (code.substring(4)=="00") {
			returnV = code.substring(0,2) + "0000";
		} else {
			returnV = code.substring(0,4) + "00";
		}
		return returnV;
	}
};


Core.PageBarForVelocity = function (options){
	var me = this;
	this.options = options;
	this.isLoadFormQuery = false;
	//console.log(options.pageBarFormId);
	this.pageBarForm = jQuery('#' + options.pageBarFormId);
	//console.log(this.pageBarForm);
	if(options.queryFormId){
		//console.log(options.queryFormId);
		this.formQuery = jQuery('#' + options.queryFormId);
		//console.log(this.formQuery);		
		setTimeout(function(){me.loadFormQuery();},800);
	}
	
	var action = me.pageBarForm.attr('action');
	//console.log(action)
	if(action){
		var pos = action.indexOf('?');
		//console.log(pos);
		action = action.substring(pos+1);
		var tmps = action.split('&');
		//console.log(tmps);
		if(tmps.length>0){
			jQuery.each(tmps,function(){
				var tmp2s = action.split('=');
				if(tmp2s[1]){
					me.pageBarForm.append('<input type="hidden" name="'+tmp2s[0]+'" value="'+tmp2s[1]+'"/>');
				}
			});		
		}
	}		
};

Core.PageBarForVelocity.prototype = {
	loadFormQuery:function(){
		var me = this;
		//console.log(me);	
		if(this.formQuery){
			var currQuerys = me.formQuery.formToArray();
			//console.log(currQuerys);		
			
			jQuery.each(currQuerys,function(){
				//console.log(this.value);	
				if(this.value && this.name!='pageSize' && this.name!='sortCode'){
					me.pageBarForm.append('<input type="hidden" name="'+this.name+'" value="'+this.value+'"/>');
				}
			});					
			
			me.isLoadFormQuery = true;
		}
	},
	changePage:function(pageCount,pageno){
		var me = this;
		var form = me.pageBarForm;
		
		if(!me.isLoadFormQuery){
			me.loadFormQuery();
		}
		
		var gpPageInput = form.find('input.txt_pageno');
		var gpPageInputDom = gpPageInput.get(0);
	 	//console.log(gpPageInput)
		if(!pageno){
			pageno = gpPageInput.val();
		}
		 //console.log(pageno)
		var tempvalue='' + pageno;	
        if(tempvalue == ''){
        	Core.PopUtil.warn("请输入您要跳转的页号!");
      		//alert("请输入您要跳转的页号!");
      	  	gpPageInputDom.select();
			return false;
        }

		if(!(/^\d+$/.test(tempvalue))){
			Core.PopUtil.warn("页号必须是整数!");
      		//alert("页号必须是整数!");
      	  	gpPageInputDom.select();
			return false;
        }
		if(tempvalue < 1){
			Core.PopUtil.warn("显示的页数不能小于1");
      	    //alert("显示的页数不能小于1");
      	  	gpPageInputDom.select();
			return false;
        }
        if(tempvalue > pageCount ){
        	Core.PopUtil.warn("显示的页数不能大于总页数!");
      	    //alert("显示的页数不能大于总页数!");
      	    gpPageInputDom.select();
			return false;
        }
	
  	    form.find('input[name=pageNo]').val(tempvalue);
		
		//console.log(form.get(0).action)
  	    
  	    form.get(0).submit();
		
		return false;
		
	    /*var url = Core.UrlUtil.format('$!submitUrl',Core.UrlUtil.getUrlQueryFromForm('myTabPageListForm_$!{id}'));

		if($paramters.anchor)
			url +="#${paramters.anchor}";
		else
			url +="#list";
		end
		
		Core.UrlUtil.redirect(url);
		return false;*/	
	}
};

/**
 * 格式化日期
 * @param {} fmt
 * @return {}
 */
Date.prototype.format = function(fmt){ //author: meizz    
	var o = {   
		"M+" : this.getMonth()+1,                 //月份    
		"d+" : this.getDate(),                    //日    
		"h+" : this.getHours(),                   //小时    
		"m+" : this.getMinutes(),                 //分    
		"s+" : this.getSeconds(),                 //秒    
		"q+" : Math.floor((this.getMonth()+3)/3), //季度    
		"S"  : this.getMilliseconds()             //毫秒    
	 };   
  	if(/(y+)/.test(fmt)){   
		fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
  	}
	for(var k in o)   
    	if(new RegExp("("+ k +")").test(fmt))   
  			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  	
  	return fmt;   
}; 

/**
 * 格式化字符串
 * <br>
 * <code>
 * 
 * </code>
 * @return {String}
 */
String.format = function() {
    if( arguments.length == 0 )
        return null;
    var str = arguments[0]; 
    for(var i=1;i<arguments.length;i++) {
        var re = new RegExp('\\{' + (i-1) + '\\}','gm');
        str = str.replace(re, arguments[i]);
    }
    return str;
};

/**
 * 左补齐
 * @param {} pads
 * @param {} padChar
 * @return {}
 */
String.prototype.leftPad = function(pads,padChar){
	var padLength = pads - this.length;
	
	if(padLength <= 0){
		return this;		
	}
	
	var returnV = '';
	
	for(var i=0; i < padLength; i++){
		returnV += padChar;
	}
	
	returnV += this;
	
	return returnV;
};

/**
 * 去掉两端空格
 * @return {String}
 */
String.prototype.trim = function(){
	if(!this)
		return '';
	return this.replace(/(^\s*)|(\s*$)/g, '');
};

var App = {
	charset: 'UTF-8',
	appServer : null,	
	pageUrlSuffix:'',
	ajaxActionSuffix: '', 
	webresourcesUrl : null,	
	getCurrentSystemUrl:function(path){
		var url = location.protocol + '//' + location.host;
		//console.log(url);
		if(path && path.length>0 && path.charAt(0) != '/'){
			path = '/' + path;
		}
		url += path;
		//console.log(url);
		return url;
	},
	getFunctionUrl : function(subSystemUrl,functionPath) {
		var url = subSystemUrl;
		if(functionPath && functionPath.length>0 && functionPath.charAt(0) != '/'){
			functionPath = '/' + functionPath;
		}
		url += functionPath;
		
		return url;
	},
	SubSystem:null,
	getFullUploadUrl : function(path){
		return App.updateUrl + '/' + path;//App.updateUrl 先在vm中赋值
	},
	getFullProjectResPath :function (path){
		return App.projectResPath + '/' + path;//App.projectResPath 先在vm中赋值
	}, 
	getFullRefPath :function (path){
		return App.refPath + '/' + path;//App.refPath 在先在vm中赋值
	},
	pageSize:20,
	getImagesUrl : function(system,image){
		return App.webresourcesUrl+"/ui/" + system + "/images/" + image;
	},
	/**
	 * 
	 * @param {} box
	 * @param {} params
	 */
	initCls:function(box,params){		
		params = jQuery.extend({		
			inputCls:'common_input',
			textareaCls:'common_textarea',
			selectCls:'common_select',
			inputFocusCls:'common_inputFocus',
			disabledCls:'common_readonly',
			datePickerCls:'datePicker',
			btnsHoverSelector:'btnLink'
		}, params);
		
		//console.log(params);
		
		//给所有的input加样式
		var inputs = box.find(':text,:password,:file');
		inputs.addClass(params.inputCls);
		inputs.not('[readonly],[disabled]').not('.' + params.datePickerCls).addClass(params.inputCls + '_bg');		
		inputs.filter('[disabled]').addClass(params.disabledCls);
		//inputs.filter('[readonly],[disabled]').addClass(params.disabledCls);
		
		var selects = box.find('select');
		selects.addClass(params.selectCls).addClass(params.inputCls + '_bg');
		
		var textareas = box.find('textarea');
		textareas.addClass(params.textareaCls).addClass(params.inputCls + '_bg');	
		
		if(params.inputRemoveClses){
			//console.log(params.inputRemoveCls);
			jQuery.each(params.inputRemoveClses,function(){	
				//console.log(this.toString());
				var all = $();
				all.add(inputs).add(selects).add(textareas).removeClass(this.toString());
				all=null;
			})
		}
		
		if(params.inputFocusCls){
			inputs.add(textareas).bind('focus', function() {			
				$(this).addClass(params.inputFocusCls);
			}).bind('blur', function() {
				$(this).removeClass(params.inputFocusCls);
			});
		}
	},
	/**
	 * 修改校验码图片
	 */
 	changeValidateCode : function(imgId){
		var img = document.getElementById("imgValidateCode");
	
		var timenow = new Date().getTime();
		  //每次请求需要一个不同的参数，否则可能会返回同样的验证码
		  //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。
		img.src= App.getCurrentSystemUrl("validateCode"+App.ajaxActionSuffix+"?d=" + timenow);
	},	
	/**
	 * 退出
	 */
	userLogout:function(serviceUrl){	
		Core.PopUtil.confirm('您确定要退出吗？', function(){
			$.post(
				serviceUrl + '/logout' + App.ajaxActionSuffix,
				function(result){
					//console.log(result);
					var loginUrl = null;
					
					if( result.data){
						loginUrl = result.data;
					}else{
						loginUrl = serviceUrl;
					}
					//console.log(loginUrl);
					//console.log(location.href);
					if(loginUrl.indexOf(location.href)==-1){
						loginUrl = loginUrl + '?r='+encodeURIComponent(location.href);
					}
					Core.UrlUtil.redirect(loginUrl);
				}				
			)		
		});		
	},
	/***
	 * 将地区代码转换成地区名称
	 * @param {String} value
	 * @return {String}
	 */
	convertRegionCodeToName : function(value){
		var returnV = '';
		if (value) {
			var name = App.Regions[value];
			if(name){
				returnV += name;
			}
			
			if(value.substring(4) != '00'){				
				var cityCode  = value.substring(0,4) + '00';				
				var cityName = App.Regions[cityCode];				
				if(cityName){
					returnV = cityName + '>' + returnV;
				}				
			}	
			
			if(value.substring(2) != '0000'){
				var pCode  = value.substring(0,2) + '0000';	
				var pName= App.Regions[pCode];
				if(pName){
					returnV = pName + '>' + returnV;
				}
			}	
		}
		return returnV;
	},
	/**
	 * 获取将地区代码转换成文本的函数句柄
	 * @return {}
	 */
	getRegionTextHandler : function(){
		return function(value){
			return App.convertRegionCodeToName(value);
		} 
	},
	/**
	 * 渲染图片
	 * @param params {object}
	 * picBoxSize
	 * imagePath
	 * imageHoverPath
	 * clickUrl
	 * target
	 * noimageText 
	 * imageSizeEqualsBoxSize {boolean}
	 */
	renderUploadImage : function (params){
		if(params.imageSizeEqualsBoxSize == null){
			params.imageSizeEqualsBoxSize = true;
		}

		var returnHtml = '<table align="center" cellpadding="0" cellspacing="0" border="0"><tr>';
		returnHtml += '<td style="vertical-align:middle;padding:0px;*padding-top:3px;background-color:#f7f7f7;border:solid 1px #dedede;width:'+params.picBoxSize[0] + 'px;height:'+params.picBoxSize[1]+'px" align="center">';
		if(!params.imagePath){
			//returnHtml += '<img src="'+App.projectResPath+'/image/nobody.png"/>';
			returnHtml += '<div>' + (params.noimageText ? params.noimageText :'暂无图片') + '</div>';
		}else{
			if(params.clickUrl){
				returnHtml += '<a href="'+params.clickUrl+'" target="'+params.target+'">';
			}
			returnHtml += '<img src="'+App.uploadUrl+ params.imagePath +'"';
			if(params.imageSizeEqualsBoxSize){
				returnHtml += ' width="'+params.picBoxSize[0] + '" height="'+params.picBoxSize[0] + '" />';
			}
			if(params.clickUrl){returnHtml += '</a>';}
		}
		returnHtml += '</td></tr></table>';
		return returnHtml;
	},
	/**
	 * 渲染图片
	 * @param params {object}
	 * imagePath
	 * clickUrl
	 * target
	 */
	renderSimpleUploadImage : function (params){
		var returnHtml = '';
		if(!params.imagePath){
			returnHtml += '<img src="'+App.getImagesPath('commons')+'nophoto.jpg"/>';
		}else{
			if(params.clickUrl){
				returnHtml += '<a href="'+params.clickUrl+'" target="'+params.target+'">';
			}
			returnHtml += '<img src="'+App.uploadUrl+ params.imagePath +'"';
			if(params.imageSize){
				returnHtml += ' width="'+params.imageSize[0] + '" height="'+params.imageSize[0] + '" />';
			}
			if(params.clickUrl){returnHtml += '</a>';}
		}
		return returnHtml;
	}
	,
	/**
	 * 判断ajax调用是否成功
	 * @param {com.ancun.products.core.model.ActionResult} result 结果
	 * @return {Boolean}
	 */
	isSubmitSuccess:function(result){
		if(!result || !result.type){
			return true;
		}
		
		return result.type != 'error' && result.type != 'warn';
	},
	renderDataDict:function(dd){
		//console.log(dd);
		var returnV = '';
		if(dd){
			if(dd.color){
				returnV += '<span style="color:'+dd.color+'">'
			}
			returnV += dd.text;
			if(dd.color){
				returnV += '</span>'
			}
		}
		return returnV;
	},
	renderDataDictByGroupAndValue:function(group,value){
		var returnO=null;
		var ddMap = App.DataDictMap[group];
		if(ddMap){
			var dd = ddMap[value];
			if(dd){
				returnO = App.renderDataDict(dd);
			}
		}
		return returnO;
	},
	getDataDictByGroupAndValue:function(group,value){
		return App.renderDataDictByGroupAndValue(group,value);
	},
	getDataDictTextHandler : function(group){
		return function(value){
			var returnValue=null;			
			//console.log(typeof(group));
			if(typeof(group) == 'string'){
				group = App.DataDictMap[group];
			}
			var dd = null;
			if(group){
				var dd = group[value];
				if(dd){
					returnValue = App.renderDataDict(dd);
				}
			}			
			
			return returnValue;
		}
	},
	/**
	 * 数据字典数据,在Controller中赋值
	 */
	DataDictArray:{},
	/**
	 * 数据字典map,在Controller中赋值
	 */
	DataDictMap:{},
	/**
	 * 地区根节点,value:1
	 * @type String 
	 */
	REGION_ROOT_CODE:'1',	
	
	closeMsg:function(e){
		jQuery(e).parent().fadeOut('slow');
	},
	EncodePWD : function(s){
		var smd5 = Core.DigestUtils.md5(s);
		return Core.DigestUtils.sha256(smd5);
	},
	/**
	 * 绑定数据字典
	 * @param {} select
	 * @param {} group
	 * @param {} selectedValue
	 * @param {} noDefault
	 */
	bindDatadictForSelect: function(select,group,selectedValue,noDefault,callback){
		AncunApiClient.getJson(App.getApiUrl('datadict'),{
			data:{group:group},
			success:function(response){
				if(noDefault){
					select.find('option').remove();
				}else{
					select.find('option').eq(0).nextAll().remove();
				}
				$.each(response.list,function(){
					//console.log(this);
					select.append(String.format('<option value="{0}">{1}</option>',this.value,this.text))
				});
				if(selectedValue){
					select.val(selectedValue);
				}	
				if(callback){
					callback();
				}
			}
		});
	},
	getApiUrl:function(functionUrl){
		return Config.apiHost + '/' + functionUrl;
	},
	/**
	 * 绑定数据字典
	 * @param {} radios
	 * @param {} group
	 * @param {} selectedValue
	 */
	bindDatadictForRadio: function(radiosBox,name,group,selectedValue,callback){
		AncunApiClient.getJson(App.getApiUrl('datadict'),{
			data:{group:group},
			success:function(response){			
				$.each(response.list,function(){
					var val = this.value;
					//console.log(val);
					var checked = (selectedValue&&selectedValue==val) ? 'checked' : '';
					radiosBox.append(String.format('<input type="radio" id="{0}_{2}" name="{0}" {1} value="{2}" /> <label for="{0}_{2}">{3}</label> ',name,checked,val, this.text));
				});
				if(radiosBox.find('input:checked').length==0){
					var radios = radiosBox.find('input[name='+name+']');
					if(radios.length>0){
						radios.get(0).checked = true;
					}
				}
				if(callback){
					callback();
				}
			}
		});
	},
	/**
	 * 绑定数据字典
	 * @param {} radios
	 * @param {} group
	 * @param {} selectedValue
	 */
	bindDatadictForCheckbox: function(checkboxes,name,group,selectedValue,callback){
		AncunApiClient.getJson(App.getApiUrl('datadict'),{
			data:{group:group},
			success:function(response){			
				$.each(response.list,function(){
					var val = this.value;
					//console.log(val);
					var checked = (selectedValue&&selectedValue==val) ? 'checked' : '';
					checkboxes.append(String.format('<input type="checkbox" id="{0}_{2}" name="{0}" {1} value="{2}" /> <label for="{0}_{2}">{3}</label> ',name,checked,val, this.text));
				});
//				if(checkboxes.find('input:checked').length==0){
//					var radios = checkboxes.find('input[name='+name+']');
//					if(radios.length>0){
//						radios.get(0).checked = true;
//					}
//				}
				if(callback){
					callback();
				}
			}
		});
	}
};

/***
 * 公用的Action
 * @type {Object}
 */
App.ActionUrl = {
	getDatadict: function(){ 
		return App.getCurrentSystemUrl('getDatadict' + App.ajaxActionSuffix);
	},
	getRegionTree: function(){
		return App.getCurrentSystemUrl('getRegionsByParentCodeForTree' + App.ajaxActionSuffix);
	},
	getRegionsForCombobox: function(){
		return App.getCurrentSystemUrl('getRegionsForCombobox' + App.ajaxActionSuffix);
	}
};

jQuery.ajaxSetup({
	cache:false
	,contentType: "application/x-www-form-urlencoded; charset=" + App.charset
});

jQuery(document).ajaxComplete(function(event,response, settings){
	var exception = response.getResponseHeader('exception');
	if(exception ){		
		//console.log(result);
		if(exception == '1000123'){//登录超时
			Core.UrlUtil.redirect(result.data);
		}else if(!top.isNotAlertedGlobalMsg){
			//console.log(response.responseText);
			var result = jQuery.parseJSON(response.responseText);			
			Core.PopUtil.alert(result.msg,{
				type:result.type
			});
		}
	}
}).ajaxError(function(event,request, settings){
	Core.PopUtil.alert('系统忙，请稍后再试！',{type:'error'});
	//console.log(event);
	//console.log(request);
	top.isNotAlertedGlobalMsg = true;
});

/**
 * 安存Api javascript 客户端
 * @type 
 */
var AncunApiClient = {
	/**成功编号*/
	SUCCESS_CODE:'1000000',	
	/**
	 * 获取json数据
	 * @param {String} url
	 * @param {Object} settings 同jquery的$ajax()的settings
	 */
	getJson:function(url,settings){	
		settings = jQuery.extend({
			type:'GET',
			contentType:'application/x-www-form-urlencoded'
		},settings);
		AncunApiClient._call(url,settings);
	},
	/**
	 * 提交json数据，向服务端发送http请求，请求方式为post,请求contentType为application/json
	 * @param {String} url
	 * @param {Object} settings 同jquery的$ajax()的settings
	 */
	postJson:function(url,settings){
		settings = jQuery.extend({
			type:'POST',
			contentType:'application/json'
		},settings);
		AncunApiClient._call(url,settings);
	},
	/**
	 * 向服务端发送http请求，请求方式为post,请求contentType为application/x-www-form-urlencoded
	 * @param {String} url
	 * @param {Object} settings 同jquery的$ajax()的settings
	 * @param {Object} form 可为空
	 */
	postForm:function(url,settings,form){
		settings = jQuery.extend({
			type:'POST',
			contentType:'application/x-www-form-urlencoded'					
		},settings);
		AncunApiClient._call(url,settings,form);
	},
	/**
	 * @param {String} url
	 * @param {Object} settings 同jquery的$ajax()的settings
	 * @param {Object} form 可为空
	 */
	postMultipart:function(url,settings,form){
		settings = jQuery.extend({
			type:'POST',
			contentType:'multipart/form-data',
			dataType:'json'			
		},settings);
		AncunApiClient._call(url,settings,form);
	},
	/**
	 * @param {String} url
	 * @param {Object} settings 同jquery的$ajax()的settings
	 */
	putJson:function(url,settings){
		settings = jQuery.extend({
			type:'PUT',
			contentType:'application/json'
		},settings);
		AncunApiClient._call(url,settings);
	},	
	_call:function(url,settings,form){
//		settings = jQuery.extend({
//			contentType:'application/json'
//		},settings)
		
		var successHanlder = settings.success;	
		settings.success = function(response){			
			//console.log(response);
			if(typeof(response)=='string'){
				try{
					response = $.parseJSON(response);
				}catch(ex){}
			}
			if(!response||response.code == AncunApiClient.SUCCESS_CODE){
				successHanlder(response);			
			}else if(settings.fail){
				//console.log(response);
				settings.fail(response.code,response.msg);
				top.isNotAlertedGlobalMsg = 1;//标识已经提示过了
			}else{
				Core.PopUtil.alert(response.msg,{
					type:response.type
				});				
				//Core.PopUtil.warn(response.msg);
				top.isNotAlertedGlobalMsg = 1;//标识已经提示过了
			}
		};		
	
		var headers = settings.headers ? settings.headers : {};
		//console.log(typeof(data));	
		if(settings.contentType == 'application/json' && settings.type.toUpperCase()!='GET'){
			var data = settings.data;	
			settings.data = $.toJSON(data);
			//console.log(typeof(settings.data));
			headers.reqlength = settings.data.length;
			headers.sign = Core.DigestUtils.md5(settings.data);
//			 settings.headers = jQuery.extend({
//				'reqlength':settings.data.length,
//				'sign':Core.DigestUtils.md5(settings.data)	
//			},settings.headers);
		}
	
		headers.reqtime = Core.formatDate(new Date(),'yyyyMMddhhmmss');
		if(Config && Config.accesskey){
			headers.accesskey = Config.accesskey;
		}
		
		headers.accept = '*/*';
		headers['X-Requested-With']='XMLHttpRequest';
		
		settings.headers = headers;
		
//		 settings.headers = jQuery.extend({
//			'reqtime':Core.formatDate(new Date(),'yyyyMMddhhmmss')		
//		},settings.headers);
		//console.log(settings);
		
		if(form){
			//console.log(form);
			settings.url = url;
			form.ajaxSubmit(settings);
		}else{
			jQuery.ajax(url,settings);
		}
	}
};


/**
 * 
 * @param {Object} config
 *    <li> provinceSelectId: 省份selectId</li>
 *    <li> citySelectId: 城市selectId</li>
 *    <li> regionSelectId: 地区selectId</li>
 *    <li> provinceSelect: 省份select</li>
 *    <li> citySelect: 城市select</li>
 *    <li> regionSelect: 地区select</li>
 *    <li> provinceValue: 省份值</li>
 *    <li> cityValue: 城市值</li>
 *    <li> regionValue: 地区值</li>
 *    <li> dataUrl: 接口地址</li>
 *    <li> callback: 数据加载之后的回调</li>
 */
App.regionBox = function(config){
	var me = this;	
	
	if(config.dataUrl){
		me.getDataActionUrl = config.dataUrl;
	}else{
		me.getDataActionUrl = App.getCurrentSystemUrl('getRegionsByParentCode');;
	}
	//console.log(me);
	config = jQuery.extend({
		provinceFirstOption:'[请选择省份]',
		cityFirstOption:'[请选择城市]',
		regionFirstOption:'[请选择地区]'
	},config);
	
	var selectProvince = config.provinceSelect?config.provinceSelect: $('#' + config.provinceSelectId);
	var selectCity = config.citySelect?config.citySelect:$('#' + config.citySelectId);
	var selectRegion = config.regionSelect?config.regionSelect:$('#' + config.regionSelectId);
	
	//console.log(config);
	
	selectProvince.html('<option value="">' + config.provinceFirstOption + '</option>');
	me._initCityAndRegion(selectCity,config.cityFirstOption,selectRegion,config.regionFirstOption);

	me._load(selectProvince,'1',config.provinceFirstOption,config.provinceValue,function(selectValue){
		
		if(config.callback){
			config.callback();
		}
		me.select(config.provinceValue,config.cityValue,config.regionValue);
//		me._load(selectCity,selectValue,config.cityFirstOption,config.cityValue,function(selectValue){
//			me._load(selectRegion,selectValue,config.regionFirstOption,config.regionValue);
//		});
	});	
	//console.log(config.callback)	
	selectProvince.change(function(){				
		me._initCityAndRegion(selectCity,config.cityFirstOption,selectRegion,config.regionFirstOption);
		if(this.value != ''){
			me._load(selectCity,this.value,config.cityFirstOption);			
		}
	});
	
	selectCity.change(function(){
		if(this.value != ''){
			me._load(selectRegion,this.value,config.regionFirstOption);			
		}
	});
	
	me._config = config;
	me._selectProvince = selectProvince;
	me._selectCity = selectCity;
	me._selectRegion = selectRegion;
};

App.regionBox.prototype = {
	select:function(provinceValue,cityValue,regionValue,callback){
		var me = this;
		if(provinceValue){
			//setTimeout(function() {
				jQuery(me._selectProvince).val(provinceValue);
			//},100);
			var callback1;
			
			if(me._selectRegion.size()>0&&cityValue){
				callback1 = function(selectValue){
					me._load(me._selectRegion,selectValue,me._config.regionFirstOption,regionValue,callback);
				}
			}else{
				callback1 = callback;
			}
				
			me._load(me._selectCity,provinceValue,me._config.cityFirstOption,cityValue,callback1);
		}
	},
	_load : function (select,parentCode,firstOption,value,callback){
		var me = this;		
		if(select.size()>0&&parentCode){
			jQuery.get(
				me.getDataActionUrl,
				{'parentCode':parentCode},
			    function(responseText, textStatus){
					if(jQuery.trim(responseText) != ''){
						select.html('<option value="">' + firstOption + '</option>' + responseText);					
						if(value){
							//setTimeout(function() {
								jQuery(select).val(value);
							//},100);
						}						
					}
					if(callback){
						//console.log(callback);
						callback(value);
					}
				}
			);
		}
	},	
	_initCityAndRegion : function (selectCity,cityFirstOption,selectRegion,regionFirstOption){
		if(selectCity.size()>0){
			selectCity.html('<option value="">' + cityFirstOption + '</option>');
		}
		if(selectRegion.size()>0){
			selectRegion.html('<option value="">' + regionFirstOption + '</option>');
		}
	}
};