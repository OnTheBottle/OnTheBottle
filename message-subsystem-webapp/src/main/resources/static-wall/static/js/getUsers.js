function getUsers() {

    $.ajax({
        method: "POST",
        url: "/getUsers",
        dataType:'json',
        async: true,
        contentType: 'application/json',
        success: function(select) {
            if(select.status == "Done") {
                var selectoptions = select.data;

var i=0;
                var selectlist = $('<select id="user1" onchange="imgUser(),getPosts()"/>');
                selectoptions.forEach(function (value) {

                    $('<option/>', {text: value.username}).appendTo(selectlist);
                    const user = { name: value.username, id: value.id };
                    var item = user.name;
                    localStorage.setItem(item, JSON.stringify(user));
                });

                $('#user').append(selectlist);
             }
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
};