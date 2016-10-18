/**
* @include "../admin.js"
 */
var
 /**
 * 状态常量
 */
DataRegionStatus={
	/**正常*/
	NORMAL:"1",
	/**失效*/
	DELETED:"-1"
},
isUpdateOrAdd=true,
_oldParentCode,
_editWin,
_code,
_parentCode=1,
_parentName,
_currendData
;
/** 父级地区的查询参数，在vm中赋值 */
var _queryParentCode = null;

jQuery(function($) {
	var regionTree = new Ext.ux.ComboBoxTree({
		hiddenName:'parentCode',
		width:162,
		listWidth:200,
		dataUrl:App.ActionUrl.getRegionTree(),
		folderSelectable:true,
		rootValue:App.REGION_ROOT_CODE,
		showFullPath:false,
		params:{level:4}
	});
	
	regionTree.render('box_parentCode');	
	if(_queryParentCode){
		var codePath = Core.RegionUtil.getCodeFullPath(_queryParentCode);
		regionTree.selectPath(codePath);
	}	
	
	$('.edit').click(function(){
		var selected = getSelectedRow(this);
		isUpdateOrAdd=true;
		openEditWin(selected.data);
	});
	$('.addChild').click(function(){
		isUpdateOrAdd=false;
		openEditWin(null);
		$("#ext_code").removeAttr("readonly");
	});
	$('.updateStatus').click(updateStatus);	
});

function selectCodeValue(value){
	$("#oldParentCode").val(value);
	var v = value;
	if(v==1){
		$("#code_begin").html("");
		$("#code_end").html("0000");
		if(isUpdateOrAdd){
			$("#ext_code").val(s1);
		}else{
			$("#ext_code").val("");
		}
	}else{
		var _v ="00";
		var s1=Ext.util.Format.substr(v,0,2);
		var s2=Ext.util.Format.substr(v,2,2);
		var s3=Ext.util.Format.substr(v,4,2);
		if(s2==_v&&s3==_v){
			$("#code_begin").html(s1);
			$("#code_end").html("00");
			if(isUpdateOrAdd){
				$("#ext_code").val(s1);
			}else{
				$("#ext_code").val("");
			}
		}else if(s2!=_v&&s3==_v){
			$("#code_begin").html(s1+s2);
			$("#code_end").html("");
			if(isUpdateOrAdd){
				$("#ext_code").val(s3);
			}else{
				$("#ext_code").val("");
			}
		}else{
			$("#code_begin").html(s1+s2);
			$("#code_end").html("");
			if(isUpdateOrAdd){
				$("#ext_code").val(s3);
			}else{
				$("#ext_code").val("");
			}
		}
	}
}
function editCodeValue(value){
	$("#oldParentCode").val(value);
	var v = value;
	if(v==1){
		$("#code_begin").html("");
		$("#code_end").html("0000");
		if(isUpdateOrAdd){
			$("#ext_code").val(s1);
		}else{
			$("#ext_code").val("");
		}
	}else{
		var _v ="00";
		var s1=Ext.util.Format.substr(v,0,2);
		var s2=Ext.util.Format.substr(v,2,2);
		var s3=Ext.util.Format.substr(v,4,2);
		if(s2==_v&&s3==_v){
			$("#code_begin").html("");
			$("#code_end").html("0000");
			if(isUpdateOrAdd){
				$("#ext_code").val(s1);
			}else{
				$("#ext_code").val("");
			}
		}else if(s2!=_v&&s3==_v){
			$("#code_begin").html(s1);
			$("#code_end").html(s3);
			if(isUpdateOrAdd){
				$("#ext_code").val(s2);
			}else{
				$("#ext_code").val("");
			}
		}else{
			$("#code_begin").html(s1+"00");
			$("#code_end").html("");
			if(isUpdateOrAdd){
				$("#ext_code").val(s3);
			}else{
				$("#ext_code").val("");
			}
		}
	}
}

function getSelectedRow(e){
	var tr = $(e).parents('tr');
	var dataBox = tr.find('span.data');	
	var data = Core.FormUtils.getFormValues(dataBox);	
	return {
		tr:tr,
		data:data
	};
}

function updateStatus(){
	var selected = getSelectedRow(this);
	var data = selected.data;
	var $this = $(this);
	if(data.code){
		var opname = $this.text();
		var regionName = data.regionName;
		Core.PopUtil.confirm('确定'+opname+'地区 <b style="color:#ff6600;">'+regionName+'</b> ?',function(){
			//var tr = selected.tr;	
			var newStatus;
			if(data.currentStatus == 1){
				newStatus = -1;
			}else{
				newStatus = 1;
			}
			data.status = newStatus;
			Core.appendLoadingIcon($this);			
			$.post(
				'updateStatus',
				data,
				function(v){
					Core.removeLoadingIcon($this);
					if(App.isSubmitSuccess(v)){
						//Core.PopUtil.alert(
							//'地区 <b style="color:#ff6600;">'+regionName+'</b> '+opname+'成功!',{
							//autohide:true,
							//callback:function(){
								//console.log(selected.tr);
								var tr = selected.tr;
								tr.find('input[name=currentStatus]').val(newStatus);
//								var statusText = App.getDataDictByGroupAndValue('regionStatus',newStatus)
								var statusText = '<font color='+ (newStatus == DataRegionStatus.NORMAL ? 'green>正常':'red>失效')+'</font>';
								tr.find('td.statusText').html(statusText);
								
								if(newStatus == 1){//按钮
									$this.text('停用');
								}else{
									$this.text('启用');
								}
							//}
						//});	
					}
				}
			);				
		});
	}
}
/**打开表单窗口*/
function openEditWin(data){
	if(data!=null){
		_currendData=data;
		_code =data.code;
		_parentCode=data.parentCode;
	}else{
		_code=1;
		_parentCode=1;
	}
	var regionTreeCopy = new Ext.ux.ComboBoxTree({
		hiddenName:'parentCode',
		width:162,
		listWidth:200,
		height: 180,
		dataUrl:App.ActionUrl.getRegionTree(),
		root: {id:1, text:'中国', expanded: true},
		rootVisible: true,
		rootSelectable:true,
		folderSelectable:true,
		leafSelectable:true,
		showFullPath:false,
	    editable: false,  
		allowBlank: false,
		rootValue: 1,
		value: 1,
		params:{level:3},
		setValue: function(value){  
			if (Ext.isObject(value)){ //点击树节点时的选择  
	            this.doSetValue(value);  
	        }  
	        else{ //只是设置一个值，从后台获取这个值的路径，并在树中选中这个节点  
	            //console.log(value);  
	            if (!this.tree) this.initTree();  
	            if (value === this.tree.root.id ||   
	                    (Ext.isDefined(this.rootValue) && value === this.rootValue)){ //根节点  
	                this.tree.root.select();  
	                this.doSetValue(this.root);  
	                return;  
	            }
	            var url = this.nodePathUrl;  
	            if (!url){  
	                this.doSetValue({id: value});  
	                return;  
	            }  
	        }  
			var node=this.tree.getSelectionModel().getSelectedNode();//当前节点
			if(node.text=="[请选择]"){
				this.tree.root.select();  
                this.doSetValue(this.root);  
                return;
			}
		    selectCodeValue(node.id);
		}
	});
	if(!_editWin){//只执行一次，即只实例化一个_editWin对象
		var html='<form class="winFormBox"><table>'+
				'<tr><th class="w75">父级(省/市)：</th><td id="box_parentCode_copy"></td></tr>'+
				//(isUpdateOrAdd?
						'<tr><th>代码：</th><td><span id="code_begin"></span><input id="ext_code" class="required w30" maxlength="2" type="text"/><span id="code_end">0000</span><input id="region_code" name="code" class="required regionCode" type="hidden" /><span class="red">*</span></td></tr>'
				//:'<tr><th>代码：</th><td><input id="ext_code" name="code" class="required w150 regionCode" maxlength="6" type="text"/><span class="red">*</span></td></tr>')
				+
				'<tr><th>名称：</th><td><input name="regionName"  class="required  w150" maxlength="30" type="text"/> <span class="red">*</span></td></tr>'+				
				//'<tr><th>父级：</th><td><div id="box_formParentCode" class="fl"></div><span class="red ml5">*</span></td></tr>'+	
				'<tr><th>邮编：</th><td><input name="zip"  class="postCode  w150" maxlength="6" type="text"/></td></tr>'+		
				'<tr><td colspan="2" class="red">注意：代码"0000"结尾代表某省，代码"00"结尾代表某市，其他为区/县</td></tr>'+
				'</table><input name="oldParentCode" type="hidden" /></form>';		
		_editWin = new Boxy(html,{
			width:550,
			height:180,		
			unloadOnHide : false,	
			afterShow: renderForm,//每次打开后执行
			answers : ['保 存'],	//按钮		
			callback : save,
			hideAfterCallback:false,//保存按钮点击，不自动关闭窗口
			title:'添加地区'//不设置没有标题栏，每次打开后再根据是添加还是修改重新setTitle()
		});
	}
	if(isUpdateOrAdd){
		_editWin.setTitle('修改地区');
	}else{
		_editWin.setTitle('添加地区');
	}
	
	_editWin.show();
	if(data==null){
		//添加
		$("#box_parentCode_copy").html("");
		regionTreeCopy.render('box_parentCode_copy');
	}else{
		//修改
		$.get('getdetail',{code:_parentCode},function(data){
			$("#box_parentCode_copy").html("");
			if(data!=null&&data.regionName!=null){
				_parentName=data.regionName;	
			}else{
				_parentName='中国';
			}
			$("#box_parentCode_copy").html(_parentName+'&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="editPreSelect();">修改</a>');
			$("#parentCode").val(_parentCode);
		});
		//regionTreeCopy.render('box_parentCode_copy');
	}
}

function editPreSelect(){
	var regionTreeCopy = new Ext.ux.ComboBoxTree({
		hiddenName:'parentCode',
		width:162,
		listWidth:200,
		height: 180,
		dataUrl:App.ActionUrl.getRegionTree(),
		root: {id:1, text:'中国', expanded: true},
		rootVisible: true,
		rootSelectable:true,
		folderSelectable:true,
		leafSelectable:true,
		showFullPath:false,
	    editable: false,  
		allowBlank: false,
		rootValue: 1,
		value: 1,
		params:{level:3},
		setValue: function(value){  
	        if (Ext.isObject(value)){ //点击树节点时的选择  
	            this.doSetValue(value);  
	        }  
	        else{ //只是设置一个值，从后台获取这个值的路径，并在树中选中这个节点  
	            //console.log(value);  
	        	
	            if (!this.tree) this.initTree();  
	            if (value === this.tree.root.id ||   
	                    (Ext.isDefined(this.rootValue) && value === this.rootValue)){ //根节点  
	                this.tree.root.select();  
	                this.doSetValue(this.root);  
	                return;  
	            }
	            var url = this.nodePathUrl;  
	            if (!url){  
	                this.doSetValue({id: value});  
	                return;  
	            }  
	        }  
	        var node=this.tree.getSelectionModel().getSelectedNode();//当前节点
	        selectCodeValue(node.id);
		}
	});
	$("#box_parentCode_copy").html("");
	regionTreeCopy.render('box_parentCode_copy');
}
var _editForm;
/**
 * 渲染窗口,每次打开后执行
 */
function renderForm(){
	if(!_editForm){	//只执行一次	
		_editForm = this.boxy.find('form');		
		App.initCls(_editForm);
	}
	_editForm.validate();
	_editForm.resetForm();
	//去掉验证提示
	_editForm.find('div.success').hide();
	_editForm.find('div.error').hide();
	_editForm.find('input.error').removeClass('error');
	
	var inp_code = _editForm.find('input[name=code]');
	var inp_codeRequierdTag = inp_code.next('.red');
	if(isUpdateOrAdd){//修改
		inp_code.attr('readonly',true)//修改时，代码不可编辑,并去掉验证
		inp_codeRequierdTag.hide();
		Core.FormUtils.setFormValues(_editForm,_currendData);
		editCodeValue(_code);
//		this.setTitle('修改地区1');
	}else{
		inp_code.removeAttr('readonly');//添加时，代码可编辑,并加上验证
		inp_codeRequierdTag.show();	
//		this.setTitle('添加');
	}
	App.initCls(inp_code.parent());//给地区代码输入框设置样式
}

/**
 * 保存提交
 */
function save(){
	if(_editForm.valid()){//验证
		$("#region_code").val($("#code_begin").html()+$("#ext_code").val()+$("#code_end").html());
		var url = isUpdateOrAdd ? 'update' : 'add';
		_editForm.ajaxSubmit({url:url+App.ajaxActionSuffix,type:'POST',success:function(result){			
			if(App.isSubmitSuccess(result)){
				Core.PopUtil.alert('操作成功！',{autohide:true, callback:function(){					
					_editWin.hide(refresh());
				}});				
			}
		}});
	}
}

function refresh(){
	Core.UrlUtil.refresh();
}