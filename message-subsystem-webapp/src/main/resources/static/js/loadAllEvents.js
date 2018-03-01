$(document).ready(function () {
    $.ajax({
        type: "POST",
        url: "/showAllEvents",
        dataType: "json",
        data: {},
        success: function (data) {
            var select = document.getElementsByClassName('select')[0];
            var events = data.events;
            var choiceEvent;

            if (events.length !== 0) {
                choiceEvent = "Выберите эвент";
                addOption(select, 0, choiceEvent);
                for (key in events) {
                    addOption(select, key, events[key]);
                }
            } else {
                choiceEvent = "Нет ниодного эвента";
                addOption(select, 0, choiceEvent);
            }
        }
    });
});

function addOption(oListbox, key, value) {
    var oOption = document.createElement("option");
    oOption.appendChild(document.createTextNode(value));
    oOption.setAttribute("value", key);
    oListbox.appendChild(oOption);
}
