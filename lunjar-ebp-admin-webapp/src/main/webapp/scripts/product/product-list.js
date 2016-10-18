jQuery(function(){
	
});


var _win;
function openWin(){
	if(!_win){
		_win = new Core.Window('selectForm',{
			width:500,
			height:300,
			unloadOnHide : false,
			title : '基础服务授权',
			afterShow: function(){	
				
			}			
		});	
	}
	_win.open();
}

					
function grantService(productId){
	$('#productId').val(productId);
	AncunApiClient.getJson("/product/getApiTypes", {
		data : {productId:productId},
		success : function(result) {
			var serviceCode = result.data;
			if(serviceCode){
				var codeArr = new Array(serviceCode.length);
				for (i=0;i<serviceCode.length ;i++ ) 
				{ 
					codeArr[i] = serviceCode.substr(i,1);
				} 
				$.each(codeArr,function(n,value) {  
					if(value == 0){
						$('#ck_' + n).removeAttr("checked");
					}else{
						$('#ck_' + n).prop("checked",true);
					}
            	});  
				openWin();
			}
		}
	});
}



function closeWin(){
	_win.hide();
}


function changeStatus(id, productName, status, type){
	var operate;
	if(status == 1 && type == 1) {
		operate = "确定恢复产品["+productName+"]接入?";
	}
	if(status == 1 && type == 2) {
		operate = "确定开通公司章?";
	}
	if(status == 2 && type == 1) {//终止接入
		operate = "终止介入后，产品["+productName+"]将无法通过保全接口推送保全数据";
		operate = operate +'  确定终止接入 <b style="color:#ff6600;">(非常重要，请谨慎操作)</b> ?';
	}
	if(status == 0 && type == 2) {//终止接入
		operate = "确定关闭公司章?";
	}
	Core.PopUtil.confirm(operate ,function(){
		var backUrl = $('#form_search').attr('action');
	    $.ajax({
	        url : "update-status",
	        type : 'post',
	        data:{
	            id:id,
	            status:status,
	            type:type
	        },
	        success : function(result) {
	            if (App.isSubmitSuccess(result)) {
	                Core.PopUtil.alert('操作成功！', {
	                    autohide : true,
	                    callback : function() {
	                        Core.UrlUtil.redirect(backUrl);
	                    }
	                });
	            }
	        }
	    });
	})
}