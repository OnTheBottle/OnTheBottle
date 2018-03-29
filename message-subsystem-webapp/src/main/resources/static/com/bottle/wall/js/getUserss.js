function getUserss() {

    $.ajax({
        method: "POST",
        url: "/getUserss", //TODO
        dataType:'json',
        async: true,
        contentType: 'application/json',
        success: function(select) {
            if(select.status == "Done") {
                var selectoptions = select.data;

var i=0;
                var selectlist = $('<select id="user1" onchange="imgUser(),getPosts()"/>');
                selectoptions.forEach(function (value) {

                    $('<option/>', {text: value.name}).appendTo(selectlist);
                    const user = { name: value.name, id: value.id };
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