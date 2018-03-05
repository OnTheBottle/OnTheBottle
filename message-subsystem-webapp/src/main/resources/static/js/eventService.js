function saveEvent() {
    var id = $("#active-events").val();
    var title = document.getElementById("title").value;
    var text = document.getElementById("text").value;
    var startTime = document.getElementById("startTime").value;
    var endTime = document.getElementById("endTime").value;
    var place = $("#places").val();

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

$("#active-events").change(function () {
    if($(this).val() == 0) {
        clearInfo();
        return false;
    }

    $("#passed-events").val(0);
    loadInfoEvent($(this).val());
});

$("#passed-events").change(function () {
    if($(this).val() == 0) {
        clearInfo();
        return false;
    }

    $("#active-events").val(0);
    loadInfoEvent($(this).val());
});

function loadInfoEvent(id) {
    $.ajax({
        type: "POST",
        url: "/showInfoEvent",
        dataType: "json",
        data: {id: id},
        success: function (data) {
            addText(data);
        }
    });
}

function clearInfo() {
    document.getElementById('title').value = '';
    document.getElementById('text').value = '';
    document.getElementById('startTime').value = '';
    document.getElementById('endTime').value = '';
    $("#places").val(0);
}

function addText(data) {
    document.getElementById('title').value = data.title;
    document.getElementById('text').value = data.text;
    document.getElementById('startTime').value = data.startTime;
    document.getElementById('endTime').value = data.endTime;
    $("#places").val(data.place);

    var usersList = data.users;
    var selectUser = document.getElementById('users-event')
    for (var key in usersList) {
        addOption(selectUser, usersList[key], usersList[key]);
    }
}