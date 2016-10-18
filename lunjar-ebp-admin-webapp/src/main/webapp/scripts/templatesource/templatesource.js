/**
 * @include "../admin.js"
 */
$(function(){
	/* 上传点击 */
	var dictGroup="productType";
	
	$(".son_select_show").hide();
	$("#selectParent").change(function(){
		var parentValue = $(this).val();
		$.post("getDcitGroupByParentValue",{dictGroup:dictGroup,parentValue:parentValue},function(data){
			var _html="";
			if(data!=null&&data.length>0){
				$(".son_select_show").show();
				var data_sel=$("#productType").attr("data-sel");
				for(var i=0;i<data.length;i++){
					if(data_sel!=""&&data_sel==data[i].value){
						_html+="<option value=\""+data[i].value+"\" selected>";
					}else{
						_html+="<option value=\""+data[i].value+"\">";
					}
					_html+=data[i].text;
					_html+="</option>";
				}
			}else{
				$(".son_select_show").hide();
			}
			$("#productType").html(_html);
			$("#productType").change(function(){
				var _value=$(this).val();
				if(_value!=""){
					$.post("getVersion",{productType:_value},function(data){
						$("#version").val(data);
					});
				}else{
					$("#version").val("");
				}
			});
			$("#productType").change();
		});
	});
	$("#selectParent").change();
	
	
	
	$(".son_select_show2").hide();
	$("#selectParent2").change(function(){
		var parentValue = $(this).val();
		$.post("getDcitGroupByParentValue",{dictGroup:dictGroup,parentValue:parentValue},function(data){
			var _html="";
			if(data!=null&&data.length>0){
				$(".son_select_show2").show();
				var data_sel=$("#productType2").attr("data-sel");
				for(var i=0;i<data.length;i++){
					if(data_sel!=""&&data_sel==data[i].value){
						_html+="<option value=\""+data[i].value+"\" selected>";
					}else{
						_html+="<option value=\""+data[i].value+"\">";
					}
					_html+=data[i].text;
					_html+="</option>";
				}
			}else{
				$(".son_select_show2").hide();
			}
			$("#productType2").html(_html);
			$("#productType2").change(function(){
				var _value=$(this).val();
				if(_value!=""){
					$.post("getVersion",{productType:_value},function(data){
						$("#version2").val(data);
					});
				}else{
					$("#version2").val("");
				}
			});
			$("#productType2").change();
		});
	});
	$("#selectParent2").change();
	
	
	
	
	$('.updateStatus').click(function(){
		var sss=$(this).html();
		var data = $(this).parent().find(".data");
		var id=data.find("input[name=id]").val();
		var productType=data.find("input[name=productType]").val();
		Core.PopUtil.confirm('确定要<b style="color:#ff6600;">'+sss+'</b>模版?',function(){
			$.post("enable"+App.ajaxActionSuffix,{productType:productType,id:id},function(result){
				if(App.isSubmitSuccess(result)){
					Core.PopUtil.alert('模版<b style="color:#ff6600;">'+sss+'</b>成功！',{autohide:true, callback:function(){
						$("#form_search").submit();
					}});			
				}else{
					Core.PopUtil.error(result.msg,false);
				}
			});
		});
	});
});

/**
 * 上传模版弹出框
 */
var _editForm,_editWin;
function editWin(){	
	_editForm=$(".winFormBox");
	if(!_editWin){// 要判断一下，没有实例化过，才实例化
		_editWin = new Core.Window($('#editForm'),{
			width:400,
			height:280,
			answers:['提 交','关 闭'],			
			hideAfterCallback:false,// 保存按钮点击，不自动关闭窗口
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
		Core.FormUtils.resetForm(_editForm,true);// 每次打开窗口时，重置表单
	}
}

/**
 * 保存提交
 */
function save(){
	var _parentSelect=$("#selectParent2").val();
	var _prodcutType= $("#productType2").val();
	var _path=$("#file_path").val();
	if(_parentSelect==null||_parentSelect==""){
		Core.PopUtil.warn('请选择行业领域！',false);
	}else if(_prodcutType==null||_prodcutType==""){
		Core.PopUtil.warn('请选择业务类型！',false);
	}else if(_path==null||_path==""){
		Core.PopUtil.warn('请选择上传文件！',false);
	}else{
		var url = 'upload';
		var data = Core.FormUtils.getFormValues(_editForm);
		_editForm.ajaxSubmit({
			url:url+App.ajaxActionSuffix,
			data:{},
			type:'POST',
//			contentType: 'multipart/form-data',
//			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success:function(result){
				if(App.isSubmitSuccess(result)){
					Core.PopUtil.alert('模版<b style="color:#ff6600;">上传</b>成功！',{autohide:true, callback:function(){
						_editWin.hide();
						Core.UrlUtil.refresh();
					}});				
				}else{
					Core.PopUtil.error(result.msg,false);
				}
			}
		});
	}
}