import $ from 'jquery';
import template from 'art-template/dist/template';
import popupTpl from './popup';
import './style';


class Event {
	constructor() {
		this.listeners = {};
	}

	on(event, cb) {
		let cbs = this.listeners[event] || [];

		cbs.push(cb);
		this.listeners[event] = cbs;
	}

	off(event, cb) {
		let cbs = this.listeners[event] || [];
		const index = cbs.indexOf(cb);

		if (cb && index !== -1) {
			cbs = cbs.slice(index, 1);
		}

		if (!cb) {
			cbs = [];
		}

		this.listeners[event] = cbs;
	}

	fire(event, ...params) {
		const cbs = this.listeners[event] || [];
		let bool = false;

		cbs.forEach(cb => {
			bool = cb && cb(...params);
		});

		return bool;
	}
}


export default class Popup extends Event {
	constructor(options) {
		super(options);
		this.options = options || {};
	}

	_initEvent() {
		this.$popup.on('click', e => {
			if (e.target === this.$popup.get(0)) {
				this.cancel();
			}
		});
	}

	_hide() {
		this.$popup.addClass('z-hidden');
	}

	render() {
		if (!this.$popup) {
			this.$popup = $(template.compile(popupTpl)(this.options));
			$('body').append(this.$popup);
			this._initEvent();
		}
		return this;
	}

	show() {
		const boo = this.fire('show', this.$popup);
		this.render();
		this.$popup.removeClass('z-hidden');
	}

	confirm() {
		const boo = this.fire('confirm', this.$popup);
		if (!boo) {
			this._hide();
		}
	}

	cancel() {
		const boo = this.fire('cancel', this.$popup);
		if (!boo) {
			this._hide();
		}
	}

	getPopup() {
		return this.$popup;
	}

	renderContent(content) {
		this.$popup.find('.popup-content').html(content);
		return this;
	}
}
