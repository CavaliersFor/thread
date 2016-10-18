import $ from 'jquery';
import {
	get,
	post
} from './ajax';

const POSTPATH = window.shopId;
const PAGESIZE = 10;
const APIS = {
	itemlist: `/product/listJson/${POSTPATH}`,
	itemdetail: `/product/getProdInfoReJson/${POSTPATH}`,
	add2car: `/product/addCart/${POSTPATH}`,
	listCart: `/product/listCartJson/${POSTPATH}`,
	editCart: `/product/editCart/${POSTPATH}`
};


export function getItemList(categoryId, pageNo) {
	return get(APIS.itemlist, {
		pageSize: PAGESIZE,
		categoryId,
		pageNo
	});
}

export function getItemDetail(id) {
	return get(APIS.itemdetail, {
		id
	});
}

export function addItem2Car(productId, skuId, num) {
	return post(APIS.add2car, {
		productId,
		skuId,
		num
	});
}

export function getListCart() {
	return get(APIS.listCart, {});
}

export function editCart(id, flag, num) {
	return post(APIS.editCart, {
		id,
		flag,
		num
	});
}