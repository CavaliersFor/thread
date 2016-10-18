/**
* @include "../admin.js"
 */
$(function(){
	//选择行业领域
	$("#selectParent").change(function(){
		var productType = $(this).val();
		$.post("getProductList",{productType:productType},function(data){
			var _html="";
			if(data!=null&&data.length>0){
				for(var i=0;i<data.length;i++){
					_html+="<option value=\""+data[i].id+"\">";
					_html+=data[i].productName;
					_html+="</option>";
				}
				$("#sel_product").html(_html);
			}else{
				$("#sel_product").html("");
			}
			$("#sel_product").change();
		});
	});
	
	$("#sel_product").change(function(){
		var productId = $(this).val();
		$.post("getItemList",{productId:productId},function(data){
			var _html="";
			if(data!=null&&data.length>0){
				for(var i=0;i<data.length;i++){
					_html+="<option isMultipaths=\""+data[i].isMultipaths+"\" value=\""+data[i].id+"\">";
					_html+=data[i].itemName;
					_html+="</option>";
				}
				$("#sel_item").html(_html);
				$("#sel_item").change();
			}else{
				$("#_dlc").html("缺少保全点");
				$("#sel_item").html("");
			}
		});
	});
	
	$("#sel_item").change(function(){
		var isMultipaths=$(this).find("option:checked").attr("isMultipaths");
		if(isMultipaths=="1"){
			
			var itemId = $(this).val();
			$.post("getFlowList",{itemId:itemId},function(data){
				var _html="";
				if(data!=null&&data.length>0){
					for(var i=0;i<data.length;i++){
						_html+="<option value=\""+data[i].id+"\">";
						_html+=data[i].flowName;
						_html+="</option>";
					}
					$("#sel_flow").html(_html);
				}else{
					$("#sel_flow").html("");
				}
			});
			
			$("#tr_flow").show();
			$("#_dlc").html("多流程");
			$("#sel_flow").change();
		}else{
			$("#sel_flow").html("");
			
			$("#_dlc").html("单流程");
			$("#tr_flow").hide();
			var productId=$("#sel_product").val();
			var itemId=$("#sel_item").val();
			$.post("getVersion",{productId:productId,itemId:itemId},function(data){
				$("#version").val(data);
			});
		}
	});
	
	$("#sel_flow").change(function(){
		var productId=$("#sel_product").val();
		var itemId=$("#sel_item").val();
	    var flowId=$("#sel_flow").val();
		if(itemId!=""){
			$.post("getVersion",{productId:productId,itemId:itemId,flowId:flowId},function(data){
				$("#version").val(data);
			});
		}else{
			$("#version").val("");
		}
	});
	
});
/**
 * 上传模版弹出框
 */
var _editForm,_editWin;
function editWin(){	
	_editForm=$(".winFormBox");
	if(!_editWin){//要判断一下，没有实例化过，才实例化
		_editWin = new Core.Window($('#editForm'),{
			width:400,
			height:280,
			answers:['提 交','关 闭'],			
			hideAfterCallback:false,//保存按钮点击，不自动关闭窗口
			afterShow: initForm,	
			title:'上传模版窗口',
			callback:function(btn){
				if(btn == '提 交'){
					save();
				}else{
					_editWin.hide();
				}
			}
		});
	}
	_editWin.show();
	function initForm(){
		Core.FormUtils.resetForm(_editForm,true);//每次打开窗口时，重置表单
	}
}


/**
 * 启用时间弹出框
 */
var _editForm2,_editWin2;
function editWin2(obj){
	_editForm2=$(".winFormBox2");
	if(!_editWin2){//要判断一下，没有实例化过，才实例化
		_editWin2 = new Core.Window($('#editForm2'),{
			width:400,
			height:100,
			answers:['提 交','关 闭'],			
			hideAfterCallback:false,//保存按钮点击，不自动关闭窗口
			afterShow: initForm2,	
			title:'启用时间弹出框',
			callback:function(btn){
				if(btn == '提 交'){
					save2();
				}else{
					_editWin2.hide();
				}
			}
		});
	}
	_editWin2.show();
	$("#input_template_id").val(obj);
	function initForm2(){
		Core.FormUtils.resetForm(_editForm2,true);//每次打开窗口时，重置表单
	}
}

function deleteTemplate(obj){
	Core.PopUtil.confirm('确定<b style="color:#ff6600;">删除</b>模版?',function(){
		$.post("delete"+ App.ajaxActionSuffix,{id:obj},function(result){
			if(App.isSubmitSuccess(result)){
				Core.PopUtil.alert('模版<b style="color:#ff6600;">删除</b>成功!'
						,{autohide:true, callback:function(){
					Core.UrlUtil.refresh();
				}});				
			}
		});
	});
}

/**
 * 保存提交
 */
function save2(){
	var _id=$("#input_template_id").val();
	var _gmtStart= $("#input_gmt_start").val();
	if(_gmtStart==null||_gmtStart==""){
		Core.PopUtil.warn('没选择启用时间！',false);
	}else if(_id==null||_id==""){
		Core.PopUtil.warn('缺少模版id！',false);
	}else{
		var url = 'edit';
		var data = Core.FormUtils.getFormValues(_editForm2);
		_editForm2.ajaxSubmit({
			url:url+App.ajaxActionSuffix,
			data:data,
			type:'POST',
			success:function(result){
				if(App.isSubmitSuccess(result)){
					Core.PopUtil.alert('模版<b style="color:#ff6600;">启用</b>时间<b style="color:#ff6600;">'+_gmtStart+'</b> 设置成功!'
							,{autohide:true, callback:function(){
						Core.UrlUtil.refresh();
					}});
				}
			}
		});
	}
}

/**
 * 保存提交
 */
function save(){
//	if(_editForm.valid()){//验证
	var _parentSelect=$("#selectParent").val();
	var _productId= $("#sel_product").val();
	var _itemId= $("#sel_item").val();
	var _isMultipaths=$("#sel_item").find("option:checked").attr("isMultipaths");
	var _flowId= $("#sel_flow").val();
	var _path=$("#file_path").val();
	if(_parentSelect==null||_parentSelect==""){
		Core.PopUtil.warn('请选择<b style="color:#ff6600;">行业领域</b>！',false);
	}else if(_productId==null||_productId==""){
		Core.PopUtil.warn('请选择<b style="color:#ff6600;">产品</b>！',false);
	}else if(_itemId==null||_itemId==""){
		Core.PopUtil.warn('请选择<b style="color:#ff6600;">保全点</b>！',false);
	}else if(_isMultipaths=="1"&&(_flowId==null||_flowId=="")){
		Core.PopUtil.warn('请选择<b style="color:#ff6600;">步骤</b>！',false);
	}else if(_path==null||_path==""){
		Core.PopUtil.warn('请选择<b style="color:#ff6600;">上传文件</b>！',false);
	}else{
		var url = 'upload';
		var data = Core.FormUtils.getFormValues(_editForm);
		_editForm.ajaxSubmit({
			url:url+App.ajaxActionSuffix,
			data:{},
			type:'POST',
//			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success:function(result){
				if(App.isSubmitSuccess(result)){
					Core.PopUtil.alert('模版<b style="color:#ff6600;">上传</b>成功！',{autohide:true, callback:function(){
						Core.UrlUtil.refresh();
					}});				
				}else{
					Core.PopUtil.error(result.msg,false);
				}
			}
		});
	}
}