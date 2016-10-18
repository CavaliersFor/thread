/**
 * 分页方法
 * 
 */
var PAGE = {
	createPage : function(pageCount, pageIndex,id) {
		var pageCount = pageCount;// $!trades.pageCount;//模拟后台总页数
		var pageIndex = pageIndex;// $trades.pageNo;//当前第几页变量
		var pageSplice = 5;// 分页显示个数
		var pageNo = pageIndex;
		var pgSp = new Antiwild({
			pageCount : pageCount,// 后台总页数
			pageIndex : pageIndex,// 当前页 >=1 <=pageCount
			pageSplice : pageSplice,// 分页显示个数 > 1
			indexClass : 'active',// 当前页显示class
			pageNode : '#FenYe',// 导入的Dom id or class
			pageClickFn : function(index) {// 点击页面执行方法
				// console.log(index);//索引
				PAGE.pageNext("3", (parseInt($('#FenYe li a').eq(index).text())),pageCount,id);
			},
			pageUp : function() {// 点击上一页执行方法
				// console.log('上一页');
				PAGE.pageNext("1", pageNo,pageCount,id);
			},
			pageDown : function() {// 点击下一个执行方法
				// console.log('下一页');
				PAGE.pageNext("2", pageNo,pageCount,id);
			}
		})
	},
	pageNext:function pageNext(a,b,pageCount,id){
	    var nextPageNo = 0;
	    if(a==1){
	      nextPageNo = b-1;
	      //上一页
	      if(nextPageNo<1){
	        //alert("已经是第一页了");
	        return ;
	      }
	    }else if(a==2){
	      //下一页
	      nextPageNo = b+1;
	      if(nextPageNo>pageCount){
	        //alert("已经是最后一页了");
	        return ;
	      }
	    }else if(a==3){
	      //
	      nextPageNo = b;
	    }
	    jQuery("#pageNo").val(nextPageNo);
	    jQuery("#"+id).submit();
	  }
};