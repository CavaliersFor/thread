
import {
  AddToCar,
  Message
} from 'component'
import './style';

import $ from 'jquery';
import template from 'art-template/dist/template';

import tplTabItem from './tab-item.tpl';
import tplItemList from './item-list.tpl';

import {
  getItemDetail
} from '../../api/item-api.js';

class Classicalmix {
  constructor() {
    this.combinationProducts = globalData.combinationProducts;
    this.combinationProduct;
    this.init();
  }

  init() {
    // console.log(this.combinationProducts);
    this.combinationProduct = this.getCp(0);
    this.renderTab();
    this.renderItemList();
    this.initEvent();
  }

  setTotalPrice() {
    var total = 0;
    var discount = 0;
    this.combinationProduct.forEach((p) => {
      total += p.realPrice * p.num;
      discount += (p.price - p.realPrice) * p.num;
    });
    $('.js-total').html(total);
    $('.js-discount').html(discount);
  }

  getCp(index) {
    var result = [];
    var cp = this.combinationProducts[index];
    var range = [1,2,3,4,5];
    range.forEach((r) => {
      if (cp[`product${r}Id`]) {
        result.push({
          id: cp[`product${r}Id`],
          name: cp[`product${r}Name`],
          picPath: cp[`product${r}PicPath`],
          skus: cp[`product${r}Skus`],
          realPrice: cp[`product${r}RealPrice`],
          price: cp[`product${r}Price`],
          num: cp[`product${r}Num`]
        });
      }
    });
    $('input[name="id"]').val(cp.id);
    console.log(result);
    return result;
  }

  renderTab() {
    var tpl = template.compile(tplTabItem)({
      combinationProducts: this.combinationProducts
    });

    $('.js-tab').html(tpl);
  }

  renderItemList() {
    var tpl = template.compile(tplItemList)({
      shopId: window.shopId,
      cp: this.combinationProduct
    });
    $('#itemList').html(tpl);
    this.setTotalPrice();
  }

  initEvent() {
    $(document).on('click', '.js-sku', (e) => {
      this.showSku($(e.currentTarget));
    }).on('click', '.js-tab-item', (e) => {
      this.changeTab($(e.currentTarget));
    }).on('submit', '.classicalmix-buybox', (e) => {
      if (!this.validate()) {
        return false;
      }
      this.submitForm($(e.currentTarget));
    });
  }

  submitForm() {
    var value = this.combinationProduct.map((cp) => {
      return {
        productId: cp.id,
        skuId: cp.skuId || null,
        num: cp.num
      }
    });
    $('input[name="value"]').val(JSON.stringify(value));
  }

  validate() {
    var canSumbit = true;
    var cps = this.combinationProduct;

    for (var i = 0, len = cps.length; i < len; i++ ) {
      if (cps[i].skus.length !== 0 && !cps[i].skuId) {
        canSumbit = false;
        Message.show({
          content: '请选择商品属性'
        });
        break
      }
    }

    return canSumbit;
  }

  changeTab($tab) {
    if ($tab.hasClass('active')) {
      return;
    }
    var index = +$tab.data('index');
    $('.js-tab-item').removeClass('active');
    $tab.addClass('active');
    this.combinationProduct = this.getCp(index);
    this.renderItemList();
  }

  showSku($btn) {
    this.tempIndex = $btn.data('index');
    var id = this.combinationProduct[this.tempIndex].id;
    getItemDetail(id).then((res) => {
      if (!this.carBox) {
        this.initShopCart();
      }
      this.carBox.show(3, res.data.product, res.data.productSkus);
    });
  }

  initShopCart() {
    this.carBox = new AddToCar();

    this.carBox.on('choose', (data) => {
      var value;
      var skuId = data.skuId;
      this.combinationProduct[this.tempIndex].skus.forEach((sku) => {
        if (sku.id === skuId) {
          value = sku.propertiesname;
        }
      });
      $(`.js-sku-value[data-index="${this.tempIndex}"]`).html(value);
      this.combinationProduct[this.tempIndex].skuId = skuId;
      this.tempIndex = null;
    });
  }
}

new Classicalmix();