$(function (){
	$('table').DataTable( {
		"scrollY": "145px",
		"scrollCollapse": true,
		"destroy": true,
		"dom": '<"toolbar">frtip',
		"pageLength": 100
	});
	
	$('table').on('click', '.clickable-row', function(event) {
		$.confirm({
		    title: 'Confirm!',
		    content: '',
		    buttons: {
		        open: {
		        	action : function () {
		        		
		        	},
		        	btnClass : 'btn-info'
		        	
		            
		        },
		        deleteUser : {
		        	action : function () {
		        		
		        	},
		        	btnClass : 'btn-danger',
		        	text : 'DELETE'
		        }
		    },
		    backgroundDismissAnimation : 'glow'
		});	
	});
	
});
function createUser(){
	if($('#name').val() == ''){
		alert("Please Enter User Name");
		return;
	}
	if(!isEmail($('#name').val())){
		alert("Please enter valid email");
	    return;
	}
	if($('#team').val() == ''){
		alert("Please Select Team");
		return;
	}
	if($('#role').val() == ''){
		alert("Please Select Role");
		return;
	}
	
	var data = {};
	data.team = $('#team').val();
	data.name = $('#name').val();
	data.role = $('#role').val();
	
	$.ajax({
		  type: "POST",
		  url: "/users",
		  data:  JSON.stringify(data),
		  dataType: "json",
		  contentType: "application/json"
		})
		.done(function(data) {
		   $('#name').val("");
	       alert("User Created Successfully");
	       location.reload();
	    }).fail(function(data) {
	       alert("Error In Creating User");
	    })
}

function isEmail(email) {
  var re = /^([a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$)/;
  return re.test(email);
}

