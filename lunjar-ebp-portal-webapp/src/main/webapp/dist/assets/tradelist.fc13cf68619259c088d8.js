webpackJsonp([3],{0:function(t,a,e){t.exports=e(42)},42:function(t,a,e){"use strict";function i(t){return t&&t.__esModule?t:{"default":t}}function d(t){return y[t]}function r(t){return S[t]}function s(t){var a=0;return t.forEach(function(t){a+=t.num}),a}function n(){var t=(0,m["default"])(".js-loading-box");t.removeClass("z-hidden"),(0,g.getTradeList)(L,++N).then(function(a){t.addClass("z-hidden"),o(a)},j.handleError)}function o(t){var a=$["default"].compile(T["default"])(t);k=!1,t.page.hasNext||(x=!0,(0,m["default"])(".js-loaded-all").removeClass("z-hidden")),(0,m["default"])("#tradelistWrapper").append(a),m["default"].LayzrUpdate()}function c(t,a){var e=$["default"].compile(T["default"])({list:[a]});t.replaceWith(e),m["default"].LayzrUpdate()}function l(t){var a=t.data("tid");window.location.href="/trade/detail?tradeId="+a}function u(t){var a=t.data("tid");(0,g.closeTrade)(a).then(function(a){var e=t.parents(".js-trade");(0,j.showSuccessTip)(a.msg),L?e.remove():c(e,a.data)},j.handleError)}function f(t){var a=t.data("tid");(0,g.confirmReceiptGoods)(a).then(function(t){(0,j.showSuccessTip)(t.msg),setTimeout(function(){window.location.reload()},1500)},j.handleError)}var p=e(1),m=i(p),v=e(14),h=(i(v),e(2)),$=i(h);e(3);var g=e(7),j=e(4),P=e(13),w=e(88),T=i(w);e(73);var b=10,k=!1,x=!1,N=1,L=void 0,y={1:"待付款",2:"待付款",3:"部分发货",4:"待发货",5:"已发货",6:"已签收",7:"交易成功",8:"已关闭",9:"已关闭",10:"待付款确认中付款",11:"0元购合约中"},S={1:"已申请退款，等待卖家同意",2:"已同意退款，等待买家退货",3:"已退货，等待卖家确认收货",4:"卖家拒绝退款",5:"退款关闭",6:"退款成功"};(0,m["default"])(function(){x=(0,m["default"])('input[name="hasLoadDone"]').val()<=b,L=(0,m["default"])('input[name="status"]').val(),$["default"].helper("getStatusLabel",d),$["default"].helper("calcTotalNum",s),$["default"].helper("getRefundStatusLabel",r),(0,m["default"])(document).on("click",".js-trade",function(t){l((0,m["default"])(t.currentTarget))}).on("click",".js-pay-trade",function(t){(0,P.payTrade)((0,m["default"])(t.currentTarget)),t.stopPropagation()}).on("click",".js-close-trade",function(t){var a=(0,m["default"])(t.currentTarget);m["default"].Confirm({title:"提示",content:"确定取消订单吗？",confirm:function(){u(a)},cancel:function(){}}),t.stopPropagation()}).on("click",".js-show-logicinfo",function(t){var a=(0,m["default"])(t.currentTarget),e=a.data("lid");window.location.href="https://m.kuaidi100.com/result.jsp?nu="+e,t.stopPropagation()}).on("click",".js-confirm-get",function(t){var a=(0,m["default"])(t.currentTarget);m["default"].Confirm({title:"提示",content:"确定确认收货吗？",confirm:function(){f(a)},cancel:function(){}}),t.stopPropagation()}),(0,m["default"])(window).onScrollToBottom(function(){x||k||(k=!0,n())})})},73:function(t,a){},88:function(t,a){t.exports='{{each list as $trade i}} <div class="tradelist-item js-trade" data-tid={{$trade.trade.id}}> <div class=tradelist-header> <a class=show-info href=/index> <img data-normal={{$trade.trade.headPicUrl}} alt=""> <span>{{$trade.trade.shopName}}</span> </a> <span class="trade-status js-trade-status">{{$trade.trade.status | getStatusLabel}}</span> </div> <div class=itemlist> {{each $trade.list as $item k}} <div class="itemlist-item checkbox small"> <a class=itemlist-pic href=javascript:void(0);> <img data-normal={{$item.productPicPath}}> </a> <div class=itemlist-detail> <span class=itemlist-title href="">{{$item.productName}}</span> <div class=itemlist-sku> <span>{{$item.propertiesname}}</span> </div> </div> <div class=itemlist-vertical-price> <span>¥{{$item.totalRealPrice}}</span> {{if $item.totalPrice && $item.totalPrice !== $item.totalRealPrice}} <span>¥{{$item.totalPrice}}</span> {{/if}} <span>x{{$item.num}}</span> </div> </div> {{/each}} </div> <div class=tradelist-summary> 共{{$trade.list | calcTotalNum}}件商品 合计 : ¥ {{$trade.trade.realPrice}} {{if $trade.trade.expressPrice > 0}} (含运费 ¥{{$trade.trade.expressPrice}}) {{/if}} </div> <div class=tradelist-actions> {{if $trade.status === 1 || $trade.status === 2}} <a class="action-btn js-close-trade" data-tid={{$trade.trade.id}}>取消订单</a> <a class="action-btn js-pay-trade" data-tid={{trade.trade.id}} data-tradeno={{trade.trade.tradeNo}} data-shopname={{trade.trade.shopName}} data-realprice={{trade.trade.realPrice}}>付款</a> {{/if}} {{if $trade.status === 3 || $trade.status === 4}} {{/if}} {{if ($trade.status === 3 || $trade.status === 5 || $trade.status === 6 || $trade.status === 7) && $trade.distributionMode == 1}} <a class="action-btn js-show-logicinfo" data-lid={{trade.trade.expressNo}}>查看物流</a> {{/if}} {{if $trade.status === 5}} <a class="action-btn js-confirm-get" data-tid={{$trade.trade.id}}>确认收货</a> {{/if}} </div> </div> {{/each}}'}});
//# sourceMappingURL=tradelist.fc13cf68619259c088d8.js.map