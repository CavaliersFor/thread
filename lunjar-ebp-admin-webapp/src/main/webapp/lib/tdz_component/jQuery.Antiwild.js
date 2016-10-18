
/*
// jQuery 分页插件
// auto --> Enjurokcc
*/
;(function($){
var Antiwild = function(option){
      option.pageIndex>option.pageCount?option.pageIndex=option.pageCount:null;
      option.pageSplice>option.pageCount?option.pageSplice=option.pageCount:null;
      this.pageCount  =  option.pageCount;
      this.pageIndex  =  Math.abs(parseInt(option.pageIndex));
      this.pageSplice =  option.pageSplice;
      this.indexClass =  option.indexClass;
      this.pageNode   =  option.pageNode;
      this.pageClick  =  option.pageClickFn;
      this.clickUp    =  option.pageUp;
      this.clickDown  =  option.pageDown;
      this.liNode     =  option.pageNode+' #pageGro li';//li节点
      this.limiNum    =  this.pageCount - this.pageSplice + 1;//超出循环最小基数值
      this.disNum     =  1;//错位值
      this.ajax       =  option.ajax;//ajax

      this.init();//初始化
      this.domClick();//点击页面执行相应方法
      this.pageUpClick();//上一页
      this.pageDownClick();//上一页

}

Antiwild.prototype = {

     init : function(){

      var _self = this,liFrom,liTo;   
      if(_self.pageCount>_self.pageSplice){
        //from index to splice  
        
        if(parseInt(_self.pageIndex) <= parseInt(_self.limiNum)){

          if(parseInt(_self.pageIndex-_self.disNum)<=0){//当前页<=差值
            liFrom = parseInt(_self.pageIndex);
            liTo = parseInt(_self.pageIndex) + parseInt(_self.pageSplice);
          }else{
              liFrom = parseInt(_self.pageIndex-_self.disNum);
              liTo = parseInt(_self.pageIndex) + parseInt(_self.pageSplice)-_self.disNum;
          }
           
        }else{                        
           liFrom = _self.limiNum;
           liTo = _self.pageCount+1;
        }
        
        }else{
        //from index to _self.pageCount
        liFrom = 1;
          liTo = (_self.pageCount+1);
       }
       //初始加载li所有节点+当前页 + class
        _self.forEach(liFrom,liTo,_self.pageIndex);
     
     },
     domClick : function(){//点击li
      var _self = this;
      if(_self.pageClick){
        $(document).on("click",_self.liNode,function(){
          
        if(!$(this).hasClass(_self.indexClass)){
                 var index = $(this).index();//获取当前索引
              //console.log(index);
              $(_self.liNode).removeClass(_self.indexClass);
              $(this).addClass(_self.indexClass);
              if(_self.ajax){
                if(index==(_self.pageSplice-1)){//下一页最后一个 (ajax)
                  if($(this).text()<parseInt(_self.limiNum)){//在最小基数内
                         var liFrom = parseInt($(this).text());
                         var liTo = parseInt(liFrom)+parseInt(_self.pageSplice);
                         _self.forEach(liFrom,liTo,liFrom);
                  }else{
                          liFrom = _self.limiNum;
                        liTo = _self.pageCount+1;
                        _self.forEach(liFrom,liTo,parseInt($(this).text()));
                  }
                }else if(index==0){//上一页最后一个
                  
                 if(parseInt($(this).text())>=parseInt(_self.pageSplice)){//在最小基数内
                         var liFrom = parseInt($(this).text())-parseInt(_self.pageSplice) + 1;
                         var liTo   = parseInt($(this).text())+1;
                         _self.forEach(liFrom,liTo,parseInt($(this).text()));
                  }else{
                          liFrom = 1;
                          liTo = _self.pageSplice+1;
                        _self.forEach(liFrom,liTo,parseInt($(this).text()));
                  }
                }
              }
            _self.pageClick(index);//执行外部方法
        }
        })  
      }
     },
     pageUpClick : function(){
       var _self = this;

         $(_self.pageNode).on("click",'.pageUp',function(){
           var pageNum = parseInt($(_self.liNode+'.'+_self.indexClass).text());//获取当前页
           if(pageNum<=1){pageNum=1;alert('当前为第一页');return false;};
           if(parseInt(pageNum) <= parseInt(_self.limiNum)){//在最小基数前既是Dom不必隐藏重新刷新
              var liFrom = pageNum-1;
              var liTo = parseInt(pageNum)+parseInt(_self.pageSplice)-1;
              _self.forEach(liFrom,liTo,liFrom);
           }else{
            var index = $(_self.liNode+'.'+_self.indexClass).index();//获取当前索引
              $(_self.liNode).removeClass(_self.indexClass);//清除所有选中
              $(_self.liNode).eq(index-1).addClass(_self.indexClass);//选中上一页
           }
           
           if(_self.clickUp){
               _self.clickUp();//执行外部方法
           }
       })
     },
     pageDownClick : function(){
      var _self = this;
      $(_self.pageNode).on("click",'.pageDown',function(){

        var pageNum = parseInt($(_self.liNode+'.'+_self.indexClass).text());//获取当前页
           if(pageNum>=_self.pageCount){pageNum=_self.pageCount;alert('当前为最后一页');return false;};
           //console.log(pageNum)
           if(parseInt(pageNum)<parseInt(_self.limiNum)){
              var liFrom = pageNum+1;
              var liTo   = parseInt(pageNum)+parseInt(_self.pageSplice)+1;
              _self.forEach(liFrom,liTo,liFrom);
           }else{
              var index = $(_self.liNode+'.'+_self.indexClass).index();//获取当前索引
              $(_self.liNode).removeClass(_self.indexClass);//清除所有选中
              $(_self.liNode).eq(index+1).addClass(_self.indexClass);//选中上一页
           }
           
           if(_self.clickDown){
               _self.clickDown();//执行外部方法
           }

      })

     },
     forEach : function(liFrom,liTo,eq){//循环输出节点
       var _self = this;
       var liDom = '';
       for(var i = liFrom;i<liTo;i++){
           liDom +=  '<li data-id="'+i+'">'
                     +'<a href="#nogo" data-page='+i+'>'+i+'</a>'
                     +'</li>';
         }
        var DivDom = 
        '<div class="pageUp" data-page="1"><</div>'
        +'<ul id="pageGro" class="page-items">'+liDom+'</ul>'
        +'<div class="pageDown" data-page="2">></div>';
        $(_self.pageNode).html(DivDom);
        for(var k=0;k<$(_self.liNode).length;k++){
          if( eq == parseInt($(_self.liNode).eq(k).text())){
            $(_self.liNode).eq(k).addClass(_self.indexClass);
          }
        }
     }


}
window['Antiwild'] = Antiwild;
})(jQuery)
