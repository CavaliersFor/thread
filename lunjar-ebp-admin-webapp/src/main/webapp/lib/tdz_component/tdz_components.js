(function webpackUniversalModuleDefinition(root, factory) {
	if(typeof exports === 'object' && typeof module === 'object')
		module.exports = factory(require("template"));
	else if(typeof define === 'function' && define.amd)
		define(["template"], factory);
	else if(typeof exports === 'object')
		exports["tdz_components"] = factory(require("template"));
	else
		root["tdz_components"] = factory(root["template"]);
})(this, function(__WEBPACK_EXTERNAL_MODULE_66__) {
return /******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};

/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {

/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;

/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};

/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);

/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;

/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}


/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;

/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;

/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "/assets/lib/tdz-component/";

/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__(10);


/***/ },
/* 1 */,
/* 2 */,
/* 3 */,
/* 4 */,
/* 5 */,
/* 6 */,
/* 7 */,
/* 8 */,
/* 9 */,
/* 10 */
/***/ function(module, exports, __webpack_require__) {

	/**
	 * by haw
	 */

	// $(function() {
		// load components
		
		// 忽略不支持console的浏览器调用
		if (typeof console === "undefined" || typeof console.log === "undefined") {
		 console = {};
		 console.log = function() {};
		}

		__webpack_require__(11);
		__webpack_require__(13);
		__webpack_require__(15);
		__webpack_require__(21);
		__webpack_require__(23);
		__webpack_require__(29);
		
		__webpack_require__(31);
		__webpack_require__(34);
		__webpack_require__(37);
		__webpack_require__(39);
		__webpack_require__(41);
		__webpack_require__(44);
		__webpack_require__(47);
		__webpack_require__(49);
		__webpack_require__(51);
		__webpack_require__(54);
		__webpack_require__(57);
		__webpack_require__(59);
		__webpack_require__(62);
		__webpack_require__(65);
		__webpack_require__(70);
		__webpack_require__(73);
		__webpack_require__(77);
		__webpack_require__(82);
		__webpack_require__(86);

		__webpack_require__(47);
		__webpack_require__(92);
		__webpack_require__(96);
	// });


/***/ },
/* 11 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 12 */,
/* 13 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 14 */,
/* 15 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 16 */,
/* 17 */,
/* 18 */,
/* 19 */,
/* 20 */,
/* 21 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 22 */,
/* 23 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 24 */,
/* 25 */,
/* 26 */,
/* 27 */,
/* 28 */,
/* 29 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 30 */,
/* 31 */
/***/ function(module, exports, __webpack_require__) {

	/**
	 * by haw
	 */

	__webpack_require__(32);

/***/ },
/* 32 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 33 */,
/* 34 */
/***/ function(module, exports, __webpack_require__) {

	__webpack_require__(35);

	// $('.side-nav-item.has-sub-nav:not(".active")').find('.sub-side-nav').hide();
	var $doc = $(document);
	$doc.on('click', '.side-nav-item.has-sub-nav', function (e) {
	  
	  var $this = $(this);
	  if ($this.hasClass('active')) {
	    $this.find('.sub-side-nav').slideUp('normal', function () {
	      $this.removeClass('active');
	    });
	  } else {
	    $this.find('.sub-side-nav').slideDown('normal', function () {
	      $this.addClass('active');
	      var top = $doc.scrollTop();
	      $doc.scrollTop(top - 1);
	      $doc.scrollTop(top);
	    });
	  }
	}).on('click', '.sub-side-nav-item', function (e) {
	  e.stopPropagation();
	});

/***/ },
/* 35 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 36 */,
/* 37 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 38 */,
/* 39 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 40 */,
/* 41 */
/***/ function(module, exports, __webpack_require__) {

	/**
	 * by haw
	 */
	'use strict';

	__webpack_require__(42);
	var containerClass = '.dropdown:not(.disabled)';
	var timer = null;

	var clearMenus = function () {
	  $('.dropdown.open').each(function () {
	    var $this = $(this);
			var $icon = $this.find('.icon-downfill,.icon-upfill');

	    $this.removeClass('open');
			$icon.addClass('icon-downfill').removeClass('icon-upfill');
	  });
	}

	var display = function(isShow) {
		var $dropdown = $(this);
		var $icon = $dropdown.find('.icon-downfill,.icon-upfill');

		if($dropdown.is('.disabled, :disabled') || 
			$dropdown.attr('data-trigger') !== 'hover') {
			return;
		}

		clearTimeout(timer);

		if(isShow) {
			clearMenus();
			$dropdown.addClass('open');
			$icon.removeClass('icon-downfill').addClass('icon-upfill');
		} else {
			timer = setTimeout(function() {
				$dropdown.removeClass('open');
				$icon.addClass('icon-downfill').removeClass('icon-upfill');
			}, 500);
		}

		return false;
	};

	var toggle = function() {
		var $dropdown = $(this);
		var $icon = $dropdown.find('.icon-downfill,.icon-upfill');
		var isShow = $dropdown.hasClass('open');

		if($dropdown.attr('data-trigger') === 'hover') {
			return;
		}

		clearMenus();
		if(!isShow) {
			$dropdown.addClass('open');
			$icon.removeClass('icon-downfill').addClass('icon-upfill');
		}

		return false;
	};

	var setValue = function() {
		var $dropdownItem = $(this);
		var $dropdown = $dropdownItem.parents('.dropdown');

		if($dropdownItem.is('.disabled, :disabled') || 
			$dropdown.is('.disabled, :disabled') || $dropdownItem.attr('href')) {
			return;
		}
		var $value = $dropdown.find('.dropdown-text');
		var val = $dropdownItem.attr('data-value');
		var text = $dropdownItem.text();
		var $input = $dropdown.find('input[type="hidden"]');

		// 为适应二级类目需要去掉前面的层级
		// text = text.replace(/\|--&nbsp;/g, '').replace(/\|--\s/g, '');
		
		$value.attr('data-value', val).text(text);
		$dropdown.find('.active').removeClass('active');
		$dropdownItem.addClass('active');
		$dropdown.trigger('dropdown.change', val, text);
		$input.val(val).trigger('change');
	};


	$(document).on('click', containerClass, function() {
		toggle.call(this);
	})
	.on('mouseenter', containerClass, function() {
		display.call(this, true);
	})
	.on('mouseleave', containerClass, function() {
		display.call(this);
	})
	.on('click', '.dropdown-item', function() {
		setValue.call(this);
	})
	.on('click', function(e) {
		var $this = $(e.target);

		if(!($this.is(containerClass) || $this.parent().is(containerClass))) {
			clearMenus();
		}
	});

	// Dropdown api
	// options = {
	//   value: ,	// active item value
	//   display: 'show' | 'hide',
	//   index: ,	// active index item
	// }
	$.fn.dropdown = function(options) {
	  return this.each(function() {
	  	var $dropdown = $(this);
	  	var $dropdownItems = null;

	  	if(typeof options === 'object') {
	      if(options.display) {
	      	display.call(this, options.display === 'show');
	      }
	      if(options.index >= 0) {
	      	$dropdownItems = $dropdown.find('.dropdown-item').eq(options.index);
	      }
	      if(options.value) {
	      	$dropdown.find('.dropdown-item').each(function() {
	      		var $this = $(this);

	      		if($this.attr('data-value') == options.value) {
	      			$dropdownItems = $this;
	      		}
	      	});
	      }

	      $dropdownItems && $dropdownItems.trigger('click');
	  	}
	  });
	}

/***/ },
/* 42 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 43 */,
/* 44 */
/***/ function(module, exports, __webpack_require__) {

	__webpack_require__(45);

	$(document).on('click', '.input ~ i', function () {
	  $(this).siblings('.input').focus();
	});

/***/ },
/* 45 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 46 */,
/* 47 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 48 */,
/* 49 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 50 */,
/* 51 */
/***/ function(module, exports, __webpack_require__) {

	/**
	 * by haw
	 */
	'use strict';

	__webpack_require__(52);
	var timer = null;
	var containerClass = '.selector:not(.disabled)';

	var clearMenus = function () {
	  $('.selector.open').each(function () {
	    var $this = $(this);
	    $this.removeClass('open');
			$this.find('.icon-down,.icon-up').addClass('icon-down').removeClass('icon-up');
	  });
	}

	var display = function(isShow) {
		var $selector = $(this);
		var $icon = $selector.find('.icon-down,.icon-up');

		if($selector.is('.disabled, :disabled') || 
			$selector.attr('data-trigger') !== 'hover') {
			return;
		}

		clearTimeout(timer);

		if(isShow) {
			$selector.addClass('open');
			$icon.removeClass('icon-down').addClass('icon-up');
		} else {
			timer = setTimeout(function() {
				$selector.removeClass('open');
				$icon.addClass('icon-down').removeClass('icon-up');
			}, 500);
		}

		return false;
	};

	var toggle = function() {
		var $selector = $(this);
		var $icon = $selector.find('.icon-down,.icon-up');
		var isShow = $selector.hasClass('open');

		if($selector.attr('data-trigger') === 'hover') {
			return;
		}

		clearMenus();
		if(!isShow) {
			$selector.addClass('open');
			$icon.removeClass('icon-down').addClass('icon-up');
		}

		return false;
	};

	var setValue = function() {
		var $selectorItem = $(this);
		var $selector = $selectorItem.parents('.selector');

		if($selectorItem.is('.disabled, :disabled, .selector-input') || 
			$selector.is('.disabled, :disabled') || $selectorItem.attr('href')) {
			return;
		}
		var $value = $selector.find('.selector-text');
		var $input = $selector.find('input[type="hidden"]');

		var val = $selectorItem.attr('data-value');
		var text = $selectorItem.text();

		// 为适应二级类目需要去掉前面的层级
		// text = text.replace(/\|--&nbsp;/g, '').replace(/\|--\s/g, '');

		$value.attr('data-value', val).text(text);
		$selector.find('.active').removeClass('active');
		$selectorItem.addClass('active');
		$selector.trigger('selector.change', val, text);
		$input.val(val).trigger('change');

		clearMenus();
	};


	$(document).on('click', containerClass, function() {
		toggle.call(this);
	})
	.on('mouseenter', containerClass, function() {
		display.call(this, true);
	})
	.on('mouseleave', containerClass, function() {
		display.call(this);
	})
	.on('click', '.selector-item', function(e) {
		setValue.call(this);
		e.stopPropagation();
	})
	.click(function(e) {
		var $this = $(e.target);
		var $parent = $this.parents(containerClass);

		if(!($this.is(containerClass) || $this.parent().is(containerClass)) || 
			$parent.size() === 0) {
			clearMenus();
		}
	});

	// Selector api
	// options = {
	//   value: ,	// active item value
	//   display: 'show' | 'hide',
	//   index: ,	// active index item
	// }
	$.fn.selector = function(options) {
	  return this.each(function() {
	  	var $selector = $(this);
	  	var $selectorItems = null;

	  	if(typeof options === 'object') {
	      if(options.display) {
	      	display.call(this, options.display === 'show');
	      }
	      if(options.index >= 0) {
	      	$selectorItems = $selector.find('.selector-item').eq(options.index);
	      }
	      if(options.value) {
	      	$selector.find('.selector-item').each(function() {
	      		var $this = $(this);

	      		if($this.attr('data-value') == options.value) {
	      			$selectorItems = $this;
	      		}
	      	});
	      }

	      $selectorItems && $selectorItems.trigger('click');
	  	}
	  });
	}

/***/ },
/* 52 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 53 */,
/* 54 */
/***/ function(module, exports, __webpack_require__) {

	/**
	 * by haw
	 */
	'use strict';

	__webpack_require__(55);

	var toggle = function(isShow) {
		var $switch = $(this);

		if($switch.is('.disabled')) {
			return;
		}

		$switch.toggleClass('open');
		$switch.trigger('switch.change', $switch.is('.open'));
	};

	$(document).on('click', '.switch', function() {
		toggle.call(this);
	});

	// switch api 
	$.fn.switcher = function() {
		return this.each(function() {
			toggle.call(this);
		});
	};

/***/ },
/* 55 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 56 */,
/* 57 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 58 */,
/* 59 */
/***/ function(module, exports, __webpack_require__) {

	/**
	 * by haw
	 */
	'use strict';

	__webpack_require__(60);

	var display = function(isShow) {
		var $message = $(this).parents('.message');

		isShow ? $message.removeClass('z-hidden') : $message.addClass('z-hidden');

		$message.trigger('message.change', isShow);
	};

	$(document).on('click', '.message-close', function() {
		display.call(this, false);
	});

	// message api
	$.fn.message = function(isShow) {
		return this.each(function() {
			display.call(this, isShow);
		});
	};


/***/ },
/* 60 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 61 */,
/* 62 */
/***/ function(module, exports, __webpack_require__) {

	/**
	 * by haw
	 */
	'use strict';

	__webpack_require__(63);

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

		if($checkbox.is('.disabled, :disabled')) {
			return;
		}

		if($checkbox.is('.half-checkbox')) {
			checked = true;
			$checkbox.removeClass('half-checkbox');
			$checkbox.find('.icon-halfcheck').removeClass('icon-halfcheck').addClass('icon-checked');
		}

		if($checkbox.is('.radio')) {
			clearCheckedRadio($input.attr('name'));
		}

		checked ? $checkbox.addClass(CHECKED_CLASS) : $checkbox.removeClass(CHECKED_CLASS);
		$input.prop('checked', checked).trigger('change');
		$checkbox.trigger('checkbox.change', checked);
	};

	/*$(document).on('click', '.checkbox', function() {
		check.call(this, !$(this).is('.checked'));
		return false;
	}).on('click', '.radio', function() {
		var checked = $(this).is('.checked');

		if(checked) {
			return false;
		}

		check.call(this, true);
		return false;
	// }).on('change', 'input[type="checkbox"]', function() {
	// 	var $this = $(this);
	// 	check.call($this.parents('.checkbox'), !$this.is(':checked'));
	// 	return false;
	});*/

	$.fn.checkbox = function(checked) {
		check.call(this, checked);
	};

/***/ },
/* 63 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 64 */,
/* 65 */
/***/ function(module, exports, __webpack_require__) {

	/**
	 * by haw
	 */

	var template = __webpack_require__(66);
	var tooltipTpl = __webpack_require__(67);
	var $tooltip = null;
	var timer = null;
	__webpack_require__(68);

	var createConf = function(config) {
		var $target = $(this);
		var def = {
			icon: $target.attr('data-icon'),
			style: $target.attr('data-style'),
			placement: $target.attr('data-placement') || 'top',
			align: $target.attr('data-align') || 'center',
			text: $target.attr('data-text') || '',
			simple: $target.attr('data-type') === 'simple',
			distance: $target.attr('data-distance') || 12
		};

		var setting = $.extend(def, config);
		return setting;
	};

	var getPos = function($target, $tip, config) {
		var distance = parseInt(config.distance);
		var position = $target.position();
		var tWidth = $target.outerWidth();
		var tHeight = $target.outerHeight();
		var width = $tip.outerWidth();
		var height = $tip.outerHeight();
		var marginLeft = parseInt($target.css('marginLeft'));
		var marginTop = parseInt($target.css('marginTop'));
		var x = position.left + (isNaN(marginLeft) ? 0 : marginLeft);
		var y = position.top + (isNaN(marginTop) ? 0 : marginTop);

		switch(config.placement) {
			case 'top': 
				y -= height + distance;
				break;
			case 'right': 
				x += tWidth + distance;
				break;
			case 'bottom': 
				y += tHeight + distance;
				break;
			case 'left': 
				x -= width + distance;
				break;
		}
		switch(config.align) {
			case 'center': 
				if(config.placement === 'left' || config.placement === 'right') {
					y -= (height - tHeight) / 2;
				} else {
					x -= (width - tWidth) / 2;
				}
				break;
			case 'right': 
				x += tWidth - width;
				break;
			case 'bottom': 
				y += tHeight - height;
				break;
			case 'left': 
				break;
			default:

		}

		return {
			top: y,
			left: x
		};
	};

	var hide = function() {
		var $target = $(this);
		
		timer = setTimeout(function() {
			remove($target);
		}, 500);
	};

	var remove = function($target) {
		$target = $target || $('[data-tooltip-show=true]');
		$tooltip && $tooltip.remove();
		$target.removeAttr('data-tooltip-show');
	};

	var show = function($target, config) {
		var $wrap = $target.parent();
		var $tip = $(template.compile(tooltipTpl)(config));
		
		$wrap.append($tip);
		$tooltip && $tooltip.remove();
		$tooltip = $tip;
		$target.attr('data-tooltip-show', true);

		// 绑定事件
		if(config.okHide) {
			$tip.find('.btn[data-ok]').on('click', function() {
				config.okHide();
				remove();
			});
		}
		if(config.cancelHide) {
			$tip.find('.btn[data-cancel]').on('click', function() {
				config.cancelHide();
				remove();
			});
		}

		// 定位
		var pos = getPos($target, $tip, config);

		$tip.css({
			top: pos.top,
			left: pos.left,
			opacity: 1
		});
		return $tip;
	};

	var toggle = function(config) {
		var $target = $(this);

		clearTimeout(timer);
		remove();
		
		if(!$target.attr('data-tooltip-show')) {
			show($target, config);
		} else {
			$tooltip && $tooltip.remove();
			$target.removeAttr('data-tooltip-show');
		}
		if ($tooltip) {
			return $tooltip;
		}
	};
	var display = function(config) {
		var self = this;
		var $target = $(this);

		clearTimeout(timer);
		remove();

		if(!$target.attr('data-tooltip-show')) {
			var $tip = show($target, config);
			$tip.on('mouseenter', function() {
				clearTimeout(timer);
			}).on('mouseleave', function() {
				hide.call(self);
			});
		}

		return $tip;
	};

	$(document).on('mouseenter', '[data-toggle="tooltip"][data-trigger="hover"]', function() {
		display.call(this, createConf.call(this, {}));
	});
	$(document).on('mouseleave', '[data-toggle="tooltip"][data-trigger="hover"]', function() {
		hide.call(this);
	});
	$(document).on('click', '[data-toggle="tooltip"][data-trigger="click"]', function(e) {
		toggle.call(this, createConf.call(this, {}));
		e.stopPropagation();
		// return false;
	});
	$(document).on('click', '.tooltip', function(e) {
		e.stopPropagation();
		// return false;
	});
	$(document).on('click', function() {
		remove();
	});


	$.fn.tooltip = function(config) {
		return toggle.call(this, createConf.call(this, config));
	};

/***/ },
/* 66 */
/***/ function(module, exports) {

	module.exports = __WEBPACK_EXTERNAL_MODULE_66__;

/***/ },
/* 67 */
/***/ function(module, exports) {

	module.exports = "<div class=\"tooltip tooltip-{{placement}} {{align}} {{if simple}}tooltip-black{{/if}} {{if cancelHide || okHide}}tooltip-comfirm{{/if}}\">\n\t<div class=\"tooltip-arrow\"></div>\n\t<div class=\"tooltip-inner\">\n\t\t{{if icon}}\n\t\t\t<i class=\"{{icon}} {{style}}\"></i>\n\t\t{{/if}}\n\t\t<span>\n\t\t\t{{#text}}\n\t\t</span>\n\t</div>\n\t{{if cancelHide || okHide}}\n\t\t<div class=\"tooltip-footer\">\n\t\t\t{{if okHide}}\n\t\t\t\t<a class=\"btn btn-small btn-primary\" data-ok>{{okText || '确定'}}</a>\n\t\t\t{{/if}}\n\t\t\t{{if cancelHide}}\n\t\t\t\t<a class=\"btn btn-small\" data-cancel>{{cancelText || '取消'}}</a>\n\t\t\t{{/if}}\n\t\t</div>\n\t{{/if}}\n</div>";

/***/ },
/* 68 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 69 */,
/* 70 */
/***/ function(module, exports, __webpack_require__) {

	/**
	 * by acwong
	 */

	__webpack_require__(71);

	$(document).on('click', '.tab li:not(".no-action")', function () {
	  $(this).addClass('active').siblings().removeClass('active');
	});

/***/ },
/* 71 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 72 */,
/* 73 */
/***/ function(module, exports, __webpack_require__) {

	/**
	 * by haw
	 */


	__webpack_require__(74);
	var template = __webpack_require__(66);
	var alertTpl = __webpack_require__(76);
	var $tpl = null;
	var timer = null;

	if(!$.alert) {
		$.alert = function(config) {
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
			if($tpl) {
				$tpl.remove();
				clearTimeout(timer);
			}
			$tpl = $(template.compile(alertTpl)(conf));

			$('body').append($tpl);
			$tpl.css({
				marginLeft: -$tpl.outerWidth() / 2
			}).addClass('in');

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
		};
	} else {
		//console.log('alert is already use!!');
	}

/***/ },
/* 74 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 75 */,
/* 76 */
/***/ function(module, exports) {

	module.exports = "<div class=\"alert\">\n\t<div class=\"alert-inner\">\n\t\t<i class=\"{{icon}} {{style}}\"></i>\n\t\t<span class=\"alert-text\">\n\t\t\t{{text}}\n\t\t</span>\n\t</div>\n</div>";

/***/ },
/* 77 */
/***/ function(module, exports, __webpack_require__) {

	/*jshint sub:true*/
	/*
	 * js come from :bootstrap-datepicker.js 
	 * Started by Stefan Petre; improvements by Andrew Rowls + contributors
	 * you con get the source from github: https://github.com/eternicode/bootstrap-datepicker
	*/
	__webpack_require__(78);
	__webpack_require__(80);
	/*jshint sub:true*/
	/*
	 * js come from :bootstrap-datepicker.js
	 * Started by Stefan Petre; improvements by Andrew Rowls + contributors
	 * you con get the source from github: https://github.com/eternicode/bootstrap-datepicker
	 */
	! function($, undefined) {

		var $window = $(window);

		function UTCDate() {
			return new Date(Date.UTC.apply(Date, arguments));
		}

		function UTCToday() {
			var today = new Date();
			return UTCDate(today.getFullYear(), today.getMonth(), today.getDate());
		}

		function alias(method) {
			return function() {
				return this[method].apply(this, arguments);
			};
		}

		var DateArray = (function() {
			var extras = {
				get: function(i) {
					return this.slice(i)[0];
				},
				contains: function(d) {
					// Array.indexOf is not cross-browser;
					// $.inArray doesn't work with Dates
					var val = d && d.valueOf();
					for (var i = 0, l = this.length; i < l; i++)
						if (this[i].valueOf() === val)
							return i;
					return -1;
				},
				remove: function(i) {
					this.splice(i, 1);
				},
				replace: function(new_array) {
					if (!new_array)
						return;
					if (!$.isArray(new_array))
						new_array = [new_array];
					this.clear();
					this.push.apply(this, new_array);
				},
				clear: function() {
					this.length = 0;
				},
				copy: function() {
					var a = new DateArray();
					a.replace(this);
					return a;
				}
			};

			return function() {
				var a = [];
				a.push.apply(a, arguments);
				$.extend(a, extras);
				return a;
			};
		})();


		// Picker object

		var Datepicker = function(element, options) {
			this.dates = new DateArray();
			this.viewDate = UTCToday();
			this.focusDate = null;

			this._process_options(options);

			this.element = $(element);
			this.isInline = false;
			this.isInput = this.element.is('input');
			this.component = this.element.is('.date') ? this.element.find('.add-on, .input-group-addon, .sui-btn') : false;
			this.hasInput = this.component && this.element.find('input').length;
			if (this.component && this.component.length === 0)
				this.component = false;

			this.picker = $(DPGlobal.template);

			if (this.o.timepicker) {
				this.timepickerContainer = this.picker.find('.timepicker-container');
				this.timepickerContainer.timepicker();
				this.timepicker = this.timepickerContainer.data('timepicker');
				this.timepicker._render();
				//this.setTimeValue();
			}

			this._buildEvents();
			this._attachEvents();

			if (this.isInline) {
				this.picker.addClass('datepicker-inline').appendTo(this.element);
			} else {
				this.picker.addClass('datepicker-dropdown dropdown-menu');
			}

			if (this.o.rtl) {
				this.picker.addClass('datepicker-rtl');
			}

			if (this.o.size === 'small') {
				this.picker.addClass('datepicker-small');
			}

			this.viewMode = this.o.startView;

			if (this.o.calendarWeeks)
				this.picker.find('tfoot th.today')
				.attr('colspan', function(i, val) {
					return parseInt(val) + 1;
				});

			this._allow_update = false;

			this.setStartDate(this._o.startDate);
			this.setEndDate(this._o.endDate);
			this.setDaysOfWeekDisabled(this.o.daysOfWeekDisabled);

			this.fillDow();
			this.fillMonths();

			this._allow_update = true;

			this.update();
			this.showMode();

			if (this.isInline) {
				this.show();
			}
		};

		Datepicker.prototype = {
			constructor: Datepicker,

			_process_options: function(opts) {
				// Store raw options for reference
				this._o = $.extend({}, this._o, opts);
				// Processed options
				var o = this.o = $.extend({}, this._o);

				// Check if "de-DE" style date is available, if not language should
				// fallback to 2 letter code eg "de"
				var lang = o.language;
				if (!dates[lang]) {
					lang = lang.split('-')[0];
					if (!dates[lang])
						lang = defaults.language;
				}
				o.language = lang;

				switch (o.startView) {
					case 2:
					case 'decade':
						o.startView = 2;
						break;
					case 1:
					case 'year':
						o.startView = 1;
						break;
					default:
						o.startView = 0;
				}

				switch (o.minViewMode) {
					case 1:
					case 'months':
						o.minViewMode = 1;
						break;
					case 2:
					case 'years':
						o.minViewMode = 2;
						break;
					default:
						o.minViewMode = 0;
				}

				o.startView = Math.max(o.startView, o.minViewMode);

				// true, false, or Number > 0
				if (o.multidate !== true) {
					o.multidate = Number(o.multidate) || false;
					if (o.multidate !== false)
						o.multidate = Math.max(0, o.multidate);
					else
						o.multidate = 1;
				}
				o.multidateSeparator = String(o.multidateSeparator);

				o.weekStart %= 7;
				o.weekEnd = ((o.weekStart + 6) % 7);

				var format = DPGlobal.parseFormat(o.format);
				if (o.startDate !== -Infinity) {
					if (!!o.startDate) {
						if (o.startDate instanceof Date)
							o.startDate = this._local_to_utc(this._zero_time(o.startDate));
						else
							o.startDate = DPGlobal.parseDate(o.startDate, format, o.language);
					} else {
						o.startDate = -Infinity;
					}
				}
				if (o.endDate !== Infinity) {
					if (!!o.endDate) {
						if (o.endDate instanceof Date)
							o.endDate = this._local_to_utc(this._zero_time(o.endDate));
						else
							o.endDate = DPGlobal.parseDate(o.endDate, format, o.language);
					} else {
						o.endDate = Infinity;
					}
				}

				o.daysOfWeekDisabled = o.daysOfWeekDisabled || [];
				if (!$.isArray(o.daysOfWeekDisabled))
					o.daysOfWeekDisabled = o.daysOfWeekDisabled.split(/[,\s]*/);
				o.daysOfWeekDisabled = $.map(o.daysOfWeekDisabled, function(d) {
					return parseInt(d, 10);
				});

				var plc = String(o.orientation).toLowerCase().split(/\s+/g),
					_plc = o.orientation.toLowerCase();
				plc = $.grep(plc, function(word) {
					return (/^auto|left|right|top|bottom$/).test(word);
				});
				o.orientation = {
					x: 'auto',
					y: 'auto'
				};
				if (!_plc || _plc === 'auto')
				; // no action
				else if (plc.length === 1) {
					switch (plc[0]) {
						case 'top':
						case 'bottom':
							o.orientation.y = plc[0];
							break;
						case 'left':
						case 'right':
							o.orientation.x = plc[0];
							break;
					}
				} else {
					_plc = $.grep(plc, function(word) {
						return (/^left|right$/).test(word);
					});
					o.orientation.x = _plc[0] || 'auto';

					_plc = $.grep(plc, function(word) {
						return (/^top|bottom$/).test(word);
					});
					o.orientation.y = _plc[0] || 'auto';
				}
			},
			_events: [],
			_secondaryEvents: [],
			_applyEvents: function(evs) {
				for (var i = 0, el, ch, ev; i < evs.length; i++) {
					el = evs[i][0];
					if (evs[i].length === 2) {
						ch = undefined;
						ev = evs[i][1];
					} else if (evs[i].length === 3) {
						ch = evs[i][1];
						ev = evs[i][2];
					}
					el.on(ev, ch);
				}
			},
			_unapplyEvents: function(evs) {
				for (var i = 0, el, ev, ch; i < evs.length; i++) {
					el = evs[i][0];
					if (evs[i].length === 2) {
						ch = undefined;
						ev = evs[i][1];
					} else if (evs[i].length === 3) {
						ch = evs[i][1];
						ev = evs[i][2];
					}
					el.off(ev, ch);
				}
			},
			_buildEvents: function() {
				if (this.isInput) { // single input
					this._events = [
						[this.element, {
							focus: $.proxy(this.show, this),
							keyup: $.proxy(function(e) {
								if ($.inArray(e.keyCode, [27, 37, 39, 38, 40, 32, 13, 9]) === -1)
									this.update();
							}, this),
							keydown: $.proxy(this.keydown, this)
						}]
					];
				} else if (this.component && this.hasInput) { // component: input + button
					this._events = [
						// For components that are not readonly, allow keyboard nav
						[this.element.find('input'), {
							focus: $.proxy(this.show, this),
							keyup: $.proxy(function(e) {
								if ($.inArray(e.keyCode, [27, 37, 39, 38, 40, 32, 13, 9]) === -1)
									this.update();
							}, this),
							keydown: $.proxy(this.keydown, this)
						}],
						[this.component, {
							click: $.proxy(this.show, this)
						}]
					];
				} else if (this.element.is('div')) { // inline datepicker
					this.isInline = true;
				} else {
					this._events = [
						[this.element, {
							click: $.proxy(this.show, this)
						}]
					];
				}
				//timepicker change
				if (this.o.timepicker) {
					this._events.push(
						[this.timepickerContainer, {
							'time:change': $.proxy(this.timeChange, this)
						}]
					)
				}

				this._events.push(
					// Component: listen for blur on element descendants
					[this.element, '*', {
						blur: $.proxy(function(e) {
							this._focused_from = e.target;
						}, this)
					}],
					// Input: listen for blur on element
					[this.element, {
						blur: $.proxy(function(e) {
							this._focused_from = e.target;
						}, this)
					}]
				);

				this._secondaryEvents = [
					[this.picker, {
						click: $.proxy(this.click, this)
					}],
					[$(window), {
						resize: $.proxy(this.place, this)
					}],
					[$(document), {
						'mousedown touchstart': $.proxy(function(e) {
							// Clicked outside the datepicker, hide it
							if (!(
								this.element.is(e.target) ||
								this.element.find(e.target).length ||
								this.picker.is(e.target) ||
								this.picker.find(e.target).length
							)) {
								this.hide();
							}
						}, this)
					}]
				];
			},
			_attachEvents: function() {
				this._detachEvents();
				this._applyEvents(this._events);
			},
			_detachEvents: function() {
				this._unapplyEvents(this._events);
			},
			_attachSecondaryEvents: function() {
				this._detachSecondaryEvents();
				this._applyEvents(this._secondaryEvents);
				if (this.o.timepicker) {
					this.timepicker._attachSecondaryEvents();
				}
			},
			_detachSecondaryEvents: function() {
				this._unapplyEvents(this._secondaryEvents);
				if (this.o.timepicker) {
					this.timepicker._detachSecondaryEvents();
				}
			},
			_trigger: function(event, altdate) {
				var date = altdate || this.dates.get(-1),
					local_date = this._utc_to_local(date);

				this.element.trigger({
					type: event,
					date: local_date,
					dates: $.map(this.dates, this._utc_to_local),
					format: $.proxy(function(ix, format) {
						if (arguments.length === 0) {
							ix = this.dates.length - 1;
							format = this.o.format;
						} else if (typeof ix === 'string') {
							format = ix;
							ix = this.dates.length - 1;
						}
						format = format || this.o.format;
						var date = this.dates.get(ix);
						return DPGlobal.formatDate(date, format, this.o.language);
					}, this)
				});
			},
			timeChange: function(e) {
				this.setValue();
			},
			show: function(e) {
				if (e && e.type === "focus" && this.picker.is(":visible")) return;
				if (!this.isInline)
					this.picker.appendTo('body');
				this.picker.show();
				this.place();
				this._attachSecondaryEvents();
				if (this.o.timepicker) {
					this.timepicker._show();
				}
				this._trigger('show');
			},

			hide: function() {
				if (this.isInline)
					return;
				if (!this.picker.is(':visible'))
					return;
				this.focusDate = null;
				this.picker.hide().detach();
				this._detachSecondaryEvents();
				this.viewMode = this.o.startView;
				this.showMode();

				if (
					this.o.forceParse &&
					(
						this.isInput && this.element.val() ||
						this.hasInput && this.element.find('input').val()
					)
				)
					this.setValue();
				if (this.o.timepicker) {
					this.timepicker._hide();
				}
				this._trigger('hide');
			},

			remove: function() {
				this.hide();
				this._detachEvents();
				this._detachSecondaryEvents();
				this.picker.remove();
				delete this.element.data().datepicker;
				if (!this.isInput) {
					delete this.element.data().date;
				}
			},

			_utc_to_local: function(utc) {
				return utc && new Date(utc.getTime() + (utc.getTimezoneOffset() * 60000));
			},
			_local_to_utc: function(local) {
				return local && new Date(local.getTime() - (local.getTimezoneOffset() * 60000));
			},
			_zero_time: function(local) {
				return local && new Date(local.getFullYear(), local.getMonth(), local.getDate());
			},
			_zero_utc_time: function(utc) {
				return utc && new Date(Date.UTC(utc.getUTCFullYear(), utc.getUTCMonth(), utc.getUTCDate()));
			},

			getDates: function() {
				return $.map(this.dates, this._utc_to_local);
			},

			getUTCDates: function() {
				return $.map(this.dates, function(d) {
					return new Date(d);
				});
			},

			getDate: function() {
				return this._utc_to_local(this.getUTCDate());
			},

			getUTCDate: function() {
				return new Date(this.dates.get(-1));
			},

			setDates: function() {
				var args = $.isArray(arguments[0]) ? arguments[0] : arguments;
				this.update.apply(this, args);
				this._trigger('changeDate');
				this.setValue();
			},

			setUTCDates: function() {
				var args = $.isArray(arguments[0]) ? arguments[0] : arguments;
				this.update.apply(this, $.map(args, this._utc_to_local));
				this._trigger('changeDate');
				this.setValue();
			},

			setDate: alias('setDates'),
			setUTCDate: alias('setUTCDates'),

			setValue: function() {
				var formatted = this.getFormattedDate();
				if (!this.isInput) {
					if (this.component) {
						this.element.find('input').val(formatted).change();
					}
				} else {
					this.element.val(formatted).change();
				}
			},

			setTimeValue: function() {
				var val, minute, hour, time;
				time = {
					hour: (new Date()).getHours(),
					minute: (new Date()).getMinutes()
				};
				if (this.isInput) {
					element = this.element;
				} else if (this.component) {
					element = this.element.find('input');
				}
				if (element) {

					val = $.trim(element.val());
					if (val) {
						var tokens = val.split(" "); //datetime
						if (tokens.length === 2) {
							val = tokens[1];
						}
					}
					val = val.split(':');
					for (var i = val.length - 1; i >= 0; i--) {
						val[i] = $.trim(val[i]);
					}
					if (val.length === 2) {
						minute = parseInt(val[1], 10);
						if (minute >= 0 && minute < 60) {
							time.minute = minute;
						}
						hour = parseInt(val[0].slice(-2), 10);
						if (hour >= 0 && hour < 24) {
							time.hour = hour;
						}
					}
				}
				this.timepickerContainer.data("time", time.hour + ":" + time.minute);
			},

			getFormattedDate: function(format) {
				if (format === undefined)
					format = this.o.format;

				var lang = this.o.language;
				var text = $.map(this.dates, function(d) {
					return DPGlobal.formatDate(d, format, lang);
				}).join(this.o.multidateSeparator);
				if (this.o.timepicker) {
					if (!text) {
						text = DPGlobal.formatDate(new Date(), format, lang);
					}
					text = text + " " + this.timepickerContainer.data('time');
				}
				return text;
			},

			setStartDate: function(startDate) {
				this._process_options({
					startDate: startDate
				});
				this.update();
				this.updateNavArrows();
			},

			setEndDate: function(endDate) {
				this._process_options({
					endDate: endDate
				});
				this.update();
				this.updateNavArrows();
			},

			setDaysOfWeekDisabled: function(daysOfWeekDisabled) {
				this._process_options({
					daysOfWeekDisabled: daysOfWeekDisabled
				});
				this.update();
				this.updateNavArrows();
			},

			place: function() {
				if (this.isInline)
					return;
				var calendarWidth = this.picker.outerWidth(),
					calendarHeight = this.picker.outerHeight(),
					visualPadding = 10,
					windowWidth = $window.width(),
					windowHeight = $window.height(),
					scrollTop = $window.scrollTop();

				var zIndex = parseInt(this.element.parents().filter(function() {
					return $(this).css('z-index') !== 'auto';
				}).first().css('z-index')) + 10;
				var offset = this.component ? this.component.parent().offset() : this.element.offset();
				var height = this.component ? this.component.outerHeight(true) : this.element.outerHeight(false);
				var width = this.component ? this.component.outerWidth(true) : this.element.outerWidth(false);
				var left = offset.left,
					top = offset.top;

				this.picker.removeClass(
					'datepicker-orient-top datepicker-orient-bottom ' +
					'datepicker-orient-right datepicker-orient-left'
				);

				if (this.o.orientation.x !== 'auto') {
					this.picker.addClass('datepicker-orient-' + this.o.orientation.x);
					if (this.o.orientation.x === 'right')
						left -= calendarWidth - width;
				}
				// auto x orientation is best-placement: if it crosses a window
				// edge, fudge it sideways
				else {
					// Default to left
					this.picker.addClass('datepicker-orient-left');
					if (offset.left < 0)
						left -= offset.left - visualPadding;
					else if (offset.left + calendarWidth > windowWidth)
						left = windowWidth - calendarWidth - visualPadding;
				}

				// auto y orientation is best-situation: top or bottom, no fudging,
				// decision based on which shows more of the calendar
				var yorient = this.o.orientation.y,
					top_overflow, bottom_overflow;
				if (yorient === 'auto') {
					top_overflow = -scrollTop + offset.top - calendarHeight;
					bottom_overflow = scrollTop + windowHeight - (offset.top + height + calendarHeight);
					if (Math.max(top_overflow, bottom_overflow) === bottom_overflow)
						yorient = 'top';
					else
						yorient = 'bottom';
				}
				this.picker.addClass('datepicker-orient-' + yorient);
				if (yorient === 'top')
					top += height + 6;
				else
					top -= calendarHeight + parseInt(this.picker.css('padding-top')) + 6;

				this.picker.css({
					top: top,
					left: left,
	        zIndex: zIndex > 1020 ? zIndex : 1030
				});
			},
			_getTime:function(date){
				var h,m;
				date  = new Date(date);
				h = date.getHours();
				if (h<10) {
					h = "0" + h;
				}
				m = date.getMinutes();
				if (m<10) {
					m = "0" + m;
				}
				return h + ":" + m;
			},
			_allow_update: true,
			update: function() {
				if (!this._allow_update)
					return;

				var oldDates = this.dates.copy(),
					dates = [],
					fromArgs = false;
				if (arguments.length) {
					$.each(arguments, $.proxy(function(i, date) {
						//获取第一个的时间,用来update 时间
						if (this.o.timepicker&&i === 0) {
							
							this.timepicker.update(this._getTime(date)); //不要更新input
						}
						if (date instanceof Date)
							date = this._local_to_utc(date);
						else if(typeof date == "string" && this.o.timepicker){
							date = date.split(" ")[0];
						}
						dates.push(date);
					}, this));
					fromArgs = true;


					
				} else {
					dates = this.isInput ? this.element.val() : this.element.data('date') || this.element.find('input').val();
					if (dates&&this.o.timepicker) {//合体模式
						var tokens = dates.split(" ");
						if (tokens.length === 2) {  //有时间
							dates = tokens[0];
							//调用timepicker 的_updateUI
							this.timepicker.update(tokens[1],true); //不要更新input
						}
					}
					if (dates && this.o.multidate)
						dates = dates.split(this.o.multidateSeparator);
					else
						dates = [dates];
					delete this.element.data().date;
				}

				dates = $.map(dates, $.proxy(function(date) {
					return DPGlobal.parseDate(date, this.o.format, this.o.language);
				}, this));
				dates = $.grep(dates, $.proxy(function(date) {
					return (
						date < this.o.startDate ||
						date > this.o.endDate ||
						!date
					);
				}, this), true);
				this.dates.replace(dates);

				if (this.dates.length)
					this.viewDate = new Date(this.dates.get(-1));
				else if (this.viewDate < this.o.startDate)
					this.viewDate = new Date(this.o.startDate);
				else if (this.viewDate > this.o.endDate)
					this.viewDate = new Date(this.o.endDate);

				if (fromArgs) {
					// setting date by clicking
					this.setValue();
				} else if (dates.length) {
					// setting date by typing
					if (String(oldDates) !== String(this.dates))
						this._trigger('changeDate');
				}
				if (!this.dates.length && oldDates.length)
					this._trigger('clearDate');

				this.fill();
			},

			fillDow: function() {
				var dowCnt = this.o.weekStart,
					html = '<tr class="week-content">';
				if (this.o.calendarWeeks) {
					var cell = '<th class="cw">&nbsp;</th>';
					html += cell;
					this.picker.find('.datepicker-days thead tr:first-child').prepend(cell);
				}
				while (dowCnt < this.o.weekStart + 7) {
					html += '<th class="dow">' + dates[this.o.language].daysMin[(dowCnt++) % 7] + '</th>';
				}
				html += '</tr>';
				this.picker.find('.datepicker-days thead').append(html);
			},

			fillMonths: function() {
				var html = '',
					i = 0;
				while (i < 12) {
					html += '<span class="month">' + dates[this.o.language].monthsShort[i++] + '</span>';
				}
				this.picker.find('.datepicker-months td').html(html);
			},

			setRange: function(range) {
				if (!range || !range.length)
					delete this.range;
				else
					this.range = $.map(range, function(d) {
						return d.valueOf();
					});
				this.fill();
			},

			getClassNames: function(date) {
				var cls = [],
					year = this.viewDate.getUTCFullYear(),
					month = this.viewDate.getUTCMonth(),
					today = new Date();
				if (date.getUTCFullYear() < year || (date.getUTCFullYear() === year && date.getUTCMonth() < month)) {
					cls.push('old');
				} else if (date.getUTCFullYear() > year || (date.getUTCFullYear() === year && date.getUTCMonth() > month)) {
					cls.push('new');
				}
				if (this.focusDate && date.valueOf() === this.focusDate.valueOf())
					cls.push('focused');
				// Compare internal UTC date with local today, not UTC today
				if (this.o.todayHighlight &&
					date.getUTCFullYear() === today.getFullYear() &&
					date.getUTCMonth() === today.getMonth() &&
					date.getUTCDate() === today.getDate()) {
					cls.push('today');
				}
				if (this.dates.contains(date) !== -1)
					cls.push('active');
				if (date.valueOf() < this.o.startDate || date.valueOf() > this.o.endDate ||
					$.inArray(date.getUTCDay(), this.o.daysOfWeekDisabled) !== -1) {
					cls.push('disabled');
				}
				if (this.range) {
					if (date > this.range[0] && date < this.range[this.range.length - 1]) {
						cls.push('range');
					}
					if ($.inArray(date.valueOf(), this.range) !== -1) {
						cls.push('selected');
					}
				}
				return cls;
			},

			fill: function() {
				var d = new Date(this.viewDate),
					year = d.getUTCFullYear(),
					month = d.getUTCMonth(),
					startYear = this.o.startDate !== -Infinity ? this.o.startDate.getUTCFullYear() : -Infinity,
					startMonth = this.o.startDate !== -Infinity ? this.o.startDate.getUTCMonth() : -Infinity,
					endYear = this.o.endDate !== Infinity ? this.o.endDate.getUTCFullYear() : Infinity,
					endMonth = this.o.endDate !== Infinity ? this.o.endDate.getUTCMonth() : Infinity,
					todaytxt = dates[this.o.language].today || dates['en'].today || '',
					cleartxt = dates[this.o.language].clear || dates['en'].clear || '',
					tooltip;
				this.picker.find('.datepicker-days thead th.datepicker-switch')
					.text(year + '年 ' + dates[this.o.language].months[month]);
				this.picker.find('tfoot th.today')
					.text(todaytxt)
					.toggle(this.o.todayBtn !== false);
				this.picker.find('tfoot th.clear')
					.text(cleartxt)
					.toggle(this.o.clearBtn !== false);
				this.updateNavArrows();
				this.fillMonths();
				var prevMonth = UTCDate(year, month - 1, 28),
					day = DPGlobal.getDaysInMonth(prevMonth.getUTCFullYear(), prevMonth.getUTCMonth());
				prevMonth.setUTCDate(day);
				prevMonth.setUTCDate(day - (prevMonth.getUTCDay() - this.o.weekStart + 7) % 7);
				var nextMonth = new Date(prevMonth);
				nextMonth.setUTCDate(nextMonth.getUTCDate() + 42);
				nextMonth = nextMonth.valueOf();
				var html = [];
				var clsName;
				while (prevMonth.valueOf() < nextMonth) {
					if (prevMonth.getUTCDay() === this.o.weekStart) {
						html.push('<tr>');
						if (this.o.calendarWeeks) {
							// ISO 8601: First week contains first thursday.
							// ISO also states week starts on Monday, but we can be more abstract here.
							var
							// Start of current week: based on weekstart/current date
								ws = new Date(+prevMonth + (this.o.weekStart - prevMonth.getUTCDay() - 7) % 7 * 864e5),
								// Thursday of this week
								th = new Date(Number(ws) + (7 + 4 - ws.getUTCDay()) % 7 * 864e5),
								// First Thursday of year, year from thursday
								yth = new Date(Number(yth = UTCDate(th.getUTCFullYear(), 0, 1)) + (7 + 4 - yth.getUTCDay()) % 7 * 864e5),
								// Calendar week: ms between thursdays, div ms per day, div 7 days
								calWeek = (th - yth) / 864e5 / 7 + 1;
							html.push('<td class="cw">' + calWeek + '</td>');

						}
					}
					clsName = this.getClassNames(prevMonth);
					clsName.push('day');

					if (this.o.beforeShowDay !== $.noop) {
						var before = this.o.beforeShowDay(this._utc_to_local(prevMonth));
						if (before === undefined)
							before = {};
						else if (typeof(before) === 'boolean')
							before = {
								enabled: before
							};
						else if (typeof(before) === 'string')
							before = {
								classes: before
							};
						if (before.enabled === false)
							clsName.push('disabled');
						if (before.classes)
							clsName = clsName.concat(before.classes.split(/\s+/));
						if (before.tooltip)
							tooltip = before.tooltip;
					}

					clsName = $.unique(clsName);
					var currentDate;
					var today = new Date();
					if (this.o.todayHighlight &&
						prevMonth.getUTCFullYear() === today.getFullYear() &&
						prevMonth.getUTCMonth() === today.getMonth() &&
						prevMonth.getUTCDate() === today.getDate()) {
						currentDate = '今日';
					} else {
						currentDate = prevMonth.getUTCDate();
					}
					html.push('<td class="' + clsName.join(' ') + '"' + (tooltip ? ' title="' + tooltip + '"' : '') + 'data-day="' + prevMonth.getUTCDate() + '"' + '>' + currentDate + '</td>');
					if (prevMonth.getUTCDay() === this.o.weekEnd) {
						html.push('</tr>');
					}
					prevMonth.setUTCDate(prevMonth.getUTCDate() + 1);
				}
				this.picker.find('.datepicker-days tbody').empty().append(html.join(''));

				var months = this.picker.find('.datepicker-months')
					.find('th:eq(1)')
					.text(year)
					.end()
					.find('span').removeClass('active');

				$.each(this.dates, function(i, d) {
					if (d.getUTCFullYear() === year)
						months.eq(d.getUTCMonth()).addClass('active');
				});

				if (year < startYear || year > endYear) {
					months.addClass('disabled');
				}
				if (year === startYear) {
					months.slice(0, startMonth).addClass('disabled');
				}
				if (year === endYear) {
					months.slice(endMonth + 1).addClass('disabled');
				}

				html = '';
				year = parseInt(year / 10, 10) * 10;
				var yearCont = this.picker.find('.datepicker-years')
					.find('th:eq(1)')
					.text(year + '-' + (year + 9))
					.end()
					.find('td');
				year -= 1;
				var years = $.map(this.dates, function(d) {
						return d.getUTCFullYear();
					}),
					classes;
				for (var i = -1; i < 11; i++) {
					classes = ['year'];
					if (i === -1)
						classes.push('old');
					else if (i === 10)
						classes.push('new');
					if ($.inArray(year, years) !== -1)
						classes.push('active');
					if (year < startYear || year > endYear)
						classes.push('disabled');
					html += '<span class="' + classes.join(' ') + '">' + year + '</span>';
					year += 1;
				}
				yearCont.html(html);
			},

			updateNavArrows: function() {
				if (!this._allow_update)
					return;

				var d = new Date(this.viewDate),
					year = d.getUTCFullYear(),
					month = d.getUTCMonth();
				switch (this.viewMode) {
					case 0:
						if (this.o.startDate !== -Infinity && year <= this.o.startDate.getUTCFullYear() && month <= this.o.startDate.getUTCMonth()) {
							this.picker.find('.prev').css({
								visibility: 'hidden'
							});
						} else {
							this.picker.find('.prev').css({
								visibility: 'visible'
							});
						}
						if (this.o.endDate !== Infinity && year >= this.o.endDate.getUTCFullYear() && month >= this.o.endDate.getUTCMonth()) {
							this.picker.find('.next').css({
								visibility: 'hidden'
							});
						} else {
							this.picker.find('.next').css({
								visibility: 'visible'
							});
						}
						break;
					case 1:
					case 2:
						if (this.o.startDate !== -Infinity && year <= this.o.startDate.getUTCFullYear()) {
							this.picker.find('.prev').css({
								visibility: 'hidden'
							});
						} else {
							this.picker.find('.prev').css({
								visibility: 'visible'
							});
						}
						if (this.o.endDate !== Infinity && year >= this.o.endDate.getUTCFullYear()) {
							this.picker.find('.next').css({
								visibility: 'hidden'
							});
						} else {
							this.picker.find('.next').css({
								visibility: 'visible'
							});
						}
						break;
				}
			},

			click: function(e) {
				e.preventDefault();
				if ($(e.target).parents(".timepicker-container")[0]) {
					return;
				}
				var target = $(e.target).closest('span, td, th'),
					year, month, day;
				if (target.length === 1) {
					switch (target[0].nodeName.toLowerCase()) {
						case 'th':
							switch (target[0].className) {
								case 'datepicker-switch':
									this.showMode(1);
									break;
								case 'prev':
								case 'next':
									var dir = DPGlobal.modes[this.viewMode].navStep * (target[0].className === 'prev' ? -1 : 1);
									switch (this.viewMode) {
										case 0:
											this.viewDate = this.moveMonth(this.viewDate, dir);
											this._trigger('changeMonth', this.viewDate);
											break;
										case 1:
										case 2:
											this.viewDate = this.moveYear(this.viewDate, dir);
											if (this.viewMode === 1)
												this._trigger('changeYear', this.viewDate);
											break;
									}
									this.fill();
									break;
								case 'today':
									var date = new Date();
									date = UTCDate(date.getFullYear(), date.getMonth(), date.getDate(), 0, 0, 0);

									this.showMode(-2);
									var which = this.o.todayBtn === 'linked' ? null : 'view';
									this._setDate(date, which);
									break;
								case 'clear':
									var element;
									if (this.isInput)
										element = this.element;
									else if (this.component)
										element = this.element.find('input');
									if (element)
										element.val("").change();
									this.update();
									this._trigger('changeDate');
									if (this.o.autoclose)
										this.hide();
									break;
							}
							break;
						case 'span':
							if (!target.is('.disabled') && !target.is('[data-num]')) {
								this.viewDate.setUTCDate(1);
								if (target.is('.month')) {
									day = 1;
									month = target.parent().find('span').index(target);
									year = this.viewDate.getUTCFullYear();
									this.viewDate.setUTCMonth(month);
									this._trigger('changeMonth', this.viewDate);
									if (this.o.minViewMode === 1) {
										this._setDate(UTCDate(year, month, day));
									}
								} else {
									day = 1;
									month = 0;
									year = parseInt(target.text(), 10) || 0;
									this.viewDate.setUTCFullYear(year);
									this._trigger('changeYear', this.viewDate);
									if (this.o.minViewMode === 2) {
										this._setDate(UTCDate(year, month, day));
									}
								}
								this.showMode(-1);
								this.fill();
							}
							break;
						case 'td':
							if (target.is('.day') && !target.is('.disabled')) {
								day = target.data('day');
								day = parseInt(day, 10) || 1;
								year = this.viewDate.getUTCFullYear();
								month = this.viewDate.getUTCMonth();
								if (target.is('.old')) {
									if (month === 0) {
										month = 11;
										year -= 1;
									} else {
										month -= 1;
									}
								} else if (target.is('.new')) {
									if (month === 11) {
										month = 0;
										year += 1;
									} else {
										month += 1;
									}
								}
								this._setDate(UTCDate(year, month, day));
							}
							break;
					}
				}
				if (this.picker.is(':visible') && this._focused_from) {
					$(this._focused_from).focus();
				}
				delete this._focused_from;
			},

			_toggle_multidate: function(date) {
				var ix = this.dates.contains(date);
				if (!date) {
					this.dates.clear();
				} else if (ix !== -1) {
					this.dates.remove(ix);
				} else {
					this.dates.push(date);
				}
				if (typeof this.o.multidate === 'number')
					while (this.dates.length > this.o.multidate)
						this.dates.remove(0);
			},

			_setDate: function(date, which) {
				if (!which || which === 'date')
					this._toggle_multidate(date && new Date(date));
				if (!which || which === 'view')
					this.viewDate = date && new Date(date);

				this.fill();
				this.setValue();
				this._trigger('changeDate');
				var element;
				if (this.isInput) {
					element = this.element;
				} else if (this.component) {
					element = this.element.find('input');
				}
				if (element) {
					element.change();
				}
				if (this.o.autoclose && (!which || which === 'date')) {
					this.hide();
				}
			},

			moveMonth: function(date, dir) {
				if (!date)
					return undefined;
				if (!dir)
					return date;
				var new_date = new Date(date.valueOf()),
					day = new_date.getUTCDate(),
					month = new_date.getUTCMonth(),
					mag = Math.abs(dir),
					new_month, test;
				dir = dir > 0 ? 1 : -1;
				if (mag === 1) {
					test = dir === -1
					// If going back one month, make sure month is not current month
					// (eg, Mar 31 -> Feb 31 == Feb 28, not Mar 02)
					? function() {
						return new_date.getUTCMonth() === month;
					}
					// If going forward one month, make sure month is as expected
					// (eg, Jan 31 -> Feb 31 == Feb 28, not Mar 02)
					: function() {
						return new_date.getUTCMonth() !== new_month;
					};
					new_month = month + dir;
					new_date.setUTCMonth(new_month);
					// Dec -> Jan (12) or Jan -> Dec (-1) -- limit expected date to 0-11
					if (new_month < 0 || new_month > 11)
						new_month = (new_month + 12) % 12;
				} else {
					// For magnitudes >1, move one month at a time...
					for (var i = 0; i < mag; i++)
					// ...which might decrease the day (eg, Jan 31 to Feb 28, etc)...
						new_date = this.moveMonth(new_date, dir);
					// ...then reset the day, keeping it in the new month
					new_month = new_date.getUTCMonth();
					new_date.setUTCDate(day);
					test = function() {
						return new_month !== new_date.getUTCMonth();
					};
				}
				// Common date-resetting loop -- if date is beyond end of month, make it
				// end of month
				while (test()) {
					new_date.setUTCDate(--day);
					new_date.setUTCMonth(new_month);
				}
				return new_date;
			},

			moveYear: function(date, dir) {
				return this.moveMonth(date, dir * 12);
			},

			dateWithinRange: function(date) {
				return date >= this.o.startDate && date <= this.o.endDate;
			},

			keydown: function(e) {
				if (this.picker.is(':not(:visible)')) {
					if (e.keyCode === 27) // allow escape to hide and re-show picker
						this.show();
					return;
				}
				var dateChanged = false,
					dir, newDate, newViewDate,
					focusDate = this.focusDate || this.viewDate;
				switch (e.keyCode) {
					case 27: // escape
						if (this.focusDate) {
							this.focusDate = null;
							this.viewDate = this.dates.get(-1) || this.viewDate;
							this.fill();
						} else
							this.hide();
						e.preventDefault();
						break;
					case 37: // left
					case 39: // right
						if (!this.o.keyboardNavigation)
							break;
						dir = e.keyCode === 37 ? -1 : 1;
						if (e.ctrlKey) {
							newDate = this.moveYear(this.dates.get(-1) || UTCToday(), dir);
							newViewDate = this.moveYear(focusDate, dir);
							this._trigger('changeYear', this.viewDate);
						} else if (e.shiftKey) {
							newDate = this.moveMonth(this.dates.get(-1) || UTCToday(), dir);
							newViewDate = this.moveMonth(focusDate, dir);
							this._trigger('changeMonth', this.viewDate);
						} else {
							newDate = new Date(this.dates.get(-1) || UTCToday());
							newDate.setUTCDate(newDate.getUTCDate() + dir);
							newViewDate = new Date(focusDate);
							newViewDate.setUTCDate(focusDate.getUTCDate() + dir);
						}
						if (this.dateWithinRange(newDate)) {
							this.focusDate = this.viewDate = newViewDate;
							this.setValue();
							this.fill();
							e.preventDefault();
						}
						break;
					case 38: // up
					case 40: // down
						if (!this.o.keyboardNavigation)
							break;
						dir = e.keyCode === 38 ? -1 : 1;
						if (e.ctrlKey) {
							newDate = this.moveYear(this.dates.get(-1) || UTCToday(), dir);
							newViewDate = this.moveYear(focusDate, dir);
							this._trigger('changeYear', this.viewDate);
						} else if (e.shiftKey) {
							newDate = this.moveMonth(this.dates.get(-1) || UTCToday(), dir);
							newViewDate = this.moveMonth(focusDate, dir);
							this._trigger('changeMonth', this.viewDate);
						} else {
							newDate = new Date(this.dates.get(-1) || UTCToday());
							newDate.setUTCDate(newDate.getUTCDate() + dir * 7);
							newViewDate = new Date(focusDate);
							newViewDate.setUTCDate(focusDate.getUTCDate() + dir * 7);
						}
						if (this.dateWithinRange(newDate)) {
							this.focusDate = this.viewDate = newViewDate;
							this.setValue();
							this.fill();
							e.preventDefault();
						}
						break;
					case 32: // spacebar
						// Spacebar is used in manually typing dates in some formats.
						// As such, its behavior should not be hijacked.
						break;
					case 13: // enter
						focusDate = this.focusDate || this.dates.get(-1) || this.viewDate;
						this._toggle_multidate(focusDate);
						dateChanged = true;
						this.focusDate = null;
						this.viewDate = this.dates.get(-1) || this.viewDate;
						this.setValue();
						this.fill();
						if (this.picker.is(':visible')) {
							e.preventDefault();
							if (this.o.autoclose)
								this.hide();
						}
						break;
					case 9: // tab
						this.focusDate = null;
						this.viewDate = this.dates.get(-1) || this.viewDate;
						this.fill();
						this.hide();
						break;
				}
				if (dateChanged) {
					if (this.dates.length)
						this._trigger('changeDate');
					else
						this._trigger('clearDate');
					var element;
					if (this.isInput) {
						element = this.element;
					} else if (this.component) {
						element = this.element.find('input');
					}
					if (element) {
						element.change();
					}
				}
			},

			showMode: function(dir) {
				if (dir) {
					this.viewMode = Math.max(this.o.minViewMode, Math.min(2, this.viewMode + dir));
				}
				this.picker
					.find('>div')
					.hide()
					.filter('.datepicker-' + DPGlobal.modes[this.viewMode].clsName)
					.css('display', 'block');
				this.updateNavArrows();
			}
		};

		var DateRangePicker = function(element, options) {
			this.element = $(element);
			this.inputs = $.map(options.inputs, function(i) {
				return i.jquery ? i[0] : i;
			});
			delete options.inputs;

			$(this.inputs)
				.datepicker(options)
				.bind('changeDate', $.proxy(this.dateUpdated, this));

			this.pickers = $.map(this.inputs, function(i) {
				return $(i).data('datepicker');
			});
			this.updateDates();
		};
		DateRangePicker.prototype = {
			updateDates: function() {
				this.dates = $.map(this.pickers, function(i) {
					return i.getUTCDate();
				});
				this.updateRanges();
			},
			updateRanges: function() {
				var range = $.map(this.dates, function(d) {
					return d.valueOf();
				});
				$.each(this.pickers, function(i, p) {
					p.setRange(range);
				});
			},
			dateUpdated: function(e) {
				// `this.updating` is a workaround for preventing infinite recursion
				// between `changeDate` triggering and `setUTCDate` calling.  Until
				// there is a better mechanism.
				if (this.updating)
					return;
				this.updating = true;

				var dp = $(e.target).data('datepicker'),
					new_date = dp.getUTCDate(),
					i = $.inArray(e.target, this.inputs),
					l = this.inputs.length;
				if (i === -1)
					return;

				$.each(this.pickers, function(i, p) {
					if (!p.getUTCDate())
						p.setUTCDate(new_date);
				});

				//临时修复选择后面的日期不会自动修正前面日期的bug
				var j = 0;
				for (j = 0; j < this.pickers.length; j++) {
					this.dates[j] = this.pickers[j].getDate();
				}
				j = i - 1;
				while (j >= 0 && new_date < this.dates[j]) {
					this.pickers[j--].setUTCDate(new_date);
				}

				if (new_date < this.dates[i]) {
					// Date being moved earlier/left
					while (i >= 0 && new_date < this.dates[i]) {
						this.pickers[i--].setUTCDate(new_date);
					}
				} else if (new_date > this.dates[i]) {
					// Date being moved later/right
					while (i < l && new_date > this.dates[i]) {
						this.pickers[i++].setUTCDate(new_date);
					}
				}
				this.updateDates();

				delete this.updating;
			},
			remove: function() {
				$.map(this.pickers, function(p) {
					p.remove();
				});
				delete this.element.data().datepicker;
			}
		};

		function opts_from_el(el, prefix) {
			// Derive options from element data-attrs
			var data = $(el).data(),
				out = {},
				inkey,
				replace = new RegExp('^' + prefix.toLowerCase() + '([A-Z])');
			prefix = new RegExp('^' + prefix.toLowerCase());

			function re_lower(_, a) {
				return a.toLowerCase();
			}
			for (var key in data)
				if (prefix.test(key)) {
					inkey = key.replace(replace, re_lower);
					out[inkey] = data[key];
				}
			return out;
		}

		function opts_from_locale(lang) {
			// Derive options from locale plugins
			var out = {};
			// Check if "de-DE" style date is available, if not language should
			// fallback to 2 letter code eg "de"
			if (!dates[lang]) {
				lang = lang.split('-')[0];
				if (!dates[lang])
					return;
			}
			var d = dates[lang];
			$.each(locale_opts, function(i, k) {
				if (k in d)
					out[k] = d[k];
			});
			return out;
		}

		var old = $.fn.datepicker;
		$.fn.datepicker = function(option) {
			var args = Array.apply(null, arguments);
			args.shift();
			var internal_return;
			this.each(function() {
				var $this = $(this),
					data = $this.data('datepicker'),
					options = typeof option === 'object' && option;
				if (!data) {
					var elopts = opts_from_el(this, 'date'),
						// Preliminary otions
						xopts = $.extend({}, defaults, elopts, options),
						locopts = opts_from_locale(xopts.language),
						// Options priority: js args, data-attrs, locales, defaults
						opts = $.extend({}, defaults, locopts, elopts, options);
					if ($this.is('.input-daterange') || opts.inputs) {
						var ropts = {
							inputs: opts.inputs || $this.find('input').toArray()
						};
						$this.data('datepicker', (data = new DateRangePicker(this, $.extend(opts, ropts))));
					} else {
						$this.data('datepicker', (data = new Datepicker(this, opts)));
					}
				}
				if (typeof option === 'string' && typeof data[option] === 'function') {
					internal_return = data[option].apply(data, args);
					if (internal_return !== undefined)
						return false;
				}
			});
			if (internal_return !== undefined)
				return internal_return;
			else
				return this;
		};

		var defaults = $.fn.datepicker.defaults = {
			autoclose: true,
			beforeShowDay: $.noop,
			calendarWeeks: false,
			clearBtn: false,
			daysOfWeekDisabled: [],
			endDate: Infinity,
			forceParse: true,
			format: 'yyyy-mm-dd',
			keyboardNavigation: true,
			language: 'zh-CN',
			minViewMode: 0,
			multidate: false,
			multidateSeparator: ',',
			orientation: "auto",
			rtl: false,
			size: '',
			startDate: -Infinity,
			startView: 0,
			todayBtn: false,
			todayHighlight: true,
			weekStart: 0,
			timepicker: false,
		};
		var locale_opts = $.fn.datepicker.locale_opts = [
			'format',
			'rtl',
			'weekStart'
		];
		$.fn.datepicker.Constructor = Datepicker;
		var dates = $.fn.datepicker.dates = {
			"en": {
				days: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
				daysShort: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
				daysMin: ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"],
				months: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
				monthsShort: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
				today: "Today",
				clear: "Clear"
			},
			"zh-CN": {
				days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
				daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
				daysMin: ["日", "一", "二", "三", "四", "五", "六", "日"],
				months: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
				monthsShort: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
				today: "今日",
				weekStart: 0
			}
		};

		var DPGlobal = {
			modes: [{
				clsName: 'days',
				navFnc: 'Month',
				navStep: 1
			}, {
				clsName: 'months',
				navFnc: 'FullYear',
				navStep: 1
			}, {
				clsName: 'years',
				navFnc: 'FullYear',
				navStep: 10
			}],
			isLeapYear: function(year) {
				return (((year % 4 === 0) && (year % 100 !== 0)) || (year % 400 === 0));
			},
			getDaysInMonth: function(year, month) {
				return [31, (DPGlobal.isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month];
			},
			validParts: /dd?|DD?|mm?|MM?|yy(?:yy)?/g,
			nonpunctuation: /[^ -\/:-@\[\u3400-\u9fff-`{-~\t\n\r]+/g,
			parseFormat: function(format) {
				// IE treats \0 as a string end in inputs (truncating the value),
				// so it's a bad format delimiter, anyway
				var separators = format.replace(this.validParts, '\0').split('\0'),
					parts = format.match(this.validParts);
				if (!separators || !separators.length || !parts || parts.length === 0) {
					throw new Error("Invalid date format.");
				}
				return {
					separators: separators,
					parts: parts
				};
			},
			parseDate: function(date, format, language) {
				if (!date)
					return undefined;
				if (date instanceof Date)
					return date;
				if (typeof format === 'string')
					format = DPGlobal.parseFormat(format);
				var part_re = /([\-+]\d+)([dmwy])/,
					parts = date.match(/([\-+]\d+)([dmwy])/g),
					part, dir, i;
				if (/^[\-+]\d+[dmwy]([\s,]+[\-+]\d+[dmwy])*$/.test(date)) {
					date = new Date();
					for (i = 0; i < parts.length; i++) {
						part = part_re.exec(parts[i]);
						dir = parseInt(part[1]);
						switch (part[2]) {
							case 'd':
								date.setUTCDate(date.getUTCDate() + dir);
								break;
							case 'm':
								date = Datepicker.prototype.moveMonth.call(Datepicker.prototype, date, dir);
								break;
							case 'w':
								date.setUTCDate(date.getUTCDate() + dir * 7);
								break;
							case 'y':
								date = Datepicker.prototype.moveYear.call(Datepicker.prototype, date, dir);
								break;
						}
					}
					return UTCDate(date.getUTCFullYear(), date.getUTCMonth(), date.getUTCDate(), 0, 0, 0);
				}
				parts = date && date.match(this.nonpunctuation) || [];
				date = new Date();
				var parsed = {},
					setters_order = ['yyyy', 'yy', 'M', 'MM', 'm', 'mm', 'd', 'dd'],
					setters_map = {
						yyyy: function(d, v) {
							return d.setUTCFullYear(v);
						},
						yy: function(d, v) {
							return d.setUTCFullYear(2000 + v);
						},
						m: function(d, v) {
							if (isNaN(d))
								return d;
							v -= 1;
							while (v < 0) v += 12;
							v %= 12;
							d.setUTCMonth(v);
							while (d.getUTCMonth() !== v)
								d.setUTCDate(d.getUTCDate() - 1);
							return d;
						},
						d: function(d, v) {
							return d.setUTCDate(v);
						}
					},
					val, filtered;
				setters_map['M'] = setters_map['MM'] = setters_map['mm'] = setters_map['m'];
				setters_map['dd'] = setters_map['d'];
				date = UTCDate(date.getFullYear(), date.getMonth(), date.getDate(), 0, 0, 0);
				var fparts = format.parts.slice();
				// Remove noop parts
				if (parts.length !== fparts.length) {
					fparts = $(fparts).filter(function(i, p) {
						return $.inArray(p, setters_order) !== -1;
					}).toArray();
				}
				// Process remainder
				function match_part() {
					var m = this.slice(0, parts[i].length),
						p = parts[i].slice(0, m.length);
					return m === p;
				}
				if (parts.length === fparts.length) {
					var cnt;
					for (i = 0, cnt = fparts.length; i < cnt; i++) {
						val = parseInt(parts[i], 10);
						part = fparts[i];
						if (isNaN(val)) {
							switch (part) {
								case 'MM':
									filtered = $(dates[language].months).filter(match_part);
									val = $.inArray(filtered[0], dates[language].months) + 1;
									break;
								case 'M':
									filtered = $(dates[language].monthsShort).filter(match_part);
									val = $.inArray(filtered[0], dates[language].monthsShort) + 1;
									break;
							}
						}
						parsed[part] = val;
					}
					var _date, s;
					for (i = 0; i < setters_order.length; i++) {
						s = setters_order[i];
						if (s in parsed && !isNaN(parsed[s])) {
							_date = new Date(date);
							setters_map[s](_date, parsed[s]);
							if (!isNaN(_date))
								date = _date;
						}
					}
				}
				return date;
			},
			formatDate: function(date, format, language) {
				if (!date)
					return '';
				if (typeof format === 'string')
					format = DPGlobal.parseFormat(format);
				var val = {
					d: date.getUTCDate(),
					D: dates[language].daysShort[date.getUTCDay()],
					DD: dates[language].days[date.getUTCDay()],
					m: date.getUTCMonth() + 1,
					M: dates[language].monthsShort[date.getUTCMonth()],
					MM: dates[language].months[date.getUTCMonth()],
					yy: date.getUTCFullYear().toString().substring(2),
					yyyy: date.getUTCFullYear()
				};
				val.dd = (val.d < 10 ? '0' : '') + val.d;
				val.mm = (val.m < 10 ? '0' : '') + val.m;
				date = [];
				var seps = $.extend([], format.separators);
				for (var i = 0, cnt = format.parts.length; i <= cnt; i++) {
					if (seps.length)
						date.push(seps.shift());
					date.push(val[format.parts[i]]);
				}
				return date.join('');
			},
			headTemplate: '<thead>' +
				'<tr class="date-header">' +
				'<th class="prev"><b></b></th>' +
				'<th colspan="5" class="datepicker-switch"></th>' +
				'<th class="next"><b></b></th>' +
				'</tr>' +
				'</thead>',
			contTemplate: '<tbody><tr><td colspan="7"></td></tr></tbody>',
			footTemplate: '<tfoot>' +
				'<tr>' +
				'<th colspan="7" class="today"></th>' +
				'</tr>' +
				'<tr>' +
				'<th colspan="7" class="clear"></th>' +
				'</tr>' +
				'</tfoot>',
			timepicerTemplate: '<div class="timepicker-container"></div>'
		};
		DPGlobal.template = '<div class="datepicker">' +
			'<div class="datepicker-days clearfix">' +
			'<table class=" table-condensed">' +
			DPGlobal.headTemplate +
			'<tbody></tbody>' +
			DPGlobal.footTemplate +
			'</table>' +
			DPGlobal.timepicerTemplate +
			'</div>' +
			'<div class="datepicker-months">' +
			'<table class="table-condensed">' +
			DPGlobal.headTemplate +
			DPGlobal.contTemplate +
			DPGlobal.footTemplate +
			'</table>' +
			'</div>' +
			'<div class="datepicker-years">' +
			'<table class="table-condensed">' +
			DPGlobal.headTemplate +
			DPGlobal.contTemplate +
			DPGlobal.footTemplate +
			'</table>' +
			'</div>' +
			'</div>';

		$.fn.datepicker.DPGlobal = DPGlobal;


		/* DATEPICKER NO CONFLICT
		 * =================== */

		$.fn.datepicker.noConflict = function() {
			$.fn.datepicker = old;
			return this;
		};


		/* DATEPICKER DATA-API
		 * ================== */

		$(document).on(
			'focus.datepicker.data-api click.datepicker.data-api',
			'[data-toggle="datepicker"]',
			function(e) {
				var $this = $(this);
				if ($this.data('datepicker'))
					return;
				e.preventDefault();
				// component click requires us to explicitly show it
				$this.datepicker('show');
			}
		);
		$(function() {
			$('[data-toggle="datepicker-inline"]').datepicker();
		});

	}(window.jQuery, undefined);

	 /*jshint sub:true*/
	 /*jshint sub:true*/
	!function ($) {
	  function TimePicker(element, cfg){
	    if(!(this instanceof TimePicker)){
	      return new TimePicker(element, cfg);
	    }

	    this.init(element, cfg);
	  }

	  TimePicker.prototype = {

	    _defaultCfg: {
	      hour: (new Date()).getHours(),
	      minute: (new Date()).getMinutes(),
	      orientation: {x: 'auto', y: 'auto'},
	      keyboardNavigation: true
	    },

	    init: function(element, cfg){

	      this.element  = $(element)
	      this.isInline = false;
	      this.isInDatepicker = false;
	      this.isInput = this.element.is('input');
	      
	      this.component = this.element.is('.date') ? this.element.find('.add-on, .input-group-addon, .sui-btn') : false;
	      this.hasInput = this.component && this.element.find('input').length;
	      if (this.component && this.component.length === 0)
	        this.component = false;


	      this.picker = $('<div class="timepicker"></div>');


	      this.o = this.config = $.extend(this._defaultCfg, cfg);

	      this._buildEvents();
	      this._attachEvents();

	      if(this.isInDatepicker){
	        this.picker.addClass('timepicker-in-datepicker').appendTo(this.element);
	      }else if (this.isInline){
	        this.picker.addClass('timepicker-inline').appendTo(this.element);
	        this._show();
	      }else{
	        this.picker.addClass('timepicker-dropdown dropdown-menu');
	      }
	    },

	    destory: function(){
	      this._detachSecondaryEvents();
	      this.picker.html('');
	      this.picker = null;
	    },

	    _show: function(){
	      if (!this.isInline&&!this.isInDatepicker)
	          this.picker.appendTo('body');
	      this.picker.show();
	      this._place();
	      this._render();
	      this._attachSecondaryEvents();
	    },
	    show: function () {
	      return this._show();
	    },
	    _hide: function(){
	      if (this.isInline || this.isInDatepicker)
	        return;
	      if (!this.picker.is(':visible'))
	        return;
	      this.focusDate = null;
	      this.picker.hide().detach();
	      this._detachSecondaryEvents();
	      this._setValue();
	    },

	    _keydown: function(e){
	      if (this.isInDatepicker) return;
	      if (this.picker.is(':not(:visible)')){
	        if (e.keyCode === 27) // allow escape to hide and re-show picker
	          this._show();
	        return;
	      }
	      var dir,rol;
	      switch (e.keyCode){
	        case 27: // escape
	          this._hide();
	          e.preventDefault();
	          break;
	        case 37: // left
	        case 39: // right
	          if (!this.o.keyboardNavigation)
	            break;//和input 输入有冲突 注释掉
	          // dir = e.keyCode === 37 ? 'up' : 'down';
	          // rol = 'hour';
	          // this._slide(rol,dir);
	          break;
	        case 38: // up
	        case 40: // down
	          if (!this.o.keyboardNavigation)
	            break;
	          // dir = e.keyCode === 38 ? 'up' : 'down';
	          // rol = 'minute';
	          // this._slide(rol,dir);
	          break;
	        case 32: // spacebar
	          // Spacebar is used in manually typing dates in some formats.
	          // As such, its behavior should not be hijacked.
	          break;
	        case 13: // enter
	          this._hide();
	          break;
	      }
	    },

	    _place:function(){
	      if (this.isInline || this.isInDatepicker)
	          return;
	      var calendarWidth = this.picker.outerWidth(),
	        calendarHeight = this.picker.outerHeight(),
	        visualPadding = 10,
	        $window = $(window),
	        windowWidth = $window.width(),
	        windowHeight = $window.height(),
	        scrollTop = $window.scrollTop();

	        var zIndex = parseInt(this.element.parents().filter(function(){
	            return $(this).css('z-index') !== 'auto';
	          }).first().css('z-index'))+10;
	        var offset = this.component ? this.component.parent().offset() : this.element.offset();
	        var height = this.component ? this.component.outerHeight(true) : this.element.outerHeight(false);
	        var width = this.component ? this.component.outerWidth(true) : this.element.outerWidth(false);
	        var left = offset.left,
	          top = offset.top;

	        this.picker.removeClass(
	          'datepicker-orient-top datepicker-orient-bottom '+
	          'datepicker-orient-right datepicker-orient-left'
	        );

	        if (this.o.orientation.x !== 'auto'){
	          this.picker.addClass('datepicker-orient-' + this.o.orientation.x);
	          if (this.o.orientation.x === 'right')
	            left -= calendarWidth - width;
	        }
	        // auto x orientation is best-placement: if it crosses a window
	        // edge, fudge it sideways
	        else {
	          // Default to left
	          this.picker.addClass('datepicker-orient-left');
	          if (offset.left < 0)
	            left -= offset.left - visualPadding;
	          else if (offset.left + calendarWidth > windowWidth)
	            left = windowWidth - calendarWidth - visualPadding;
	        }

	        // auto y orientation is best-situation: top or bottom, no fudging,
	        // decision based on which shows more of the calendar
	        var yorient = this.o.orientation.y,
	          top_overflow, bottom_overflow;
	        if (yorient === 'auto'){
	          top_overflow = -scrollTop + offset.top - calendarHeight;
	          bottom_overflow = scrollTop + windowHeight - (offset.top + height + calendarHeight);
	          if (Math.max(top_overflow, bottom_overflow) === bottom_overflow)
	            yorient = 'top';
	          else
	            yorient = 'bottom';
	        }
	        this.picker.addClass('datepicker-orient-' + yorient);
	        if (yorient === 'top')
	          top += height + 6;
	        else
	          top -= calendarHeight + parseInt(this.picker.css('padding-top')) + 6;

	        this.picker.css({
	          top: top,
	          left: left,
	          zIndex: zIndex > 1020 ? zIndex : 1030
	        });
	    },

	    // envent method
	    _events: [],
	    _secondaryEvents: [],
	    _applyEvents: function(evs){
	      for (var i=0, el, ch, ev; i < evs.length; i++){
	        el = evs[i][0];
	        if (evs[i].length === 2){
	          ch = undefined;
	          ev = evs[i][1];
	        }
	        else if (evs[i].length === 3){
	          ch = evs[i][1];
	          ev = evs[i][2];
	        }
	        el.on(ev, ch);
	      }
	    },
	    _unapplyEvents: function(evs){
	      for (var i=0, el, ev, ch; i < evs.length; i++){
	        el = evs[i][0];
	        if (evs[i].length === 2){
	          ch = undefined;
	          ev = evs[i][1];
	        }
	        else if (evs[i].length === 3){
	          ch = evs[i][1];
	          ev = evs[i][2];
	        }
	        el.off(ev, ch);
	      }
	    },

	    _attachEvents: function(){
	      this._detachEvents();
	      this._applyEvents(this._events);
	    },
	    _detachEvents: function(){
	      this._unapplyEvents(this._events);
	    },
	    _attachSecondaryEvents: function(){
	      this._detachSecondaryEvents();
	      this._applyEvents(this._secondaryEvents);
	      this._pickerEvents();
	    },
	    _detachSecondaryEvents: function(){
	      this._unapplyEvents(this._secondaryEvents);
	      this.picker.off('click');
	    },

	    _buildEvents:function(){
	      if (this.isInput){ // single input
	        this._events = [
	          [this.element, {
	            focus: $.proxy(this._show, this),
	            keyup: $.proxy(function(e){
	              if ($.inArray(e.keyCode, [27,37,39,38,40,32,13,9]) === -1)
	                this._updateUI();
	            }, this),
	            keydown: $.proxy(this._keydown, this)
	          }]
	        ];
	      }
	      else if (this.component && this.hasInput){ // component: input + button
	        this._events = [
	          // For components that are not readonly, allow keyboard nav
	          [this.element.find('input'), {
	            focus: $.proxy(this._show, this),
	            keyup: $.proxy(function(e){
	              if ($.inArray(e.keyCode, [27,37,39,38,40,32,13,9]) === -1)
	                this._updateUI();
	            }, this),
	            keydown: $.proxy(this._keydown, this)
	          }],
	          [this.component, {
	            click: $.proxy(this._show, this)
	          }]
	        ];
	      }
	      else if (this.element.is('div')){  // inline timepicker
	        if (this.element.is('.timepicker-container')) {
	          this.isInDatepicker = true;
	        } else{
	          this.isInline = true;
	        }
	      }
	      else {
	        this._events = [
	          [this.element, {
	            click: $.proxy(this._show, this)
	          }]
	        ];
	      }
	      this._events.push(
	        // Component: listen for blur on element descendants
	        [this.element, '*', {
	          blur: $.proxy(function(e){
	            this._focused_from = e.target;
	          }, this)
	        }],
	        // Input: listen for blur on element
	        [this.element, {
	          blur: $.proxy(function(e){
	            this._focused_from = e.target;
	          }, this)
	        }]
	      );

	      this._secondaryEvents = [
	        [$(window), {
	          resize: $.proxy(this._place, this)
	        }],
	        [$(document), {
	          'mousedown touchstart': $.proxy(function(e){
	            // Clicked outside the datepicker, hide it
	            if (!(
	              this.element.is(e.target) ||
	              this.element.find(e.target).length ||
	              this.picker.is(e.target) ||
	              this.picker.find(e.target).length
	            )){
	              this._hide();
	            }
	          }, this)
	        }]
	      ];
	    },

	    _pickerEvents: function(){

	      var self = this;

	      this.picker.on('click', '.J_up', function(ev){

	        var target = ev.currentTarget,
	          parentNode = $(target).parent(),
	          role = parentNode.attr('data-role');

	        self._slide(role, 'up');

	      }).on( 'click', '.J_down',function(ev){
	        var target = ev.currentTarget,
	          parentNode = $(target).parent(),
	          role = parentNode.attr('data-role');

	        self._slide(role, 'down');

	      }).on( 'click', 'span',function(ev){

	        var target = ev.currentTarget,
	          parentNode = $(target).parent().parent().parent(),
	          role = parentNode.attr('data-role'),
	          targetNum = target.innerHTML,
	          attrs = self[role + 'Attr'],
	          step = parseInt(targetNum - attrs.current,10),
	          dur;
	        if(step > 0){
	          self._slideDonw(attrs, step);
	        }else{
	          self._slideUp(attrs, -step);
	        }

	      });
	    },

	    _slide: function(role, direction){

	      var attrs = this[role+ 'Attr'];

	      if(direction == 'up'){
	        this._slideUp(attrs);	
	      }else if(direction == 'down'){
	        this._slideDonw(attrs);
	      }
	    },

	    _slideDonw: function(attrs, step, notSetValue){

	      step = step || 1;
	      var cp = attrs.cp,
	        dur = attrs.ih*step;

	      attrs.current += step;

	      if(attrs.current > attrs.maxSize){
	        attrs.current = 0;
	        dur = -attrs.ih * attrs.maxSize;
	      }

	      attrs.cp -= dur;
	      this._animate(attrs.innerPickerCon, attrs.cp);

	      $('.current', attrs.innerPickerCon).removeClass('current');
	      $('span[data-num="' + attrs.current + '"]', attrs.innerPickerCon).addClass('current');
	      if (!notSetValue) {
	        this._setValue();
	      }
	    },

	    _slideUp: function(attrs, step ,notSetValue){

	      step = step || 1;

	      var cp = attrs.cp,
	        dur = attrs.ih*step;

	      attrs.current -= step;

	      if(attrs.current < 0){
	        attrs.current = attrs.maxSize;
	        dur = -attrs.ih * attrs.maxSize;
	      }

	      attrs.cp += dur;
	      this._animate(attrs.innerPickerCon, attrs.cp);
	      $('.current', attrs.innerPickerCon).removeClass('current');
	      $('span[data-num="' + attrs.current + '"]', attrs.innerPickerCon).addClass('current');
	      if (!notSetValue) {
	        this._setValue();
	      }
	    },
	    _updateSlide:function(attrs,step){
	      var notSetValue = true;
	      if(step&&(step > 0)){
	        this._slideDonw(attrs, step, notSetValue);
	      }else if(step){
	        this._slideUp(attrs, -step, notSetValue);
	      }
	    },
	    _updateUI: function(){
	      var oldMimute = this.o.minute,
	          oldHour = this.o.hour,
	          attrs,role,step;
	      
	      this._getInputTime();
	      

	      if (oldMimute !== this.o.minute) {
	        attrs = this['minuteAttr'];
	        step = parseInt(this.o.minute - attrs.current,10);
	        this._updateSlide(attrs,step);
	      }
	      if (oldHour !== this.o.hour) {
	        attrs = this['hourAttr'];
	        step = parseInt(this.o.hour - attrs.current,10);
	        this._updateSlide(attrs,step);
	      }
	    },

	    //将时间设置在input 或者 data-api里
	    _doSetValue:function(timeStr,notSetValue){
	      var element;
	      if (this.isInput){
	        element = this.element;
	      }
	      else if (this.component){
	        element = this.element.find('input');
	      }
	      if (element){
	        element.change();
	        element.val(timeStr);
	      }else if(this.isInDatepicker){
	        this.element.data("time",timeStr);
	        if (!notSetValue) {
	          this.element.trigger('time:change');
	        }
	      }
	    },
	    _render: function(){
	      this.picker.html('');
	      this._getInputTime();
	      this._renderHour();
	      this._renderMinutes();
	      this._renderSplit();
	      //form input
	      this._setValue();
	    },
	    _foramtTimeString:function(val){
	      var time = {
	        minute:0,
	        hour:0
	      },minute,hour;
	      val = val.split(':');
	      for (var i = val.length - 1; i >= 0; i--) {
	        val[i] = $.trim(val[i]);
	      }
	      if (val.length === 2) {
	        minute = parseInt(val[1],10);
	        if (minute >= 0 && minute < 60) {
	          time.minute = minute;
	        }
	        hour = parseInt(val[0],10);
	        if (hour >= 0 && hour < 24) {
	          time.hour = hour;
	        }
	      }
	      return time;
	    },
	    _getInputTime: function(){
	      if (this.isInline&&this.isInDatepicker) return;
	      var element,minute,hour,val,time;
	      if (this.isInput||this.isInDatepicker){
	        element = this.element;
	      }
	      else if (this.component){
	        element = this.element.find('input');
	      }
	      if (element){
	        if(this.isInDatepicker){
	          val = $.trim(element.data('time'));
	        }else{
	          val = $.trim(element.val());
	        }
	        time = this._foramtTimeString(val)
	        this.o.minute = time.minute;
	        this.o.hour = time.hour;
	      }
	    },

	    _juicer: function(current,list){
	      var items = '',item;
	      for (var i = list.length - 1; i >= 0; i--) {
	        if (list[i] == current) {
	          item = '<span ' + 'class="current" data-num="' + i + '">' + list[i] + '</span>';
	        } else{
	          item = '<span ' + 'data-num="' + i + '">' + list[i] + '</span>';
	        }
	        items = item + items;
	      }
	      return '<div class="picker-wrap">' +
	            '<a href="javascript:;" class="picker-btn up J_up"><b class="arrow"></b><b class="arrow-bg"></b></a>' +
	              '<div class="picker-con">'+
	                '<div class="picker-innercon">'+
	                  items +
	                '</div>' +
	              '</div>' +
	              '<a href="javascript:;" class="picker-btn down J_down"><b class="arrow"></b><b class="arrow-bg"></b></a>' +
	            '</div>';
	    },

	    _renderHour: function(){
	      var self = this,
	        hourRet = [];

	      for(var i = 0; i < 24; i++){
	        hourRet.push(self._beautifyNum(i));
	      }

	      var tpl = this._juicer(self.o.hour,hourRet),
	        $tpl = $(tpl);

	      $tpl.attr('data-role', 'hour');

	      this.picker.append($tpl);

	      this.hourAttr = this._addPrefixAndSuffix($tpl, 23);
	      this.hourAttr.current = this.o.hour;
	      this.hourAttr.maxSize = 23;
	    },

	    _renderMinutes: function(){
	      var self = this,
	        minuteRet = [];
	      for(var i = 0; i < 60; i++){
	        minuteRet.push(self._beautifyNum(i));
	      }

	      var tpl = this._juicer(self.o.minute, minuteRet),
	        $tpl = $(tpl);

	      $tpl.attr('data-role', 'minute');

	      this.picker.append($tpl);

	      this.minuteAttr = this._addPrefixAndSuffix($tpl, 59);
	      this.minuteAttr.current = this.o.minute;
	      this.minuteAttr.maxSize = 59;
	    },

	    _addPrefixAndSuffix: function(parentNode, maxSize){

	      var self = this,
	        pickerCon = $('.picker-con', parentNode),
	        innerPickerCon = $('.picker-innercon', parentNode),
	        currentNode = $('.current', parentNode),
	        itemH = currentNode.outerHeight(),
	        parentH = pickerCon.outerHeight(),
	        fixNum = Math.floor(parentH/itemH) + 1,
	        currentNodeOffsetTop,
	        currentPosition,
	        tpl = '';

	      for(var j = maxSize - fixNum; j <= maxSize; j++){
	        tpl += '<span>' + self._beautifyNum(j) + '</span>';
	      }

	      innerPickerCon.prepend($(tpl));

	      tpl = '';

	      for(var i = 0; i < fixNum; i ++){
	        tpl += '<span>' + self._beautifyNum(i) + '</span>';
	      }

	      innerPickerCon.append($(tpl));

	      currentNodeOffsetTop = currentNode.offset().top - pickerCon.offset().top;
	      currentPosition =  -currentNodeOffsetTop + itemH * 2;
	      this._animate(innerPickerCon, currentPosition);

	      return {
	        ph: parentH,
	        cp: currentPosition,
	        ih: itemH,
	        innerPickerCon: innerPickerCon,
	        scrollNum: fixNum - 1
	      };
	    },

	    _renderSplit: function(){
	      var tpl = '<div class="timePicker-split">' +
	              '<div class="hour-input"></div>' +
	              '<div class="split-icon">:</div>' +
	              '<div class="minute-input"></div>' +
	            '</div>';

	      this.picker.append($(tpl));
	    },
	    _getCurrentTimeStr: function(){
	      var  text, minute, hour;
	      hour = this.hourAttr.current;
	      minute =  this.minuteAttr.current;
	      text = this._beautifyNum(hour)+':'+ this._beautifyNum(minute);
	      return text;
	    },
	    _setValue: function(){
	      if (this.isInline) return;
	      this._doSetValue(this._getCurrentTimeStr()); //将时间装填在 input 或者 data api 里
	    },

	    _animate: function(node, dur){
	    	node.css({
	        'top': dur + 'px',
	      });

	      // if ($.support.transition) {
	      //   node.css({
	      //     'top': dur + 'px',
	      //   });
	      // }else{
	      //   node.animate({
	      //     top: dur + 'px',
	      //   },300);
	      // }
	      
	    },

	    _beautifyNum: function(num){
	      num = num.toString();
	      if(parseInt(num) < 10){
	        return '0' + num;
	      }

	      return num;
	    },
	    //通过参数来更新日期
	    //timeStr(string): 12:20
	    //notSetValue(string): false/true , 是否需要将数值设置在input中. true 的时候只能设置在data-api中,这个参数只用在datepicker中
	    update: function(timeStr,notSetValue){
	      this._doSetValue(timeStr,notSetValue);
	      this._updateUI();
	    },

	    getTime: function(){
	      return this._getCurrentTimeStr();
	    }
	  }

	  /* DROPDOWN PLUGIN DEFINITION
	     * ========================== */
	  //maincode end
	  var old = $.fn.timepicker;
	  $.fn.timepicker = function(option){
	    var args = Array.apply(null, arguments);
	      args.shift();
	      var internal_return;
	    this.each(function(){
	      var $this = $(this)
	          , data = $this.data('timepicker')
	      if (!data) $this.data('timepicker', (data = new TimePicker(this,option)))
	      if (typeof option === 'string' && typeof data[option] === 'function'){
	        internal_return = data[option].apply(data, args);
	        if (internal_return !== undefined)
	          return false;
	      }
	    });
	    if (internal_return !== undefined)
	      return internal_return;
	    else
	      return this;
	  }
	  /* TIMEPICKER NO CONFLICT
	    * =================== */

	  $.fn.timepicker.noConflict = function(){
	    $.fn.timepicker = old;
	    return this;
	  };


	  /* TIMEPICKER DATA-API
	  * ================== */

	  $(document).on(
	    'focus.timepicker.data-api click.timepicker.data-api',
	    '[data-toggle="timepicker"]',
	    function(e){
	      var $this = $(this);
	      if ($this.data('timepicker'))
	        return;
	      e.preventDefault();
	      // component click requires us to explicitly show it
	      $this.timepicker('_show');
	    }
	  );
	  $(function(){
	    $('[data-toggle="timepicker-inline"]').timepicker();
	  });
	}(window.jQuery)



/***/ },
/* 78 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 79 */,
/* 80 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 81 */,
/* 82 */
/***/ function(module, exports, __webpack_require__) {

	/**
	 * ui.tree
	 */

	__webpack_require__(83);

	if(!$.ui) {
		$.ui = {};
	}
	/**
	 * ui.Emitter
	 */
	(function(ui) {
		ui.Emitter = Emitter;
		function Emitter() {
			this.callbacks = {};
		};

		Emitter.prototype.on = function(event, fn) {
			(this.callbacks[event] = this.callbacks[event] || []).push(fn);
			return this;
		};
		Emitter.prototype.once = function(event, fn) {
			var self = this;

			function on() {
				self.off(event, on);
				fn.apply(this, arguments);
			}

			this.on(event, on);
			return this;
		};
		Emitter.prototype.off = function(event, fn) {
			var callbacks = this.callbacks[event];
			if (!callbacks) return this;
			// remove all handlers
			if (1 == arguments.length) {
				delete this.callbacks[event];
				return this;
			}
			// remove specific handler 
			var i = callbacks.indexOf(fn);
			callbacks.splice(i, 1);
			return this;
		};
		Emitter.prototype.emit = function(event) {
			var args = [].slice.call(arguments, 1),
				callbacks = this.callbacks[event];
			if (callbacks) {
				for (var i = 0, len = callbacks.length; i < len; ++ i) {
					callbacks[i].apply(this, args);
				}
			}
			return this;
		};
	})($.ui);

	(function(ui, html) {
		ui.Tree = Tree;
	 	ui.tree = function(data, container, sub, selected) {
	 		switch (arguments.length) {
	 			case 4:
	 				return new Tree({ data: data, container: container, sub: sub, selected: selected });
	 			case 3:
	 				return new Tree({ data: data, container: container, sub: sub, selected: true });
	    		case 2:
	      			return new Tree({ data: data, container: container, sub: sub, selected: true });
	    		case 1:
	      			return new Tree({ data: data, container: 'body', sub: true, selected: true });
	  		}
	 	};
	 	function Tree(options) {
	 		ui.Emitter.call(this);
			options = options || {};
			this._options = options;
			this.template = html;
			this.el = $(this.template);
			this.render(options);
			this.initEvent();
	 	};

	 	Tree.prototype = new ui.Emitter;

	 	/**
	 	 * 递归处理节点是否选中
	 	 * @return {[type]} [description]
	 	 */
	 	Tree.prototype.handleData = function(data) {

	 		for(var i = 0; i < data.length; i++) {
	 			var node = data[i];
	 			var subNode = node.subcate;
	 			var checkedNum = 0;

	 			if(subNode && subNode.length > 0) {
					for(var j = 0; j < subNode.length; j++) {
						if(subNode[j].checked) {
							checkedNum++;
						}
					}
					if(checkedNum === subNode.length) {
						node.cate.checked = true;
					} else if(checkedNum > 0) {
						node.cate.subchecked = true;
					}
				}
	 		}

	 		// function isChildChecked(node) {
	 		// 	var subNode = node.subcate;
	 		// 	var checkedNum = 0;
	 		// 	var subCheckedNum = 0;
	 		// 	var flag;

	 		// 	if(subNode && subNode.length > 0) {
	 		// 		for(var i = 0; i < subNode.length; i++) {
	 		// 			flag = isChildChecked(subNode[i]);
	 		// 			if(flag === 'checked') {
	 		// 				checkedNum++;
	 		// 			} else if(flag === 'subchecked') {
	 		// 				subCheckedNum++;
	 		// 			}
	 		// 		}
	 		// 		if(checkedNum === subNode.length) {
	 		// 			node.cate.checked = true;
	 		// 			return 'checked';
	 		// 		} else if(subCheckedNum != 0) {
	 		// 			node.cate.subchecked = true;
	 		// 			return 'subchecked';
	 		// 		} else {
	 		// 			return 'unchecked';
	 		// 		}
	 		// 	} else {
	 		// 		if(node.cate.checked) {
	 		// 			return 'checked';
	 		// 		} else {
	 		// 			return 'unchecked';
	 		// 		}
	 		// 	}
	 		// }
	 		return data;
	 	};

	 	Tree.prototype.render = function(options) {
	 		var el = this.el,
	 			self = this,
	 			data = this.handleData($.extend(true, [], options.data)),
	 			length = data.length,
	 			sub = options.sub,
	 			selected = options.selected;

	 		if (0 < length) {
	 			var nodeHtml = [];
	 			for (var i = 0; i < length; i++) {
	 				var node = data[i],
	 					hasSubCate = node.subcate.length > 0 ? true : false; 
	 				
	 				// 渲染本行
	 				nodeHtml.push('<tr tid="' + node.cate['cid'] + '">');
	 				if (i == 0) {
	 					nodeHtml.push('<td><span class="tree-el tree-img-el tree-line23-el"></span></td>');
	 					
	 				} else if (i == length - 1) {
	 					nodeHtml.push('<td><span class="tree-el tree-img-el tree-line12-el"></span></td>');

	 				} else {
	 					nodeHtml.push('<td><span class="tree-el tree-img-el tree-line123-el"></span></td>');
	 					
	 				}

	 				if (hasSubCate) {
	 					nodeHtml.push('<td><a class="tree-el tree-img-el tree-unfold-el" href="javascript:void(0);" type="pulldown"></a></td>');
	 				} else {
	 					nodeHtml.push('<td><span class="tree-el tree-img-el tree-line24-el"></td>');
	 				}

	 				// 判断是否一开始就选中
	 				var temp = '<td><span class="tree-el tree-img-el #{classname}" tid="' + node.cate['cid'] + 
	 					'" type="node" #{status}></span></td>' +
						'<td><span class="tree-text-el" type="text">' + node.cate['name'] + '</span></td>' +
						'<td></td>';
	 				if(node.cate.subchecked) {
	 					temp = temp.replace('#{classname}', 'tree-sub-checked-el')
	 						.replace('#{status}', 'sts="subChecked"');
	 				} else if(node.cate.checked) {
	 					temp = temp.replace('#{classname}', 'tree-checked-el')
	 						.replace('#{status}', 'sts="checked"');
	 				} else {
	 					temp = temp.replace('#{classname}', 'tree-chk-el')
	 						.replace('#{status}', '');
	 				}

	 				nodeHtml.push(temp);

	 				nodeHtml.push('</tr>');

	 				// 渲染子行
	 				if (hasSubCate) {
	 					var subCates = node.subcate,
	 						subCateLength = subCates.length;

	 					nodeHtml.push('<tr ptid="' + node.cate['cid'] + '">');
		 				if (i < length - 1) {
							nodeHtml.push('<td><span class="tree-el tree-img-el tree-line13-el"></span></td>');
						} else {
							nodeHtml.push('<td></td>');
						}
						nodeHtml.push('<td></td>');
		 				if (hasSubCate) {
		 					nodeHtml.push('<td><span class="tree-el tree-img-el tree-line13-el"></span></td>');
		 				} else {
		 					nodeHtml.push('<td></td>');
		 				}
		 				nodeHtml.push('<td></td><td></td></tr>');

	 					for (var j = 0; j < subCateLength; j++) {
	 						var subNode = subCates[j];
	 						// 渲染子本行
	 						nodeHtml.push('<tr tid="' + subNode['cid'] + '" ptid="' + node.cate['cid'] + '">');
		 					if (i < length - 1) {
		 						nodeHtml.push('<td><span class="tree-el tree-img-el tree-line13-el"></span></td>');
		 					} else {
		 						nodeHtml.push('<td></td>');
		 					}
			 				nodeHtml.push('<td></td>');

			 				if (j < subCateLength - 1) {
			 					nodeHtml.push('<td><span class="tree-el tree-img-el tree-line123-el"></span></td>');
			 				} else {
			 					nodeHtml.push('<td><span class="tree-el tree-img-el tree-line12-el"></span></td>');
			 				}

			 				// 判断是否一开始就选中
			 				var subTemp = '<td><span class="tree-el tree-img-el #{classname}" tid="' + subNode['cid'] + 
			 					'" ptid="' + node.cate['cid'] + '" type="node" #{status}></span></td>' +
								'<td><span class="tree-text-el" type="text">' + subNode['name'] + '</span></td>' +
								'<td></td>';
			 				if(subNode.subchecked) {
			 					subTemp = subTemp.replace('#{classname}', 'tree-sub-checked-el')
			 						.replace('#{status}', 'sts="subChecked"');
			 				} else if(subNode.checked) {
			 					subTemp = subTemp.replace('#{classname}', 'tree-checked-el')
			 						.replace('#{status}', 'sts="checked"');
			 				} else {
			 					subTemp = subTemp.replace('#{classname}', 'tree-chk-el')
			 						.replace('#{status}', '');
			 				}

			 				nodeHtml.push(subTemp);

			 				nodeHtml.push('</tr>');

			 				// 渲染下一行
			 				if (j < subCateLength - 1) {
			 					nodeHtml.push('<tr ptid="' + node.cate['cid'] + '">');
			 					if (i < length - 1) {
			 						nodeHtml.push('<td><span class="tree-el tree-img-el tree-line13-el"></span></td>');
			 					} else {
			 						nodeHtml.push('<td></td>');
			 					}
			 					nodeHtml.push('<td></td><td><span class="tree-el tree-img-el tree-line13-el"></span></td>' +
			 						'<td></td><td></td></tr>');
			 				}
	 					}
	 				}

	 				// 渲染下一行
	 				if (i < length - 1) {
	 					nodeHtml.push('<tr>');
		 				if (i < length - 1) {
							nodeHtml.push('<td><span class="tree-el tree-img-el tree-line13-el"></span></td>');
						} else {
							nodeHtml.push('<td></td>');
						}
		 				nodeHtml.push('<td></td><td></td><td></td><td></td></tr>');
	 				}
	 			}

	 			el.find('tbody').html(nodeHtml.join(''));
	 		}
	 	};

	 	Tree.prototype.initEvent = function() {
			this.el.on('click', '.tree-chk-el, .tree-sub-checked-el', function() {	// 选中
				var $this = $(this),
					tid = $this.attr('tid'),
					ptid = $this.attr('ptid');

				// 本节点
				check($this);
				// 子节点
				check($('.tree-chk-el[ptid=' + tid + ']'));
				// 父节点
				if (1 == subElChkSts(ptid)) {
					check($('.tree-el[tid=' + ptid + ']'));
				} else if (2 == subElChkSts(ptid)) {
					subCheck($('.tree-el[tid=' + ptid + ']'));
				} else if (0 == subElChkSts(ptid)) {
					uncheck($('.tree-el[tid=' + ptid + ']'));
				}
			}).on('click', '.tree-checked-el', function() {		// 取消选中
				var $this = $(this),
					tid = $this.attr('tid'),
					ptid = $this.attr('ptid');

				// 本节点
				uncheck($this);
				// 本节点
				uncheck($('.tree-checked-el[ptid=' + tid + ']'));
				// 父节点
				if (1 == subElChkSts(ptid)) {
					check($('.tree-el[tid=' + ptid + ']'));
				} else if (2 == subElChkSts(ptid)) {
					subCheck($('.tree-el[tid=' + ptid + ']'));
				} else if (0 == subElChkSts(ptid)) {
					uncheck($('.tree-el[tid=' + ptid + ']'));
				}
			}).on('click', '.tree-unfold-el', function() {		// 收起
				var $this = $(this),
					tid = $this.parents('tr').attr('tid');
				$('tr[ptid=' + tid + ']').hide();
				$this.removeClass('tree-unfold-el').addClass('tree-fold-el');
			}).on('click', '.tree-fold-el', function() {		// 展开
				var $this = $(this),
					tid = $this.parents('tr').attr('tid');
				$('tr[ptid=' + tid + ']').show();
				$this.removeClass('tree-fold-el').addClass('tree-unfold-el');
			}).on('click', '.tree-text-el[type="text"]', function() {		// 点击文本
				$(this).parents('tr').find('span[type="node"]').click();
			});
	 	};

		Tree.prototype.show = function(fn) {
			var self = this,
				el = self.el,
				container = self._options.container;
	 	
	 		self.emit('show');

		    el.appendTo(container);
	 		return self;
	 	};
	 	Tree.prototype.hide = function(ms) {
			var self = this;
			// duration
			if (ms) {
				setTimeout(function() {
					self.hide();
				}, ms);
				return this;
			}
			this.emit('hide');
			
			// hide / remove
			this.el.addClass('f-dn');
			if (this._effect) {
				setTimeout(function(self) {
					self.remove();
				}, 500, this);
			} else {
				self.remove();
			}

			return this;
		};
		Tree.prototype.remove = function() {
			this.el.remove();
			return this;
		};

		// Tree 操作
		// 节点事件监听
		Tree.prototype.on = function(event, handler) {
			if ('function' == typeof(handler)) {
				if ('change' == event) {
					this.el.on('propertychange', 'span[type="node"]', handler);
				} else {
					this.el.on(event, 'span[type="node"]', handler);
				}
			}
		}
		// 节点操作
		// 增加节点
		Tree.prototype.add = function() {

		}

		Tree.prototype.getStatus = function() {
			var result = {};
			this.el.find('span[type="node"]').each(function() {
				var $this = $(this),
					cid = $this.attr('tid'),
					status = $this.attr('sts');

				if (undefined == status) {
					result[cid] = 0;
				} else if ('checked' == status)  {
					result[cid] = 1;
				} else if ('subChecked' == status) {
					result[cid] = 2;
				}
			});
			return result;
		}

		Tree.prototype.check = function(cid) {
			if (undefined != cid) {
				var $node = this.el.find('span[type="node"][tid=' + cid + ']');
				if ('checked' != $node.attr('sts')) {
					$node.click();
				}
			}
		}

		Tree.prototype.uncheck = function(cid) {
			if (undefined != cid) {
				var $node = this.el.find('span[type="node"][tid=' + cid + ']');
				if ('undefined' != $node.attr('sts')) {
					$node.click();
				}
			}
		}

	})($.ui, '<table class=\"u-tree\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\"><tbody>' +
			'</tbody></table>');

	// 1 全选 0 全不选 2 部分选
	function subElChkSts(tid) {
		var subEls = $('.tree-el[ptid=' + tid + ']'),
			length = subEls.length,
			checkedNum = 0;
			status = 0;

		subEls.each(function() {
			if ('checked' == $(this).attr('sts')) {
				checkedNum ++;
			} 
		});

		if (checkedNum > 0) {
			status = checkedNum == length ? 1 : 2;
		} else {
			status = 0;
		}

		return status;
	}


	function check($el) {
		$el.removeClass('tree-chk-el').removeClass('tree-sub-checked-el').addClass('tree-checked-el').attr('sts', 'checked');
	}

	function subCheck($el) {
		$el.removeClass('tree-chk-el').removeClass('tree-checked-el').addClass('tree-sub-checked-el').attr('sts', 'subChecked');
	}

	function uncheck($el) {
		$el.removeClass('tree-checked-el').removeClass('tree-sub-checked-el').addClass('tree-chk-el').removeAttr('sts')
	}

/***/ },
/* 83 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 84 */,
/* 85 */,
/* 86 */
/***/ function(module, exports, __webpack_require__) {

	/**
	 * by acwong
	 */

	__webpack_require__(87);

	var template = __webpack_require__(66);
	var loadingTpl = __webpack_require__(90);
	var tableloadingtpl =__webpack_require__(91);

	var Loading = function (element, options) {
	  var defaultOptions = {
	    position: 'absolute' // static table
	    // text: 加载中
	    // top: 
	    // left: 
	    // colspan:  
	  };

	  this.$element = $(element);
	  this.options = $.extend(defaultOptions, options);
	};

	Loading.prototype = {
	  show: function () {
	    if (this.options.position !== 'table-static') {
	      this.$loading = $(template.compile(loadingTpl)(this.options));
	    } else {
	      this.$loading = $(template.compile(tableloadingtpl)(this.options));
	    }

	    var $element = this.$element;
	    var $loading = this.$loading;
	    var $loadingInner = this.$loading.find('.loading-inner');

	    if (this.options.position === 'absolute' || this.options.position === 'table-absolute') {
	      $element.append($loading);
	      this.elementPosition = $element.css('position');
	      $element.css('position', 'relative');
	      // $loading.addClass('mask');
	      $loading.css({
	        'position': 'absolute',
	        'top': this.options.position === 'table-absolute' ? '44px' : 0,
	        'left': 0,
	        'bottom': 0
	      });

	      if (this.options.top || this.options.left) {
	        $loadingInner.css({
	          'position': 'absolute',
	          'top': this.options.top ? this.options.top + 'px' : '50%',
	          'left': this.options.left ? this.options.left + 'px' : '50%'
	        }).css({
	          'margin-left': this.options.left ? 0 : $loadingInner.width() / -2 + 'px',
	          'margin-top': this.options.top ? 0 : $loadingInner.height() / -2 + 'px'
	        });

	      } else {
	        $loadingInner.css({
	          'position': 'absolute',
	          'top': '50%',
	          'left': '50%'
	        }).css({
	          'margin-left': $loadingInner.width() / -2 + 'px',
	          'margin-top': $loadingInner.height() / -2 + 'px'
	        });
	      }
	    }

	    if (this.options.position === 'static') {
	      $element.html($loading);
	      $loading.css('position', 'relative');
	      $loadingInner.css({
	        'position': 'absolute',
	        'top': '50%',
	        'left': '50%'
	      }).css({
	        'margin-left': $loadingInner.width() / -2 + 'px',
	        'margin-top': $loadingInner.height() / -2 + 'px'
	      });
	    }

	    if (this.options.position === 'table-static') {
	      $element.html($loading);
	    }

	    return this;
	  },

	  hide: function () {
	    if (this.options.position === 'absolute') {
	      if (this.elementPosition) {
	        this.$element.css('position', this.elementPosition);
	      }
	      this.$loading.remove();
	    } else {
	      this.$loading.remove();
	    }

	    return this;
	  }
	};

	$.fn.loading = function(conf) {
	  return new Loading(this, conf);
	};

/***/ },
/* 87 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 88 */,
/* 89 */,
/* 90 */
/***/ function(module, exports) {

	module.exports = "<div class=\"loading\">\n  {{if position === 'absolute' || position === 'table-absolute'}}\n  <div class=\"mask\"></div>\n  {{/if}}\n  <div class=\"loading-inner\">\n    <i class=\"loading-pic\"></i>\n    {{if text}}\n    <p class=\"loading-text\">{{text}}</p>\n    {{/if}}\n  </div>\n</div>";

/***/ },
/* 91 */
/***/ function(module, exports) {

	module.exports = "<tr>\n  <td colspan=\"{{colspan}}\">\n    <div class=\"loading loading-table\" >\n      <div class=\"loading-inner\">\n        <i class=\"loading-pic\"></i>\n        {{if text}}\n        <p class=\"loading-text\">{{text}}</p>\n        {{/if}}\n      </div>\n    </div>\n  </td>\n</tr>";

/***/ },
/* 92 */
/***/ function(module, exports, __webpack_require__) {

	/**
	 * by acwong
	 */


	/**
	 * Modal 组件用法
	 * 1.新建Modal
	 *   先写好弹窗的结构，然后利用弹窗的 id 新建一个弹窗组件
	 *   var modal = $('#modal').modal({});
	 *
	 *   第二个参数为配置项
	 *   options = {
	 *     backdrop: true // 是否显示遮罩层
	 *     keyboard: true // 是否使用Esc 关闭弹窗
	 *     okHide: fn,    // 确定关闭弹窗回调函数
	 *     cancelHide     // 取消关闭弹窗回调函数
	 *     backdropParent: 
	 *   }
	 *
	 * 2.事件
	 *   show: 弹窗打开
	 *   shown: 弹窗打开完毕
	 *   hide: 弹窗关闭
	 *   hidden: 弹窗关闭完毕
	 *   okHide: 确定关闭弹窗
	 *   cancleHid: 取消关闭弹窗
	 *
	 * 3.静态方法
	 *   
	 *
	 *   $.confirm({
	 *      type: 'confirm',
	        backdrop: true,
	        keyboard: true,
	        hasfoot: true,
	        hashead: true,
	        closeBtn: true,
	        title: '确认',
	        okBtn: '确认',
	        cancleBtn: '取消',
	        html: ''
	 *   })
	 */

	var template = __webpack_require__(66);
	var tplAlert = __webpack_require__(93);

	__webpack_require__(94);

	var Modal = function (element, options) {
	  var defaultOptions = {
	    backdrop: true,
	    keyboard: true
	  }

	  this.options = $.extend(defaultOptions, options);
	  this.$element = $(element);
	  this.init();
	}

	Modal.prototype = {
	  constructor: Modal,

	  init: function () {
	    var $ele = this.$element;
	    var opt = this.options;
	    var w = opt.width;
	    var h = opt.height;
	    var standardW = {
	      small: 450,
	      large: 800
	    };

	    $ele
	      .on('click.dismiss.modal', '[data-dismiss="modal"]', $.proxy(this.hide, this))
	      .on('click.ok.modal', ':not(.disabled)[data-ok="modal"]', $.proxy(this.okHide, this));

	    if (w) {
	      standardW[w] && (w = standardW[w]);
	      $ele.width(w).css('margin-left', -parseInt(w) / 2);
	    }
	  },

	  show: function () {
	    var self = this;
	    var e = $.Event('show');
	    var $ele = this.$element;
	    $ele.trigger(e);

	    if (this.isShown) {
	      return false;
	    }

	    this.isShown = true;
	    this.escape();
	    this.backdrop(function () {
	      if (!$ele.parent().length) {
	        $ele.appendTo($('body'));
	      }

	      // 局部弹窗
	      if (self.$backdropParent) {
	        $ele.appendTo(self.$backdropParent)
	          .css('position', 'absolute');
	      }

	      self.resize();
	      $ele.show();

	      if ($ele[0].offsetWidth) {
	        $ele.addClass('in');
	      }

	      self.enforceFocus();

	      $ele.trigger('shown');
	    });

	  },

	  hide: function (e) {
	    e && e.preventDefault();
	    var $ele = this.$element;
	    e = $.Event('hide');
	    this.hideReason != 'ok' && $ele.trigger('cancelHide');
	    $ele.trigger(e);

	    if (this.hideReason != 'ok' && this.options.cancelHide) {
	      this.options.cancelHide && this.options.cancelHide();
	    }

	    if (!this.isShown) {
	      return false;
	    }
	    this.isShown = false;
	    this.escape();
	    this.hideModal();
	  },

	  okHide: function (e) {
	    var self = this;
	    var $ele = this.$element;
	    if (!e) {
	      hideWithOk();
	      return;
	    }

	    var fn = this.options.okHide;
	    var ifNeedHide = true;

	    if (!fn) {

	    }

	    $ele.trigger('okHide');

	    typeof fn == 'function' && (ifNeedHide = fn.call(this));

	    if (ifNeedHide !== false) {
	      hideWithOk();
	    }

	    function hideWithOk () {
	      self.hideReason = 'ok';
	      self.hide(e);
	    }

	    return this.$element;
	  },

	  hideModal: function () {
	    var self = this;
	    var $ele = this.$element;
	    $ele.removeClass('in');
	    setTimeout(function () {
	      $ele.hide();
	    }, 200);

	    this.backdrop(function () {
	      setTimeout(function () {
	        self.removeBackdrop();
	      }, 200);
	      $ele.trigger(self.hideReason == 'ok' ? 'okHidden' : 'cancelHidden')
	      self.hideReason = null;
	      $ele.trigger('hidden');

	      $ele.data('hidetype') == 'remove' && $ele.remove();
	    });

	  },

	  resize: function () {
	    var $ele = this.$element;
	    var $header = $ele.find('.modal-header');
	    var $footer = $ele.find('.modal-footer');
	    var $body = $ele.find('.modal-body');
	    var eleH = $ele.height();
	    var winH = $(window).height();
	    var mt = 0;
	    var maxHeight = winH - $header.outerHeight() - $footer.outerHeight();

	    if (eleH >= winH) {
	      mt = -winH / 2;
	      $body.css({
	        maxHeight: maxHeight
	      });
	    } else {
	      mt = (winH - eleH) / (1 + 1.618) - winH / 2;
	      $body.css({
	        maxHeight: 600
	      });
	    }

	    if($body.outerHeight() <= 360) {
	      $body.css({
	        overflow: 'visible'
	      });
	    } else {
	      $body.css({
	        overflow: 'auto'
	      });
	    }

	    $ele.css('margin-top', parseInt(mt));
	    return $ele;
	  },

	  backdrop: function (callback) {
	    var self = this;
	    var opt = this.options;

	    if (this.isShown) {
	      if (opt.backdrop !== false) {
	        // 局部弹窗
	        if (opt.backdropParent) {
	          this.$backdropParent = $(opt.backdropParent);
	          this.$backdropParent.css('position', 'relative');
	          this.$backdrop = $('<div class="modal-backdrop"></div>').appendTo(this.$backdropParent);
	        } else {
	          this.$backdrop = $('<div class="modal-backdrop"></div>').appendTo($('body'));
	        }
	        
	        this.$backdrop.click(
	          opt.backdrop == 'static' ?
	            $.proxy(this.$element[0].focus, this.$element[0]) : $.proxy(this.hide, this)
	        );

	        if (this.$backdrop[0].offsetWidth) {
	          this.$backdrop.addClass('in');
	        }

	        // 局部弹窗
	        if (opt.backdropParent) {
	          this.$backdrop.css('position', 'absolute');
	          callback && callback();
	          return false;
	        }
	      }


	      w1 = $(window).width();
	      $('body').addClass('fancybox-lock-test');
	      w2 = $(window).width();
	      $('body').removeClass('fancybox-lock-test');
	      $("<style type='text/css' id='fancybox'>.fancybox-margin{margin-right:" + (w2 - w1) + "px;}</style>").appendTo($('head'));
	      $('body').addClass('fancybox-margin').addClass('modal-open');
	      callback && callback();
	    } else {

	      if (this.$backdrop) {
	        this.$backdrop.removeClass('in');
	        setTimeout(function () {
	          $('body').removeClass('fancybox-margin').removeClass('modal-open');
	          $('#fancybox').remove();
	        }, 200);
	        callback && callback();
	      }
	    }
	  },

	  escape: function () {
	    var self = this;
	    if (this.isShown && this.options.keyboard) {
	      this.$element.on('keyup.dismiss.modal', function (e) {
	        // console.log(e);
	        e.which == 27 && self.hide();
	      });
	    } else if (!this.isShown) {
	      this.$element.off('keyup.dismiss.modal');
	    }
	  },

	  enforceFocus: function () {
	    var self = this;
	    $(document).off('focusin.modal').on('focusin.modal', function (e) {
	      if (self.$element[0] !== e.target && !self.$element.has(e.target).length) {
	        self.$element.focus();
	      }
	    });
	  },

	  removeBackdrop: function () {
	    this.$backdrop && this.$backdrop.remove();
	    this.$backdrop = null;
	  }
	}

	$.extend({
	  _modal: function ($tpl, cfg) {
	    var modal = new Modal($tpl, cfg);
	    modal.show();
	  },

	  confirm: function (customCfg) {
	    var defaultCfg = {
	      // type: 'success',
	      backdrop: true,
	      keyboard: true,
	      hasfoot: true,
	      hashead: true,
	      closeBtn: true,
	      title: '确认',
	      okBtn: '确认',
	      cancleBtn: '取消',
	      html: ''
	    }

	    customCfg = customCfg || {};

	    var cfg = $.extend(defaultCfg, customCfg);

	    var tpl = template.compile(tplAlert)({
	      opt: cfg
	    });

	    this._modal($(tpl), cfg);
	  }
	});

	$.fn.modal = function(conf) {
	  return new Modal(this, conf);
	}

	module.exports = Modal;



/***/ },
/* 93 */
/***/ function(module, exports) {

	module.exports = "<div class=\"modal\" data-hidetype=\"remove\">\n  <div class=\"modal-dialog\">\n    <div class=\"modal-content\">\n      {{if opt.hashead}}\n      <div class=\"modal-header\">\n        {{if opt.closeBtn}}\n        <button class=\"close\" data-dismiss=\"modal\">×</button>\n        {{/if}}\n        <h4 class=\"modal-title\">{{opt.title}}</h4>\n      </div>\n      {{/if}}\n      <div class=\"modal-body {{if opt.hasfoot}}has-footer{{/if}}\">\n        {{if opt.text}}\n          <div class=\"modal-confirm z-ib\">\n            <span>\n              {{if opt.type === 'success'}}\n              <i class=\"icon-successfill z-i-success\"></i>\n              {{/if}}\n              {{if opt.type === 'error'}}\n              <i class=\"icon-warnfill z-i-danger\"></i>\n              {{/if}}\n              {{if opt.type === 'warn'}}\n              <i class=\"icon-warnfill z-i-warn\"></i>\n              {{/if}}\n            </span>\n            <span class=\"confirm-text\">\n              {{#opt.text}}\n            </span>\n          </div>\n        {{else}}\n          {{#opt.html}}\n        {{/if}}\n      </div>\n      {{if opt.hasfoot}}\n      <div class=\"modal-footer\">\n        {{if opt.okBtn}}\n        <div class=\"btn btn-primary btn-large\" data-ok=\"modal\">{{opt.okBtn}}</div>\n        {{/if}}\n        {{if opt.cancleBtn}}\n        <div class=\"btn btn-gray btn-large\" data-dismiss=\"modal\">{{opt.cancleBtn}}</div>\n        {{/if}}\n      </div>\n      {{/if}}\n    </div>\n  </div>\n</div>";

/***/ },
/* 94 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 95 */,
/* 96 */
/***/ function(module, exports, __webpack_require__) {

	/**
	 * by acwong
	 */

	/**
	 * Pagination 组件用法
	 * 1.创建组件
	 *   var pagination = $('#pagination').pagination({});
	 *
	 *   配置项
	 *
	 *   options = {
	 *     pages: 50, // 总页数
	 *     current: 1, // 当前页数默认为1
	 *     onSelect: fn,  // 点击或输入页面时候调用的函数，传递点击或输入的页码为参数
	 *     onChangePageSize: fn // 点击条数下拉，传递所选条数为参数
	 *     pageSizeList: [10, 100, 200] // 条数下拉的选项
	 *     pageSize: 10 // 可选，默认为 pageSizeList 第一项
	 *   }
	 *
	 *   调用 pagination.updatePages(pages, pageToGo)
	 *   pages: 新总页数
	 *   pageToGo: 跳到目标页数
	 *
	 *   调用 pagination.updateTotalCount(total, pageToGo, pageSize)
	 *   total: 新总条数
	 *   pageToGo: 跳到目标页数
	 *   
	 */

	var template = __webpack_require__(66);
	var tplPagination = __webpack_require__(97);
	var tplPaginationSimple = __webpack_require__(98);

	__webpack_require__(99);

	var Pagination = function ($wrap, options) {
	  var defaultOptions = {
	    showCtrl: false,
	    current: 1,
	    type: 'normal'
	    // pageSizeList: 
	    // pages
	    // pageSize
	    // totalCount
	    // selectorDirection
	  };

	  this.$wrap = $wrap;
	  this.options = $.extend(defaultOptions, options);

	  if (!this.options.pages && this.options.pageSizeList) {
	    this.options.pageSize = this.options.pageSize || this.options.pageSizeList[0];
	    this.options.pages = Math.ceil(this.options.totalCount / this.options.pageSize);
	  }

	  this.init();
	}

	Pagination.prototype = {
	  init: function () {
	    this.getPaginationArray();
	    this.render();
	    this.initEvent();
	  },

	  initEvent: function () {
	    var self = this;
	    var opt = this.options;
	    var $wrap = this.$wrap;

	    if (opt.onSelect) {
	      $wrap.on('click', '[data-page]', function () {
	        var page = parseInt($(this).data('page'));

	        if (!page || page <= 0 || page > opt.pages || page === opt.current) {
	          return false;
	        }

	        opt.current = page;
	        self.render();
	        opt.onSelect && opt.onSelect(page);
	      });

	      $wrap.on('keyup', '.js-page-num', function (e) {
	        var page = parseInt($(this).val());

	        if (!page || page <= 0 || page > opt.pages) {
	          return false;
	        }

	        // console.log(e);
	        if (e.which === 13) {
	          opt.current = page;
	          self.render();
	          opt.onSelect && opt.onSelect(page);
	        }
	      });

	      $wrap.on('change', '.page-size-control input', function () {
	        self.options.pageSize = parseInt($(this).val());
	        var pages = Math.ceil(self.options.totalCount / self.options.pageSize);

	        opt.onChangePageSize && opt.onChangePageSize(self.options.pageSize);

	        self.updatePages(pages, 1);

	      });
	    }
	    
	  },

	  render: function (pages) {
	    var $pageWrap = this.$wrap.parent();

	    if (this.options.type === 'normal') {
	      var tpl = template.compile(tplPagination)({
	        opt: this.options
	      });
	    } else if (this.options.type === 'simple') {
	      var tpl = template.compile(tplPaginationSimple)({
	        opt: this.options
	      });
	    }

	    if (pages === 0) {
	       $pageWrap.hide();
	    } else {
	      $pageWrap.show();
	      this.$wrap.html(tpl);
	    }
	  },

	  getPaginationArray: function () {
	    var pages = this.options.pages;
	    var pagesArr = [];

	    for (var i = 1; i <= pages; i++) {
	      pagesArr.push(i);
	    }

	    this.options.pagesArr = pagesArr;
	  },

	  updatePages: function (pages, pageToGo) {
	    var pages = parseInt(pages);
	    var pageToGo = parseInt(pageToGo);
	      
	    this.options.pages = pages || 0;

	    if (pageToGo) {
	      this.options.current = pageToGo;
	    }
	    this.getPaginationArray();
	    this.render(pages);
	  },

	  updateTotalCount: function (totalCount, pageToGo, pageSize) {
	    this.options.totalCount = totalCount;
	    this.options.pageSize = pageSize || this.options.pageSizeList[0];

	    var pages = Math.ceil(this.options.totalCount / this.options.pageSize);

	    this.updatePages(pages, pageToGo);
	  }
	}

	$.fn.pagination = function(conf) {
	  return new Pagination(this, conf);
	}

	module.exports = Pagination;

/***/ },
/* 97 */
/***/ function(module, exports) {

	module.exports = "{{if opt.pageSizeList}}\n<div class=\"page-size-control\">\n  <div class=\"selector {{if opt.selectorDirection !== 'down'}}up{{/if}}\">\n    <a class=\"selector-text\">{{opt.pageSize || opt.pageSizeList[0]}}条/页</a>\n    <i class=\"icon-down\"></i>\n    <input type=\"hidden\" value=\"{{opt.pageSize}}\"/>\n    <ul class=\"selector-menu\">\n      {{each opt.pageSizeList as pageSize}}\n      <li>\n        <a class=\"selector-item\" data-value=\"{{pageSize}}\">\n          {{pageSize}}条/页<i class=\"icon-checked\"></i>\n        </a>\n      </li>\n      {{/each}}\n    </ul>\n  </div>\n</div>\n{{/if}}\n{{if opt.showCtrl}}\n<div class=\"page-control\">\n  跳至\n  <span><input type=\"text\" class=\"input js-page-num\"></span>\n  页\n</div>\n{{/if}}\n<ul class=\"page-items\">\n  <li {{if opt.current === 1}}class=\"disabled\"{{/if}}><a href=\"javascript:void(0);\" {{if opt.current !== 1}}data-page=\"{{opt.current - 1}}\"{{/if}}><</a></li>\n  {{each opt.pagesArr as page}}\n    {{if opt.pages > 8}}\n      {{if opt.current <= 4}}\n        {{if page <= 5 || page === opt.pages}}\n        <li {{if page === opt.current}}class=\"active\"{{/if}}><a data-page=\"{{page}}\" href=\"javascript:void(0);\">{{page}}</a></li>\n        {{else if page === opt.pages - 1}}\n        <li class=\"ellipsis\">...</li>\n        {{/if}}\n      {{else if opt.current >= opt.pages - 3}}\n        {{if page >= opt.pages - 4 || page === 1}}\n        <li {{if page === opt.current}}class=\"active\"{{/if}}><a data-page=\"{{page}}\" href=\"javascript:void(0);\">{{page}}</a></li>\n        {{else if page === 2}}\n        <li class=\"ellipsis\">...</li>\n        {{/if}}\n      {{else}}\n        {{if page === 1 || page === opt.pages}}\n        <li {{if page === opt.current}}class=\"active\"{{/if}}><a data-page=\"{{page}}\" href=\"javascript:void(0);\">{{page}}</a></li>\n        {{/if}}\n        {{if page === 2 || page === opt.pages - 1}}\n        <li class=\"ellipsis\">...</li>\n        {{/if}}\n        {{if page >= opt.current - 2 && page <= opt.current + 2}}\n        <li {{if page === opt.current}}class=\"active\"{{/if}}><a data-page=\"{{page}}\" href=\"javascript:void(0);\">{{page}}</a></li>\n        {{/if}}\n      {{/if}}\n\n    {{else}}\n      <li {{if page === opt.current}}class=\"active\"{{/if}}><a href=\"javascript:void(0);\" data-page=\"{{page}}\">{{page}}</a></li>\n    {{/if}}\n  {{/each}}\n  <li class=\"last {{if opt.current === opt.pages || opt.pages <= 1}}disabled{{/if}}\"><a href=\"javascript:void(0);\" {{if opt.current !== opt.pages}}data-page=\"{{opt.current + 1}}\"{{/if}}>></a></li>\n</ul>";

/***/ },
/* 98 */
/***/ function(module, exports) {

	module.exports = "<span><</span>\n<span><input type=\"text\" class=\"input js-page-num\"></span>\n<span>/</span>\n<span>{{opt.pages}}</span>\n<span>></span>";

/***/ },
/* 99 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ }
/******/ ])
});
;