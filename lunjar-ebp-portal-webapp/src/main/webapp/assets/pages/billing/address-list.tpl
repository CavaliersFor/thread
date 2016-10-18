<div class="address-wrap" id="main" style="background-color:#f7f7f7;">
  <div class="address-content">
    {{if type === 1}}
      {{each addList as add}}
        <div class="address js-choose-add" data-id="{{add.id}}">
          <a href="javascript:void(0);" class="address-info choose">
            <div class="ad-info">
              <div>{{add.buyerName}}</div>
              <div>{{add.buyerPhone}}</div>
            </div>
            <div class="ad-detail">{{add.province}}{{add.city}}{{add.region}}{{add.buyerAddress}}</div>
          </a>
        </div>
      {{/each}}
    {{else if type === 2}}
      {{each addList as add}}
        <div class="address js-choose-collect" data-id="{{add.id}}" data-bill="{{billIndex}}">
          <a href="javascript:void(0);" class="address-info choose">
            <div class="ad-info">
              <div>{{add.clpName}}</div>
              <div>{{add.clpTelephone}}</div>
            </div>
            <div class="ad-detail">{{add.clpAddress}}</div>
          </a>
        </div>
      {{/each}}
    {{/if}}
  </div>  
</div>
<div class="buttons">
  {{if type === 1}}
    <a href="/buyAddress/addPage/{{shopId}}" class="new-btn">添加新地址</a>
  {{/if}}
  <a href="javascript:void(0);" class="back-btn js-back">返回</a>
</div>
