function showLast3() {
     $.ajax({
        method: "POST",
        url: "/showLast3",
        dataType:'json',
        async: true,
        contentType: "charset=UTF-8",
        success: function(posts) {
            var post = posts["posts"].replace("[", "").replace("]", "").split(";");

            alert(posts);
            $.each(post, function addDynamicExtraField() {
                            var div = $('<div/>', {
                            'class' : 'DynamicExtraField'
                        }).appendTo($('#DynamicExtraFieldsContainer'));
                        var br = $('<br/>').appendTo(div);
                        var label = $('<label/>').html("Post").appendTo(div);
                        var input = $('<input/>', {
                            value : 'Удаление',
                            type : 'button',
                            'class' : 'DeleteDynamicExtraField'
                        }).appendTo(div);
                        input.click(function() {
                            $(this).parent().remove();
                        });
                        var br = $('<br/>').appendTo(div);
                        var text = $('<text/>',{text:this}, {
                            name : 'DynamicExtraField[]'
                        }).appendTo(div);
                    })
        },
        error: function (xhr, status, error) {
             alert(error);
         }
     });
}
