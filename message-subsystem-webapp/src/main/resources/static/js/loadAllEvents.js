$(document).ready(function() {
    $.ajax({
        type: "POST",
        url: "/showAllEvents",
        dataType: "json",
        data: {},
        success: function (data) {
            var select = document.getElementsByClassName('select')[0];
            var eventlList = data.eventList;
            if (eventlList != null) {
                for (var event in eventlList) {
                    addOption(select, eventlList[event]);
                }
            }
        }
    });
});

function addOption(oListbox, event) {
    var oOption = document.createElement("option");
    oOption.appendChild(document.createTextNode(event));
    oOption.setAttribute("value", event);
    oListbox.appendChild(oOption);
}