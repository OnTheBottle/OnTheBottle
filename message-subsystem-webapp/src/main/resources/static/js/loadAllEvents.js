$(document).ready(function () {
    $.ajax({
        type: "POST",
        url: "/showAllEvents",
        dataType: "json",
        data: {},
        success: function (data) {
            var select = document.getElementsByClassName('select')[0];
            var eventsIdList = data.eventsId;
            var eventTitleList = data.eventTitle;
            var choiceEvent;

            if (eventsIdList.length !== 0) {
                choiceEvent = "Выберите эвент";
                addOption(select, choiceEvent, 0);
                for (var event in eventsIdList) {
                    addOption(select, eventTitleList[event], eventsIdList[event]);
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
