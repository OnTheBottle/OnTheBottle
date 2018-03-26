function comment() {

    commenting();
    var user = JSON.parse(localStorage.getItem("userOnBoard"));
    var userId = user.userId;
    var poster = JSON.parse(localStorage.getItem("postOnBoard"));
    var postId = poster.postId;
    var textComment = JSON.parse(localStorage.getItem("TextComment"));
    var text = textComment.text;
    if (text.length != 0) {
        var comment = {"comment": text, "post_id": postId, "user_id": userId};
        $.ajax({
            method: "POST",
            url: "/сomment",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(comment),
            success: function (message) {
                if (message.status == "Done") {
                    // showComments(postId);
                    showComments();
                }
            },
            error: function (xhr, status, error) {
                alert("нихрена");
            }
        });
    }
    else alert("please comment");
}