{{each list as item i}}
	{{if item.regionName === selected}}
	<option value="{{item.regionName}}" data-code="{{item.code}}" selected="selected">{{item.regionName}}</option>
	{{else}}
	<option value="{{item.regionName}}" data-code="{{item.code}}">{{item.regionName}}</option>
	{{/if}}
{{/each}}
