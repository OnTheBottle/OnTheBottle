function load(id) {
    $.ajax({
        type: "POST",
        url: "/showInfoEvent",
        dataType: "json",
        data: {id: id},
        success: function (data) {
            var eventAbout = document.getElementById('eventAbout');
            for (key in data) {
                addText(eventAbout, key, data[key]);
            }
        }
    });
}

function addText(textArea, key, value) {
    textArea.innerHTML += '- ' + key + ' : ' + value + '\n';
}