{{each list as $trade i}}
<div class="tradelist-item js-trade" data-tid="{{$trade.trade.id}}">
  <div class="tradelist-header">
    <a class="show-info" href="/index/{{shopId}}">
      <img data-normal="{{$trade.trade.headPicUrl}}" alt="">
      <span>{{$trade.trade.shopName}}</span>
    </a>
    <span class="trade-status js-trade-status">{{$trade.trade.status | getStatusLabel}}</span>
  </div>

  <!-- 宝贝列表 -->
  <div class="itemlist">
    {{each $trade.list as $item k}}
    <div class="itemlist-item checkbox small">
      <a class="itemlist-pic" href="javascript:void(0);">
        <img data-normal="{{$item.productPicPath}}">
      </a>
      <div class="itemlist-detail">
        <span class="itemlist-title" href="">{{$item.productName}}</span>
        <div class="itemlist-sku">
          <span>{{$item.propertiesname}}</span>
        </div>
      </div>
      <div class="itemlist-vertical-price">
        <span>¥{{$item.totalRealPrice}}</span>
        {{if $item.totalPrice && $item.totalPrice !== $item.totalRealPrice}}
        <span>¥{{$item.totalPrice}}</span>
        {{/if}}
        <span>x{{$item.num}}</span>
        {{if $item.productStatus == 2}}
        <span>已下架</span>
        {{/if}}
      </div>
    </div>
    {{/each}}
  </div>
  <!-- /宝贝列表 -->

  <div class="tradelist-summary">
    共{{$trade.list | calcTotalNum}}件商品 合计 : ¥ {{$trade.trade.realPrice}} 
    {{if $trade.trade.expressPrice > 0}}
    (含运费 ¥{{$trade.trade.expressPrice}})
    {{/if}}
  </div>
  <div class="tradelist-actions">
    {{if $trade.trade.status === 1 || $trade.trade.status === 2}}
      <a class="action-btn js-close-trade" data-tid="{{$trade.trade.id}}">取消订单</a>
      {{if $trade.itCanPaid}}
      <a class="action-btn js-pay-trade" data-tid="{{trade.trade.id}}" data-tradeno="{{trade.trade.tradeNo}}" data-shopname="{{trade.trade.shopName}}" data-realprice="{{trade.trade.realPrice}}">付款</a>
      {{/if}}
    {{/if}}
    {{if $trade.trade.status === 3 || $trade.trade.status === 4}}
      <!-- <a class="action-btn js-tip-send">提醒发货</a> -->
    {{/if}}
    {{if $trade.trade.distributionMode == 1}}
      {{if $trade.trade.status === 3 || $trade.trade.status === 5 || $trade.trade.status === 6 || $trade.trade.status === 7}}
        <a class="action-btn js-show-logicinfo" data-lid="{{trade.trade.expressNo}}">查看物流</a>
      {{/if}}
      {{if $trade.trade.status === 5}}
        <a class="action-btn js-confirm-get" data-tid="{{$trade.trade.id}}">确认收货</a>
      {{/if}}
    {{/if}}
  </div>
</div>
{{/each}}