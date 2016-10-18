import $ from 'jquery';
import template from 'art-template/dist/template';
import Popup from '../popup';
import datepickerTpl from './datepicker.tpl';
import './style';


function getMonth(date) {
  return date.getMonth() + 1;
}

function parseDate(date) {
	if (!date) {
		return null;
	}

	return {
		year: date.getFullYear(),
		month: getMonth(date),
		day: date.getDate()
	};
}

function date2str({year, month = 1, day = 1}, split = '-') {
	month = prefixZero(month);
	day = prefixZero(day);

	return `${year}${split}${month}${split}${day}`;
}

function prefixZero(num) {
	return num < 10 ? `0${num}` : num;
}

function getTotalDateOfMonth(year, month) {
	const date = new Date(date2str({year, month}, '/'));
  let newDate = new Date(date.getTime());

  newDate.setDate(1);
  newDate.setMonth(getMonth(newDate));
  newDate.setDate(0);

  return newDate.getDate();
}

const handler = {
	handleYear: ({year, month, day}, step) => {
		return {
			year: year + step,
			month,
			day
		};
	},

	handleMonth: ({year, month, day}, step) => {
		month = month + step;
		month = month > 12 ? 1 : month;
		month = month <= 0 ? 12 : month;

		return {
			year,
			month,
			day: handler.handleDay({year, month, day}, 0).day
		};
	},

	handleDay: ({year, month, day}, step) => {
		const maxday = getTotalDateOfMonth(year, month);

		day = day + step;
		if (day > maxday) {
			day = 1;
			if (step !== 0) {
				month = handler.handleMonth({year, month, day}, 1).month;
			}
		}
		if (day <= 0) {
			day = maxday;
			if (step !== 0) {
				month = handler.handleMonth({year, month, day}, -1).month;
			}
		}

		return {
			year,
			month,
			day
		};
	}
};

class Datepicker {

	/**
	 * 	
	 * @param  {object} options 
	 * @param {Date} options.date 当前日期
	 * @param {Date} options.minDate 当前日期
	 * @param {Date} options.maxDate 当前日期
	 * @param {Date} options.confirmText 当前日期
	 * @param {Date} options.cancelText 当前日期
	 * @param {Date} options.confirm 当前日期
	 * @param {Date} options.cancel 当前日期
	 */
	constructor(options) {
		this.data = parseDate(options.date || new Date());

		this.options = options;
		this.popup = new Popup({
			position: 'center'
		}).render();
		this.$popup = this.popup.getPopup();

		// if (options.minDate) {
		// 	this.minDate = parseDate(options.minDate);
		// }
		// if (options.maxDate) {
		// 	this.minDate = parseDate(options.maxDate);
		// }
		this._initEvent();
		this._show();
	}

	_setData(data) {
		this.data = {
			...this.data,
			...data
		};

		this._render();
	}

	_initEvent() {
		const {
			confirm,
			cancel,
			minDate,
			maxDate
		} = this.options;

		this.$popup.on('click', '.datepicker-add:not(.disabled)', e => {
			const $btn = $(e.currentTarget);
			const fn = $btn.data('flag');
			let data = handler[fn](this.data, 1);
			const date = new Date(`${data.year}/${data.month}/${data.day}`).getTime();

			if (date > maxDate) {
				data = parseDate(maxDate);
			}

			this._setData(data);
		})
		.on('click', '.datepicker-cut:not(.disabled)', e => {
			const $btn = $(e.currentTarget);
			const fn = $btn.data('flag');
			let data = handler[fn](this.data, -1);
			const date = new Date(`${data.year}/${data.month}/${data.day}`).getTime();

			if (date < minDate) {
				data = parseDate(minDate);
			}

			this._setData(data);
		})
		.on('click', '.datepicker-confirm', e => {
			confirm && confirm(date2str(this.data));
			this._cancel();
		})
		.on('click', '.datepicker-cancel', e => {
			cancel && cancel(e);
			this._cancel();
		});
	}

	_show() {
		this._render();		
		this.popup.show();
	}

	_render() {
		const popup = this.popup;
		const {
			confirmText,
			cancelText,
			minDate,
			maxDate
		} = this.options;
		const {
			year,
			month,
			day
		} = this.data;
		const tpl = template.compile(datepickerTpl)({
			confirmText,
			cancelText,
			year,
			month: prefixZero(month),
			day: prefixZero(day),
			title: `请选择提货日期 : ${date2str(this.data)}`,
			minDate: parseDate(minDate),
			maxDate: parseDate(maxDate)
		});

		popup.renderContent(tpl);
	}

	_cancel() {
		this.popup.cancel();
		this.$popup.remove();
	}
}

$.Datepicker = (options) => {
	new Datepicker(options);
};
$.Datepicker.parseDate = parseDate;
$.Datepicker.date2str = date2str;