function createGoogleLogin(){
	// Initialize Firebase
	var config = {
		    apiKey: "AIzaSyATeCSZTQXDK27eD0dO0t12IkyeB5u0iUs",
		    authDomain: "webproject1-a5aaf.firebaseapp.com",
		    databaseURL: "https://webproject1-a5aaf.firebaseio.com",
		    projectId: "webproject1-a5aaf",
		    storageBucket: "",
		    messagingSenderId: "493262486597"
		  };
	
	firebase.initializeApp(config);
	var provider = new firebase.auth.GoogleAuthProvider();
	
	firebase.auth().signInWithPopup(provider).then(function(result) {
		// This gives you a Google Access Token. You can use it to access the
		// Google API.
		var token = result.credential.accessToken;
		// The signed-in user info.
		var user = result.user;
		// console.log(token);

		var googleId = user.email;
		var googleName = user.displayName;
		googleLogin(googleId, googleName);
	
	}).catch(function(error) {
	  	  // Handle Errors here.
	  	  var errorCode = error.code;
	  	  var errorMessage = error.message;
	  	  // The email of the user's account used.
	  	  var email = error.email;
	  	  // The firebase.auth.AuthCredential type that was used.
	  	  var credential = error.credential;
	  	  // ...
	  	  console.log(error);
  	});
}

function googleLogin(googleId, googleName){
 	$.ajax({
	 	type : "POST",
	  	url : "/BondDebtManager/apiLogin.do",
	  	data : 'id='+googleId+'&name='+googleName,
	  	async : true,
	  	success : function(data){
	  		flag = data.trim();
	  		if(flag == '1'){
	  			alert('안녕하세요')
	  			location.href="/BondDebtManager/hello.do";
	  		} else {
	  			alert('아이디 또는 비밀번호가 올바르지 않습니다.');
	  		}
	  	},
  		error : function(request,status,error){
  			location.href="/BondDebtManager/hello.do";
  	   }
	});
}