function imgUser() {
    $('#img').empty();
    var users = document.getElementById("user1").value;
    var user= JSON.parse(localStorage.getItem(users));
    var userId=user.id;
       $.ajax({
        method: "POST",
        url: "/getImage",
        dataType:'json',
        contentType: 'application/json',
        data:JSON.stringify(userId),
        success: function(image) {
            if(image.status == "Done") {
                if(image.data){
                var imageU = image.data;
                var img = $('<img />', {

                src: imageU.path
                });
                img.appendTo($('#img'));
            }}
                },
        error: function (xhr, status, error) {
        alert(error);
    }
});
};

