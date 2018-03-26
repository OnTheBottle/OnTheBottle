function getSelect() {
       $.ajax({
        method: "POST",
        url: "/getSelect",
        dataType:'json',
        async: true,
        contentType: 'application/json',
        success: function(select) {
            if(select.status == "Done") {
                var selectoptions = select.data;
                var i=0;
                var selectlist = $('<select id="security"/>');
                selectoptions.forEach(function (value) {

                        $('<option/>', {text: value.name}).appendTo(selectlist);
                    const security = { security_id: value.id,securityName:value.name};
                    var item = security.securityName;
                    localStorage.setItem(item, JSON.stringify(security));
                });
                $('#sel').append(selectlist);
            }
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
};
