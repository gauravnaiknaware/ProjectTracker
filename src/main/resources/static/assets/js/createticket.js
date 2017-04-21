var due;

$(document).ready(function() {
	$('#due_date').daterangepicker({
	  singleDatePicker: true,
	  startDate: moment(),
	  timePicker: true,
	  locale:{
		  format: 'YYYY-MM-DD hh:mm:ss'
	  }
	}/*,function(start){alert(start); $('#due_date').html(start.format('YYYY-MM-DD hh:mm:ss'));}*/ );
	/*$('#due_date').on('apply.daterangepicker', function(ev, picker) {
	  alert(picker.startDate);
	});*/
});



function formSubmit(e){
	if($('#zone').val() == ''){
		alert("Please Select Zone");
		return;
	} 
	if($('#client_name').val() == ''){
		alert("Please Enter Client Name");
		return;
	}
	if($('#name').val() == ''){
		alert("Please Enter Campaign Name");
		return;
	}
	if($('#task').val() == ''){
		alert("Please Select Task Type");
		return;
	}
	if($('#team').val() == ''){
		alert("Please Select Team");
		return;
	}
	if($('#status').val() == ''){
		alert("Please Select Status");
		return;
	}
	if($('#priority').val() == ''){
		alert("Please Select Priority");
		return;
	}
	if($('#description').val() == ''){
		alert("Please Provide Description");
		return;
	}
	
	var data = {};
	data.zone = $('#zone').val();
	data.client_name = $('#client_name').val();
	data.name = $('#name').val();
	data.due_date = Date.parse( $('#due_date').val() ) ;
	data.status = $('#status').val();
	data.priority = $('#priority').val();
	data.team = $('#team').val();
	data.task = $('#task').val();
	data.description = $('#description').val();
	
	
	$.ajax({
	  type: "POST",
	  url: "/campaign",
	  data:  JSON.stringify(data),
	  dataType: "json",
	  contentType: "application/json"
	})
	.done(function(data) {
	   $('#client_name').val("");
	   $('#name').val("");
	   $('#description').val("");
       alert("Ticket Generated Successfully");
    }).fail(function(data) {
    	alert("Error In Creating Ticket");
    });
	
}