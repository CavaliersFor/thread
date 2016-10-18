{{each bills as bill index}}
<div class="bill js-bill" data-index="{{index}}" data-id="{{bill.enterpriseId}}">
  <div class="shop-header bill-item">
    <img src="{{bill.headPicUrl}}" alt="" class="shop-pic">
    <span>{{bill.enterpriseName}}</span>
  </div>
  <div class="items">
    {{each bill.listCartDto as item}}
      <div class="item">
        <a href="javascript:void(0);">
          <img src="{{item.picPath}}" alt="" class="product">
        </a>

        <div class="pro-right">
          <div class="pro-name">{{item.productName}}</div>
          <div class="pro-sku">{{item.propertiesname}}</div>
          <div class="detail">
            <span class="price">￥ {{item.productRealPrice.toFixed(2)}}</span>
            <span class="num">x{{item.num}}</span>
          </div>
        </div>
      </div>
    {{/each}}
  </div>
  {{if bill.discountDto.discountDesc}}
  <div class="deliver bill-item">
    <span>优惠信息：</span>
    <span>{{bill.discountDto.discountDesc}}</span>
  </div>
  {{/if}}
  {{if bill.distributionMode === 2}}
  <div class="bill-item bill-link js-show-collect" data-index="{{index}}">
    <span>请选择自提地址</span>
  </div>
  <div class="bill-item bill-link z-hidden js-select-date" data-index="{{index}}">
    <input type="hidden" name="collectTime">
    <span>请选择提货时间</span>
  </div>
  <div class="bill-item">
    自提人姓名：<input type="text" class="bill-input js-collect-name" placeholder="自提人姓名">
  </div>
  <div class="bill-item">
    自提人手机：<input type="number" class="bill-input js-collect-mobile" placeholder="自提人手机">
  </div>
  {{/if}}
  <div class="bill-item">
    <label class="radios_box">
      <input type="checkbox" class="input_redu js-invoice">
    </label>
    <span>是否需要发票</span>
  </div>
  <div class="bill-item js-invoice-option z-hidden">
    <label class="radios_box">
      <input type="radio" name="invoiceType" class="input_redu js-invoice-type" checked value="1">
    </label>
    <span>电子发票</span>
    <label class="radios_box">
      <input type="radio" name="invoiceType" class="input_redu js-invoice-type" value="2">
    </label>
    <span>纸质发票</span>
  </div>
  <div class="bill-item js-invoice-option z-hidden">
    <input type="text" class="bill-input bill-input-invoice js-invoice-name" placeholder="发票抬头">
  </div>
  <div class="memo bill-item">
    <textarea class="bill-input" name="buyerRemarks" placeholder="给卖家留言"></textarea>
  </div>

{{if bill.paymentMode === 1}}
 <form name="alipayment" id="alipayment" action="http://dev.alipay.mall.lunjar.com/alipayapi.jsp" method="post" target="_blank">
  <h4 class="titlePay">选择支付方式</h4>
  <div class="bill-item payType">
    <div class="payType-item">
       <span class="i-p icon-z-0"></span>
       <span class="text">支付宝钱包 (推荐)</span>
       <label class="radios_box checked">
      <input type="radio" name="payType" class="input_redu js-pay-type" value="1" checked>
       </label>
    </div>
    <div class="payType-item">
      <span class="i-p icon-z-1"></span>
      <span class="text">支付宝网页支付</span>
       <label class="radios_box">
      <input type="radio" name="payType" class="input_redu js-pay-type" value="2">
       </label>
    </div>
  </div>
  <input type="submit" class="z-hidden" />
  <input type="hidden" name="WIDout_trade_no" id="WIDout_trade_no" value="" />
  {{each bill.listCartDto as item}}
    <input type="hidden" name="WIDsubject" id="WIDsubject" value="{{item.productName}}" />
  {{/each}}
  <input type="hidden" name="WIDtotal_fee" id="WIDtotal_fee" value="{{bill.discountDto.realPrice.toFixed(2)}}" />
  <input type="hidden" name="WIDbody" id="WIDbody" value="" />
  <input type="hidden" name="type" id="type" value="" />

 </form>
{{/if}}

  <div class="total bill-item">
    <span>共 <strong>{{bill.num}}</strong> 件商品</span>
    <span>合计：
      <strong>￥<span class="js-bill-price" data-exp-price="{{bill.discountDto.expressPrice}}">{{bill.discountDto.realPrice.toFixed(2)}}</span>
      </strong>
      <span class="js-price-info">
        ({{if bill.distributionMode === 2}}自提费{{else}}快递费{{/if}}: ¥{{bill.discountDto.expressPrice}})
      </span>
    </span>
  </div>
</div>
{{/each}}