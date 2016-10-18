import $ from 'jquery';
import './tabbar';

$(() => {

	$(document).on('click', e => {
		$('.js-item-category').removeClass('on');
	})
	.on('click', '.js-item-category', e => {
		const $btn = $(e.currentTarget);

		$btn.toggleClass('on');
		e.stopPropagation();
	});
});