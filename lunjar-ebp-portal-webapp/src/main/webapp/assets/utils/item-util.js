
export function parseSku(propsName, propsAlias) {
	if(!propsName || !propsAlias) {
		return [];
	}

	let result = [];
	const kinds = propsName.split(';');
	const alias = propsAlias.split(';');

	kinds.forEach(kind => {
		const props = kind.split(':');
		let data = {
			value: props[0],
			name: props[1],
			list: []
		};

		alias.forEach(item => {
			const values = item.split(':');

			if(values[0] === props[0]) {
				data.list.push({
					value: values[1],
					name: values[2]
				});
			}
		});

		result.push(data);
	});

	return result;
}