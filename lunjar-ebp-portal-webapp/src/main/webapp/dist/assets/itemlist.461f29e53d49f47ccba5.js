webpackJsonp([5],{0:function(t,a,e){t.exports=e(39)},39:function(t,a,e){"use strict";function i(t){return t&&t.__esModule?t:{"default":t}}function d(){var t=(0,l["default"])(".js-loading-box");t.removeClass("z-hidden"),(0,u.getItemList)(I,++w).then(function(a){t.addClass("z-hidden"),o(a)},p.handleError)}function o(t){var a=c["default"].compile(m["default"])(t);h=!1,t.page.hasNext||(g=!0,(0,l["default"])(".js-loaded-all").removeClass("z-hidden")),(0,l["default"])("#itemlistWrapper").append(a),l["default"].LayzrUpdate()}var n=e(1),l=i(n),s=e(2),c=i(s),r=e(3),u=e(5),p=e(4),f=e(86),m=i(f);e(70);var v=10,h=!1,g=!1,w=1,I=1;(0,l["default"])(function(){g=(0,l["default"])('input[name="hasLoadDone"]').val()<=v,I=(0,l["default"])('input[name="cid"]').val(),new r.Swiper(".js-top-swiper",{pagination:".swiper-pagination",loop:!0,autoplay:3e3}),(0,l["default"])(window).onScrollToBottom(function(){g||h||(h=!0,d())})})},70:function(t,a){},86:function(t,a){t.exports='{{each list as item i}} <div class=itemlist-item> <a class=itemlist-pic href="/product/getInfo?id={{item.productId}}"> <img data-normal={{item.productPicPath}}> </a> <div class=itemlist-detail> <a class=itemlist-title href="/product/getInfo?id={{item.productId}}"> {{item.productName}} </a> <div class=itemlist-horizontal-price> <p>¥ {{item.realPrice}}</p> <a class="icon-add2car js-add2car" data-pid={{item.productId}}></a> </div> </div> </div> {{/each}}'}});
//# sourceMappingURL=itemlist.461f29e53d49f47ccba5.js.map