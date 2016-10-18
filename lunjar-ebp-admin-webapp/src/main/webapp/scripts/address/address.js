

/**
 * 地址相关的js
 */
var ADDRESS = {
	ohtml :"<option value='0'>请选择</option>",
	loadCity : function() {
		var proEl = $("#provinceDiv option:selected");
		var provinceId = proEl.val();
		var provinceName = proEl.text();
		if (provinceId != 0) {
			jQuery.ajax({
						url : "/address/getCity",
						data : "code=" + provinceId,
						type : "POST",
						dataType : "json",
						success : function(data) {
							if (data != null && data.success) {
								var cityHtml = ADDRESS.ohtml;
								if (data.list) {
									for (var i = 0; i < data.list.length; i++) {
										cityHtml += "<option value='"
												+ data.list[i].code + "'>"
												+ data.list[i].regionName
												+ "</option>";
									}
									$("#cityDiv").html(cityHtml);
									$("#countyDiv").html(ADDRESS.ohtml);
								}
							} else {
								jQuery.alert({
									type : 'error',
									text : data.msg,
									time : 3000
								});
							}
						},
						error : function() {
							jQuery.alert({
								type : 'error',
								text : '操作失败,请稍后重试!',
								time : 3000
							});
						}
					});
		}
	},
	loadCounty:function(){
		var cityEl = $("#cityDiv option:selected");
		var cityId = cityEl.val();
		if(cityId!=0){
		jQuery.ajax({
			url:"/address/getRegion",
			data:"code="+cityId,
			type:"POST",
			dataType:"json",
			success:function(data){
				if(data!=null && data.success){
					var cityHtml = ADDRESS.ohtml;
					if(data.list){
						for(var i=0; i<data.list.length;i++){
							cityHtml += "<option value='" + data.list[i].code + "'>" + data.list[i].regionName + "</option>";
						}
						$("#countyDiv").html(cityHtml);
					}
				}else{
					jQuery.alert({
						type: 'error',
						text: data.msg,
						time: 3000
					});
				}
			},
			error:function(){
				jQuery.alert({
					type: 'error',
					text: '操作失败,请稍后重试!',
					time: 3000
				});
			}
		});
		}
	}
};