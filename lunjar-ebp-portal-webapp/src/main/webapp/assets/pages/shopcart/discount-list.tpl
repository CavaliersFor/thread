<div class="discount-wrap">
  <ul>
    {{each discounts as discount}}
    <li>满 <span class="d-danger"> ￥{{discount.conditions}} </span> {{if discount.discountFee}}减 <span class="d-danger"> ￥{{discount.discountFee}} </span>{{/if}}{{if discount.isPost === 1}}{{if discount.discountFee}}，{{/if}}包邮{{/if}}</li>
    {{/each}}
  </ul>
</div>
<div class="dicount-buttons">
  <a href="javascript:void(0);" class="js-close-popup">关闭</a>
</div>