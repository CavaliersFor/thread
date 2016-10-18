import $ from 'jquery';
import template from 'art-template/dist/template';
import Popup from '../popup';
import Message from '../message';
import '../stepper';
import addtocarTpl from './addtocar.tpl';
import {
	getItemDetail,
	addItem2Car
} from '../../api/item-api';
import {
	parseSku
} from '../../utils/item-util';
import './style';

const ADD_TO_CAR = 1;
const DIRECT_BUY = 2;
const GET_INFO = 3;
const EVENT_ADD = 'add';
const EVENT_CHOOSE = 'choose';

export default class AddToCar {
	static ADD_TO_CAR = ADD_TO_CAR;
	static DIRECT_BUY = DIRECT_BUY;
	static GET_INFO = GET_INFO;

	constructor() {
		this.data = {};
		this.popup = new Popup().render();
		this.$popup = this.popup.getPopup();
		this._initEvent();
	}

	_setData(data) {
		this.data = {
			...this.data,
			...data
		};
	}

	_initEvent() {
		const popup = this.popup;

		template.helper('activeSkuHelper', activeSkuHelper);
		this.$popup.on('click', '.js-cancel', e => {
			popup.cancel();
		})
		.on('click', '.js-skubtn', e => {
			this.changeSku($(e.currentTarget));
		})
		.on('change', '.js-currentnum', (e, num) => {
			this.changeNum(num);
		})
		.on('click', '.js-additem', e => {
			this.add2Car();
		});
	}

	_updateCarUI(productSku) {
		if(!productSku) {
			return;
		}

		const $popup = this.$popup;
		const $input = $popup.find('.js-currentnum');
		let num = 1;

		$popup.find('.js-price').text(productSku.price);
		$popup.find('.js-stock').text(productSku.quantity);
		if (productSku.quantity > 0) {
			$input.data('max', productSku.quantity);
		} else {
			num = 0;
			$input.data('max', 0);
		}
		$input.val(num);

		this._setData({
			num,
			skuId: productSku.id
		});
	}

	changeSku($btn) {
		if($btn.hasClass('.active')) {
			return;
		}
		$btn.siblings('.active').removeClass('active');
		$btn.addClass('active');

		const $skubtns = this.$popup.find('.js-skubtn.active');
		let skus = [];

		$skubtns.each((i, btn) => {
			const sku = btn.dataset;

			skus.push(`${sku.prop}:${sku.value}`);
		});
		this._updateCarUI(getSelectedSku(this.productSkus, skus));
	}

	changeNum(num) {
		this._setData({
			num
		});
	}

	add2Car() {
		this.popup.confirm();
		if (this.data.type === DIRECT_BUY) {
			saveItem2Car(this.data, res => {
				window.location = `/trade/commitPage?ids=${res.data.id}&shopId=${window.shopId}`;
			});
		} else if (this.data.type === ADD_TO_CAR) {
			saveItem2Car(this.data, res => {
				this.popup.fire(EVENT_ADD, this.data);
			});
		} else {
			this.popup.fire(EVENT_CHOOSE, this.data);
		}
	}

	show(type, product, productSkus = [], skuId, num = 1) {
		const popup = this.popup;
		const skus = parseSku(product.propsName, product.propsAlias);
		let selSku = productSkus[0];

		productSkus.forEach(item => {
			if (item.id === skuId) {
				selectedSku = item;
			}
		});

		this.productSkus = productSkus;
		this._setData({
			type,
			num,
			skuId: selSku ? selSku.id : undefined,
			productId: product.id
		});
		popup.renderContent(template.compile(addtocarTpl)({
			type,
			skus,
			num,
			pathUrl: product.pathUrl,
			price: selSku ? selSku.price : product.price,
			quantity: selSku ? selSku.quantity : product.productNum,
			selectedSku: selSku ? selSku.properties : '',
		}));
		popup.show();
	}

	on(event, cb) {
		this.popup.on(event, cb);
	}
}

function saveItem2Car({productId, skuId, num}, cb) {
	addItem2Car(productId, skuId, num).then(res => {
		Message.show({
			content: res.data.msg
		});
		cb && cb(res);
	}, err => {
		Message.show({
			content: err.msg
		});
	});
}

function activeSkuHelper(prop, value, selectedSku) {
	return selectedSku && selectedSku.indexOf(`${prop}:${value}`) !== -1;
}

function getSelectedSku(productSkus, skus) {
	const len = productSkus.length;

	for (let i = 0; i < len; i++) {
		let isMatch = true;
		let temp = productSkus[i];
		let properties = temp.properties;

		skus.forEach(sku => {
			if (properties.indexOf(sku) === -1) {
				isMatch = false;
			}
		});

		// 完全匹配，即已选择完整的 sku
		if (isMatch && skus.length === properties.split(';').length) {
			return temp;
		}
	}
}

// 全局监听加入购物车的事件
$(function() {
	let carBox;

	$(document).on('click', '.js-add-item-2-car', e => {
		const productId = $(e.currentTarget).data('pid');
		
		if (!carBox) {
			carBox = new AddToCar();
		}
		getItemDetail(productId).then(res => {
			const {
				product,
				productSkus
			} = res.data;

			carBox.show(AddToCar.ADD_TO_CAR, product, productSkus);
		}, err => {
			Message.show({
				content: err.msg,
				timeout: 3000
			});
		});

	});
})