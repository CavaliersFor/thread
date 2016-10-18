import $ from 'jquery';
import {
  post,
	jsonp
} from './ajax';

const PAY_HOST = 'http://wx.mall.lunjar.com/internal';

function prepay(data) {
	const {
    deviceInfo,
    outTradeNo,
    desc,
    totalFee,
    openId
	} = data;

  // alert(JSON.stringify(data));
	return jsonp(PAY_HOST, {
		method: 'createWeiXinPayOrder',
    deviceInfo,
    outTradeNo,
    desc,
    totalFee,
    openId
	});
}

export function weixinPay(openId, trade) {
	const deffered = $.Deferred();
	let params = {
    openId,
    deviceInfo: trade.shopName,
    outTradeNo: trade.tradeNo,
    desc: `易趣购-订单编号${trade.tradeNo}`,
    totalFee: Math.ceil(trade.realPrice * 100)
	};

  // console.log(params);
	if (typeof WeixinJSBridge === 'undefined') {
    if (document.addEventListener) {
      document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
    } else if (document.attachEvent) {
      document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
      document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
    }
  } else {
    onBridgeReady();
  }

  function onBridgeReady() {
  	prepay(params).then(data => {
      // alert('返回结果：' + JSON.stringify(data));

  		if (data.isSuccess) {
  			WeixinJSBridge.invoke('getBrandWCPayRequest', data.prepayParams, res => {
  				if (res.err_msg === 'get_brand_wcpay_request:cancel' || 
  					res.err_msg === 'get_brand_wcpay_request:fail') {
  					deffered.reject(res);
  				} else {
  					deffered.resolve(res);
  				}
  			});
  		} else {
  			deffered.reject(data);
  		}
  	}, e => {
  		deffered.reject(e);
  	});
  }

  return deffered;
}
