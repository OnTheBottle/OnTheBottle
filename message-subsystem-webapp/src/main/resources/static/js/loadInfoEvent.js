$("#active-events").change(function () {
    if($(this).val() == 0) {
        clearInfo();
        return false;
    }

    loadInfoEvent($(this).val());
});

$("#passed-events").change(function () {
    if($(this).val() == 0) {
        clearInfo();
        return false;
    }

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
    document.getElementById('place').value = '0';
}

function addText(data) {
    document.getElementById('title').value = data.title;
    document.getElementById('text').value = data.text;
    document.getElementById('startTime').value = data.startTime;
    document.getElementById('endTime').value = data.endTime;
    document.getElementById('place').value = data.place;
}