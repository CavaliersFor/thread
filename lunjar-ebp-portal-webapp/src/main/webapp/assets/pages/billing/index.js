// import '../../components/base';
// import '../../components/address-list';
import {
  Popup,
  Message
} from 'component'
import './billing.scss';

import $ from 'jquery';
import template from 'art-template/dist/template';
import { getAddressList } from '../../api/address-api.js';
import {
  commitTrade,
  asynExpress,
  asynCombineExpress,
  paySuccess
} from '../../api/trade-api.js';
import {
  weixinPay
} from '../../api/weixin-api.js';
import {
  handleError,
  showSuccessTip
} from '../../utils/response-util.js';

import tplBills from './bill.tpl';
import tplAddList from './address-list.tpl';
import tplAddDetail from './address-detail.tpl';

function payForTrade(trades, payNo) {
  let tid = payNo;
  let shopNames = [];
  let trade = {
    realPrice: 0
  };

  // 单个订单支付
  if (!payNo) {
    payNo = trades[0].tradeNo;
    tid = trades[0].id;
  }

  trades.forEach(item => {
    trade.realPrice += item.realPrice;
    shopNames.push(item.shopName);
  });

  trade.shopName = shopNames.join(',');
  trade.tradeNo = payNo;
  // alert(JSON.stringify(trade));
  weixinPay(window.openId, trade).then(res => {
    // alert(JSON.stringify(res));
    $.Loading.hide();
    showSuccessTip('支付成功！', 3000);
    paySuccess(tid).then(success, success);
  }, e => {
    // alert('error----' + JSON.stringify(e));
    $.Loading.hide();
    window.location.href = `/trade/list?shopId=${window.shopId}&status=2`;
  });

  function success(res) {
    if (trades.length === 1) {
      window.location.href = `/trade/detail?shopId=${window.shopId}&tradeId=${trades[0].id}`;
    } else {
      window.location.href = `/trade/list?shopId=${window.shopId}&status=3`;
    }
  }
}

class Billing {
  constructor() {
    this.commitParams;
    this.popup;
    this.bills = globalData.bills;
    this.postFlag = globalData.postFlag;
    this.value = globalData.value;
    this.combineId = globalData.combineId;
    this.paymentMode = globalData.paymentMode;
    this.init();
  }

  init() {
    if (this.postFlag === 1) {
      this.addressId = null;
      this.getAddList();
    }
    this.renderBills();

    this.initEvent();
    console.log(this.bills);
  }
  /**
   * 获取买家的地址列表
   */
  getAddList() {
    getAddressList().then((res) => {
      this.addList = res.list;
    });
  }
  /**
   * 渲染订单
   */
  renderBills() {
    var tpl = template.compile(tplBills)({
      bills: this.bills
    });
    $('#billList').html(tpl);
  }

  initEvent() {
    $(document).on('click', '.js-show-add', () => {
      this.showAddList();
    }).on('click', '.js-choose-add', (e) => {
      this.chooseAdd($(e.currentTarget));
    }).on('click', '.js-back', () => {
      this.popup.cancel();
    }).on('click', '.js-show-collect', (e) => {
      this.showCollect($(e.currentTarget));
    }).on('click', '.js-choose-collect', (e) => {
      this.chooseCollect($(e.currentTarget));
    }).on('click', '.js-select-date', e => {
      this.showSelectColDate($(e.currentTarget));
    }).on('click', '.js-commit:not(".disabled")', (e) => {
      this.commit($(e.currentTarget));
    }).on('change', '.js-invoice', (e) => {
      this.toggleShowInvoiceOption($(e.currentTarget));
    }).on('change', '.js-invoice-type', (e) => {
      this.chooseInvoiceType($(e.currentTarget));
    });
  }

  chooseInvoiceType($checkbox) {
    console.log($checkbox.prop('checked'));
    return false;
  }
  /**
   * 展示发票信息表单
   * @param  {Object} $checkbox checkbox对象
   */
  toggleShowInvoiceOption($checkbox) {
    var $bill = $checkbox.parents('.js-bill');
    // var billIndex = +$bill.data('index');
    var $invoiceOption = $bill.find('.js-invoice-option');
    if ($checkbox.prop('checked')) {
      $invoiceOption.removeClass('z-hidden')
    } else {
      $invoiceOption.addClass('z-hidden')
    }
    // this.bills[billIndex].isInvoice = $checkbox.prop('checked') ? 1 : 0;
  }
  /**
   * 提交订单
   * @param  {Object} $btn 按钮
   */
  commit($btn) {
    if (!this.validateForm()) {
      return;
    }
    if(this.paymentMode === 1){//支付宝pay
      //post val 
      this.getCommitParams();
      var pruductName = this.bills[0]['listCartDto'][0].productName;
      commitTrade(JSON.stringify(this.commitParams), this.value, this.combineId).then((res) => {
        const data = res.data;
        //console.log(data)
        //var price = data.trades[0]['realPrice'];//价格
        var outTradeNo = data.payNo+data.trades[0].tradeNo;//订单编号
        var pruductDesc  = '易趣购-'+pruductName+'\-'+data.trades[0].tradeDesc;//订单描述
        $('#alipayment input[name=WIDout_trade_no]').val(outTradeNo);
        $('#alipayment input[name=WIDbody]').val(pruductDesc);
        var payType = $btn.parents('.main').find('.js-pay-type:checked').val();
        $('#alipayment input[name=type]').val(payType);
        setTimeout(function(){
          $('#alipayment').submit();
          $('#alipayment input[type=submit]').trigger('click');
        },50);
      }, err => {
        alert(JSON.stringify(err));
      });

      
      
    }else{//微信pay
      $btn.addClass('disabled');
      this.getCommitParams();
      $.Loading.show();
      commitTrade(JSON.stringify(this.commitParams), this.value, this.combineId).then((res) => {
        const data = res.data;
        console.log(data)
        // alert(JSON.stringify(res));
        payForTrade(data.payNo ? data.trades : data, data.payNo);
      }, err => {
        $btn.removeClass('disabled');
        $.Loading.hide();
        alert(JSON.stringify(err));
      });
    }
  }
  /**
   * 给隐藏表单赋值 -- post 支付宝 pay 
   */
  postInput (){

  }
  
  /**
   * 获取将要提交的订单信息 -- 微信pay
   */
  getCommitParams() {
    this.commitParams = this.bills.map((bill, index) => {
      var $bill = $(`.js-bill[data-index="${index}"]`);
      var result = {};
      if (!this.value) {
        result.cartIdList = bill.listCartDto.map((cart) => {
          return cart.id
        });
      }
      result.enterpriseId = bill.enterpriseId;
      // 地址
      result.addressId = bill.distributionMode === 2 ? bill.addressId : this.addressId;
      // 发票内容
      result.isInvoice = $bill.find('.js-invoice').prop('checked') ? 1 : 0;
      if (result.isInvoice === 1) {
        result.invoiceType = $bill.find('.js-invoice-type:checked').val();
        result.invoiceName = $bill.find('.js-invoice-name').val();
      }
      // 自提内容
      if (bill.distributionMode === 2) {
        result.buyerName = $bill.find('.js-collect-name').val();
        result.buyerPhone = $bill.find('.js-collect-mobile').val();
        result.collectTime = $bill.find('input[name="collectTime"]').val();
      }
      result.buyerRemarks = $bill.find('textarea[name="buyerRemarks"]').val();
      //支付方式
      var type = $bill.find('.js-pay-type:checked').val();
      if(type){
        result.payType = type;
      }
      console.log(result);
      return result;
    });
  }
  /**
   * 校验表单
   * @return {Boolean} 是否通过验证
   */
  validateForm() {
    if (this.postFlag === 1 && !this.addressId) {
      Message.show({
        content: '请选择快递收货地址'
      });
      return false;
    }

    return this.validateInvoice() && this.validateCollect();
  }
  /**
   * 校验发票信息
   * @return {Boolean} 是否通过验证
   */
  validateInvoice() {
    var canSubmit = true;
    var $bills = $('.js-bill');

    for (let i = 0, len = $bills.length; i < len; i++) {
      var $bill = $($bills[i]);
      if (!$bill.find('.js-invoice').prop('checked')) {
        continue;
      } else if (!$bill.find('.js-invoice-name').val()) {
        canSubmit = false;
        Message.show({
          content: '请输入发票抬头'
        });
        break;
      }
    }
    return canSubmit;
  }
  /**
   * 校验自提信息
   * @return {Boolean} 是否通过验证
   */
  validateCollect() {
    var canSubmit = true;
    var regMobile = /^1[3578][0-9]{9}$/;
    for (let i = 0, len = this.bills.length; i < len; i++) {
      var bill = this.bills[i];
      var $bill = $(`.js-bill[data-index="${i}"]`);
      if (bill.distributionMode === 1) {
        continue;
      }
      if (bill.distributionMode === 2 && !bill.addressId) {
        canSubmit = false;
        Message.show({
          content: '请选择自提地址'
        });
        break;
      }
      if (!$.trim($bill.find('.js-collect-name').val())) {
        canSubmit = false;
        Message.show({
          content: '请输入自提人姓名'
        });
        break;
      }
      if (!regMobile.test($bill.find('.js-collect-mobile').val())) {
        canSubmit = false;
        Message.show({
          content: '请输入正确的自提人手机'
        });
        break;
      }
    }
    return canSubmit;
  }
  /**
   * 选择自提地址
   * @param  {Object} $add 被选的自提地址DOM
   */
  chooseCollect($add) {
    var add;
    var id = +$add.data('id');
    var billIndex = +$add.data('bill');
    this.bills[billIndex].collectPlaces.forEach((item) => {
      if (item.id === id) {
        add = item;
      }
    });
    this.renderCollectAdd(add, billIndex);
    this.popup.confirm();
    this.bills[billIndex].addressId = id;
    this.changeCollectPrice(billIndex, add);

    const $date = $('.js-select-date[data-index="' + billIndex + '"]');
    const colStartDate = new Date(add.collectStartTime);
    const $dateIpt = $date.find('input');
    const dateStr = $.Datepicker.date2str($.Datepicker.parseDate(colStartDate));

    $dateIpt.data('min', add.collectStartTime);
    $dateIpt.data('max', add.collectEndTime);
    $dateIpt.val(dateStr);
    $date.find('span').text(`提货日期：${dateStr}`);
    $date.removeClass('z-hidden');
    this.showSelectColDate($date, add.collectStartTime, add.collectEndTime);
  }

  showSelectColDate($date, collectStartTime, collectEndTime) {
    const $input = $date.find('input');
    const val = $input.val();

    if(!collectStartTime) {
      collectStartTime = $input.data('min');
      collectEndTime = $input.data('max');
    }
    const colStartDate = new Date(collectStartTime);
    const colEndDate = new Date(collectEndTime);

    $.Datepicker({
      date: new Date(val.replace(/-/g, '/')),
      minDate: colStartDate,
      maxDate: colEndDate,
      confirm: (dateStr) => {
        $input.val(dateStr);
        $date.find('span').text(`提货日期：${dateStr}`);
      }
    });
  }

  /**
   * 更新自提订单的价格
   * @param  {Number} index 订单索引
   * @param  {Object} add   地址对象
   */
  changeCollectPrice(index, add) {
    var $bill = $(`.js-bill[data-index="${index}"]`);
    var $billPrice = $bill.find('.js-bill-price');
    var oldPayment = $billPrice.html();
    var postFee = $billPrice.data('exp-price');
    var newPayment = + oldPayment - postFee + add.fee;

    $billPrice.data('exp-price', add.fee);
    $billPrice.html(newPayment.toFixed(2));
    $bill.find('.js-price-info').html(`(自提费: ￥${add.fee})`);
    this.changeTotalPrice();
  }
  /**
   * 更新总价
   */
  changeTotalPrice() {
    var total = 0;
    $('.js-bill-price').each((index, price) => {
      total += +$(price).html();
    });
    $('.js-total').html(total);
  }
  /**
   * 渲染选中的自提地址
   * @param  {Object} add       地址对象
   * @param  {Number} billIndex 订单索引
   */
  renderCollectAdd(add, billIndex) {
    var tpl = template.compile(tplAddDetail)({
      add: add,
      type: 2
    });
    $(`.js-bill[data-index="${billIndex}"]`)
      .find('.js-show-collect')
      .html(tpl);
  }
  /**
   * 展示自提地址列表
   * @param  {Object} $btn 按钮对象
   */
  showCollect($btn) {
    var index = +$btn.data('index');
    if (!this.popup) {
      this.popup = new Popup().render();
    }
    this.popup.renderContent(template.compile(tplAddList)({
      shopId: window.shopId,
      addList: this.bills[index].collectPlaces,
      type: 2,
      billIndex: index
    })).show();
  }
  /**
   * 选择地址
   * @param  {Object} $add 被选中的地址对象
   */
  chooseAdd($add) {
    var id = +$add.data('id');
    var add;
    if (id === this.addressId) {
      this.popup.cancel();
      return;
    }
    this.addList.forEach((item) => {
      if (item.id === id) {
        add = item;
      }
    });
    this.renderAdd(add);
    this.popup.confirm();
    this.addressId = id
    this.changeExpressPrice();
  }
  /**
   * 更新快递订单的价格
   */
  changeExpressPrice() {
    var fn;
    if (!this.value) {
      var ids = [];
      this.bills.forEach((bill) => {
        if (bill.distributionMode === 1) {
          ids = ids.concat(bill.listCartDto.map((cart) => {
            return cart.id;
          }));
        }
      });
      asynExpress(ids.join(','), this.addressId).then((res) => {
        this.renderExpressPrice(res);
      });
    } else {
      asynCombineExpress(this.combineId, this.bills[0].enterpriseId, this.addressId).then((res) => {
        this.renderExpressPrice(res);
      });
    }
  }

  renderExpressPrice(res) {
    var newBills = res.data.cartDtos;
    newBills.forEach((bill) => {
      console.log(bill);
      var $bill = $(`.js-bill[data-id="${bill.enterpriseId}"]`);
      var $billPrice = $bill.find('.js-bill-price');
      var oldPayment = $billPrice.html();
      var postFee = $billPrice.data('exp-price');
      var newPayment = + oldPayment - postFee + bill.discountDto.expressPrice;

      $billPrice.data('exp-price', bill.discountDto.expressPrice)
      $billPrice.html(newPayment.toFixed(2));
      $bill.find('.js-price-info').html(` (快递费: ￥${bill.discountDto.expressPrice})`);
      this.changeTotalPrice();
    });
  }

  /**
   * 渲染被选中的快递地址
   * @param  {Object} add 地址对象
   */
  renderAdd(add) {
    var tpl = template.compile(tplAddDetail)({
      add: add,
      type: 1
    });
    $('.js-add-detail').html(tpl);
  }
  /**
   * 展示快递地址列表
   */
  showAddList() {
    if (!this.popup) {
      this.popup = new Popup().render();
    }
    this.popup.renderContent(template.compile(tplAddList)({
      shopId: window.shopId,
      addList: this.addList,
      type: 1
    })).show();

  }
}

new Billing();