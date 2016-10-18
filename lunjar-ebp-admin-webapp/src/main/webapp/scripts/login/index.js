//login.js
function checkPhone(){ 
    var phone = $('.phone').val();
    if(!(/^1(3|4|5|7|8)\d{9}$/.test(phone))){ 
        $.alert({
          type: 'error',
          text: '手机号码有误!',
          time: 3000
        });
        return false;
    } 
}

$(function(){
     
	 $(document).on('click','.pc-adimn-main .new-header .right .edit',function(ev){
      var showDom = $(this).find('.setting');
      if(showDom.is(':hidden')){
      	showDom.fadeIn(200);
      }else{
      	showDom.fadeOut(200);
      }
	 })
	 $(document).on('click','body',function(ev){
        var el = $(ev.target);
      if(el.is('.pc-adimn-main .new-header .right .edit') ||el.parents().is('ul.setting')){
      }else{
        $('ul.setting').hide();
      }
      })
	 

})