$(function (){
	$('table').DataTable( {
		"scrollY": "345px",
		"scrollCollapse": true,
		"destroy": true,
		"dom": '<"toolbar">frtip',
		"pageLength": 100
	});
	
	
	$('table').on('click', '.clickable-row', function(event) {
		
		console.log($(this).attr('id'));
		console.log($(this).data("href"));
		
		var tempId = $(this).attr('id');
		var tempUrl = $(this).data("href");
		
		
		if($(this).hasClass('confirm')) {
			
			$.confirm({
			    title: 'Confirm!',
			    content: '',
			    buttons: {
			        confirm: {
			        	action : function () {
			        		window.location = '/console/assign/'+tempId;
			        	},
			        	btnClass : 'btn-success',
			        	text : 'Assign to me'
			            
			        },
			        open: {
			        	action : function () {
			        		window.location = tempUrl;
			        	},
			        	btnClass : 'btn-info'
			        	
			            
			        },
			        close : {
			        	action : function () {
			        		
			        	},
			        	btnClass : 'btn-default'
			        }
			    },
			    backgroundDismissAnimation : 'glow'
			});
			
		} else {
			
			
			$.confirm({
			    title: 'Confirm!',
			    content: '',
			    buttons: {
			        open: {
			        	action : function () {
			        		window.location = tempUrl;
			        	},
			        	btnClass : 'btn-info'
			        	
			            
			        },
			        close : {
			        	action : function () {
			        		
			        	},
			        	btnClass : 'btn-default'
			        }
			    }
			});
		}
	});
});