function getPosts() {
    $('#userPosts').empty();
    var creator = document.getElementById("user1").value;
    var user = JSON.parse(localStorage.getItem(creator));
    var userId = user.id;
    var author = user.name;

    function uuidv4() {
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
            var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
            return v.toString(16);
        });
    }

       $.ajax({
        method: "POST",
        url: "/getPosts",
        dataType: 'json',
        async: true,
        contentType: 'application/json',
        data: JSON.stringify(userId),
        success: function (posts) {
            if (posts.status == "Done") {
                var arrayPosts = posts.data;

                var posts = $('<div id="posts" />');
                arrayPosts.forEach(function (value) {
                    var postId = value.id;
                    var creator = value.user;
                    var images = value.images;
                    var id = creator.id;
                    var name = creator.name;



                    var likes = value.likes;
                    var post = $('<div />', {id: postId, class: "posting"});
                    var info = $('<div class="info"/>').appendTo(post);
                    var user = $('<div class="userImage"/>').appendTo(info);
                    if(creator.imageUser){

                        var imageUser = creator.imageUser;
                        var userImage = imageUser.path;
                    var img = $('<img />', {
                        src: userImage
                    });
                    img.appendTo(user);}

                    var title = value.title;
                    var text = "post by " + name + "<br>" + value.date + "<br>" + value.title;
                    $('<div class="dateTitle"/>').html(text).appendTo(info);

                    var menuPost = $('<div class="menuPost"/>').appendTo(info);
                    if (id == userId) {
                        localStorage.setItem("True", "klient sovpal");
                        menuPost.append('<button class="deletePost" title="delete post" onclick="deletePost(this)"/>');
                        menuPost.append('<button class="editPost" title="edit post " onclick="editPost(this)"/>');

                    }
                    menuPost.append('<button class="addComment" title="places" onclick="getLink(this)"/>');
                    var textPost = value.text;
                    $('<div class="text"/>').html(textPost).appendTo(post);

                    images.forEach(function (value) {
                        var image = value.path;
                        var imid = value.id;
                        var p = $('<p/>', {id: imid, class: "image"});
                        var myImage = new Image();
                        myImage.src = image;
                        myImage.url = image;
                        p.append(myImage);
                        post.append(p);
                    });

                    var blockLikes = $('<div />', {id: uuidv4(), class: "likes"}).appendTo(post);

                    if (likes.length===0){


                    }
                    var counterLike=0;
                    var str="";

                    likes.forEach(function (value) {
                         var status=value.status;
                         var us=value.user;

                       counterLike++;

                        if(us.id===userId){str="Вы и еще";counterLike--;}
                    });

                var titleLike=str+" "+counterLike+" человек оценили пост";
                blockLikes.append('<button class="like" id="1" title="like" onclick="addLike(this)"/>');
                blockLikes.append('<button class="dislike" id="2" title="dislike" onclick="addLike(this)"/>');
                    var a1=$('<a class="liked"  href="regard()"/>').appendTo(blockLikes);
                    a1.attr("title",titleLike);
                    a1.text(likes.length);
      posts.append(post);
                });

                $('#userPosts').append(posts);
            }
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
}
var windowopen;
function getLink(post) {
    var creator = document.getElementById("user1").value;
    var user = JSON.parse(localStorage.getItem(creator));
    var userId = user.id;
    var postId = post.parentNode.parentNode.parentNode.id;
    var workUser = {userId: userId};
    var workPost = {postId: postId};
    localStorage.setItem("userOnBoard", JSON.stringify(workUser));
    localStorage.setItem("postOnBoard", JSON.stringify(workPost));
    var postData = {userId: userId, postId: postId};
    localStorage.setItem("Post data", JSON.stringify(postData));
   windowopen= window.open('post.html', 'Add Comment', 'width=600,height=400');
    var winClosed = setInterval(function () {

        if (windowopen.closed) {
            clearInterval(winClosed);
            getPosts(); //Call your function here
        }

    }, 250);
}

function deletePost(a) {
    var id = a.parentNode.parentNode.parentNode.id;
        $.ajax({
        method: "POST",
        url: "/deletePost",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(id),
        success: function (message) {
            if (message.status == "Done") {
                $('#' + id).remove();

                }
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });

}



