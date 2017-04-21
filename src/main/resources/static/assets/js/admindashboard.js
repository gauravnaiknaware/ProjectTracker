$(function (){
	$('table').DataTable( {
		"scrollX" : true,
		"scrollY": "345px",
		"scrollCollapse": true,
		"destroy": true,
		"dom": '<"toolbar">frtip',
		"pageLength": 100
	});
	
	$('table').on('dblclick', '.clickable-row', function(event) {
		 window.location = $(this).data("href");
		 
		 /*$("#open-model").click();
		 $.get( "/campaign"+"/"+id, function(data) {
			console.log(data);
			$('#zone').html(data.zone);
	 		$('#client_name').html(data.client_name);
	  		$('#name').html(data.name);
	  		$('#due_date').html(moment(data.due_date).format("YYYY-MM-DD hh:mm:ss"));
	  		$('#status').html(data.status);
	   		$('#priority').html(data.priority);
	  		$('#team').html(data.team);
	  		$('#task').html(data.task);
		 })
		 .fail(function() {
			console.log( "error" );
		 });
		 */
		 
		 
		 
		 
	});
});