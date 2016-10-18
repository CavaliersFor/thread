import Message from '../components/message';

export function handleError(err, timeout = 1500) {
	Message.show({
		content: err.msg,
		timeout
	});
}

export function showSuccessTip(content, timeout = 1500) {
	Message.show({
		content,
		timeout
	});
}