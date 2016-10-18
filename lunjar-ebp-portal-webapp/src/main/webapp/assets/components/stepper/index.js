import './stepper.scss';
import $ from 'jquery';

class Stepper {
  constructor() {
    this.init();
  }
  init() {
    $(document).on('click', '.btn-minus', (e) => {
      this.operate('minus', $(e.target));
    }).on('click', '.btn-add', (e) => {
      this.operate('add', $(e.target));
    });
  }
  operate(operate, $btn) {
    // var $wrapper = $btn.parents('.stepper');
    var $input = $btn.siblings('input');
    var min = $input.data('min') || 0;
    var max = $input.data('max') || Infinity;
    var value = +$input.val() || min;
    if (operate === 'add' && value < max) {
      value++;
    } else if (operate === 'minus' && value > min) {
      value--;
    }
    $input.val(value).trigger('change', value);
  }
}

new Stepper();