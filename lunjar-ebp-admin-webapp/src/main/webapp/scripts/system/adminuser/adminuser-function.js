/**
* @include "../../admin.js"
 */

/**
 * 用户权限 在 vm中赋值
 */
var _userFunctions;
var _checkedClass='checked';
var _chk_all;
var _itemsSelector = ':checkbox[name=items]';

jQuery(function(){
	var allitem = $('#allitembox').find(_itemsSelector);
	
	_chk_all = $('#chk_all');
	
	_chk_all.click(function(){
		var checked = this.checked;
		allitem.each(function(){
			this.checked = checked;
		});
		setCheckedLabelClass(checked,allitem.next());
	})
	
	$.each(_userFunctions,function(i,o){
		$(_itemsSelector +'[value='+o+']').attr('checked','checked').next().addClass(_checkedClass);
	});

	//console.log(allitem);
	allitem.click(function(){
		var $this=$(this);
		var checked = this.checked;
		var labels = $this.next();	
		var children = null;	
		if(!$this.is('.action')){//不是权限，则需要选中下级
			var thisTr = $this.parentsUntil('tr').parent();		
			children = thisTr.find(_itemsSelector);
			children.each(function(){
				this.checked = checked;
			});
			labels = labels.add(children.next());
		}
		setCheckedLabelClass(checked,labels);
		//console.log(labels);
		
		if($this.is('.action') && !checked ){
			return;
		}		
		setParentChecked(this);
	});	
	
	$('#btnSubmit').click(function(){
		var btn = $(this);
		Core.appendLoadingIcon(btn);
		$('#form').ajaxSubmit({
			url:'saveuserfunctions'+App.ajaxActionSuffix,
			method:'POST',
			success:function(result){	
				Core.removeLoadingIcon(btn);
				if(App.isSubmitSuccess(result)){
					Core.PopUtil.success('权限保存成功!',function(){
						Core.UrlUtil.redirect('listb');
					});
				}
			}
		})		
	});
});

function setCheckedLabelClass(checked,labels){
	if(checked){
		labels.addClass(_checkedClass);
	}else{
		labels.removeClass(_checkedClass);
	}
}

function setParentChecked(checkbox){
	var $checkbox = $(checkbox);
	var parentTd = null;
	var thisBox = null;
	if($checkbox.is('.action')){
		thisBox = $checkbox.parentsUntil('td').parent();
		parentTd = thisBox.parent().find('td:first');
	}else{	
		thisBox = $checkbox.parentsUntil('table').parent();
		parentTd = thisBox.parent().prev();
	}
	//console.log(parentTd);
	if(parentTd.size()==0){
		return;
	}	
	var brotherCheckboxs = $(_itemsSelector,thisBox);
	var checked = false;
	brotherCheckboxs.each(function(){//只要有一个选中，则上级也选中
		if(this.checked){
			checked = this.checked;
			return;
		}
	})
	var parentCheckbox = $(_itemsSelector + ':first',parentTd);
	//console.log(checked);
	parentCheckbox.each(function(){
		this.checked = checked;
	});
	setCheckedLabelClass(checked,parentCheckbox.next());
	setParentChecked(parentCheckbox.get(0));
 }
