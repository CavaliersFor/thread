import {
	get,
	post,
	jsonp
} from './ajax';

const POSTPATH = window.shopId;
const PAGESIZE = 10;
const APIS = {
	tradelist: `/trade/listJson/${POSTPATH}`,
	tradecancel: `/trade/cancel/${POSTPATH}`,
	tradecommit: `/trade/commit/${POSTPATH}`,
	applyrefund: `/trade/applyRefund/${POSTPATH}`,
	asynexpress: `/trade/asynCommitPage/${POSTPATH}`,
	confirmreceipt: `/trade/confirmReceipt/${POSTPATH}`,
	asyncombineexpress: `/trade/asynCommitCombinPage/${POSTPATH}`,
	paysuccess: `/trade/pay/${POSTPATH}`
};


export function getTradeList(status, pageNo) {
	return get(APIS.tradelist, {
		pageSize: PAGESIZE,
		status,
		pageNo
	});
}

export function closeTrade(id) {
	return post(APIS.tradecancel, {
		id
	});
}

export function commitTrade(value, combinValue, combinationId) {
	return post(APIS.tradecommit, {
		value,
		combinValue,
		combinationId
	});
}

export function asynExpress(ids, addressId) {
	return post(APIS.asynexpress, {
		ids,
		addressId
	});
}

export function confirmReceiptGoods(id) {
	return post(APIS.confirmreceipt, {
		id
	});
}

export function applyRefund(id, reason) {
	return post(APIS.applyrefund, {
		id,
		reason
	});
}

export function asynCombineExpress(id, enterpriseId, addressId) {
	return post(APIS.asyncombineexpress, {
		id,
		enterpriseId,
		addressId
	});
}

export function paySuccess(id) {
	return post(APIS.paysuccess, {
		id
	});
}
