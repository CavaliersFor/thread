import $ from 'jquery';
import './checkbox.scss';

$(document).on('click', '.radios_box', (e) => {
  var $this = $(e.currentTarget).find('input[type="checkbox"]');
  if ($this.prop('checked')) {
    $this.parent().addClass('checked');
  } else {
    $this.parent().removeClass('checked');
  }
});

$(document).on('click', '.radios_box', (e) => {
  var $this = $(e.currentTarget).find('input[type="radio"]');
  var name = $this.prop('name');
  if ($this.prop('checked')) {
    $(`input[name="${name}"]`).each((index, input) => {
      $(input).prop('checked', false)
        .parent().removeClass('checked');
    });
    $this
      .prop('checked', true)
      .parent().addClass('checked');
  }
});