import $ from 'jquery';
import {
	get,
	post
} from './ajax';

const POSTPATH = window.shopId;
const PAGESIZE = 10;
const APIS = {
	addrsave: `/buyAddress/add/${POSTPATH}`,
	addrdelete: `/buyAddress/delete/${POSTPATH}`,
	addrlist: `/buyAddress/listJson/${POSTPATH}`,
	addrcitys: `/buyAddress/getCity/${POSTPATH}`,
	addrregion: `/buyAddress/getRegion/${POSTPATH}`,
};
let cache = {
	citys: {},
	regions: {}
}


export function saveAddress(address) {
	return post(APIS.addrsave, address);
}

export function deleteAddress(id) {
	return post(APIS.addrdelete, {
		id
	});
}

export function getAddressList() {
	return get(APIS.addrlist);
}

export function getCitysByProvince(code) {
	return get(APIS.addrcitys, {
		code
	});
}

export function getRegionByCity(code) {
	return get(APIS.addrregion, {
		code
	});
}
