import $ from 'jquery';
import {
	Swiper
} from 'component';
import './style';


$(() => {

	// 轮播图
	new Swiper('.js-top-swiper', {
		pagination: '.swiper-pagination',
    loop: true,
    autoplay: 3000
  });

});