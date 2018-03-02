function saveEvent() {
    var id = $("#active-events").val();
    var title = document.getElementById("title").value;
    var text = document.getElementById("text").value;
    var startTime = document.getElementById("startTime").value;
    var endTime = document.getElementById("endTime").value;
    var place = document.getElementById("place").value;

    $.ajax({
        type: "POST",
        url: "/saveEvent",
        dataType: "json",
        data: {id: id, title: title, text: text, startTime: startTime, endTime: endTime, place: place},
        success: function (data) {
            document.getElementById("result").innerText = data.result;
        }
    });
}