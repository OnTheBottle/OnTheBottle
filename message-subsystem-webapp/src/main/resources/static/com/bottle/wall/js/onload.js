function  onload() {
    var data = JSON.parse(localStorage.getItem("Post data"));
    var userId = data.userId;
    var postId=data.postId;
    $.ajax({
        method: "POST",
        url: "/getPost",
        dataType: 'json',
        async: true,
        contentType: 'application/json',
        data: JSON.stringify(postId),
        success: function (posted) {
            if (posted.status == "Done") {
                var onePost=posted.data;
                var author = onePost.user;
                var images = onePost.images;
                var id = author.id;
                var name = author.name;
                if(author.imageUser){
                var imageUser = author.imageUser;
                var userImage = imageUser.path;
                var img = $('<img />', {
                    src: userImage,
                });
                $('.userImage').append(img);
            }

                $('.posting').attr("id",postId);
                var text = "post by " + name + "<br>" + onePost.date + "<br>" + onePost.title;
                $('.dateTitle').html(text);

                if (id == userId) {
                    $('.menuPost').append('<button class="deletePost" title="delete post" onclick="deletepostfromwindow()"/>');
                }

                var textPost = onePost.text;
                $('.text').html(textPost);

                images.forEach(function (value) {
                    var image = value.path;
                    var imid = value.id;
                    $('.image').attr("id",imid);
                    var myImage = new Image();
                    myImage.src = image;
                    $('.image').append(myImage);

                });

                var script = document.createElement('script');
                script.src = "js/jquery-1.11.1.js";
                document.documentElement.appendChild(script);
                var script = document.createElement('script');
                script.src = "js/getPosts.js";
                document.documentElement.appendChild(script);
                var script = document.createElement('script');
                script.src = "js/addComment.js";
                document.documentElement.appendChild(script);
                var script = document.createElement('script');
                script.src = "js/showComments.js";
                document.documentElement.appendChild(script);
                showComments();
            }
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
}


