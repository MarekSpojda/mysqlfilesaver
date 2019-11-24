document.addEventListener("DOMContentLoaded", function () {
    
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
});
