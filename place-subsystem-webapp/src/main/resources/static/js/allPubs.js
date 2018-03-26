function allPubs() {
    $.ajax({
        type: "POST",
        url: "/pubList",
        dataType: "json",
        success: function (data) {
            var pubList = data.places;
            var allPlaces = document.getElementById("all_pubs");
           var select= $('<select id="places"/>');
            pubList.forEach(function (pub) {
                $('<option/>', {text: pub.name}).appendTo(select);
                });
                $('#show_places').append(select);
            },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
}