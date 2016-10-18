{{each combinationProducts as cp index}}
  <li><a class="{{if index === 0}}active{{/if}} js-tab-item" data-index="{{index}}" href="javascript:void(0);">{{cp.cpName}}</a></li>
{{/each}}
