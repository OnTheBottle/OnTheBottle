function createUser() {
    $.ajax({
        type: "POST",
        url: "/createUser",
        dataType: "json",
        data: {},
        success: function (data) {
            document.getElementById("result").innerText = data.result;
        }
    });
}