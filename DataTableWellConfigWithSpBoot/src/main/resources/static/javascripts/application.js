
// for Flash showing 
function showMsg(type, icon, title, msg){
    $.growl(false, {
        allow_dismiss: true,
        animate: {
            enter: 'animated bounceIn',
            exit: 'animated bounceOut'
        },
        delay:1000,
        offset: 5,
        command: 'closeAll'
    });
    $.growl({
            icon: icon,
            title:title ,
            message: msg
        },
        {
            type:type,
            placement:{
                from:'top',
                align:'right'
            }
        });
}

function showSuccessMsg(msg){
    showMsg("success","glyphicon glyphicon-ok","<strong> Success</strong><br/>", msg);
}
function showErrorMsg(msg){
    showMsg("danger","glyphicon glyphicon-remove", "<strong> Error</strong><br/>", msg);
}
function showInfoMsg(msg){
    showMsg("info","glyphicon glyphicon-info-sign","<strong> Info</strong><br/>", msg);
}

// End for Flash showing 



$(document).ready(function(){
//	alert("hleeo");
});

