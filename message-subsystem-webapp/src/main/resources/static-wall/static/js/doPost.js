function doPost() {
    function uuidv4() {
        return ([1e7] + -1e3 + -4e3 + -8e3 + -1e11).replace(/[018]/g, c =>
            (c ^ crypto.getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16)
        )
    }

    var idimage = uuidv4();
    var idpost = uuidv4();
    var creator = document.getElementById("user1").value;
    var security = document.getElementById("security").value;
    var text = document.getElementById("post").value;
    var title = document.getElementById("title").value;
    var user = JSON.parse(localStorage.getItem(creator));
    var sec = JSON.parse(localStorage.getItem(security));
    var userId = user.id;
    var securityId = sec.security_id;
    var post = {"post_id": idpost, "post": text, "security_id": securityId, "title": title, "user_id": userId};
    $.ajax({
        url: '/doPost',
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(post),
        success: function (response) {
            if (response.status == "Done") {

                addimage();

            }
        },
        error: function (message) {
            allert("post can't added");
        }

    });

    function addimage() {
        var a = document.getElementById("upload-file-input").files[0];
        if (!a) {
            var mess = "Post has been added successfully.";
            document.getElementById("message").innerText = mess;
            getPosts();
        }
        else {
            var file = document.getElementById("upload-file-input").files[0].name;
            var image = {"image_id": idimage, "post_id": idpost, "name": file};
            $.ajax({
                url: '/file',
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify(image),
                success: function (message) {
                    if (message.status == "Done") {
                        var mess = "Post has been added successfully.";
                        document.getElementById("message").innerText = mess;
                        getPosts();
                    }
                },
                error: function (message) {
                    console.log(message);
                }
            });
        }
    }
}