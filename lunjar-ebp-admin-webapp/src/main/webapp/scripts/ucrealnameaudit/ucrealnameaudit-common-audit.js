var _form,_submit,module;
jQuery(function($) {
    _form = jQuery('#form');
    _submit = _form.find(':submit');
    module=$("#module").val();

    var $auditReason=$("#auditReason");


    initFormValidate();

    _form.find('#auditStatus_pass').click(function(){
        $($auditReason).val('').hide();
        $("#tr_auditReason").hide();
    });

    _form.find('#auditStatus_unpass').click(function(){
        $($auditReason).show();
        $("#tr_auditReason").show();
    });

});

function initFormValidate(){
    _form.validate({
        ignore : ':hidden',
        submitHandler:function(f){

            //_submit.val('处理中...').attr('disabled',true);
            var url = '/ucrealnameaudit/audit';

            var auditStatus= $("input[name='auditStatus']:checked").val();
            var auditReason= $.trim($("#auditReason").val());
            if(auditStatus==3 && auditReason==""){
                Core.PopUtil.warn("请填写不通过理由");
                return;
            }

            var isSelectPassed=auditStatus==2;
            var msg=isSelectPassed?'确认将该用户设置为通过?':'确认将该用户设置不通过';

            Core.PopUtil.confirm(msg, function () {
                AncunApiClient.postForm(url,{
                    success:function(result){
                        if(result && result.data && result.data.auditStatus==2){
                            _submit.val('处理成功:');
                            Core.PopUtil.success('比对成功',function(){
                                Core.UrlUtil.redirect('/ucrealnameaudit/'+module);
                            }, {
                                autohide: true
                            });
                        }else{
                            var errorMsg;
                            if(isSelectPassed && result.data.auditReason){
                                errorMsg='比对失败:'+result.data.auditReason;
                                Core.PopUtil.warn(errorMsg,function(){
                                    Core.UrlUtil.redirect('/ucrealnameaudit/'+module);
                                });
                            }else{
                                errorMsg='操作成功';
                                Core.PopUtil.success(errorMsg,function(){
                                    Core.UrlUtil.redirect('/ucrealnameaudit/'+module);
                                });
                            }

                        }
                    }
                },$(f));
            });

        }
    });
}
