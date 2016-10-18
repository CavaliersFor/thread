import $ from 'jquery';
import template from 'art-template/dist/template';
import messageTpl from './message.tpl';
import './style';

const TIMEOUT = 1500;

export default class Message {
	constructor({content, icon, timeout = TIMEOUT}) {
		let data = {
			content,
			icon
		};

		this._render(data);
		this._setTimeoutHide(timeout);
	}

	_render(data) {
		this.$box = $(template.compile(messageTpl)(data));

		clearMessageTip();
		$('body').append(this.$box);
	}

	_setTimeoutHide(timeout) {
		setTimeout(() => {
			if(this.$box) {
				this.$box.remove();
			}
		}, timeout);
	}
}

Message.show = (options) => {
	return new Message(options);
};

function clearMessageTip() {
	$('.message-tipbox').remove();
}
