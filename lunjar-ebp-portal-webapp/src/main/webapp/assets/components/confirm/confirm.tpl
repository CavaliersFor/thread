<div class="alert {{if type !== 'alert'}}alert-confirm{{/if}}">
  {{if title}}
    <div class="alert-header">{{title}}</div>
  {{/if}}
  {{if type === 'propt'}}
  <div class="alert-body">
    <div class="alert-input">
      <input type="text" value="{{content}}" />
    </div>
  </div>
  {{else}}
    {{if content}}
    <div class="alert-body">{{content}}</div>
    {{/if}}
  {{/if}}
  <div class="alert-footer">
    {{if type !== 'alert'}}
      <a class="alert-button alert-cancel">取消</a>
    {{/if}}
    <a class="alert-button alert-ok">确定</a>
  </div>
</div>