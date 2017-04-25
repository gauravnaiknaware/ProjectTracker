$(function (){
	$('table').DataTable( {
		"scrollY": "345px",
		"scrollCollapse": true,
		"destroy": true,
		"dom": '<"toolbar">frtip',
		"pageLength": 100
	});
	
});
function createUser(){
	if($('#name').val() == ''){
		alert("Please Enter User Name");
		return;
	}
	if($('#role').val() == ''){
		alert("Please Select Role");
		return;
	}
	
	var data = {};
	data.role = $('#role').val();
	data.name = $('#name').val();
	
	$.ajax({
		  type: "POST",
		  url: "/user",
		  data:  JSON.stringify(data),
		  dataType: "json",
		  contentType: "application/json"
		})
		.done(function(data) {
		   $('#name').val("");
	       alert("User Created Successfully");
	    }).fail(function(data) {
	       alert("Error In Creating User");
	    })
}