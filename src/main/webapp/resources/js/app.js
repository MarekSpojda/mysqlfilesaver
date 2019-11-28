document.addEventListener("DOMContentLoaded", function () {
    let divforfiles = $("#userfiles");
    
    $('#go2').click(function(event){
      event.preventDefault();
      console.log("Hello, I'm here just for fun for the moment:)");
    }); 
    
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

    $("#go2").on("click", function () {
        $.ajax({
            type: 'GET',
            url: '/userfiles',
            contentType: 'application/json',
            success: function (data) {
                divforfiles.html(data);
            }
        });
    });
});