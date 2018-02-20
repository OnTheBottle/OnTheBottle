var btn = document.getElementById("all");
btn.onclick=function show() {
    $('content').empty();
    $.ajax({
        method: "POST",
        url: "/show",
        dataType:'json',
        async: true,
        contentType: "charset=UTF-8",
        success: function(posts) {

            var post = posts["posts"].replace("[", "").replace("]", "").split(";");

            alert(posts);

            var buttondelete = $('<button/>',
                { text: 'Delete',
                    click: function deleteAll() {

                        $.ajax({
                            method: "POST",
                            url: "/deleteAll",
                            dataType: 'json',
                            async: true,
                            contentType: "charset=UTF-8",
                            success: function (data) {
                                alert(data);
                                error: function a (xhr, status, error) {
                                    alert(error);
                                }
                            }
                        });
                      }});

            var postlist = $('<ul/>');
                $.each(post, function() {
                $('<li/>',{text:this}).appendTo(postlist);
                                    });
            $('.content').append(postlist).append(buttondelete);
          },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
};
