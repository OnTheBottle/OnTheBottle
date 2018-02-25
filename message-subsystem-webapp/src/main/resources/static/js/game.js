function tryToGame() {
    var number = document.getElementById("personNumber").value;
    $.ajax({
        type: "POST",
        url: "/doGame",
        dataType: "json",
        data: {number: number},
        success: function (data) {
            document.getElementById("result").innerText = data.result;
        }
    });
}