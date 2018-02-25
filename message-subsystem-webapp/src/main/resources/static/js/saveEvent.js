function saveEvent() {
    var title = document.getElementById("title").value;
    var text = document.getElementById("text").value;
    var startTime = document.getElementById("startTime").value;
    var endTime = document.getElementById("endTime").value;

    $.ajax({
        type: "POST",
        url: "/saveEvent",
        dataType: "json",
        data: {title: title, text: text, startTime: startTime, endTime: endTime},
        success: function (data) {
            document.getElementById("result").innerText = data.result;
        }
    });
}