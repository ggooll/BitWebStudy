/**
 * Ajax 통신과 관련된 함수 집합
 */
var httpRequest = null;

function getXMLHttpRequest() {
	if (window.ActiveXObject)
		return new ActiveXObject("Mircrosoft.XMLHTTP");
	if (window.XMLHttpRequest)
		return new XMLHttpRequest();
	return null;
}

function sendProcess(method, url, params, callback) {
	httpRequest = getXMLHttpRequest();
	httpRequest.onreadystatechange = callback;

	var httpMethod = method;
	if (httpMethod != 'GET' && httpMethod != 'POST')
		httpMethod = 'GET';

	// json형을 parameter가 넘어가는 형태로 변경시켜줌
	httpParams = '';
	if (params != null && params != '') {
		for ( var key in params) {
			if (httpParams != '')
				httpParams += '&';
			httpParams += key + '=' + encodeURIComponent(params[key]);
		}
	}

	httpUrl = url;
	if (httpMethod == 'GET' && httpParams != '') {
		httpUrl = httpUrl + '?' + httpParams;
	}

	httpRequest.open(httpMethod, httpUrl, true);
	if (httpMethod == 'POST')
		httpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	httpRequest.send(httpMethod == 'GET' ? null : httpParams);
}
