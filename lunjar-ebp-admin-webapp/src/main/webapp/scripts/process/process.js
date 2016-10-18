function addMark(){//增加蒙版
    $('body').before('<div id="MarkBG"></div>');
    $('#MarkBG').fadeIn(100);
    $('body').addClass('overflow-hidden-scroll');
  }
function removeMark(){//删除蒙版
    $('#MarkBG').remove();//删除蒙版
    $('body').removeClass('overflow-hidden-scroll');
    $('.modal.going').show();
}
/*
* popuFn({
        obj  : '.addAddressSpan',//触发
        popu : '#CombinaProduct',//窗口
        fn : function(){//确认回调
          
        }
       })
 */
function popuFn(option){
  var openobj = option.obj;
  var popuObj = option.popu;
  var goingFn = option.go;
  var reutrnFn = option._return;
  var fn = option.fn;//回调
  //open
   $(document).on('click',openobj,function(){
     var _self = $(this);
     if(goingFn){
      goingFn(_self);
     }
     addMark();
     $(popuObj).fadeIn(100);
   })
  $(document).on('click','.modal .close,.modal .btn-close-win',function(){
  $(this).parents('.modal').hide();
  removeMark();
  if(reutrnFn){
    reutrnFn(); 
   }
  })
  $(document).on('click','[data-ok="modal"]',function(){
    if(fn){
    fn();
    removeMark();
    $(popuObj).fadeOut(100);
    }
    if(reutrnFn){
      reutrnFn(); 
     }
  })
  
}
   //radio
function radioFn(tocheckedFn){
  var CHECKED_CLASS = 'checked';
  var clearCheckedRadio = function(name) {
    var $input = $('.radio.checked').find('input[name="' + name + '"]');
    var $radio = $input.parents('.radio');
    $radio.removeClass('checked');
    $input.prop('checked', false).trigger('change');
    $radio.trigger('checkbox.change', false);
  };
  var check = function(checked) {
    var $checkbox = $(this);
    var $input = $checkbox.find('input');
    if ($checkbox.is('.disabled, :disabled')) {
      return;
    }
    if ($checkbox.is('.half-checkbox')) {
      checked = true;
      $checkbox.removeClass('half-checkbox');
      $checkbox.find('.icon-halfcheck').removeClass('icon-halfcheck')
          .addClass('icon-checked');
    }
    if ($checkbox.is('.radio')) {
      clearCheckedRadio($input.attr('name'));
    }
    checked ? $checkbox.addClass(CHECKED_CLASS) : $checkbox
        .removeClass(CHECKED_CLASS);
    $input.prop('checked', checked).trigger('change');
    $checkbox.trigger('checkbox.change', checked);
  };
  $(document).on('click', '.radio', function() {

    var checked = $(this).is('.checked');
    if (checked) {
      return false;
    } else {
      if(tocheckedFn){
         tocheckedFn();
      }
      check.call(this, true);
    }
    return false
  })
}

   //单选多选
   /*
   * {1} - 单选下的input {2} -- 包住全选和单选的div {3} - 全选情况/所有子集input {4} 全选情况/所有子集select-chexkbox
   */
   function checkBox(option){
    var thisinputobj = option.obj;
    var pardiv = option.pardiv;
    var parInput = option.input;
    var selectChildrenDombox = option.child;
   
    $(document).on('click', '.select-chexkbox-Me', function() {
      if ($(this).hasClass('checked')) {
        $(this).removeClass('checked');
        $(this).find(thisinputobj).prop("checked", false);//input[name=productsId]
      } else {
        $(this).addClass('checked');
        $(this).find(thisinputobj).prop("checked", true);
      }
      var len = $(this).parents('li').find(selectChildrenDombox).length;//已经选择的长度
      var changeLen = $(this).parents('li').find(selectChildrenDombox+'.checked').length;//已经选择的长度
      if(len == changeLen){
        $(this).parents('li').find('.selectAll').addClass('checked');
        $(this).parents('li').find('.selectAll input').prop("checked", true);
      }else{
         $(this).parents('li').find('.selectAll').removeClass('checked');
        $(this).parents('li').find('.selectAll input').prop("checked", false);
      }

    });
    var forEaInput = function(bloon,self) {//多个
      for (var i = 0; i <  self.parents(pardiv).find(parInput).length; i++) {//.commoditymanage input[name=productsId]
        self.parents(pardiv).find(parInput).eq(i).prop("checked", bloon);
        if (bloon) {
          self.parents(pardiv).find(selectChildrenDombox).addClass('checked');//.commoditymanage .select-chexkbox
        } else {
          self.parents(pardiv).find(selectChildrenDombox).removeClass('checked');//全选
        }
      }
    }
    $(document).on('click', '.selectAll', function() {
      var self = $(this);
      if ($(this).hasClass('checked')) {
        $(this).removeClass('checked');
        
        forEaInput(false,self);
      } else {
        $(this).addClass('checked');
        forEaInput(true,self);
      }
    })
    }
    /*
    *全选反选 0 - 位于哪个父级 1 - 全选 2 - 反选
     */
    function selectAllorRever(option){
      var parDom = option.parDom;
      var selectAll = option.selectAll;
      var Rever = option.Rever;
      //外侧全选
       $(document).on('click',selectAll,function(){
        $(parDom+' .checkbox').each(function(){
          $(this).addClass('checked');
          $(this).find('input').prop("checked", true);
        })
       })
       //外侧反选
       $(document).on('click',Rever,function(){
        $(parDom+' .checkbox').each(function(){
          if($(this).hasClass('checked')){
              $(this).removeClass('checked');
              $(this).find('input').prop("checked", false);
          }else{
            $(this).addClass('checked');
            $(this).find('input').prop("checked", true);
          }
        })
       })
    }










    
    //number 公共触发执行方法
    $(function(){
      $(document).on('keyup','input.number',function(){
          var v = $(this).val();
          $(this).val(v.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,''));
      })
      //remove select
      $(document).on('click','body',function(ev){//selectMessage
        var el = $(ev.target);
      //console.log(el)
      if(el.is('.selectMessage dl') || el.is('input.search')||el.parents().is('.selectMessage')){
      }else{
        $('.selectMessage .result i').removeClass('on');
        $('.selectMessage dl').hide();
      }
      })
    })
    