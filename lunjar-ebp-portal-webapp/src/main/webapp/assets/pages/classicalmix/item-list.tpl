{{each cp as c index}}
<div class="itemlist-item checkbox small">
  <a class="itemlist-pic" href="/product/getInfo/{{shopId}}-{{c.id}}">
    <img src="{{c.picPath}}">
  </a>
  <div class="itemlist-detail">
    <a class="itemlist-title" href="/product/getInfo/{{shopId}}{{c.id}}">{{c.name}}</a>
    {{if c.skus.length !== 0}}
    <div class="itemlist-sku">
      <span>颜色分类 :</span>
      <span class="js-sku-value" data-index="{{index}}"></span>
      <span><a class="itemlist-skubtn js-sku" data-index="{{index}}">选择商品属性</a></span>
    </div>
    {{/if}}
  </div>
  <div class="itemlist-vertical-price">
    <span>￥{{c.realPrice}}</span>
    <span>￥{{c.price}}</span>
    <span>x{{c.num}}</span>
  </div>
</div>
{{/each}}