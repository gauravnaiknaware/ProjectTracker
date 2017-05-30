$(document).ready(function() {
	$('#due_date').daterangepicker({
	  singleDatePicker: true,
	  timePicker: true,
	  opens:"left",
	  locale:{
		  format: 'YYYY-MM-DD hh:mm:ss'
	  }
	});

	$("input,textarea").keyup(function(e) {
	    e.preventDefault();
	     if (e.target.id == 'comment') {
            return;
         }
        showUpdateBtn();
    });

    $("select").on('change', function(e) {
        e.preventDefault();
        if (e.target.id == 'status') {
            showUpdateStatusBtn();
        }
        if (e.target.id == 'team') {
            showAssignBtn();
        }
        showUpdateBtn();
    });
});

function showUpdateBtn() {
    $(".updateBtn").css("display","block");
}
function showAssignBtn() {
    $(".assignBtn").css("display","block");
}
function showUpdateStatusBtn() {
    $(".updateStatusBtn").css("display","block");
}

function ticketUpdate(id,userId){
        var data = {};
        data.zone = $('#zone').val();
        data.clientName = $('#clientName').val();
        data.name = $('#name').val();
        data.dueDate = Date.parse( $('#dueDate').text() ) ;
        data.status = $('#status').val();
        data.priority = $('#priority').val();
        data.team = $('#team').val();
        data.task = $('#task').val();
        data.description = $('#description').val();
        data.buildEntry = $('#buildEntry').val();
        data.id = id;
        data.date = $('#date').text();
        data.userId = userId;
        data.state = $('#state').text();

        console.log(data);
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

function updateStatus(id) {
    var status = $('#status').val();
    $.get( document.location.origin+"/campaign/update/"+id+"?key=status&value="+status)
    .done(function(e) {
        console.log("Successfully update");
    })
    .fail(function(e) {
        console.log("Something went wrong try again later");
    });

}

function assignTo(id) {
    var team = $('#team').val();
    window.location = "/console/assign/"+id+"/"+team;
}