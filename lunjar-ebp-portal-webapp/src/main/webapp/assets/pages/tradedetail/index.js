import $ from 'jquery';
import 'component';
import {
	closeTrade,
	applyRefund,
	confirmReceiptGoods
} from '../../api/trade-api';
import {
	handleError,
	showSuccessTip
} from '../../utils/response-util.js';
import {
	payTrade
} from '../../utils/trade-util.js';
import './style';

$(() => {

	// 订单操作
	$(document).on('click', '.js-pay-trade', e => {
		payTrade($(e.currentTarget));
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
	})
	.on('click', '.js-apply-refund', e => {
		const $btn = $(e.currentTarget);

		$.Propt({
			title: '请填写退款原因',
			content: '',
			confirm: (event, reason) => {
				askForRefund($btn, reason);
			},
			cancel: () => {}
		});
	});
});

function reloadTrade() {
	setTimeout(() => {
		window.location.reload();
	}, 1500);
}

function cancelTrade($btn) {
	const tid = $btn.data('tid');

	closeTrade(tid).then(res => {
		const $trade = $btn.parents('.js-trade');

		showSuccessTip(res.msg);
		setTimeout(() => {
			window.location.reload();
		}, 1500);
		// $trade.find('.js-trade-status').text('已关闭');
		// $btn.parent().remove();
	}, handleError);
}

function receiveGoods($btn) {
	const tid = $btn.data('tid');

	confirmReceiptGoods(tid).then(res => {
		showSuccessTip(res.msg);
		reloadTrade();
	}, handleError);
}

function askForRefund($btn, reason) {
	const oid = $btn.data('oid');

	applyRefund(oid, reason).then(res => {
		showSuccessTip(res.msg);
		$btn.replaceWith('<div class="itemlist-status">已申请退款，等待卖家同意</div>');
	}, handleError);
}