function addLike(button){
    var blockLikes=button.parentNode.id;
    var postId=button.parentNode.parentNode.id;
    var creator = document.getElementById("user1").value;
    var user = JSON.parse(localStorage.getItem(creator));
    var userId = user.id;
    if(button.id==1){
        var status='like';
        var like={"post_id":postId,"user_id":userId,"status":status}}
        if (button.id==2){
            var status='dislike';
            var like={"post_id":postId,"user_id":userId,"status":status}}

        $.ajax({
            url: "/addLike",
            type: 'POST',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(like),
            success: function (response) {
                if (response.status == "Done") {
            alert("Ваша оценка добавлена");
            var counter=response.data+1;
            var co=response.data;
var c=""+counter;
            var p=document.getElementById(postId).getElementsByTagName("a")[0];
p.remove();
var d=document.getElementById(blockLikes);
            var titleLike="Вы и еще"+co+"человек оценили пост";
                    var a1=$('<a class="liked"  href="regard()"/>').appendTo(d);
                    a1.attr("title",titleLike);
                    a1.text(c);
                    }

                if (response.status == "No") {
                    alert("Вы уже оценили пост");
                }
           },
            error: function (message) {
                console.log(message);
            }

        });
    }

