function deleteEvent() {
    var id = $("#active-events").val();

    if (id == 0) return false;

    $.ajax({
        type: "POST",
        url: "/closeEvent",
        dataType: "json",
        data: {id: id},
        success: function (data) {
            document.getElementById("result").innerHTML = data.result;
        }
    });
}