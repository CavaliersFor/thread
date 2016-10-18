import 'component';
import $ from 'jquery';
import template from 'art-template/dist/template';
import {
	saveAddress,
	getCitysByProvince,
	getRegionByCity
} from '../../api/address-api';
import {
	handleError,
	showSuccessTip
} from '../../utils/response-util.js';
import optionTpl from './option';
import './editaddress.scss';

const PHONE_RE = /^1[3578][0-9]{9}$/;


$(() => {
	const $form = $('#addrForm');
	const $provinceSelector = $('select[name="province"]');
	const $citySelector = $('select[name="city"]');
	const $regionSelector = $('select[name="region"]');

	// 初始化城市、地区的数据
	updateCitySelector();
	$provinceSelector.on('change', updateCitySelector);
	$citySelector.on('change', updateRegionSelector);
	$(document).on('click', '.js-saveaddr:not(.disabled)', commitAddress);


	function updateCitySelector() {
		const provinceCode = $provinceSelector.find('option:selected').data('code');

		getCitysByProvince(provinceCode).then(res => {
			const selected = $citySelector.find('option:selected').val();
			const tpl = template.compile(optionTpl)({
				list: res.list,
				selected
			});

			$citySelector.html(tpl);
			updateRegionSelector();
		}, handleError);
	}

	function updateRegionSelector() {
		const cityCode = $citySelector.find('option:selected').data('code');

		getCitysByProvince(cityCode).then(res => {
			const selected = $regionSelector.find('option:selected').val();
			const tpl = template.compile(optionTpl)({
				list: res.list,
				selected
			});

			$regionSelector.html(tpl);
			if (!res.list) {
				$regionSelector.addClass('z-hidden');
			} else {
				$regionSelector.removeClass('z-hidden');
			}
		}, handleError);
	}

	function commitAddress(e) {
		const $btn = $(e.currentTarget);
		const address = $form.serializeJsonObj();

		if (!address.buyerName) {
			handleError({
				msg: '收货人不能为空哦！'
			});
			return;
		}
		if (!address.buyerAddress) {
			handleError({
				msg: '详细地址不能为空哦！'
			});
			return;
		}
		if (!PHONE_RE.test(address.buyerPhone)) {
			handleError({
				msg: '请填写正确的手机号码哦！'
			});
			return;
		}

		$btn.addClass('disabled');
		saveAddress(address).then(res => {
			showSuccessTip(res.msg);
			setTimeout(() => {
				window.location.href = `/buyAddress/list/${window.shopId}`;
			}, 1500);
		}, e => {
			$btn.addClass('disabled');
			handleError(e);
		});
	}
});
