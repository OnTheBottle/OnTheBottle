function regard(postId) {
    var post_Id = postId.data;



    $.ajax({
        method: "POST",
        url: "/getPost",
        dataType: 'json',
        async: true,
        contentType: 'application/json',
        data: JSON.stringify(post_Id),
        success: function (regard) {
            if (regard.status == "Done") {
                var regards = regard.data;



                regards.forEach(function (value) {


                })


            }
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });


}