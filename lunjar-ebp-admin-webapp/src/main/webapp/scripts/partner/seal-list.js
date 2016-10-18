var _editWin;
jQuery(function() {
	$('#addBizSystem').click(function() {
		$('#apiKeyForm').submit();
	});
	$("#partnerId").change(function(){
		var partnerName=$("#partnerId").find("option:selected").text();
		$("#partnerName").val(partnerName);
	});
	check();
});

function check(){
	// 表单校验
	$("#apiKeyForm").validate({
		rules : {
			sealName : {
				required : true,
				maxlength : 64
			},
			identNo : {
				required : true,
				maxlength : 20
			},
			branchCode : {
				required : true,
				maxlength : 20
			},
			keyWord : {
				required : true,
				maxlength : 20
			},
			certTerm : {
				required : true,
				maxlength : 4,
				regex:/^[1-9]+[0-9]*$/
			}
			
		},
		messages : {
			sealName : {
				required : "印章不能为空",
				maxlength : "印章名称不能超过64位"
			},
			identNo : {
				required : "组织机构代码不能为空",
				maxlength : "组织机构代码不能超过20位"
			},
			branchCode : {
				required : "机构编码不能为空",
				maxlength : "机构编号不能超过20位"
			},
			keyWord : {
				required : "盖章关键字不能为空",
				maxlength : "盖章关键字不能超过20位"
			},
			certTerm : {
				required : "有效期不能为空",
				maxlength : "有效期不能超过4位",
				regex:"有效期只能为数字"
			}

		},
		submitHandler : function(f) {
			$("#bizSystem_cl").attr("disabled", false);
			var image= $("#image").val();
			var k = image.substr(image.indexOf("."));
			if(k!=""&&k!=".png"){
				Core.PopUtil.warn("上传图片只能为png图片");
				return;
			}
			$(f).ajaxSubmit({
				url : "makeSeal",
				type : 'post',
				success : function(result) {
				$("#bizSystem_cl").attr("disabled", false);
					if (App.isSubmitSuccess(result)) {
						Core.PopUtil.alert('保存成功！', {
							autohide : true,
							callback : function() {
								Core.UrlUtil.redirect("listb");
							}
						});
					}else{
						Core.PopUtil.warn(result.msg);
					}
				}
			});
		}
	});
}
var bizSystemWin;
// 打开新窗口
function openAddWin(winId, title, win, clId) {
	if (!win) {
		win = new Core.Window(winId, {
			width : 450,
			height : 450,
			unloadOnHide : false,
			title : title,
			afterShow : function() {

			}
		});
	}
	$('#' + clId).click(function() {
		win.hide();
		$("#apiKeyForm").unbind('mouseover mouseout');
		$("#apiKeyForm").removeClass('error2');
		$("#apiKeyForm")[0].reset();
		$("#sealImgUrl").empty();
	});

	win.open();
}

// 打开创建保全点弹框
function openAddBizSystem() {
	$("#apiKeyForm").unbind('mouseover mouseout');
	$("#apiKeyForm").removeClass('error2');
	$("#apiKeyForm")[0].reset();
	$("#sealName").removeClass('error2');
	$("#partnerId").removeClass('error2');
 	$(".selecterror2").hide();
	$("#identNo").removeClass('error2');
	$("#branchCode").removeClass('error2');
	$("#keyWord").removeClass('error2');
	$("#certTerm").removeClass('error2');
	$("#image").removeClass('error2');
	$("#sealImgUrl").empty();
	$("#identNo").removeAttr("readonly","readonly");
	$("#branchCode").removeAttr("readonly","readonly");
	openAddWin('work', ' 制章', bizSystemWin, "bizSystem_cl");
}

function editSeal(sealId){
	$.ajax({
		type : "get",
		url : 'getSeal',
		data : {
			"sealId" : sealId
		},
		success : function(result) {
			if (App.isSubmitSuccess(result)) {
				var seal=result.data;
				openAddWin('work', ' 修改章', bizSystemWin, "bizSystem_cl");
				$("#sealImgUrl").empty();
				$("#sealName").val(seal.sealName);
				$("#partnerName").val(seal.userName);
				$("#partnerId").val(seal.partnerId);
				$("#identNo").val(seal.identNo);
				$("#identNo").attr("readonly","true");
				$("#branchCode").val(seal.branchCode);
				$("#branchCode").attr("readonly","true");
				$("#keyWord").val(seal.keyWord);
				$("#certTerm").val(seal.certTerm);
				$("#sealId").val(seal.id);
				$("#image").removeAttr("required","required");
				if(seal.caBelongType==1){
					document.getElementById("caBelongType1").checked=true
				}else if(seal.caBelongType==3){
					document.getElementById("caBelongType3").checked=true
				}
				var storeUrl = $("#storeUrl").val();
				if(seal&&seal.sealImgUrl!=''){
					var html='<th>图片展示：</th><td>'
						+ '<img width="60px" height="60px" alt="" src="'+(storeUrl+"/"+seal.sealImgUrl)+'" />'
						+ '</td>';
					$("#sealImgUrl").append(html);
				}
			}
		}
	});
}

function delSeal(sealId){
	
	Core.PopUtil.confirm('确定删除印章？', function() {
		$.ajax({
			type : "post",
			url : 'delSeal',
			data : {
				"sealId" : sealId
			},
			success : function(result) {
				if (App.isSubmitSuccess(result)) {
					Core.PopUtil.alert('删除成功！', {
						autohide : true,
						callback : function() {
							Core.UrlUtil.redirect("listb");
						}
					});
				}
			}
		});
	});
}
