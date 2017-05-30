var due;

$(document).ready(function() {
	$('#dueDate').daterangepicker({
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
	
	$( "#team" ).change(function() {
	  $('#assignee').html('');
	  $('#assignee').append( $("<option>") );
	  var team = $('#team').val();
	 
	  $.get( "/users/team?team="+team, function( data ) {
	  console.log(data);
		 data.forEach(function(user) {
			 $('#assignee').append($("<option>",{text:user,value:user}));
		 });
	  });
	  
	});

	$("input,textarea").keyup(function(e){
        e.preventDefault();
        if($(e.target).hasClass("must-field-error")) {
            showInput(e.target);
        }
    });

    $("select").on('change', function(e) {
        e.preventDefault();
        if($(e.target).hasClass("must-field-error")) {
            showInput(e.target);
        }
    });

});

function showInput(e) {
   console.log(e);
   $(e).removeClass("must-field-error");
}

function formSubmit(e) {
    var val = false;
	if($('#zone').val() == ''){
//		alert("Please Select Zone");
//		return;
        $('#zone').addClass("must-field-error");
        val = true;
	} 
	if($('#clientName').val() == ''){
//		alert("Please Enter Client Name");
//		return;
        $('#clientName').addClass("must-field-error");
        val = true;
	}
	if($('#name').val() == '') {
//		alert("Please Enter Campaign Name");
//		return;
        $('#name').addClass("must-field-error");
        val = true;
	}
	if($('#brandName').val() == '') {
//        alert("Please Enter Brand Name");
//        return;
        $('#brandName').addClass("must-field-error");
        val = true;
    }
	if($('#task').val() == ''){
//		alert("Please Select Task Type");
//		return;
        $('#task').addClass("must-field-error");
        val = true;
	}
	if($('#team').val() == ''){
//		alert("Please Select Team");
//		return;
        $('#team').addClass("must-field-error");
        val = true;
	}
	if($('#status').val() == ''){
//		alert("Please Select Status");
//		return;
        $('#status').addClass("must-field-error");
        val = true;
	}
	if($('#priority').val() == ''){
//		alert("Please Select Priority");
//		return;
        $('#priority').addClass("must-field-error");
        val = true;
	}
	if($('#description').val() == ''){
//		alert("Please Provide Description");
//		return;
        $('#description').addClass("must-field-error");
        val = true;
	}

	if (val === true) {
	    alert("Please Enter Missing Fields");
	    return;
	}
	
	var data = {};
	data.zone = $('#zone').val();
	data.clientName = $('#clientName').val();
	data.name = $('#name').val();
	data.dueDate = Date.parse( $('#dueDate').val() ) ;
	data.status = $('#status').val();
	data.priority = $('#priority').val();
	data.team = $('#team').val();
	data.task = $('#task').val();
	data.description = $('#description').val();
	data.brandName = $('#brandName').val();
	data.userId = $('#assignee').val() || null;
	
	$.ajax({
	  type: "POST",
	  url: "/campaign",
	  data:  JSON.stringify(data),
	  dataType: "json",
	  contentType: "application/json"
	})
	.done(function(data) {
	   $('#clientName').val("");
	   $('#name').val("");
	   $('#description').val("");
       alert("Ticket Generated Successfully");
    }).fail(function(data) {
    	alert("Error In Creating Ticket");
    });
	
}