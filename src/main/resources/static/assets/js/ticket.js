$(function (){
	$('table').DataTable( {
		//"scrollX" : true,
		"scrollY": "345px",
		"scrollCollapse": true,
		"destroy": true,
		"dom": '<"toolbar">frtip',
		"pageLength": 100
	});
	
	$('table').on('click', '.clickable-row', function(event) {
		var tempUrl = $(this).data('href');
		var stateUrl = $(this).data('state');
		if($(this).hasClass('stop')) {
			$.confirm({
			    title: 'Confirm!',
			    content: '',
			    buttons: {
			    	start : {
			    		action : function () {
			    			window.location = stateUrl;
			    		},
			    		btnClass : 'btn-success'
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
			    	stop : {
			    		action : function () {
			    			window.location = stateUrl;
			    		},
			    		btnClass : 'btn-success'
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
		}
		
		
		
	});
});