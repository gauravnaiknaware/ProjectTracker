$(function() {
    timespent();
});

function getFormatedDate(d) {
    var dt = new Date(d);
    var localDate = dt.getFullYear()+"-"+getMLength(dt.getMonth()+1)+"-"+dt.getDate();
    var localTime = dt.toLocaleTimeString();
    var formatedDate = localDate+"T"+localTime;
    return formatedDate;
}

function getMLength(m) {
    console.log(m.toString().length)
    if(m.toString().length < 2) {
        return "0"+m;
    } else {
        return m;
    }
}

function timespent(evt) {

    var evt = [];
        $.get( "/time/campaign/"+$("#campaignId").attr("class"), function( data ) {
            for ( i in data ) {
                var st = getFormatedDate(data[i].startTime);
                var et = '';
                if(data[i].endTime != null) {
                    et = getFormatedDate(data[i].endTime);
                }
                evt.push({
                    title: data[i].user.name,
                    start: st,
                    end: et === '' ? null : et
                });
            }
            $('#timespent').fullCalendar({
                    header: {
                        left: 'prev,next today',
                        center: 'title',
                        right: 'month,agendaWeek,agendaDay,listWeek'
                    },
                    //editable: false,
                    //eventLimit: false, // allow "more" link when too many events
                    navLinks: true,
                    //disableDragging:false,
                    //eventStartEditable:false,
                    //draggable:false,
                    //disableResizing:true,
                    droppable:false,
                    events: evt
                });
        });


}