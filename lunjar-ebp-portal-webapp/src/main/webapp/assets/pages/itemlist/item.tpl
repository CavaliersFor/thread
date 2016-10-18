{{each list as item i}}
<div class="itemlist-item">
  <a class="itemlist-pic" href="/product/getInfo/{{shopId}}-{{item.productId}}">
    <img data-normal="{{item.productPicPath}}">
  </a>
  <div class="itemlist-detail">
    <a class="itemlist-title" href="/product/getInfo/{{shopId}}-{{item.productId}}">
      {{item.productName}}
    </a>
    <div class="itemlist-horizontal-price">
      <p>¥ {{item.realPrice}}</p>
      <a class="icon-add2car js-add2car" data-pid="{{item.productId}}"></a>
    </div>
  </div>
</div>
{{/each}}