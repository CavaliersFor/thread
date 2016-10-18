<div class="detail">
  {{if type === 1}}
    <div>收货人：{{add.buyerName}}</div>
    <div>电话：{{add.buyerPhone}}</div>
    <div>收货地址：{{add.province}}{{add.city}}{{add.region}}{{add.buyerAddress}}</div>
  {{else if type === 2}}
    <div>自提点：{{add.clpName}}</div>
    <div>自提点电话：{{add.clpTelephone}}</div>
    <div>自提点地址：{{add.clpAddress}}</div>
    <div>营业时间：{{add.startTime}}～{{add.endTime}}</div>
  {{/if}}
</div>