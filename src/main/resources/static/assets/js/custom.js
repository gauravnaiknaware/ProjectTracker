
 var start_date = moment();
 var end_date = moment();
 
 var accountId = '',
 	 accountLabel = '',
 	 campaignId = '',
 	 campaignLabel = '',
 	 adid = '',
 	 adLabel = '';
 
 localStorage.table = "table-account";

 var accountUrl = function(){ 
	 	return "account/getAccounts?startDate="+start_date.format('YYYY-MM-DD')+"&endDate="+end_date.format('YYYY-MM-DD');
	 },
 	 campaignUrl = function(){ 
		 return "campaign/getCampaignByAccount?accountId="+accountId+"&startDate="+start_date.format('YYYY-MM-DD')+"&endDate="+end_date.format('YYYY-MM-DD');
	 },
 	 adUrl = function(){ 
		 return "ad/getAdByCampaign?campaignId="+campaignId+"&startDate="+start_date.format('YYYY-MM-DD')+"&endDate="+end_date.format('YYYY-MM-DD');
	 },
	 adDetailsUrl = function(){
		 return "ad/getAdDetailsByAdId?adid="+adid	;
	 };

 localStorage.url = 'account';

 var exportAccount, exportCampaign, exportAd , exportAdDetails;
 
 $(function (){
 	appJs();
 	load();
 });


 var appJs = function(){
    $('#app').append( $('<div>',{ class:'container container-table'}) );
     	
    $('.container-table').append( 
    	'<div class="row">\
    		<div class="col-lg-12">\
    			<div class="panel panel-default panel-shadow">\
    				<div class="panel-heading">\
    					<div class="panel-title">\
    						<ul class="list-inline links-list">\
    							<li>\
    								<h4 class="btn btn-account">All Accounts<span></span></h4>\
    							</li>\
    							<li class="i-campaign">\
    								<i class="glyphicon glyphicon-chevron-right"></i>\
    							</li>\
    							<li>\
    								<h4 class="btn btn-campaign">Campaign<span></span></h4>\
    							</li>\
    							<li class="i-ad">\
    								<i class="glyphicon glyphicon-chevron-right"></i>\
    							</li>\
    							<li>\
    								<h4 class="btn btn-ad">Ad<span></span></h4>\
    							</li>\
					    		<li class="i-adDetails">\
									<i class="glyphicon glyphicon-chevron-right"></i>\
								</li>\
								<li>\
									<h4 class="btn btn-adDetails">Ad Details</h4>\
								</li>\
    							<li class="pull-right">\
    								<div class="date-range" class="pull-right">\
    							    <i class="glyphicon glyphicon-calendar"></i>&nbsp;\
    							    <span></span>\
    							</div>\
    							</li>\
    						</ul>\
    					</div>\
    				</div>\
    				<div class="panel-body">\
    					<div class="table-account"></div>\
    					<div class="table-campaign"></div>\
    					<div class="table-ad"></div>\
    					<div class="table-adDetails"></div>\
    				</div>\
    			</div>\
    		</div>\
    	</div>' );
    $('.btn-account,.btn-campaign,.btn-ad').on('click', function(event) {
 		switch($(this).attr('class')){
	 		case 'btn btn-account' :
	 			switch(localStorage.url){
			 		case 'account': 	 			
			 			
			 			break;
			 		case 'campaign': case 'ad': case 'adDetails': 
			 			localStorage.url = 'account';
			 			localStorage.table = "table-account";
			 			
			 			$('.table-account').show();
			 			$('.table-campaign').hide();
			 			$('.table-ad').hide();
			 			$('.table-adDetails').hide();
			 			
			 			$('li.i-campaign').css('visibility','hidden');
			 			$('h4.btn-campaign').css('visibility','hidden');
			 			
			 			$('li.i-ad').css('visibility','hidden');
			 			$('h4.btn-ad').css('visibility','hidden');
			 			
			 			$('li.i-adDetails').css('visibility','hidden');
			 			$('h4.btn-adDetails').css('visibility','hidden');
			 			
			 			$('h4.btn-account span').html('');
			 			break;
			 	}
	 			break;
	 		case 'btn btn-campaign' : 
	 			switch(localStorage.url){
			 		case 'account':  case 'campaign': 
			 			
			 			break;
			 		case 'ad': case 'adDetails':
			 			localStorage.url = 'campaign';
			 			localStorage.table = "table-campaign";
			 			
			 			$('.table-campaign').show();
			 			$('.table-ad').hide();
			 			$('.table-adDetails').hide();
			 			
			 			$('li.i-campaign').css('visibility','visible');
			 			$('h4.btn-campaign').css('visibility','visible');
			 			
			 			$('li.i-ad').css('visibility','hidden');
			 			$('h4.btn-ad').css('visibility','hidden');
			 			
			 			$('li.i-adDetails').css('visibility','hidden');
			 			$('h4.btn-adDetails').css('visibility','hidden');
			 			
			 			$('h4.btn-campaign span').html('');
			 			break;
	 			}
	 			break;
	 		case 'btn btn-ad' :
	 			console.log(localStorage.url);
	 			switch(localStorage.url){
	 			  
			 		case 'account': case 'campaign': case 'ad':			
			 			
			 			break;
			 		case 'adDetails':
			 			localStorage.url = 'ad';
			 			localStorage.table = "table-ad";
			 			
			 			$('.table-ad').show();
			 			$('.table-adDetails').hide();
			 			
			 			$('li.i-ad').css('visibility','visible');
			 			$('h4.btn-ad').css('visibility','visible');
			 			
			 			
			 			$('li.i-adDetails').css('visibility','hidden');
			 			$('h4.btn-adDetails').css('visibility','hidden');
			 			
			 			$('h4.btn-ad span').html('');
			 			break;
	 			}
	 		}
 	});
};


var load = function(){
 	$('.date-range').daterangepicker({
        startDate: start_date,
        endDate: end_date,
        ranges: {
           'Today': [moment(), moment()],
           'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
           'Last 7 Days': [moment().subtract(6, 'days'), moment()],
           'Last 30 Days': [moment().subtract(29, 'days'), moment()],
           'This Month': [moment().startOf('month'), moment().endOf('month')],
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        }
    }, cb);

    cb(start_date, end_date);
 };

 var cb = function(start,end){ 
 	start_date = start;
 	end_date = end;
 	
 	
 	$('.date-range span').html(start.format('YYYY-MM-DD') + ' - ' + end.format('YYYY-MM-DD'));
 	switch(localStorage.url){
 		case 'account': 
 			fetchRecord(accountUrl(),localStorage.table);
 			break;
 		case 'campaign': 	
 			fetchRecord(campaignUrl(),localStorage.table);
 			break;
 		case 'ad':  
 			fetchRecord(adUrl(),localStorage.table);
 			break;
 		case 'adDetails':
 			fetchRecord(adDetailsUrl(),localStorage.table);
 			break;
 	}
 };

 var getName = function(obj){
	 var name;
	 jQuery.map(Object.keys(obj), function(val, i) {
		  if (val.indexOf("_name") != -1) {
		    name = obj[val];
		  }
	 });
	 return name;
 };
 
 var putThousandsSeparators = function(value){
  if(typeof value != 'number'){
	  return value;
  }
  
  var sep = ',';
  // check if it needs formatting
  if (value.toString() === value.toLocaleString()) {
    // split decimals
    var parts = value.toString().split('.')
    // format whole numbers
    parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, sep);
    // put them back together
    value = parts[1] ? parts.join('.') : parts[0];
  } else {
    value = value.toLocaleString();
  }
  return value; 
 };
 
 var fetchRecord = function(url,table) {
 	$.LoadingOverlay("show");
 	$('.'+table).empty();

 	$('.'+table).append(
 		'<table class="table">\
    		<thead></thead>\
 			<tfoot></tfoot>\
    		<tbody></tbody>\
    	</table>');
 	
// 	switch(table){
// 		case 'table-account':
// 			break;
// 		case 'table-campaign':
// 			break;
// 		case 'table-ad':
// 			break;
// 		case 'table-adDetails':
// 			break;
// 	}
 	
 	$.get( url , function( data ) {
 		
 		if(jQuery.isEmptyObject(data)){
 			$.LoadingOverlay("hide");
 			alert("No Data");
 			return;
 		}
 		
 		setExportData(localStorage.url,data);
 		
 		var tr = $('<tr>');
 		/*tfoot tr*/
 		var tr1 = $('<tr>');
        $.each( data[0], function(key,value){
        	if(key != 'id'){
            	tr.append( $('<th>',{text:key.split("_").join(" ").replace('percentage','%')}) );
            	tr1.append( $('<th>') );
        	}
        });
        $('.'+table+' thead').append(tr);
        /*appending tr to tfoot*/
        $('.'+table+' tfoot').append(tr1);
        
        $.each( data, function(key,value){
        	
        	var tr = $('<tr>',{class:'clickable-row',id:data[key].id,value:getName(data[key])});
        	
        	$.each( data[key], function(key,value){
        		if(key != 'id'){
        			tr.append( $('<td>',{text:putThousandsSeparators(value)}) );
        		}
            });
            $('.'+table+' tbody').append(tr);
        });
  		
        
        switch(table){
        	case 'table-account':
        		$('.'+table+' table').DataTable( {
            		"scrollX" : true,
    		    	"scrollY": "345px",
    		    	"scrollCollapse": true,
    		    	"destroy": true,
    		    	"dom": '<"toolbar">frtip',
    		    	"order": [[ 3, "desc" ]],
    		    	"pageLength": 100,
    		    	"drawCallback": function ( row, data, start, end, display ) {
    		            var api = this.api(), data;
    		 
    		            // Remove the formatting to get integer data for summation
    		            var intVal = function ( i ) {
    		                return typeof i === 'string' ?
    		                    i.replace(/[\$,]/g, '')*1 :
    		                    typeof i === 'number' ?
    		                        i : 0;
    		            };
    		 
    		            // Total over all pages
    		            total1 = api
    		                .column( 1 )
    		                .data()
    		                .reduce( function (a, b) {
    		                    return intVal(a) + intVal(b);
    		                }, 0 );
    		            
    		            total2 = api
		                .column( 2 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
    		            
    		            total3 = api
		                .column( 3 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
    		            
    		            total4 = api
		                .column( 4 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
    		            
    		            total5 = api
		                .column( 5 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
    		            
    		            total7 = api
		                .column( 7 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
    		            
    		            total9 = api
		                .column( 9 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );    		          
    		 
    		            // Update footer
    		            $( api.column( 0 ).footer() ).html('Total :');
    		            $( api.column( 1 ).footer() ).html(' '+ putThousandsSeparators(total1) +'');
    		            $( api.column( 2 ).footer() ).html(' '+ putThousandsSeparators(total2) +'');
    		            $( api.column( 3 ).footer() ).html(' '+ putThousandsSeparators(total3) +'');
    		            $( api.column( 4 ).footer() ).html(' '+ putThousandsSeparators(total4) +'');
    		            $( api.column( 5 ).footer() ).html(' '+ putThousandsSeparators(total5) +'');
    		            $( api.column( 7 ).footer() ).html(' '+ putThousandsSeparators(total7) +'');
    		            $( api.column( 9 ).footer() ).html(' '+ putThousandsSeparators(total9) +'');
    		      
    		        }
    		    });
        		break;
        	case 'table-campaign':
        		$('.'+table+' table').DataTable( {
            		//"scrollX" : true,
    		    	"scrollY": "345px",
    		    	"scrollCollapse": true,
    		    	"destroy": true,
    		    	"dom": '<"toolbar">frtip',
    		    	"order": [[ 2, "desc" ]],
    		    	"pageLength": 100,
    		    	"drawCallback": function ( row, data, start, end, display ) {
    		            var api = this.api(), data;
    		 
    		            // Remove the formatting to get integer data for summation
    		            var intVal = function ( i ) {
    		                return typeof i === 'string' ?
    		                    i.replace(/[\$,]/g, '')*1 :
    		                    typeof i === 'number' ?
    		                        i : 0;
    		            };
    		 
    		            // Total over all pages
    		            total1 = api
    		                .column( 1 )
    		                .data()
    		                .reduce( function (a, b) {
    		                    return intVal(a) + intVal(b);
    		                }, 0 );
    		            
    		            total2 = api
		                .column( 2 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
    		            
    		            total3 = api
		                .column( 3 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
    		            
    		            total4 = api
		                .column( 4 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
    		            
    		            total6 = api
		                .column( 6 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
    		            
    		            total8 = api
		                .column( 8 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
    		               		          
    		 
    		            // Update footer
    		            $( api.column( 0 ).footer() ).html('Total :');
    		            $( api.column( 1 ).footer() ).html(' '+ putThousandsSeparators(total1) +'');
    		            $( api.column( 2 ).footer() ).html(' '+ putThousandsSeparators(total2) +'');
    		            $( api.column( 3 ).footer() ).html(' '+ putThousandsSeparators(total3) +'');
    		            $( api.column( 4 ).footer() ).html(' '+ putThousandsSeparators(total4) +'');
    		            $( api.column( 6 ).footer() ).html(' '+ putThousandsSeparators(total6) +'');
    		            $( api.column( 8 ).footer() ).html(' '+ putThousandsSeparators(total8) +'');    		      
    		        }
    		    	
    		    });
        		break;
        	case 'table-ad':
        		$('.'+table+' table').DataTable( {
            		"scrollX" : true,
    		    	"scrollY": "345px",
    		    	"scrollCollapse": true,
    		    	"destroy": true,
    		    	"dom": '<"toolbar">frtip',
    		    	"order": [[ 6, "desc" ]],
    		    	"pageLength": 100,
    		    	"drawCallback": function ( row, data, start, end, display ) {
    		            var api = this.api(), data;
    		 
    		            // Remove the formatting to get integer data for summation
    		            var intVal = function ( i ) {
    		                return typeof i === 'string' ?
    		                    i.replace(/[\$,]/g, '')*1 :
    		                    typeof i === 'number' ?
    		                        i : 0;
    		            };
    		 
    		            // Total over all pages
    		     
    		            
    		            total6 = api
		                .column( 6 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
    		            
    		            total7 = api
		                .column( 7 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
    		            
    		            total8 = api
		                .column( 8 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
    		            
    		            total10 = api
		                .column( 10 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 ); 
    		            
    		            total12 = api
		                .column( 12 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
    		 
    		            // Update footer
    		            $( api.column( 0 ).footer() ).html('Total :');
    		            $( api.column( 6 ).footer() ).html(' '+ putThousandsSeparators(total6) +'');
    		            $( api.column( 7 ).footer() ).html(' '+ putThousandsSeparators(total7) +'');
    		            $( api.column( 8 ).footer() ).html(' '+ putThousandsSeparators(total8) +'');
    		            $( api.column( 10 ).footer() ).html(' '+ putThousandsSeparators(total10) +'');
    		            $( api.column( 12 ).footer() ).html(' '+ putThousandsSeparators(total12) +'');
    		      
    		        }
    		    });
        		break;
        	case 'table-adDetails':
        		$('.'+table+' table').DataTable( {
            		//"scrollX" : true,
    		    	"scrollY": "345px",
    		    	"scrollCollapse": true,
    		    	"destroy": true,
    		    	"dom": '<"toolbar">frtip',
    		    	"pageLength": 100
    		    });
        		break;
        }
        
  		$('.'+table+" .toolbar").append( $('<button>',{class:'btn-default pull-right export-'+localStorage.url,text:'export',onclick:'exportFile("'+table+'")'}) );
  		$.LoadingOverlay("hide");
 	});

 	$('.'+table+' table').on('dblclick', '.clickable-row', function(event) {
        if($(this).hasClass('active')) {
           
        	switch(localStorage.url){
        		case 'account' :
        			localStorage.url = 'campaign';
        			$('li.i-campaign').css('visibility','visible');
		 			$('h4.btn-campaign').css('visibility','visible');
        			$('.table-account').hide();
        			$('.table-campaign').show();
        			$('h4.btn-account span').html(':'+accountLabel.substr(0,10)+'...');
        			
        			break;
        		case 'campaign' :
        			localStorage.url = 'ad';
        			$('li.i-ad').css('visibility','visible');
		 			$('h4.btn-ad').css('visibility','visible');
        			$('.table-campaign').hide();
        			$('.table-ad').show();
        			$('h4.btn-campaign span').html(':'+campaignLabel.substr(0,10)+'...');
        			
        			break;
        		case 'ad' :
        			localStorage.url = 'adDetails';
        			$('li.i-adDetails').css('visibility','visible');
		 			$('h4.btn-adDetails').css('visibility','visible');
		 			$('.table-ad').hide();
        			$('.table-adDetails').show();
        			$('h4.btn-ad span').html(':'+adLabel.substr(0,10)+'...');
        			
        			break;
        		case 'adDetails':
        			break;
        	}
        } else {
        	$(this).addClass('active').siblings().removeClass('active'); 
        	
     		switch(localStorage.url){
		 		case 'account':
		 			accountId = $(this).attr('id');
		 			accountLabel = $(this).attr('value');
		 			localStorage.url = 'campaign';
		 			localStorage.table = "table-campaign";
		 			$('.table-account').hide();
		 			$('.table-campaign').show();
		 			$('li.i-campaign').css('visibility','visible');
		 			$('h4.btn-campaign').css('visibility','visible');
		 			$('h4.btn-account span').html(':'+accountLabel.substr(0,10)+'...');
		 			
		 			fetchRecord(campaignUrl(),localStorage.table);
		 			break;
		 		case 'campaign': 
		 			campaignId = $(this).attr('id');
		 			campaignLabel = $(this).attr('value');
		 			localStorage.url = 'ad';
		 			localStorage.table = "table-ad";
		 			$('.table-campaign').hide();
		 			$('.table-ad').show();
		 			$('li.i-ad').css('visibility','visible');
		 			$('h4.btn-ad').css('visibility','visible');
		 			$('h4.btn-campaign span').html(':'+campaignLabel.substr(0,10)+'...');
		 			
		 			fetchRecord(adUrl(),localStorage.table);
		 			break;
		 		case 'ad': 
		 			adid =  $(this).attr('id');
		 			adLabel = $(this).attr('value');
		 			localStorage.url = 'adDetails';
		 			localStorage.table = "table-adDetails";
		 			$('.table-ad').hide();
		 			$('.table-adDetails').show();
		 			$('li.i-adDetails').css('visibility','visible');
		 			$('h4.btn-adDetails').css('visibility','visible');
		 			$('h4.btn-ad span').html(':'+adLabel.substr(0,10)+'...');
		 			
		 			fetchRecord(adDetailsUrl(),localStorage.table);
		 			break;
		 		case 'adDetails':
		 			break;
		 	}
        	
        }
    });
    
 };

 var setExportData = function(url,data){
	 switch (url){
	 	case 'account':
	 		exportAccount = data;
	 		break;
	 	case 'campaign':
	 		exportCampaign = data;
	 		break;
	 	case 'ad':
	 		exportAd = data;
	 		break;
	 	case 'adDetails':
	 		exportAdDetails = data;
	 }
 };
 
 var exportFile = function (table){
	 switch(table){
	 	case 'table-account':
	 		JSONToCSVConvertor(exportAccount, "All Accounts - "+start_date.format('MMMM D YYYY')+" - "+end_date.format('MMMM D YYYY'), true);
	 		break;
	 	case 'table-campaign':
	 		JSONToCSVConvertor(exportCampaign, ""+accountLabel+" - "+start_date.format('MMMM D YYYY')+" - "+end_date.format('MMMM D YYYY'), true);
	 		break;
	 	case 'table-ad':
	 		JSONToCSVConvertor(exportAd, ""+campaignLabel+" - "+start_date.format('MMMM D YYYY')+" - "+end_date.format('MMMM D YYYY'), true);
	 		break;
	 	case 'table-adDetails':
	 		JSONToCSVConvertor(exportAdDetails, ""+adLabel+" - "+start_date.format('MMMM D YYYY')+" - "+end_date.format('MMMM D YYYY'), true);
	 		break;
	 }
 };
