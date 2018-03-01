function deleteEvent() {
    var id = $("select").val();

    if (id == 0) return false;

    $.ajax({
        type: "POST",
        url: "/deleteEvent",
        dataType: "json",
        data: {id: id},
        success: function (data) {
            document.getElementById("result").innerHTML = data.result;
        }
    });
}