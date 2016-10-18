import $ from 'jquery';
import loadingTpl from './loading.tpl';
import './style';

$(() => {
	$.Loading = {
		show: () => {
			$('body').append(loadingTpl);
		},

		hide: () => {
			$('.loading-modal').remove();
		}
	};
});