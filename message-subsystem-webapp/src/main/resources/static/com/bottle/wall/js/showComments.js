function showComments() {
    $('.commentList').empty();
    var poster = JSON.parse(localStorage.getItem("postOnBoard"));
    var postId = poster.postId;
    $.ajax({
        method: "POST",
        url: "/showComments",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(postId),
        success: function (comments) {
            if (comments.status == "Done") {
                var arrayComments = comments.data;
                arrayComments.forEach(function (value) {

                    var user = value.user;
                    var commentid = value.id;
                    var text = "comment by " + user.username + "<br>" + value.date + "<br>" + value.text;
                    var napolnenie = $('<div />', {
                        id: commentid,
                        class: "commentModul"
                    }).html(text).appendTo('.commentList');
                    var buttons=$('<div class="buttons"/>').appendTo(napolnenie);
                    if (localStorage.getItem("True")) {
                    buttons.append($('<button class="deleteComment" title="delete comment" onclick="deleteComment(this)"/>'));
                    }

                });
            }
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
}

function deleteComment(a) {
    var id = a.parentNode.parentNode.id;
    $('#' + id).remove();
    $.ajax({
        method: "POST",
        url: "/deleteComment",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(id),
        success: function (message) {
            if (message.status == "Done") {
                 showComments();
            }
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
}

