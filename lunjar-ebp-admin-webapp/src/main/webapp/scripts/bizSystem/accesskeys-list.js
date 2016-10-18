var _editWin;
jQuery(function() {
	$('#addBizSystem').click(function() {
		$('#apiKeyForm').submit();
	})
	// 表单校验
	$("#apiKeyForm").validate({
		ignore : ':hidden',
		rules : {
			systemName : {
				required : true
			}
		},
		messages : {
			systemName : {
				required : "名称不能为空"
			}
		},
		submitHandler : function(f) {
			var formData = Core.FormUtils.getFormValues($('#apiKeyForm'));
			AncunApiClient.postForm($('#apiKeyForm').attr('action'), {
				data : formData,
				success : function(response) {
					$('#bizSystem_cl').click();
					Core.PopUtil.success("新增成功", function() {
						location.reload();
					}, {
						autohide : false
					});
				},
				fail : function(code, msg) {
					$('#bizSystem_cl').click();
					Core.PopUtil.success("新增失败", function() {
					}, {
						autohide : false
					});
				}
			});
		}
	});
})

jQuery(function($) {
	$('.addBizSystem').click(function() {
		openAddBizSystem();
	});
});

// 修改状态
function modifyStatus(id, status) {
	var operate = '';
	if (status == 1) {
		operate = '启用';
	}
	if (status == 2) {
		operate = '终止';
	}
	Core.PopUtil.confirm('<b style="color:#ff6600;">确定' + operate + '</b> ?',
			function() {
				AncunApiClient.postForm('/bizSystem/updateBizSystemstatus', {
					data : {
						id : id,
						status : status
					},
					success : function(response) {

						if (status == 1) {
							Core.PopUtil.success("启用成功!", function() {
								location.reload();
							}, {
								autohide : false
							});
						} else {
							Core.PopUtil.success("终止成功!", function() {
								location.reload();
							}, {
								autohide : false
							});
						}
					}
				});
			})
}

// 重置SecretKey
function resetSecretKey(id) {
	Core.PopUtil.confirm('<b style="color:#ff6600;">确定重置SecretKey吗</b> ?',
			function() {
				AncunApiClient.postForm('/bizSystem/resetSecretKey', {
					data : {
						id : id,
					},
					success : function(response) {
						Core.PopUtil.success("重置成功!", function() {
							location.reload();
						}, {
							autohide : false
						});
					}
				});
			})
}

var bizSystemWin;
// 打开新窗口
function openAddWin(winId, title, win, clId) {
	if (!win) {
		win = new Core.Window(winId, {
			width : 500,
			height : 300,
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

function openAddBizSystem() {
	AncunApiClient.getJson("/bizSystem/create-access-secret-key", {
		success : function(result) {
			$('#accessKey').val(result.data.split(",")[0]);
			$('#secretKey').val(result.data.split(",")[1]);
			$('#apiKeyForm').attr('action', '/bizSystem/add');
		}
	});
	openAddWin('work', ' 添加 AccessKey', bizSystemWin, "bizSystem_cl");
}

function modify(id){
	
}


