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
                addOption(select, choiceEvent, 0);
                for (key in events) {
                    addOption(select, events[key], key);
                }
            } else {
                choiceEvent = "Нет ниодного эвента";
                addOption(select, choiceEvent, 0);
            }
        }
    });
});

function addOption(oListbox, event, value) {
    var oOption = document.createElement("option");
    oOption.appendChild(document.createTextNode(event));
    oOption.setAttribute("value", value);
    oListbox.appendChild(oOption);
}

$("select").change(function () {
    if($(this).val() == 0) return false;

    alert($(this).val());
});
