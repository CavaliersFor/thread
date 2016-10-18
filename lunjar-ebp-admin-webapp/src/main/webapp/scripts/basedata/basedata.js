/**
* @include "../admin.js"
 */
 
var 
/**顶级数据项(分组)的parentValue*/
TOP_DD_PARENT_VALUE = 'root', 
/**当前选中的树节点*/
_currentNode,
/**当前父Value*/
_parentValue= 'root',
/**当前类别*/
_currentGroup,
 /**
 * 状态常量
 */
DataDictStatus={
	/**正常*/
	normal:"1",
	/**失效*/
	departure:"-1"
},
/**查询操作按钮 */
_buttons,
/**列表行模板 */
trTpl
;

jQuery(function(){
	var height = Core.getBodyHeight() - $('.r_title').height() - 40;	
	$('.bodyBox .left').height(height);
	$('.bodyBox .right').height(height);
	$('.bodyBox .middle').height(height-6);
	initLeftPanel();
	initRightPanel();
	$("#btn_add").removeAttr("disabled");
	$("#btn_add").click(function(){
		_dictValue="";
		openEditWin(null,null);
	});
});


/**初始化左侧树*/
function initLeftPanel(){
	var loader = new Ext.tree.TreeLoader({
		dataUrl : 'getGroupTree' +App.ajaxActionSuffix,
		listeners:{
			'beforeload':function(treeLoader, node) {  
                treeLoader.baseParams = {rootValue:TOP_DD_PARENT_VALUE};
            }
		}
	});

	var root = new Ext.tree.AsyncTreeNode({
		id:"|"+TOP_DD_PARENT_VALUE+"|", expanded :true,draggable : true,text:'顶级类别'
	});
	
	var leftPanel = new Core.Ext.TreePanel({
		id:'leftPanel',
		requestMethod:'GET',
		autoScroll: true,
		width:300,
        useArrows: false,  
        animate: false,
        enableDD:false,
        loader: loader,  
        root: root,
        border:false,
        rootVisible: true,
        enableSearch:true,
        listeners: { 
            'click': function(node,e){   
            	_currentNode = node;
            	var ss = node.id.split("|");
            	_currentGroup=ss[0];
            	list(ss[0],ss[1],null);
            	_currentSubSystem = _currentNode.attributes.subSystem;
            	_buttons.attr('disabled',false);
            },
            'dblclick':function(node,e){
            	if(node.id=='root'){
            		list('root','root',null);
            	}else{
            		_currentNode = node;
            		var ss = node.id.split("|");
                	_currentGroup=ss[0];
                	_parentValue=ss[2];
               		if(node.parentNode!=null&&node.parentNode.id!='root'){
               			_currentGroup=node.parentNode.id;
               			if(!_currentNode.leaf){
               				_parentValue=node.id;
               			}
               		}
               		if(node.parentNode.parentNode!=null&&node.parentNode.parentNode.id!='root'){
               			_currentGroup=node.parentNode.parentNode.id;
               		}
            		_currentSubSystem = _currentNode.attributes.subSystem;
            		//调用修改方法
            		operateBaseWin(ss,"edit");
            	}
            },
            "contextmenu":function(node,e){  
                var nodemenu=new Ext.menu.Menu({  
                     items:[
                             {  
                            	 text:"添加同级",  
                                 icon: _componentUrl+'/ext/ext-3.2.0/resources/images/default/tree/drop-add.gif',  
                                 cls: 'x-btn-text-icon',  
                                 handler:function(){
                                    var ss = node.id.split("|");
                                    if(ss[1]=="root"){
                                    	Core.PopUtil.warn('顶级类别不允许添加同级',false);
                                    }else{
                                    	operateBaseWin(ss,"addbrother");
                                    }
                                 }  
                             },
                             {  
                            	 text:"添加子级",  
                                 icon: _componentUrl+'/ext/ext-3.2.0/resources/images/default/tree/drop-add.gif',  
                                 cls: 'x-btn-text-icon',  
                                 handler:function(){ 
                                	  var ss = node.id.split("|");
                                      operateBaseWin(ss,"addson");
                                 }  
                             },
                             {  
                                 text:"编辑修改",  
                                 icon: _componentUrl+'/ext/ext-3.2.0/resources/images/default/tree/leaf.gif',  
                                 cls: 'x-btn-text-icon',  
                                 handler:function(){
                                	  var ss = node.id.split("|");
                                	  if(ss[1]=="root"){
                                      	Core.PopUtil.warn('顶级类别不允许编辑修改',false);
                                      }else{
                                    	  operateBaseWin(ss,"edit");
                                      }
                                 }  
                             }
//                             ,
//                             {  
//                            	 text:"启用",  
//                                 icon: _componentUrl+'/ext/ext-3.2.0/resources/images/default/tree/drop-yes.gif',  
//                                 cls: 'x-btn-text-icon',  
//                                 handler:function(){  
//                                	 var ss = node.id.split("|");
//                                     operateBaseWin(ss,"enabled");
//                                 }  
//                             },
//                             {  
//                            	 text:"停用",  
//                                 icon: _componentUrl+'/ext/ext-3.2.0/resources/images/default/tree/drop-no.gif',  
//                                 cls: 'x-btn-text-icon',  
//                                 handler:function(){  
//                                	 var ss = node.id.split("|");
//                                     operateBaseWin(ss,"disabled");
//                                 }  
//                            }
                     ]  
             	});  
                nodemenu.showAt(e.getPoint());//menu的showAt，不要忘记  
         	}  
        }
    });  
    leftPanel.render('tree');
    leftPanel.getRootNode().expand(true);  
}

var
/**查询表单*/
_formSearch,
/**查询条件,每次查询的时候，记录在这里，用于刷新*/
_params;
/**初始化右侧列表*/
function initRightPanel(){
	_buttons = $('.bodyBox .right table.search .btn');
	_buttons.attr('disabled',true);
	
	_formSearch = $('#formSearch');
	$('#btn_search').click(function(){//查询
		var params = Core.FormUtils.getFormValues(_formSearch);
		//console.log(params);
		list(_currentGroup,_parentValue,params);
		_params = params;
		return false;//阻止表单提交，因为#btn_search是submit
	});
	
	$('#btn_reset').click(function(){	
		Core.FormUtils.resetForm('formSearch');
		_params = null;
		list(_currentGroup,_parentValue,null);
	});
}

var _tbody;
/***
 * 查询
 * @param {String} dictGroup 类别
 * @param {Object} params 参数
 */
function list(dictGroup,dictValue,params){
	if(!trTpl){//初始化列表行模板
		trTpl = new Ext.Template('<tr><td>{no}</td>',
			'<td >{dictValue}</td>',
			'<td >{dictText}</td>',
			'<td class="statusText">{statusText}</td>',
			'<td class="sortNum">{sortNum}</td>',
			'<td >{gmtCreate}</td>',
			'<td >{gmtModify}</td>',
			'<td>' +
			'<form><input type="hidden" name="oldStatus" value="{status}"/>',
			'<input type="hidden" name="dictGroup" value="{dictGroup}"/>',
			'<input type="hidden" name="dictValue" value="{dictValue}"/>',
			'<input type="hidden" name="dictText" value="{dictText}"/>',
			'<input type="hidden" name="parentValue" value="{parentValue}"/>',
			'</form>',
			'<a class="modify" href="javascript:void(0);" id="{dictGroup}|{dictValue}|{parentValue}">修改</a>',
			'&nbsp;&nbsp;<a class="updateStatus" href="javascript:void(0);">{updateStatusText}</a></td>',
			'</tr>'
		);	
		trTpl.compile();
	}
	//将类别合并到查询参数里
	params = jQuery.extend({dictGroup:dictGroup,dictValue:dictValue}, params || {});	
	//console.log(params);	
	if(!_tbody){
		_tbody = $('table.list tbody');
	}
	_tbody.html('<tr><td colspan="8" style="text-align:left;"><div class="common_loading">数据加载中，请稍候...</div></td></tr>');
	
	$.get('getByGroup' + App.ajaxActionSuffix, params,renderList);
	
	$(".statusText").each(function(){
		var v=$(this).html();
		var s="<font color=green>正常</font>";
		if(v=="-1"){
			s="<font color=red>已删除</font>";
		}
		$(this).html(s);
	});
}

/**
 * 渲染列表
 * @param {Array[Object]} datas 渲染的数据
 */
function renderList(datas){
	//console.log(datas);
	_tbody.find('.common_loading').fadeOut('fast',function(){
		_tbody.html('');
		$.each(datas,function(i){//迭代数据，增加一行
			var data = this;
			
			data.no = i + 1;
			data.gmtCreate = Core.formatDate(data.gmtCreate,'Y-m-d H:i')
			data.gmtModify = Core.formatDate(data.gmtModify,'Y-m-d H:i')
			data.updateStatusText = data.status == DataDictStatus.normal ? '停用':'启用';
//			data.statusText =  App.getDataDictByGroupAndValue('dataDictStatus',data.status) ;
			data.statusText = '<font color='+ (data.status == DataDictStatus.normal ? 'green>正常':'red>已删除')+'</font>';
			var trHtml = trTpl.applyTemplate(data);//填充列表行模板
			
			_tbody.append(trHtml);
		});
		
		_tbody.find('.updateStatus').click(updateStatus);//停用 or 启用
		_tbody.find('.modify').click(function(){//修改
			operateBaseWin($(this).attr("id").split("|"),"edit");
		});
	});
}

function refresh(){
	list(_currentGroup,_parentValue,_params);
}

/***
 * 停用 or 启用
 */
function updateStatus(){
	var me = $(this);
	var meText = me.text();
	
	Core.PopUtil.confirm('确定'+meText+'该基础数据?',function(){
		var form = me.parent().find('form');//每个列表行的form
		var params = Core.FormUtils.getFormValues(form);//取出参数
		params.dictGroup = _currentGroup;
	
		//设置更新后的状态
		params.status = params.oldStatus == DataDictStatus.normal? DataDictStatus.departure :DataDictStatus.normal;
		
		Core.appendLoadingIcon(me);
		$.post('updateStatus' +App.ajaxActionSuffix,params,function(v){
			Core.removeLoadingIcon(me);
			if(App.isSubmitSuccess()){	
				form.find('input[name=oldStatus]').val(params.status);//修改当前状态的input的value			
			
				params.operateName = params.status == DataDictStatus.normal? '停用':'启用';
				me.parent().find('.updateStatus').text(params.operateName);//修改updateStatus链接的文本 		
				
//				var statusText = App.renderDataDictByGroupAndValue('dataDictStatus',params.status);
				var statusText = '<font color='+ (params.status == DataDictStatus.normal ? 'green>正常':'red>已删除')+'</font>';
				me.parent().parent().find('td.statusText').html(statusText);//修改statusText的显示文本 				
			}
		});
	});
}
var _editForm;
function renderForm(){
	if(!_editForm){	//只执行一次	
		_editForm = this.boxy.find('form');		
		App.initCls(_editForm);
	}
	_validator = _editForm.validate(); 
	_editForm.resetForm();
	//去掉验证提示
	_editForm.find('font.success').hide();
	_editForm.find('font.error').hide();
	_editForm.find('input.error').removeClass('error');
}

var _operateWin;
var _operate;
function operateBaseWin(ss,operate){
	_operate=operate;
	if(!_operateWin){//只执行一次，即只实例化一个_editWin对象
		var html='<form class="winFormBox"><table>'+
				'<tr><th class="w75">父级：</th><td id="box_parentCode_copy"></td></tr>'+
				'<input type="hidden" name="parentValue" value="" />'+
				'<tr><th>分组：</th><td><input name="dictGroup"  class="required" maxlength="30" type="text"/> <span class="red">*</span></td></tr>'+
				'<tr><th class="w75">代码：</th><td><input name="dictValue" class="required" maxlength="20" type="text"/> <span class="red">*</span></td></tr>'+
				'<tr><th>文本：</th><td><input name="dictText"  class="required" maxlength="30" type="text"/> <span class="red">*</span></td></tr>'+
				'<tr><th>排序：</th><td><input name="sortNum"  class="required positiveinteger" maxlength="4" type="text"/> <span class="red">*</span></td></tr>'+
				'<tr><th>扩展1：</th><td><input name="extend1" type="text"/> </td></tr>'+
				'<tr><th>扩展2：</th><td><input name="extend2" type="text"/> </td></tr>'+
				'<tr><th>扩展3：</th><td><input name="extend3" type="text"/> </td></tr>'+
				'</table></form>';		
		_operateWin = new Boxy(html,{
			width:400,
			height:323,		
			unloadOnHide : false,
			afterShow: renderForm,//每次打开后执行
			answers : ['保 存'],	//按钮	
			callback : save,
			hideAfterCallback:false,//保存按钮点击，不自动关闭窗口
			title:'添加新项'//不设置没有标题栏，每次打开后再根据是添加还是修改重新setTitle()
		});
	}
	_operateWin.show();
	
	var title="";
	if(operate=="addson"){
		title="添加子级数据字典";
		$("#box_parentCode_copy").html("");
		$.get('getdetail' +App.ajaxActionSuffix,{dictValue:ss[1],dictGroup:ss[0],stamp: Math.random()},function(data){						
//			var oldParentValue=data.parentValue;
			var _dt=data.dictText;
			if (typeof(data.parentValue) == "undefined") { 
				_dt="顶级分类";
			}
			$("#box_parentCode_copy").html(_dt);
			$("input[name='parentValue']").val(ss[1]);
			$("input[name='dictGroup']").val(ss[0]);
			if(ss[1]=="root"){
				$("input[name='dictGroup']").removeAttr("readonly");
				$("input[name='dictGroup']").val("");
			}else{
				$("input[name='dictGroup']").attr("readonly","readonly");
			}
		});
		
//		alert(ss[0]+"|"+ss[1]+"|"+ss[2]);
	}else if(operate=="addbrother"){
		title="添加同级数据字典";
		$("#box_parentCode_copy").html("");
		$.get('getdetail' +App.ajaxActionSuffix,{dictValue:ss[2],dictGroup:ss[0],stamp: Math.random()},function(data){						
			var _dt=data.dictText;
			if (typeof(data.parentValue) == "undefined") { 
				_dt="顶级分类";
			}
			$("#box_parentCode_copy").html(_dt);
			$("input[name='parentValue']").val(ss[2]);
			$("input[name='dictGroup']").val(ss[0]);
			if(ss[2]=="root"){
				$("input[name='dictGroup']").removeAttr("readonly");
				$("input[name='dictGroup']").val("");
			}else{
				$("input[name='dictGroup']").attr("readonly","readonly");
			}
		});
	}else if(operate=="edit"){
		title="编辑当前数据字典";
		$("#box_parentCode_copy").html("");
		$.get('getdetail' +App.ajaxActionSuffix,{dictValue:ss[2],dictGroup:ss[0],stamp: Math.random()},function(data){						
			var _dt=data.dictText;
			if (typeof(data.parentValue) == "undefined") { 
				_dt="顶级分类";
			}
			$("#box_parentCode_copy").html(_dt);
			$("input[name='parentValue']").val(ss[2]);
			$("input[name='dictGroup']").val(ss[0]);
			$.get('getdetail' +App.ajaxActionSuffix,{dictValue:ss[1],dictGroup:ss[0],stamp: Math.random()},function(sdata){						
				$("input[name='dictValue']").val(sdata.dictValue);
				$("input[name='dictText']").val(sdata.dictText);
				$("input[name='sortNum']").val(sdata.sortNum);
				$("input[name='extend1']").val(sdata.extend1);
				$("input[name='extend2']").val(sdata.extend2);
				$("input[name='extend3']").val(sdata.extend3);
				$("input[name='dictGroup']").attr("readonly","readonly");
			});
		});
	}else if(operate=="enabled"){//启用
		
		
	}else if(operate=="disabled"){//停用
		
	}
	_operateWin.setTitle(title);
}

/**
 * 保存提交
 */
function save(){
	if(_editForm.valid()){//验证
		var url ="";
		if(_operate=="addson"||_operate=="addbrother"){
			url="add";
		}else if(_operate=="edit"){
			url="edit";
		}else{
			
		}
		var data = Core.FormUtils.getFormValues(_editForm);
		_editForm.ajaxSubmit({
			url:url+App.ajaxActionSuffix,
			data:{},
			type:'POST',
			success:function(result){
				if(App.isSubmitSuccess(result)){
					Core.PopUtil.alert('操作成功！',{autohide:true, callback:function(){
						_operateWin.hide();
						refresh();
						$("#tree").html("");
						initLeftPanel();
					}});				
				}
			}
		});
	}
}