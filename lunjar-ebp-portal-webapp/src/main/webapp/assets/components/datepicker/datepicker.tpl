<div class="datepicker">
	<div class="datepicker-title">{{title}}</div>
	<div class="datepicker-body">
		<div class="datepicker-option">
			<div class="datepicker-add {{if maxDate && maxDate.year <= year}}disabled{{/if}}" data-flag="handleYear"></div>
			<div class="datepicker-value">{{year}} 年</div>
			<div class="datepicker-cut {{if minDate && minDate.year >= year}}disabled{{/if}}" data-flag="handleYear"></div>
		</div>
		<div class="datepicker-option">
			<div class="datepicker-add {{if maxDate && maxDate.month <= month}}disabled{{/if}}" data-flag="handleMonth"></div>
			<div class="datepicker-value">{{month}} 月</div>
			<div class="datepicker-cut {{if minDate && minDate.month >= month}}disabled{{/if}}" data-flag="handleMonth"></div>
		</div>
		<div class="datepicker-option">
			<div class="datepicker-add {{if maxDate && maxDate.month <= month && maxDate.day <= day}}disabled{{/if}}" data-flag="handleDay"></div>
			<div class="datepicker-value">{{day}} 日</div>
			<div class="datepicker-cut {{if minDate && minDate.month >= month && minDate.day >= day}}disabled{{/if}}" data-flag="handleDay"></div>
		</div>
	</div>
	<div class="datepicker-footer">
		<a class="datepicker-confirm">{{confirmText || '确定'}}</a>
		<a class="datepicker-cancel">{{cancelText || '取消'}}</a>
	</div>
</div>