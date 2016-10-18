/**
* @include "../admin.js"
 */
 
var 
/**最大打开tab数*/
MAX_OPEN_TAB_COUNT=10,
/**主菜单*/
_mainMenuLinks,
/**二级菜单*/
_subMenuLinks,
/**右边容器*/
_mainBox,
/**右边标签容器*/
_mainBoxTabBox,
/**打开的frame集合*/
_frames = {},
/**打开的标签集合*/
_tabs = {},
/**当前标签*/
_currentTab,
/**当前frame*/
_currentFrame,
/**当前打开的菜单*/
_currentMenu,
/**打开tab的总数*/
_openTabCount=0
;

jQuery(function(){ 	
	_mainMenuLinks =  $('.mainMenuLinks');
	_subMenuLinks =  $('.subMenuLink');
	_mainBox = $('#mainBox');
	_navBox = $('#navBox');
	_mainBoxTabBox = _navBox.find('ul');
	
	_mainMenuLinks.click(mainMenuClick);
	_subMenuLinks.click(subMenuClick);
	
	_currentTab = _mainBoxTabBox.find('li');
	_currentTab.click( tabSelected);
	
	_tabs['homepage'] = _currentTab;
	
	_currentFrame = _mainBox.find('iframe');
	_frames['homepage'] = _currentFrame;
	
	//折叠左边菜单
	$(".closeall").click(toggleMenu);
	
	//三级菜单
	$(".threeMenu a").click(threeMenuClick);
	
	//退出
	$("#logout").click(logout);
	
	//修改密码
	$("#modifyPwd").click(openWinUpdatePwd);
	
	//修改资料
	$("#modifyData").click(function(){
		$.get(App.getCurrentSystemUrl('/updatedata'),function(result){
			if(App.isSubmitSuccess(result)){
				var user = result.data;
				openWinUpdateData(user);		
			}
		});
	});
}); 

//折叠左边菜单
var clicknum = 0;
function toggleMenu(){
	if(clicknum%2 == 0){
		$("#menuBox").hide();
		$(this).addClass("setbgimg");
		$("#mainBox").css('margin-left',0);
	}else{
		$("#menuBox").show();
		$(this).removeClass("setbgimg");
		$("#mainBox").css('margin-left',150);
	};
	clicknum++;
}

var _currentMainMenu;
function mainMenuClick(){	
	var me = jQuery(this);	
	
	var mainMenuBox = me.parents('.mainMenuBox');
	var h3 = mainMenuBox.find('h3');
	var subMenusBox = mainMenuBox.find('.subMenusBox');
	
	//console.log(mainMenuBox);
	if(subMenusBox.is(':visible')){
		subMenusBox.slideUp("fast");
		h3.removeClass('open').addClass('close');
	}else{
		if(_currentMainMenu){
			_currentMainMenu.find('h3').removeClass('open').addClass('close');
			_currentMainMenu.find('.subMenusBox').slideUp("fast");
		}		
		subMenusBox.slideDown("fast");
		h3.removeClass('close').addClass('open');
	}	
	_currentMainMenu = mainMenuBox;
}

/**点击二级菜单*/
function subMenuClick(){
	var me = jQuery(this);
	
	if(me.is('.nobg')){	
		clearCurrentMenu();
		
		me.addClass('selected');
		_currentMenu = me;
		
		openModule(me);
	}else{	
		var _threeMenu = me.next(".threeMenu");
		if(_threeMenu.is(":hidden")){
			_threeMenu.slideDown("fast");
			$(this).addClass("setbgimg");
		}else{
			_threeMenu.slideUp("fast");
			$(this).removeClass("setbgimg");
		};
	}
	
	return false;
}

/**点击三级菜单*/
function threeMenuClick(){
	var me = jQuery(this);
	clearCurrentMenu();
	me.addClass('selected'); 
	_currentMenu = me;
	openModule(me);
	return false;
}

/**
 * 清除当前选中菜单的样式
 */
function clearCurrentMenu(){
	if(_currentMenu){
		_currentMenu.removeClass('selected');
	}	
}

var IFRAME_HTML  = '<iframe frameborder="0" width="100%" height="100%" />';

/**
 * 打开功能tab
 * @param {} module 功能菜单
 */
function openModule(module){
	var id = module.attr('id');//功能Id
	if(!id){	
		id = 'f_' + new Date().getTime();
	}
	module.attr('id',id);
	
	var href = module.attr('href');//功能页面链接
	
	var tab = _tabs[id];//根据功能Id取功能tab
	//console.log(tab);
	if(tab){//存在，表示已经打开
		clearCurrentTab();
		
		_currentTab = tab;
		_currentFrame = _frames[id];		
		_currentTab.addClass('current');//选中当前功能标签
		_currentFrame.attr('src',href).show();//选中当前功能iframe
	}else{//不存在新建
		if(_openTabCount == MAX_OPEN_TAB_COUNT){
			Core.PopUtil.warn('非常抱歉,系统最多支持打开 ['+MAX_OPEN_TAB_COUNT+'] 个标签<br/>您打开的标签超过了系统最大数!');
		}else{	
			clearCurrentTab();
					
			//新建标签
			_currentTab = $('<li title="'+module.text()+'">'+module.text()+' <a href="javascript:void(0);">&nbsp;</a></li>');
			_currentTab.attr('id','tab_'+id);//给标签设置Id
			_mainBoxTabBox.append(_currentTab);
			_tabs[id] = _currentTab;
			
			_currentTab.click(tabSelected);//加上标签点击事件	
			_currentTab.find('a').click(tabClose);//加上标签关闭事件
			
			//新建iframe
			_currentFrame = $(IFRAME_HTML);
			_currentFrame.attr('name',id);
			_mainBox.append(_currentFrame);
			_frames[id]=_currentFrame;	
			
			_currentTab.addClass('current');//选中当前功能标签
			_currentFrame.attr('src',href).show();//选中当前功能iframe
			
			_openTabCount ++;
		}
	}
}

/**隐藏当前选中的功能*/
function clearCurrentTab(){
	if(_currentTab){ //隐藏当前选中的功能
		_currentFrame.hide();
		_currentTab.removeClass('current');
	}
}

/**
 * 标签选中
 */
function tabSelected(){
	var id = getModulesId(this);
	if(_currentTab){//隐藏之前选中的功能
		_currentTab.removeClass('current');
		if(_currentFrame){
			_currentFrame.hide();
		}
	}	
	//console.log(id);
	_currentTab = $(this);
	_currentFrame = _frames[id];
	
	_currentFrame.show();//显示当前选中的功能
	_currentTab.addClass('current');
	
	clearCurrentMenu();
	
	_currentMenu = $('#'+id);
	_currentMenu.addClass('selected');
}

/**
 * 关闭
 */
function tabClose(){
	var aClose = $(this);//关闭a link
	var tab = aClose.parent();//要关闭的标签
	var id = getModulesId(tab.get(0));//要关闭的功能Id
	
	if(_currentTab.attr('id') == 'tab_' + id){
		if(_currentTab.next().size()>0){
			_currentTab.next().click();
		}else {
			_currentTab.prev().click();
		}
	}	
	
	tab.remove();
	_openTabCount --;
	_frames[id].remove();
	_tabs[id]=null;
	_frames[id]=null;	
}

function getModulesId(tabDom){
	return tabDom.id.replace('tab_','');
}

var _winUpdatePwd,_formUpdatePwd;
function openWinUpdatePwd(){
	var html = '<form  method="post" action="updatePwd'+App.ajaxActionSuffix+'"  class="winFormBox"><table><tr>' +
						'<th>旧密码：</th><td>' +
							'<input name="pwd"  class="required {rangelength:[6,20]} type="password" value="" /><em class="requiredTag">*</em>' +
						'</td></tr><tr>' +
						'<th>新密码：</th><td>' +
						'<input name="newPwd"  id="newPwd" class="required {rangelength:[6,20]}" type="password" value=""/><em class="requiredTag">*</em>' +
						'</td></tr><tr>' +
						'<th>确认密码：</th><td>' +
							'<input name="confimPwd"  class="required {rangelength:[6,20]}" type="password" value="" equalTo="#newPwd"/><em class="requiredTag">*</em>' +
						'</td></tr><tr><th></th><td>' +
							'<input type="button" value="提 交" class="btn mt5" onclick="updatePwd();"/>' +
						'</td></tr></table></form>'
	
		_winUpdatePwd = new Boxy(html,{
			width:350,
			height:220,			
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
	_winUpdatePwd.show();
}

function updatePwd(){

	// 验证密码中是否包含空格
	var newPwd=$("input[name='newPwd']").val();
	if (newPwd.indexOf(" ") != -1) {
		Core.PopUtil.warn("密码不能包含空格！");
		return;
	}

	if(_formUpdatePwd.valid()){	
		_formUpdatePwd.ajaxSubmit(function(result){
			if(App.isSubmitSuccess(result)){
				Core.PopUtil.success('修改成功！',function(){
					_winUpdatePwd.hide();
					location.reload();
				});				
			}			
		});
	}

}

var _winUpdateData,_formUpdateData;
function openWinUpdateData(user){
	var userName = user.userName == undefined ? "" : user.userName;
	var email = user.email == undefined ? "" : user.email;
	var phone = user.phone == undefined ? "" : user.phone;
	var address = user.address == undefined?"":user.address;
	var idCard = user.idCard == undefined?"":user.idCard;
	
	
	var html = '<form  method="post" action="updatedatasumit'+App.ajaxActionSuffix+'"  class="winFormBox"><table><tr>' +
	
						'<th>账号：</th><td>' +
						'<input name="account"  id="account"  class="hide" type="text" value="'+user.account+'"/>' +
						'<input name="accountt"  id="accountt"  disabled="true" type="text" value="'+user.account+'"/><em class="requiredTag">*</em>' +
						'</td></tr><tr>' +
	
						'<th>用户名：</th><td>' +
						'<input name="userName"  id="userName" class="required {rangelength:[1,20]}" type="text" value="'+userName+'"/><em class="requiredTag">*</em>' +
						'</td></tr><tr>' +
						
						'<th>邮箱：</th><td>' +
						'<input name="email"  id="email" class="email" type="text" value="'+email+'"/>' +
						'</td></tr><tr>' +
						
						'<th>电话：</th><td>' +
						'<input name="phone"  id="newPwd" class="phone" type="text" value="'+phone+'"/>' +
						'</td></tr><tr>' +
						
						'<th>地址：</th><td>' +
						'<input name="address"  id="address"  type="text" value="'+address+'"/>' +
						'</td></tr><tr>' +
						
						'<th>身份证号：</th><td>' +
						'<input name="idCard"  id="idCard"  type="text" value="'+idCard+'"/>' +
						'</td></tr><tr><th></th><td>' +
							'<input type="button" value="提 交" class="btn mt5" onclick="updateData();"/>' +
						'</td></tr></table></form>'
	
	//if(!_winUpdateData){
		_winUpdateData = new Boxy(html,{
			width:350,
			height:320,			
			unloadOnHide : false,
			title : '修改资料',
			afterShow: function(){				
				if(!_formUpdateData){					
					_formUpdateData = this.boxy.find('form');
					App.initCls(_formUpdateData);    				    			
    				_formUpdateData.validate();
				}
				_formUpdateData.resetForm().find('font.success').remove();
			}
		});		
	//}
	_winUpdateData.show();
}

function updateData(){
	if(_formUpdateData.valid()){	
		_formUpdateData.ajaxSubmit(function(result){
			if(App.isSubmitSuccess(result)){
				Core.PopUtil.success('修改成功！',function(){
					_winUpdateData.hide();
					location.reload();
				});				
			}			
		});
	}	
}


function logout(){
	Core.PopUtil.confirm('您确定要退出吗<b style="color:#ff6600;"> ?',function(){
		$.get(App.getCurrentSystemUrl('/logout'),function(result){
			if(App.isSubmitSuccess(result)){
				Core.UrlUtil.redirect(App.getCurrentSystemUrl(''));			
			}
			});
	});	
}

