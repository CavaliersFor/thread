function changeStatus(id, status) {
//	var shtml = $("#status" + sp).html();
	var val = "";
	var operate;
	var opMsg;
	if (status == 1) {
		val = 2;
		operate = "确定要下线吗？";
		opMsg = "下线操作成功!";
	} else  {
		val = 1;
		operate = "确定要发布吗？";
		opMsg = "发布操作成功!";
	}
	
	Core.PopUtil.confirm(operate ,function(){
	    $.ajax({
	        url : "updateStatus",
	        type : 'post',
	        data:{
	            id:id,
	            newStatus : val
	        },
	        success : function(result) {
	            if (App.isSubmitSuccess(result)) {
	                Core.PopUtil.alert(opMsg, {
	                    autohide : true,
	                    callback : function() {
	                    	location.reload();
	                    }
	                });
	            }
	        }
	    });
	})
//	$.post('updateStatus', {
//				id : sp,
//				newStatus : val
//			}, function(data) {
//				 
//			});
//	document.location.reload(true);
//	location.reload();
}

