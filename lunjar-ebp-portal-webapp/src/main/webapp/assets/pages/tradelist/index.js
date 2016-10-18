import $ from 'jquery';
import FastClick from 'fastclick';
import template from 'art-template/dist/template';
import 'component';
import {
	getTradeList,
	closeTrade,
	confirmReceiptGoods
} from '../../api/trade-api';
import {
	handleError,
	showSuccessTip
} from '../../utils/response-util.js';
import {
	payTrade
} from '../../utils/trade-util.js';
import tradeTpl from './trade';
import './style';

const PAGESIZE = 10;
let isLoadingList = false;
let hasLoadDone = false;
let currentPageNo = 1;
let currentStatus;
const statusMap = {
	1: '待付款',
	2: '待付款',
	3: '部分发货',
	4: '待发货',
	5: '已发货',
	6: '已签收',
	7: '交易成功',
	8: '已关闭',
	9: '已关闭',
	10: '待付款确认中付款',
	11: '0元购合约中'
};
const refundStatusMap = {
	1: '已申请退款，等待卖家同意',
	2: '已同意退款，等待买家退货',
	3: '已退货，等待卖家确认收货',
	4: '卖家拒绝退款',
	5: '退款关闭',
	6: '退款成功'
};


$(() => {
	
	// 初始化获取数据
	hasLoadDone = $('input[name="hasLoadDone"]').val() <= PAGESIZE;
	currentStatus = $('input[name="status"]').val();

	// register helper
	template.helper('getStatusLabel', getStatusLabel);
	template.helper('calcTotalNum', calcTotalNum);
	template.helper('getRefundStatusLabel', getRefundStatusLabel);

	$(document).on('click', '.js-trade', e => {
		showTradeDetail($(e.currentTarget));
	})
	.on('click', '.js-pay-trade', e => {
		payTrade($(e.currentTarget));
		e.stopPropagation();
	})
	.on('click', '.js-close-trade', e => {
		const $btn = $(e.currentTarget);

		$.Confirm({
			title: '提示',
			content: '确定取消订单吗？',
			confirm: () => {
				cancelTrade($btn);
			},
			cancel: () => {}
		});
		e.stopPropagation();
	})
	.on('click', '.js-show-logicinfo', e => {
		const $btn = $(e.currentTarget);
		const lid = $btn.data('lid');

		window.location.href = `https://m.kuaidi100.com/result.jsp?nu=${lid}`;
		e.stopPropagation();
	})
	.on('click', '.js-confirm-get', e => {
		const $btn = $(e.currentTarget);
		
		$.Confirm({
			title: '提示',
			content: '确定确认收货吗？',
			confirm: () => {
				receiveGoods($btn);
			},
			cancel: () => {}
		});
		e.stopPropagation();
	})

	// 下拉到底部刷新列表
	$(window).onScrollToBottom(() => {
		if (!hasLoadDone && !isLoadingList) {
			isLoadingList = true;
			fecthTradeList();
		}
	});
});

function getStatusLabel(status) {
	return statusMap[status];
}

function getRefundStatusLabel(refundStatus) {
	return refundStatusMap[refundStatus];
}

function calcTotalNum(orders) {
	let num = 0;

	orders.forEach(order => {
		num += order.num;
	});

	return num;
}

function fecthTradeList() {
	const $loading = $('.js-loading-box');

	$loading.removeClass('z-hidden');
	getTradeList(currentStatus, ++currentPageNo).then(res => {
		$loading.addClass('z-hidden');
		renderTradeList(res);
	}, handleError);
}

function renderTradeList(res) {
	const tpl = template.compile(tradeTpl)({
		...res,
    shopId: window.shopId
	});

	isLoadingList = false;
	if (!res.page.hasNext) {
		hasLoadDone = true;
		$('.js-loaded-all').removeClass('z-hidden');
	}

	$('#tradelistWrapper').append(tpl);
	$.LayzrUpdate();
}

function replaceTrade($trade, data) {
	const tpl = template.compile(tradeTpl)({
		list: [data]
	});

	$trade.replaceWith(tpl);
	$.LayzrUpdate();
}

function showTradeDetail($trade) {
	const tid = $trade.data('tid');

	window.location.href = `/trade/detail?shopId=${window.shopId}&tradeId=${tid}`;
}

function cancelTrade($btn) {
	const tid = $btn.data('tid');

	closeTrade(tid).then(res => {
		const $trade = $btn.parents('.js-trade');

		showSuccessTip(res.msg);
		if(!currentStatus) {
			replaceTrade($trade, res.data)
		} else {
			$trade.remove();
		}
	}, handleError);
}

function receiveGoods($btn) {
	const tid = $btn.data('tid');
	
	confirmReceiptGoods(tid).then(res => {
		showSuccessTip(res.msg);
		setTimeout(() => {
			window.location.reload();
		}, 1500);
	}, handleError);
}
