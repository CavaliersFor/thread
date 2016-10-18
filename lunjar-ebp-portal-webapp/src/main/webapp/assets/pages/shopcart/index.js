import {
  Message,
  Popup
} from 'component';
import './shopcart';

import $ from 'jquery';
import template from 'art-template/dist/template';
import { editCart } from '../../api/item-api.js';
import tplDiscountList from './discount-list.tpl';

class Shopcart {
  constructor() {
    this.init();
  }

  init() {
    this.popup;
    this.initEvent();
    this.setTotalPrice(this.getTotalPrice());
    this.initCheck();
  }

  initCheck() {
    $('.js-check:checked').each((index, check) => {
      $(check).parent().addClass('checked');
    });
    this.toggleCheckAll();
  }

  initEvent() {
    $(document).on('click', '.js-edit', (e) => {
      this.editShopList($(e.currentTarget));
    }).on('click', '.js-edit-complete', (e) => {
      this.editShopListComplete($(e.currentTarget));
    }).on('click', '.js-delete', (e) => {
      this.deleteItem($(e.currentTarget));
    }).on('change', '.js-check', (e) => {
      this.toggleCheckAll();
    }).on('change', '.js-check-all', (e) => {
      this.checkAll($(e.currentTarget));
    }).on('change', '.js-num', (e) => {
      this.changeNum($(e.currentTarget));
    }).on('click', '.js-commit', (e) => {
      this.commit();
    }).on('click', '.js-show-discounts', (e) => {
      this.shopDiscounts($(e.currentTarget));
    }).on('click', '.js-close-popup', () => {
      this.popup.cancel();
    });
  }

  shopDiscounts($btn) {
    var discounts = $btn.data('discounts');
    console.log(discounts);
    if (!this.popup) {
      this.popup = new Popup().render();
    }
    this.popup.renderContent(template.compile(tplDiscountList)({
      discounts: discounts
    })).show();
  }

  editShopList($btn) {
    var $shop = $btn.parents('.js-shop');
    $btn.html('完成').removeClass('js-edit').addClass('js-edit-complete');
    $shop.find('.js-edit-hide').addClass('z-hidden');
    $shop.find('.js-delete').removeClass('z-hidden');
  }

  editShopListComplete($btn) {
    var $shop = $btn.parents('.js-shop');
    $btn.html('编辑').removeClass('js-edit-complete').addClass('js-edit');
    $shop.find('.js-edit-hide').removeClass('z-hidden');
    $shop.find('.js-delete').addClass('z-hidden');
  }

  deleteItem($btn) {
    $.Confirm({
      title: '提示',
      content: '确定删除该商品吗？',
      confirm: () => {
        editCart($btn.data('id')).then((res) => {
          var $shop = $btn.parents('.js-shop');
          Message.show({
            content: '删除成功'
          });
          $btn.parents('.js-item').remove();
          if ($shop.find('.js-item').length === 0) {
            $shop.remove();
          }
          if ($('.js-shop').length === 0) {
            $('.js-shops').html(`
              <div class="empty-list-tip">
                <span class="empty-icon"></span>
                <div class="empty-cnt">您的购物车还没有商品哦！</div>
              </div>
            `);
          }
          this.setTotalPrice(this.getTotalPrice());
        });
      }
    });
  }

  toggleCheckAll() {
    var checkedLen = $('.js-check:checked').length;
    var len = $('.js-check').length;
    var $checkAll = $('.js-check-all');
    if (checkedLen > 0 && len === checkedLen) {
      $checkAll.prop('checked', true).parent().addClass('checked');
    } else {
      $checkAll.prop('checked', false).parent().removeClass('checked');
    }
    this.setTotalPrice(this.getTotalPrice());
  }

  checkAll($checkbox) {
    if ($checkbox.prop('checked')) {
      $('.js-check').each((index, check) => {
        $(check).prop('checked', true).parent().addClass('checked');
      });
      this.setTotalPrice(this.getTotalPrice());
    } else {
      $('.js-check').each((index, check) => {
        $(check).prop('checked', false).parent().removeClass('checked');
      });
      this.setTotalPrice(0);
    }
  }

  getTotalPrice() {
    var total = 0;
    $('.js-check:checked').each((index, check) => {
      var $check = $(check);
      var price = +$check.val();
      var num = +$check.parents('.js-item').find('.js-num').val();
      total += price * num;
    });
    return total;
  }

  setTotalPrice(price) {
    $('.js-total').html(price.toFixed(2));
  }

  changeNum($input) {
    editCart($input.data('id'), 1, $input.val()).then(res => {
      this.setTotalPrice(this.getTotalPrice());
    });
  }

  commit() {
    var ids = [];
    var $checkeds = $('.js-check:checked');
    if ($checkeds.length === 0) {
      Message.show({
        content: '请选择要买的商品'
      })
      return;
    }

    $checkeds.each((index, check) => {
      ids.push($(check).data('id'));
    });
    window.location = `/trade/commitPage?ids=${ids.join(',')}&shopId=${window.shopId}`;
  }
}

new Shopcart();