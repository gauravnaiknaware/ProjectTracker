var id;
function validateEmail() {
    var email = $('#email').val();
    if(!isEmail(email)) {
	    alert("Please enter valid email");
	    return;
    }
    $.ajax({
	  type: "GET",
	  url: "/users/"+email+"/email"
	})
	.done(function(data) {
		console.log(data);
		id = '';
		id = data;
		//$('.flip').find('.card').toggleClass('flipped');
    }).fail(function(data) {
    	alert("Please contact Your Admin.");
    });
	
}

function createPassword(){
	var pass1 = $('#password1').val();
	var pass2 = $('#password2').val();
	if(pass1.length < 4 || pass2.length <  4 ){
		alert("Password Length Should be between 5 to 15");
		return;
	}
	if(pass1 == '' || pass2 == '' ){
		alert("Please Enter Valid Password");
		return;
	}
	if(alphanumeric(pass1) || alphanumeric(pass2) ){
		alert('Please input alphanumeric characters only');
		return;
	}
	if(pass1 != pass2){
		alert("Password is not matching.");
		return;
	}
	
	 $.ajax({
	  type: "GET",
	  url: "/user/update/"+id+"?pass="+pass2
	})
	.done(function(data) {
		console.log(data);
		alert("Password Created Successfully. Go To Login");
		window.location = "/console/login";
    }).fail(function(data) {
    	alert("Please contact Your Admin.");
    });
}

function alphanumeric(inputtxt) { 
	var letters = /^[0-9a-zA-Z]+$/;
	/*if(inputtxt.test(letters)) {
		return true;
	}else{
		alert('Please input alphanumeric characters only');
		return false;
	}*/
	return !letters.test(inputtxt);
}

function isEmail(email) {
  var re = /^([a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$)/;
  return re.test(email);
}