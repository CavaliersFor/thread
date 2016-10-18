$(function(){

    // 审核不通过
    $(".act_not_pass").click(function(){
        var $this=$(this);
        var auditId=$this.attr("auditId");
        var auditStatus=3;
        editWin(auditId,auditStatus);
    });

    // 审核通过
    $(".act_pass").click(function(){
        var $this=$(this);
        var msg='该用户目前实名认证未通过，确认将其设置为通过?';
        var auditId=$this.attr("auditId");
        var auditStatus=$this.attr("auditStatus");
        if(auditStatus==4){
            msg='确认将该用户实名认证审核设置为通过?';
        }

        Core.PopUtil.confirm(msg, function () {
            AncunApiClient.postForm('audit', {
                data: {"auditId":auditId,"auditStatus":2},
                success: function (response) {
                    Core.PopUtil.success("操作成功", function () {
                        location.reload();
                    }, {
                        autohide: true
                    });
                }
            });
        });

    });

});

var _editForm,_editWin,_loading;
function editWin(auditId,auditStatus){

    if(!_editWin){
        _editWin = new Core.Window($('#editWin'),{
            width:450,
            height:180,
            answers:['提 交','关 闭'],
            hideAfterCallback:false,//保存按钮点击，不自动关闭窗口
            afterShow: initForm,
            callback:function(btn){
                if(btn == '提 交'){
                    _editForm.submit();
                }else{
                    _editWin.hide();
                }
            }
        });
    }
    _editWin.show();

    function initForm(){
        if(!_editForm){
            $.validator.setDefaults(Core.Validator.Options2);
            _editForm = this.boxy.find('#editForm');
            _editForm.validate({
                rules:{
                    auditReason:{
                        required:true
                    }
                },
                messages:{
                    auditReason:{
                        required:"请填写不通过理由"
                    }
                },
                submitHandler:submitForm
            });
            _loading = this.boxy.find('.loading');
        }
        Core.FormUtils.resetForm(_editForm,true);//每次打开窗口时，重置表单
        loadData();//加载数据
    }

    function loadData(){
        _loading.show();//显示加载中
        _editForm.hide();

        var values={"auditId":auditId,"auditStatus":auditStatus};
        Core.FormUtils.setFormValues(_editForm,values);//填充表单

        _loading.hide();
        _editForm.show();
    }

    function submitForm(){
        var btnSubmit = _editWin.getBoxy().find(':button[value="提 交"]');
        Core.appendLoadingIcon(btnSubmit);
        //var data = Core.FormUtils.getFormValues(_editForm);
        //console.log(data);
        AncunApiClient.postForm('audit',{
            //data:data,
            success:function(response){
                Core.PopUtil.success('操作成功!',function(){
                    _editWin.hide(function(){
                        Core.UrlUtil.refresh();
                    });
                });
            },complete:function(response, auditStatus){
                Core.removeLoadingIcon(btnSubmit);
            }
        },_editForm);

    }
}
