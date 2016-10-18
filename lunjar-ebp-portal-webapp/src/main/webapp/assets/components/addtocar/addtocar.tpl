<a class="addtocar-close js-cancel"></a>
<div class="addtocar">
	<div class="addtocar-item">
		<img src="{{pathUrl}}" alt="">
		<div class="addtocar-info">
			<div class="addtocar-price">¥<span class="js-price">{{price}}</span></div>
			<div class="addtocar-stock">库存<span class="js-stock">{{quantity}}</span>件</div>
		</div>
	</div>
	<div class="sku-box">
		{{each skus as sku i}}
		<section>
			<span class="sku-title">{{sku.name}}</span>
			<div class="sku-list">
				{{each sku.list as item j}}
				<a class="sku-item {{activeSkuHelper(sku.value, item.value, selectedSku) ? 'active' : ''}} js-skubtn" data-prop="{{sku.value}}" data-value="{{item.value}}">{{item.name}}</a>
				{{/each}}
			</div>
		</section>
		{{/each}}
	</div>
	{{if type !== 3}}
	<div class="addtocar-num">
		<span>购买数量</span>
		<div class="stepper">
      <a class="btn-minus red"></a>
      <input type="text" class="item-number js-currentnum" data-min="1" data-max="{{quantity}}" value="{{num}}" disabled>
      <a class="btn-add red"></a>
    </div>
	</div>
	{{/if}}
	<a class="addtocar-okbtn js-additem">{{type === 1 ? '加入购物车' : type === 2 ? '立即购买' : '确定'}}</a>
</div>