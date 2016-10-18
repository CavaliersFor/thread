import $ from 'jquery';
import {
	handleError,
	showSuccessTip
} from './response-util.js';
import {
	paySuccess
} from '../api/trade-api';
import {
	weixinPay
} from '../api/weixin-api';

export function payTrade($btn) {
	const openId = window.openId;
	const tid = $btn.data('tid');
	const trade = {
		tradeNo: $btn.data('tradeno'),
		shopName: $btn.data('shopname'),
		realPrice: $btn.data('realprice'),
		shopId : $btn.data('shopId')
	};

	$.Loading.show();
	weixinPay(openId, trade).then(res => {
		// alert(JSON.stringify(res));
		$.Loading.hide();
		showSuccessTip('支付成功！', 3000);
		paySuccess(tid).then(res => {
			window.location.href = `/trade/detail?shopId=${window.shopId}&tradeId=${tid}`; 
		}, err => {
			window.location.href = `/trade/detail?shopId=${window.shopId}&tradeId=${tid}`;
		});
	}, e => {
		// alert('error----' + JSON.stringify(e));
		$.Loading.hide();
		showSuccessTip('支付失败！', 3000);
		window.location.href = `/trade/detail?shopId=${window.shopId}&tradeId=${tid}`;
	});
}