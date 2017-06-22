function isEmptyInput(name, msg){
	if(name.value == ""){
		alert(msg);
		name.focus();
		return true;
	}
	return false;
}
