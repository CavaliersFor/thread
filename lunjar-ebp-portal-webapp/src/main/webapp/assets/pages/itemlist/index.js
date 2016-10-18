import $ from 'jquery';
import template from 'art-template/dist/template';
import {
	Swiper
} from 'component';
import {
	getItemList
} from '../../api/item-api';
import {
	handleError
} from '../../utils/response-util.js';
import itemTpl from './item';
import './style';

const PAGESIZE = 10;
let isLoadingList = false;
let hasLoadDone = false;
let currentPageNo = 1;
let currentCid = 1;
let carBox;

$(() => {
	// 初始化获取数据
	hasLoadDone = $('input[name="hasLoadDone"]').val() <= PAGESIZE;
	currentCid = $('input[name="cid"]').val();

	// 轮播图
	new Swiper('.js-top-swiper', {
		pagination: '.swiper-pagination',
    loop: true,
    autoplay: 3000
  });

	// 下拉到底部刷新列表
	$(window).onScrollToBottom(() => {
		if (!hasLoadDone && !isLoadingList) {
			isLoadingList = true;
			fecthItemList();
		}
	});
});


function fecthItemList() {
	const $loading = $('.js-loading-box');

	$loading.removeClass('z-hidden');
	getItemList(currentCid, ++currentPageNo).then(res => {
		$loading.addClass('z-hidden');
		renderItemList(res);
	}, handleError);
}

function renderItemList(res) {
	const tpl = template.compile(itemTpl)({
		...res,
    shopId: window.shopId
	});

	isLoadingList = false;
	if (!res.page.hasNext) {
		hasLoadDone = true;
		$('.js-loaded-all').removeClass('z-hidden');
	}

	$('#itemlistWrapper').append(tpl);
	$.LayzrUpdate();
}
