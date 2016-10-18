import $ from 'jquery';
import {
  Swiper,
  AddToCar
} from 'component';
import { getItemDetail, getListCart } from '../../api/item-api';
import './style';

class ItemDetail {
  constructor() {
    this.productId = globalData.productId;
    this.carBox;
    this.init();
  }

  init() {
    // 轮播
    this.initSwiper();
    // 运费
    this.initExpress();
    // 事件
    this.initEvent();
    // 获取购物车数量
    this.getCartNum();
    getItemDetail(this.productId).then(res => {
      [this.product, this.productSkus] = [res.data.product, res.data.productSkus];
    });
  }

  initSwiper() {
    new Swiper('.js-top-swiper', {
      pagination: '.swiper-pagination',
      loop: true,
      autoplay: 3000
    });
  }

  initExpress() {
    // var prices = []
    // globalData.ecPrice.split(';').forEach(item => {
    //   var p = item.split(':')[1].split(',');
    //   prices.push(+p[0], +p[0] + (+p[1] || 0));
    // });
    // var maxPrice = Math.max.apply(null, prices);
    // var minPrice = Math.min.apply(null, prices);
    console.log(globalData);
    if (globalData.ecPrice) {
      const price = globalData.ecPrice.split(';')[0].split(':')[1].split(',')[0];

      $('.js-express-price').html(`￥${price}`);
    }
    // $('.js-express-price').html(maxPrice === minPrice ? `￥${maxPrice}` : `￥${minPrice} ~ ￥${maxPrice}`);
  }

  initEvent() {
    $(document).on('click', '.js-addcart', (e) => {
      var $this = $(e.currentTarget);
      var type = $this.data('type');
      if (!this.carBox) {
        this.initShopCart();
      }
      this.carBox.show(AddToCar[type], this.product, this.productSkus);
    }).on('click', '.js-to-combine', (e) => {
      var $this = $(e.currentTarget);
      var id = $this.data('id');
      window.location = `/product/combinationProdDetail/${window.shopId}-${id}`;
    });
  }

  initShopCart() {
    this.carBox = new AddToCar();

    this.carBox.on('add', (data) => {
      var $num = $('.js-cart-num');
      var num = +$num.html();
      num += data.num;
      $num.html(num);
    });
  }

  getCartNum() {
    getListCart().then(res => {
      const $num = $('.js-cart-num');
      let num = 0;

      if (res.data.carts) {
        res.data.carts.forEach(cart => {
          num += cart.num;
        });
      }
      $num.text(num);
    });
  }
}

$(() => {
  new ItemDetail();
});
