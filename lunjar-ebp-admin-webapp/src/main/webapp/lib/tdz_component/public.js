/*
* 公共方法 @Enjurokcc
 */

;(function($){
//静态方法
var StaticFn = {
       listLastAddClass : function(obj,num,className){//给同一组所有元素按当前数量分割后的最后一个增加Class
          var lien = $(obj).length;
          var lastNum = (lien/num);//num为复数 4 8 12
          for(var i=1;i<=lastNum;i++){
            $(obj).eq(num*i-1).addClass(className);
          }
      },
      strTrim :function(str){ //删除左右两端的空格
　　     return str.replace(/(^\s*)|(\s*$)/g, "");
　　   },
      removeStrLast : function(s){//删除字符串最后一个
        s = s.substring(0,s.length-1);
        return s;
      },
      appositiveMerge:function(arr1,arr2){//两个数组 同位两两合并 str分隔符
         var arr = [],le;var num = 0;
         (arr1.length>arr2.length)?le=arr2.length:le=arr1.length;
           if(arr1.length>1&&arr2.length>1){
           for(var i=0;i<(le/2);i++){//6
            arr.push(arr1[num]+';'+arr1[num+1]+'&'+arr2[num]+';'+arr2[num+1]);
            num+=1;
           }
         }
        return arr;
      },
      skipEmptyArray : function(arr){//数组去空
          var a = [];  
          $.each(arr,function(i,v){  
              var data = $.trim(v);//$.trim()
              if('' != data){  
                  a.push(data);  
              }  
          });  
          return a;  
        },
      unique : function(arr) {//数组去除重复项
        var result = [], hash = {};
        for (var i = 0, elem; (elem = arr[i]) != null; i++) {
            if (!hash[elem]) {
                result.push(elem);
                hash[elem] = true;
            }
            return result;
          }
         },
      uniqueArr : function(arry){
          var temp = [];
          arry.sort();//先排序，前项比后项
              for(i = 0; i < arry.length; i++) {
                  if( arry[i] == arry[i+1]) {
                    continue;//跳开本次循环继续下一次循环
                }
                  temp[temp.length]=arry[i];
              }
          return temp;
      },
      arrayIndexOf : function(arr,val) {//查找数组某个值得索引
        for (var i = 0; i < arr.length; i++) {
          if (arr[i] == val) return i;
        }
        return -1;
      },
      arrRemoveIndex : function(arr,val) {//删除数组摸个位置的值 但不替换
        var index = $.arrayIndexOf(arr,val);
        if (index > -1) {
          arr.splice(index, 1);//索引
         }
        },
      yzmTimeLoding : function(obj){//发送短信获取验证码倒计时
        //获取短信验证码
        var validCode=true;
        $(obj).click(function(){
            var time=30;
            var code=$(this);
            if(validCode){
              validCode=false;
              code.addClass("Hui");
            var t = setInterval(function(){
              time--;
              code.html('剩余'+time+"秒");
              if (time==0) {
                clearInterval(t);
              code.html("重新获取");
              validCode = true;
              code.removeClass("Hui");
              }
            },1000)
            }
         })
      },
      addMarkPage : function(Name,string){//页面统一增加标记方法,以判断返回路径
        if(!isIE()){
               localStorage.setItem(Name,string); 
            }else{
                //cookie_jq(Name,string,{expires:1,path:'/'});//全局路径
            }
      },
      getMarkStringThis : function(string){
       var _value;
       if(!isIE()){
              _value = localStorage.getItem(string);
            
          }else{
            //_value = cookie_jq(string);
          }
          return _value;
      },
      combineArr :  function(arr){//二维数组组合方法
        if(arr.length>1){
            var len1=arr[0].length, len2=arr[1].length, newArr=arr.slice(0), temp=[];
            for(var i=0;i<len1;i++){
                for(var j=0;j<len2;j++){
                    temp.push(arr[0][i]+arr[1][j])
                }
            }
            newArr.splice(0,2,temp);
            return arguments.callee(newArr)
        }
        return arr[0]
       },
       combineArrAddStr :  function(arr,str){//二维数组组合方法 中间拼接字符串
        if(arr.length>1){
            var len1=arr[0].length, len2=arr[1].length, newArr=arr.slice(0), temp=[];
            for(var i=0;i<len1;i++){
                for(var j=0;j<len2;j++){
                    temp.push(arr[0][i]+str+arr[1][j])
                }
            }
            newArr.splice(0,2,temp);
            return arguments.callee(newArr)
        }
        return arr[0]
       },
      changeLeftArr : function(arr,index){//数组移动 左 或 上 1-[] 2-当前数组索引
          var temp;
          if(index===0||index>arr.length-1){
              return arr;
          }
          temp = arr[index];
          arr[index] = arr[index-1];
          arr[index-1] = temp;
          return arr; 
       },
      changeRightArr : function(arr,index){//数组移动 右 或 下 1-[] 2-当前数组索引
            var temp;
          if(index===arr.length||index>arr.length-2){
              return arr;
          }
          temp = arr[index];
          arr[index] = arr[index+1];
          arr[index+1] = temp;
          return arr; 
       },
       changeTopArr : function(arr,id){//置顶
          var temp;
          if($.isArray(arr)){
            if(id===0){
              return arr;
            }
            temp = arr[0];//保存第0个
            arr[0] = arr[id];//第0个变成当前
            arr[id] = temp;//当前的变成第0个
            return arr; 
          } 
       },
       changeDownArr : function(arr,id){//置底
          var temp;
          if($.isArray(arr)){
            if(id===arr.length||id>arr.length-2){
              return arr;
            }
            temp = arr[arr.length-1];//保存最后一个
            arr[arr.length-1] = arr[id];
            arr[id] = temp;
            return arr; 
          } 
       },

       /**
        * 公共message提示信息弹窗 -- 
        * $.message({
        *   type: 'error',//info success
            text: 'message',
            time: 3000})
        **/
        message : function(config){
           var $tpl = null;
           var conf = {
           text: config.text
           };
          switch(config.type) {
            case 'error':
              conf.icon = 'icon-warnfill';
              conf.style = 'z-danger';
              break;
            case 'info':
              conf.icon = 'icon-infomsg';
              conf.style = 'z-primary';
              break;
            default :
              conf.icon = 'icon-successfill';
              conf.style = 'z-success';
              break;
          }
          var len = $('body').find('.alert').length;
          if(len==1) {
            $('.alert').remove();
            clearTimeout(timer);
          }
          var strnbsp = " ";//空格
           $tpl = $('<div class="alert">'
                    +'<div class="alert-inner">'
                      +'<i class="'+conf.icon+''+strnbsp+''+conf.style+'"></i>'
                      +'<span class="alert-text">'
                        +conf.text
                      +'</span>'
                    +'</div>'
                  +'</div>');
            $('body').append($tpl);
            $tpl.css({
            marginLeft: -$tpl.outerWidth() / 2
            }).addClass('in');
            console.log($tpl.outerWidth());
            timer = setTimeout(function() {
              if(!$tpl) {
                return;
              }
              $tpl.removeClass('in');
              setTimeout(function() {
                $tpl.remove();
                $tpl = null;
              }, 300);
            }, config.time || 1500);
         }


}
//原型方法
var PrototypesFn = {
      maxLength : function(max,showNum){//限制文本长度 1-最大值 2-变量数字class
      this.each(function(){
              var type = this.tagName.toLowerCase();
              var inputType = this.type? this.type.toLowerCase() : null;
              if(type == "input" && inputType == "text" || inputType == "password"){
                  //Apply the standard maxLength
                  this.maxLength = max;
              }
              else if(type == "textarea"){
                  this.onkeypress = function(e){
                      var ob = e || event;
                      var keyCode = ob.keyCode;
                      var hasSelection = document.selection? document.selection.createRange().text.length > 0 : this.selectionStart != this.selectionEnd;
                      return !(this.value.length >= max && (keyCode > 50 || keyCode == 32 || keyCode == 0 || keyCode == 13) && !ob.ctrlKey && !ob.altKey && !hasSelection);
                  };
                  this.onkeyup = function(){
                      var changeNum = (max - $(this).val().length)
                      $(showNum).text(changeNum);
                      if(this.value.length > max){
                          this.value = this.value.substring(0,max);
                      }
                  };
              }
          });
      },
      
      /*
      * 点击获取验证码方法 {0} -- 读秒数
      * 调用 -- $('class').verCode(60)
       */
      
      verCode : function(timeNum){
          var time=timeNum;
          var code=$(this);
          if (!code.hasClass('going')) {
            code.addClass("going");
          var t=setInterval(function  () {
            time--;
            code.html('还剩'+time+"秒");
            if (time==0) {
              clearInterval(t);
            code.html("重新获取");
            code.removeClass("going");
            }
          },1000)
          }
      }
}




//静态方法扩展
$.extend(StaticFn);
//原型链继承  
$.fn.extend(PrototypesFn);
})(jQuery)