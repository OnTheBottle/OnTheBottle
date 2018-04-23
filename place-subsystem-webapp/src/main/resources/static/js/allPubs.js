function allPubs() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8081/pubList",
        dataType: "json",
        success: function (data) {
            var allPlaces = document.getElementById("all_pubs");
            for (var i in data.places) {
                allPlaces.innerText += '\n';
                allPlaces.innerText += 'placeid ' + data.places[i].placeid + '\n';
                allPlaces.innerText += 'name ' + data.places[i].name + '\n';
                allPlaces.innerText += 'type ' + data.places[i].type + '\n';
            }
        }, error: function (xhr, status, error) {
            alert(error);
        }
    });
}

function allComments() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8081/getPubComments",
        dataType: "json",
        success: function (data) {
            var allComments = document.getElementById("all_comments");
            for (var i in data.commentList) {
                allComments.innerText += '\n';
                allComments.innerText += 'commentid ' + data.commentList[i].commentid + '\n';
                allComments.innerText += 'commenttext ' + data.commentList[i].commenttext + '\n';
                allComments.innerText += 'placeid ' + data.commentList[i].place.placeid + '\n';
            }
        }, error: function (xhr, status, error) {
            alert(error);
        }
    });
}