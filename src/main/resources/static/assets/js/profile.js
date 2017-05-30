$(document).ready(function() {
	$("#currentPass").parent().css("display","none");
	$("#newPass").parent().css("display","none");
	$("#retypePass").parent().css("display","none");
	$("#submitPass").parent().css("display","none");
		
	$('.form-group').on('click', '#changePass', function(event) {
		$("#password").parent().css("display","none");
		$("#changePass").parent().css("display","none");
		
		$("#currentPass").parent().css("display","block");
		$("#newPass").parent().css("display","block");
		$("#retypePass").parent().css("display","block");
		$("#submitPass").parent().css("display","block");
	});
	
	$('.form-group').on('click', '#submitPass', function(event) {
		var a = $("#currentPass").val(),
			b = $("#newPass").val(),
			c = $("#retypePass").val();
		if( a == '' || b == '' || c == ''){
			alert("fields can't be blanks");
			return;
		}
		if(b != c){
			alert("new password is not matching");
			return;
		}
		if(b.length <= 4){
			alert("password length must be greater than 4");
			return;
		}
			
		var url = event.target.getAttribute("data-href");
		$.get( url+"?old="+$("#currentPass").val()+"&new="+$("#newPass").val(), function(e) {
		  console.log(e);
		  location.reload();
		})
		.done(function(e) {
			console.log(e);
			location.reload();
		})
		.fail(function(e) {
			if(e.status == 400){
				alert("Current password didn't matched")
			}
		});

	});
});