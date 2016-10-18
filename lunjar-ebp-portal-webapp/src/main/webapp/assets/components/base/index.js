import $ from 'jquery';
import Layzr from 'layzr.js';
import FastClick from 'fastclick';
import './base';


FastClick.attach(document.body);

// 扩展 jq 的功能
$.fn.onScrollToBottom = (cb, distance = 0) => {
	const $win = $(window);

	$win.scroll(e => {
		const top = $win.scrollTop();
		const docHeight = $(document).height();
		const winHeight = $win.height();

		if (top + winHeight + distance >= docHeight) {
			cb && cb();
		}
	});
};

/**
 * 把表单的数据序列化成键值对的格式，及json格式。
 */
$.fn.serializeJsonObj = function() {  
  var serializeObj = {};
  var array = this.serializeArray();
  var str = this.serialize();

  $(array).each(function() {
    if (serializeObj[this.name]) {
      if ($.isArray(serializeObj[this.name])) {
        serializeObj[this.name].push(this.value);
      } else {
        serializeObj[this.name] = [serializeObj[this.name],this.value];
      }
    } else {
      serializeObj[this.name] = this.value;
    }
  });
  return serializeObj;
};

$(() => {

  // 每个页面的图片都使用延迟加载
  const instance = Layzr();
  instance.update().check().handlers(true);
  $.LayzrUpdate = () => {
    instance.update();
  };

});
