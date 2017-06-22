/**
 *	Ajax 통신과 관련된 함수 집합 
 */
var httpRequest = null;

// XMLHttpRequest 객체 생성
function getXMLHttpRequest() {
	if(window.ActiveXObject)
		return new ActiveXObject("Mircrosoft.XMLHTTP");
	if(window.XMLHttpRequest)
		return new XMLHttpRequest();
	return null;
}

// get방식으로 url을 요청, parameter(유,무), 동작후 callback 명시
// 서버에게 어떤 파일을 요청하는 것 까지의 역할
function sendProcess(method, url, params, callback) {
	httpRequest = getXMLHttpRequest();
	httpRequest.onreadystatechange = callback;
	
	var httpMethod = method;
	if(httpMethod != 'GET' && httpMethod != 'POST')
		httpMethod = 'GET';
	
	httpParams = '';
	if(params != null && params != '') {
		for(var key in params) {
			if(httpParams != '')
				httpParams += '&';
			httpParams += key + '=' + encodeURIComponent(params[key]);
		}
	}
	
	httpUrl = url;
	if(httpMethod == 'GET' && httpParams != '') {
		httpUrl = httpUrl + '?' + httpParams;
	}
	
	httpRequest.open(httpMethod, httpUrl, true);
	if(httpMethod == 'POST')
		httpRequest.setRequestHeader('Content-type',
				'application/x-www-form-urlencoded');
	
	// post인경우 send할때 parameter를 같이 보낸다.
	httpRequest.send(httpMethod == 'GET' ? null : httpParams);
}



