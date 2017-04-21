$(document).ready(function() {
	$('#due_date').daterangepicker({
	  singleDatePicker: true,
	  timePicker: true,
	  opens:"left",
	  locale:{
		  format: 'YYYY-MM-DD hh:mm:ss'
	  }
	});
});

function ticketUpdate(id){
	
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
	data.build_entry = $('#build_entry').val();
	data.id = id;
	data.date = $('#date').text();
	
	
	$.ajax({
	  type: "PUT",
	  url: "/campaign",
	  data:  JSON.stringify(data),
	  dataType: "json",
	  contentType: "application/json"
	})
	.done(function(data) {
		if(data.status == 200){
    		alert("Ticket Update Successfully");
    	}else {
    		alert("Error In Updating Ticket");
    	}
    }).fail(function(data) {
    	if(data.status == 200){
    		alert("Ticket Update Successfully");
    	}else {
    		alert("Error In Updating Ticket");
    	}
    });
}