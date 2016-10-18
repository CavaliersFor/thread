 /*
 *
  编辑商品规格 @-Enjurokcc
 *
 */
 $(function(){
  $(document).on('blur','input.productSpecificationsName',function(){
    var id = $('input.productSpecificationsName').index(this);
    var data = 'p'+(id+1);
    var dataNmae = $(this).val();
    $(this).attr('data',data);
    $(this).attr('dataName',dataNmae);
    $(this).attr('data-pv-val',data+':'+dataNmae+';');

  })
 $(document).on('blur','.item-Specifications input.js-Refresh',function(){
    var id = $(this).parents('.js-append-list').index();
    //console.log(id)
    var psrData = $(this).parents('.item-Specifications').find('.productSpecificationsName').attr('data');
    var psrNmae = $(this).parents('.item-Specifications').find('.productSpecificationsName').attr('dataName');
    var data = psrData+':'+'v'+(id+1)+':'+$(this).val()+';';
    $(this).attr('data',data);
    $(this).attr('data-pv',psrData+':'+'v'+(id+1)+';');
    $(this).attr('data-val',psrNmae+':'+$(this).val()+';');
  })

 var  foeEachTableDom = function(){
      var TH = '',TR='';
      var THle = $('.productSpecificationsName').length;//规格名字长度
      var option = {};
      var thArrVal = [];
      var HTarrNotLast = [];
      for(var i=0;i<THle;i++){
        //if($('.productSpecificationsName').eq(i).val()!==''){
          thArrVal.push($('.productSpecificationsName').eq(i).val());
          HTarrNotLast.push($('.productSpecificationsName').eq(i).val());
        //}
      }
      option['Specifications'] = thArrVal;
      option['Specifications'].push('价格');
      option['Specifications'].push('数量');
      option['Specifications'].push('商品编码');
      var trEnd =    '<td>'
                     +'<input class="input input-number  sameAddisNull specificationsValShow0 number" type="text" value=""/>'
                     +'</td>'
                     +'<td>'
                     +'<input class="input input-number js-Num-add sameAddisNull specificationsValShow1 number" type="text" value=""/>'
                     +'</td>'
                     +'<td>'
                     +'<input class="input input-number sameAddisNull specificationsValShow2" type="text" value=""/>'
                     +'</td>'
                     +'</tr>';
       var Td_Num_arr = [];//二维数组保存生成TD节点

       for(var i=0;i<THle;i++){
         var arrTemp = [];//临时数组

         var inputle = $('.item-vue').eq(i).find('.js-Refresh').length;
         for(var s=0;s<inputle;s++){//子集内循环
             var _val  = $('.item-vue').eq(i).find('.js-Refresh').eq(s).val();
             var data = $('.item-vue').eq(i).find('.js-Refresh').eq(s).attr('data');
             var data_pv = $('.item-vue').eq(i).find('.js-Refresh').eq(s).attr('data-pv');
             var data_val = $('.item-vue').eq(i).find('.js-Refresh').eq(s).attr('data-val');
            _val!==''?arrTemp.push('<td class="All-Need-this-class thisAddClass" data="'+data+'" data-pv="'+data_pv+'" data-val="'+data_val+'">'+_val+'</td>'):null;
         }
         Td_Num_arr.push(arrTemp);
       }

       //前台数据拼接
       var combinationArr = $.combineArr(Td_Num_arr);//二维组合数组所有情况
       for(var _td in combinationArr){
            TR +=  '<tr class="optionTd">'
                     +combinationArr[_td]
                     +trEnd;
       }
      for(var l=0;l<option['Specifications'].length;l++){
        TH += '<th>'+option['Specifications'][l]+'</th>'
      }
      var newTableContent = 
          '<table id="mytable">'
              +'<tr>'
                +TH
             +' </tr>'
             +TR
           +'</table>';
      $('#resultList').html(newTableContent);
 }
 //保存用户输入的  值
$(document).on('blur','#mytable tr.optionTd td input',function(){

     var add_tr_length = $('#mytable tr.optionTd').length;//需保存输入域值的tr长度
     var thisTrPv=[];
     var thisTrVal=[];
     var priceArr=[];//已输入价格
     var productNum=[];//已输入Num
     var CommodityCodeArr = [];//商品编码
     $(this).parents('tr.optionTd').find('td.All-Need-this-class').each(function(){
      var data_pv = $(this).attr('data-pv');
      var data_val = $(this).attr('data-val');
      if(data_pv !==''){
        thisTrPv.push(data_pv);
        thisTrVal.push(data_val);
      }
      $(this).parents('tr.optionTd').attr('data-pv',thisTrPv.join(''));
      $(this).parents('tr.optionTd').attr('data-val',thisTrVal.join(''));
     })
     $('#mytable input.specificationsValShow0').each(function(){
      if($(this).val() !==''){
        priceArr.push($(this).val())
      }
     })
     $('#mytable input.specificationsValShow1').each(function(){
      if($(this).val() !==''){
        productNum.push($(this).val())
      }
     })
     $('#mytable input.specificationsValShow2').each(function(){
      if($(this).val() !==''){
        CommodityCodeArr.push($(this).val())
      }
     })
    var isNull = false;
    $('#mytable input.sameAddisNull').each(function(id){
      var id = $(this).index();
      if( $(this).val()=='' ){
        isNull = true;
        $(this).addClass('borRed');
      }else{
        isNull = false;
        $(this).removeClass('borRed');
      }
    })
    var _jsonStr_ = '';
    var inputArr = [];
    var reNuum =  $(this).parents('table').find('tr.optionTd').length;
    if(!isNull){
       //都不为空时保存数据
        var json_str='';
        var reNuum =  $(this).parents('table').find('tr.optionTd').length;
       for(var rs=0;rs<reNuum;rs++){
         json_str  += '{'+'\"properties\"'+':'+'\"'+$.removeStrLast($('tr.optionTd').eq(rs).attr('data-pv'))+'\"'+','+'\"propertiesName\"'+ ':'+'\"'+$.removeStrLast($('tr.optionTd').eq(rs).attr('data-val'))+'\"'+','+'\"price\"'+ ':'+'\"'+priceArr[rs]+'\"'+','+'\"quantity\"'+ ':'+'\"'+productNum[rs]+'\"'+','+'\"enterpriseProductNo\"'+ ':'+'\"'+CommodityCodeArr[rs]+'\"'+'}'+',';
       }
       //console.log(json_str)
       var json_arr = json_str.split(',');
       optionJsonTable = $.skipEmptyArray(json_arr);
       //console.log(optionJsonTable)
       var parTop = '';
       var parSrt = '';//源头数据
       $('input.productSpecificationsName').each(function(){
         var data = $(this).attr('data-pv-val');
         if(data){
          parTop += data ;
         }
       })
       $('.js-Refresh').each(function(){
         var data = $(this).attr('data');
         if(data){
          parSrt += data ;
         }
       })
      //保存值
      $('#saveAllVal').attr('data-specificationsval','['+optionJsonTable+']');
      //保存总类规格
      $('#saveAllVal').attr('data-pv-total-0',$.removeStrLast(parTop));
      //保存规格指针路径
      $('#saveAllVal').attr('data-pv-total-1',$.removeStrLast(parSrt));

    }else{
      return false;
    }
})

 
 //计算商品总数量
$(document).on('blur','.js-Num-add',function(){
     //数量相加
   var numBerAdd=0;
   var le = $('.js-Num-add').length;
   for(var i=0;i<le;i++){
    if($('.js-Num-add').eq(i).val() !==''){
      numBerAdd += parseInt($('.js-Num-add').eq(i).val());
    }
   }
   //console.log(numBerAdd)
   $('#publiceNum-ALL').val(numBerAdd)
 })
 //鼠标失去焦点赋值
 $(document).on('blur','.item-Specifications .new_input_wi',function(){
   if($(this).val()!==''){
    foeEachTableDom();
   }
 })
 //添加输入域
 $(document).on('click','.js-add-input-vue',function(){
  var dom = '<div class="item-Specifications">'
           +'<div class="toptab">'
             +'<div class="left">规格名称</div>'
             +'<input class="input input-number new_input_wi productSpecificationsName" type="text" value="" placeholder=""/>'
             +'<a class="btn btn-large btn-danger remove-vue">删除</a>'
             +'<div class="clear"></div>'
            +'</div>'
            +'<div class="item-vue">'
              +'<div class="js-append-list">'
               +'<div class="checkbox select-chexkbox item-vue-checkbox">'
                    +'<span class="checkbox-icon">'
                      +'<i class="icon-checked"></i>'
                      +'<input type="checkbox"   value=""/>'
                    +'</span>'
                +'</div>'
                +'<input class="input input-number new_input_wi min-120 js-Refresh disabled" type="text" placeholder="" disabled/>'
               +'</div>'
            +'</div>'
           +'</div>'
   $(this).parent('.btn-add').before(dom);
   foeEachTableDom();
   //商品总价
   var le = $('.productSpecificationsName').length;
   if(le>=1){
    $('#publiceNum-ALL').addClass('disabled');
    $('#publiceNum-ALL').next('.markThisInput').show();
    $('#publiceNum-ALL').val('');
   }
})
 //删除当前规格与值
$(document).on('click','.remove-vue',function(){
  var par = $(this).parents('.item-Specifications').remove();
  foeEachTableDom();
  //商品总价
   var le = $('.productSpecificationsName').length;
   if(le<1){
    $('#publiceNum-ALL').removeClass('disabled');
    $('#publiceNum-ALL').next('.markThisInput').hide();
    $('#publiceNum-ALL').val('');
   }
})
 //选中--输入值&&赋值
 $(document).on('click','.js-append-list .item-vue-checkbox',function(){
   var $input = $(this).next('input.js-Refresh'); 
   var le = $('.js-append-list').length;
   var dom = '<div class="js-append-list">'
               +'<div class="checkbox select-chexkbox item-vue-checkbox">'
                    +'<span class="checkbox-icon">'
                      +'<i class="icon-checked"></i>'
                      +'<input type="checkbox"   value=""/>'
                   +' </span>'
                +'</div>'
                +'<input class="input input-number new_input_wi min-120 js-Refresh disabled" type="text"   value="" placeholder="" disabled/>'
               +'</div>';
   if($(this).hasClass('checked')){
     $(this).removeClass('checked')
     $input.prop('disabled', true);
     $input.addClass('disabled');
     if(le !==1){
      $(this).parents('.js-append-list').remove();
     }else{
      return;
     }
   }else{
     $(this).addClass('checked');
     $input.prop('disabled', false);
     $input.removeClass('disabled');
     $input.focus();//选中
     //新增节点
     $(this).parents('.item-vue').append(dom);
   }
 })
 })