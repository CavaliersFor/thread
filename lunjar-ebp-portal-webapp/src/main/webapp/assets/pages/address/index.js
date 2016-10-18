import 'component';
import $ from 'jquery';
import {
	deleteAddress
} from '../../api/address-api';
import {
	handleError,
	showSuccessTip
} from '../../utils/response-util.js';
import './style';

$(() => {

	// 删除地址
	$(document).on('click', '.js-del-addr', e => {
		removeAddress($(e.currentTarget));
	});
});

function removeAddress($btn) {
	const addrId = $btn.data('id');

	deleteAddress(addrId).then(res => {
		showSuccessTip(res.msg);
		$btn.parents('.address').remove();
	}, handleError);
}