import $ from 'jquery';
import template from 'art-template/dist/template';
import Popup from '../popup';
import confirmTpl from './confirm.tpl';
import './style';

class Confirm {

	constructor(options) {
		this.data = {};
		this.options = options;
		this.popup = new Popup({
			position: 'center'
		}).render();
		this.$popup = this.popup.getPopup();

		this._initEvent();
		this._show();
	}

	_initEvent() {
		const {
			confirm,
			cancel
		} = this.options;

		this.$popup.on('click', '.alert-ok', e => {
			const value = this.$popup.find('input').val();

			confirm && confirm(e, value);
			this._cancel();
		})
		.on('click', '.alert-cancel', e => {
			cancel && cancel(e);
			this._cancel();
		});
	}

	_show() {
		const popup = this.popup;
		const data = this.options;
		const tpl = template.compile(confirmTpl)(data);

		popup.renderContent(tpl);
		popup.show();
	}

	_cancel() {
		this.popup.cancel();
		this.$popup.remove();
	}

}

function getParam(options, type) {
	const {
		title,
		content,
		confirm,
		cancel
	} = options;

	return {
		title,
		content,
		confirm,
		cancel,
		type
	};
}

$.Alert = (options) => {
	new Confirm(getParam(options, 'alert'));
};

$.Confirm = (options) => {
	new Confirm(getParam(options, 'confirm'));
};

$.Propt = (options) => {
	new Confirm(getParam(options, 'propt'));
};
