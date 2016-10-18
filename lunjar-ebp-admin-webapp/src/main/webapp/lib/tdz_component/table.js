(function webpackUniversalModuleDefinition(root, factory) {
	if(typeof exports === 'object' && typeof module === 'object')
		module.exports = factory(require("tdz_components"));
	else if(typeof define === 'function' && define.amd)
		define(["tdz_components"], factory);
	else if(typeof exports === 'object')
		exports["tdz_components"] = factory(require("tdz_components"));
	else
		root["tdz_components"] = factory(root["tdz_components"]);
})(this, function(__WEBPACK_EXTERNAL_MODULE_2__) {
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

	module.exports = __webpack_require__(7);


/***/ },
/* 1 */,
/* 2 */
/***/ function(module, exports) {

	module.exports = __WEBPACK_EXTERNAL_MODULE_2__;

/***/ },
/* 3 */,
/* 4 */,
/* 5 */,
/* 6 */,
/* 7 */
/***/ function(module, exports, __webpack_require__) {

	/**
	 * by acwong
	 */

	__webpack_require__(2);
	__webpack_require__(8);

	// $(function () {
	//   $('#testModal').on('click', function(e){
	//     $('#myModal').modal('show');
	//   })
	// })
	// 
	$(function () {
	  // var modal = new Modal('#myModal', {
	  //   width: 'large'
	  // });
	  // var modal = new Modal('#myModal', {
	  //   width: 'large'
	  // });
	  
	  var modal = $('#myModal').modal({
	    width: 'large'
	  });

	  $('#alertModal').on('click', function () {
	    // modal.show();
	    $.confirm({
	      type: 'warn', // success error
	      text: '确定？'
	    });
	  });

	  $('#testModal').on('click', function () {
	    modal.show();
	  });

	  $('#myModal').on('show', function() {
	    console.log('show');
	  });

	  $('#myModal').on('hide', function() {
	    console.log('hide');
	  });

	  $('#myModal').on('okHide', function() {
	    console.log('okHide');
	  });


	  // var pagination = new Pagination($('#pagination'), {
	  //   pages: 50,
	  //   current: 1,
	  //   showCtrl: true,
	  //   onSelect: function (num) {
	  //     alert(num);
	  //   }
	  // });
	  var pagination = $('#pagination').pagination({
	    // pages: 3,
	    current: 1,
	    totalCount: 100,
	    showCtrl: true,
	    // selectorDirection: 'down',
	    pageSizeList: [10, 100, 200], // 条数下拉的选项
	    // pageSize:   // 可选，默认是 pageSizeList 第一项
	    onSelect: function (num) {
	      alert(num);
	    },
	    onChangePageSize: function (pageSize) {
	      alert(pageSize);
	    } // 条数下拉改变时候的回调函数
	  });
	  // 改变总条数的方法参数分别为 totalCount, pageToGo, pageSize， pageSize 默认为pageSizeList 第一项
	  pagination.updateTotalCount(500, 2);


	  // var paginationSimple = new Pagination($('#pagination-simple'), {
	  //   pages: 10,
	  //   current: 1,
	  //   type: 'simple',
	  //   onSelect: function (num) {
	  //     alert(num);
	  //   }
	  // })
	});

/***/ },
/* 8 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ }
/******/ ])
});
;