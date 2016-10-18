import $ from 'jquery';

const SUCCESS_CODE = 1000000;

export function ajax(method, url, params, parse = 'json') {
	const deffered = $.Deferred();

	$[method](url, params, () => {}, parse).then(res => {
		if (res.code === SUCCESS_CODE) {
			deffered.resolve(res);
		} else {
			deffered.reject(res);
		}
	}, err => {
		deffered.reject(err);
	});

	return deffered;
}

export function get(url, params, parse) {
	return ajax('get', url, params, parse);
}

export function post(url, params, parse) {
	return ajax('post', url, params, parse);
}

export function jsonp(url, data) {
	return $.ajax({
		url,
		data,
		dataType: 'jsonp',
    jsonp: 'callback'
	});
}
