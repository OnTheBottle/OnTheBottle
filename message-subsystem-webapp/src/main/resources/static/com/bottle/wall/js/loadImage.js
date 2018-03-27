//$(document).ready(function() {
//    $("#upload-file-input").on("change", uploadFile);
//  });

function uploadFile() {
    var a= document.getElementById("upload-file-input").files[0];
    if (a) {
        var item = document.getElementById("upload-file-input").files[0].name;
        alert(item);
        localStorage.setItem(item, JSON.stringify(item));
        $.ajax({
            url: "/upload",
            type: "POST",
            data: new FormData($("#upload-file-form")[0]),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function () {

                $("#upload-file-message").text("File succesfully uploaded");
            },
            error: function () {

                $("#upload-file-message").text(
                    "File not uploaded (perhaps it's too much big or empty)");
            }
        });
    }
}