function doPost() {
    var security = document.getElementById("selectSecurity").value;
    var text = document.getElementById("post").value;
    var image="/img/1234";
    var post = {"text": text,"image":image,"security":security};
    var url = "/doPost";
    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        contentType: "charset=UTF-8",
        data: JSON.stringify(post),
        success: function (data) {
            var great=data["result"];
            alert(great);
        },
        error: function (message) {
            console.log(message);
        }

    });
}