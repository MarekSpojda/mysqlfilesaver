document.addEventListener("DOMContentLoaded", function () {
    let divforfiles = $("#userfiles");
    
    //Run endpoint without redirect, call java method and do nothing in case of success
    $('#saveform').submit(function(e){
      e.preventDefault();
      $.ajax({
        url:'/savefile/',
        type:'post',
        data:$('#saveform').serialize(),
        success:function(){
        }
      });
    });

    $("#showuserfiles").on("click", function (event) {
        event.preventDefault();
        $.ajax({
            type: 'GET',
            url: '/userfiles',
            contentType: 'application/json',
            success: function (data) {
                divforfiles.html(data);
            }
        });
    });
    
    divforfiles.on("click", "button", function (event) {
        console.log("There should be ID here:("+$(this).attr("id"));
    });
});