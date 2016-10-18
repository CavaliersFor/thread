var _form, _submit;
jQuery(function($) {
	$('.edit').click(function() {
		openEditWin();
	});
	
	$('#editProduct').click(function() {
		$('#productForm').submit();
	})
	// 表单校验
	$("#productForm").validate({
		ignore : ':hidden',
		rules : {
			"product.encryptType" : {
				required : true
			},
			"product.storePosition" : {
				required : true
			},
			"product.startTime" : {
				required : true
			},
			"product.endTime" : {
				required : true
			}
		},
		messages : {
			"product.encryptType" : {
				required : "请选择数据加密算法"
			},
			"product.storePosition" : {
				required : "请选择存储地点"
			},
			"product.startTime" : {
				required : "起始时间不能为空"
			},
			"product.endTime" : {
				endTime : "结束时间不能为空"
			}
		},
		submitHandler : function(f) {
			var formData = Core.FormUtils.getFormValues($('#productForm'));
			AncunApiClient.postForm($('#productForm').attr('action'), {
				data : formData,
				success : function(response) {
					$('#product_cl').click();
					Core.PopUtil.success("操作成功", function() {
						location.reload();
					}, {
						autohide : false
					});
				},
				fail : function(code, msg) {
					$('#product_cl').click();
					Core.PopUtil.success("操作失败", function() {
					}, {
						autohide : false
					});
				}
			});
		}
	});
});

var productWin;
//打开新窗口
function openAddWin(winId, title, win, clId) {
	if (!win) {
		win = new Core.Window(winId, {
			width : 500,
			height : 320,
			unloadOnHide : false,
			title : title,
			afterShow : function() {

			}
		});
	}
	$('#' + clId).click(function() {
		win.hide();
	});

	win.open();
}
//打开创建保全点弹框
function openEditWin() {
	$('#productForm').attr('action', '/product/update');
	openAddWin('work', ' 产品设置', productWin , "product_cl");
}

function callback(productId, result) {
	if (App.isSubmitSuccess(result)) {
		_submit.val('处理成功');
		Core.PopUtil.success('操作成功! ', function() {
			Core.UrlUtil.redirect('list');
		});
	} else {
		_submit.val('重 试').removeAttr('disabled');
	}
}


// 修改状态
function modifyStatus(itemId, status, type) {
	var operate, msg;
	if(status == 1 && type == 1) {
		operate = "确定重启保全点？";
		msg = "保全点启用成功!";
	}
	if(status == 1 && type == 2) {
		operate = "确定开通个人章？";
		msg = "个人章开通成功!";
	}
	if(status == 2 && type == 1) {
		operate = "确定中断保全点？";
		msg = "保全点中断成功!";
	}
	if(status == 0 && type == 2) {
		operate = "确定关闭个人章？";
		msg = "个人章关闭成功!";
	}
	Core.PopUtil.confirm(operate ,function(){
		AncunApiClient.postForm('/product-item/update', {
			data : {
				id : itemId,
				status : status,
				type: type
			},
			success : function(response) {
				Core.PopUtil.success(msg, function() {
					location.reload();
				}, {
					autohide : false
				});
			}
		});
	})
}

/*// 增加黑名单列表
function addIpBlack(productId) {
	var ipBlack = $('#ipBlack').val();

	if(validateIp(ipBlack)){
		AncunApiClient.postForm('/product/addIpBlack', {
			data : {
				id : productId,
				status : status,
				ipBlack : ipBlack
			},
			success : function(response) {
				Core.PopUtil.success("添加成功!", function() {
					location.reload();
				}, {
					autohide : false
				});
			}
		});
	}else {
		Core.PopUtil.success("输入的ip地址不合法!", function() {
		}, {
			autohide : false
		});
	}
}

function   validateIp(s){  
    var   arr=s.match(/^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/);  
    if(arr==null)return   false;  
    for(i=1;i<arr.length;i++)if(String(Number(arr[i]))!=arr[i]||Number(arr[i])>255)return   false;  
    return   true;  
}  

function closeIpBlack(o) {
	if(o==1) {
		$('#black').removeAttr("disabled");
	}
	if(o==0) {
		$('#black').readonly=true;
	}
}*/