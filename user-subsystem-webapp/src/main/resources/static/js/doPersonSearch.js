function doSearch() {
    $.ajax({
        type: "GET",
        url: "/person_search",
        dataType: "json",
        data: {//requestType: "user",
        search:document.getElementById("search").value,
        searchType:$("input:radio[name='searchType']:checked").val()},
        success: function (data) {
            var userList = data.listOfPersons;
            var users = document.getElementById("users");
            for (var user in userList) {
                var div = document.createElement("div");
                div.setAttribute("class", "users");
                div.innerHTML =
                    "<div class='user_avatar'>" + userList[user].avatarUrl + " </div>" +
                    "<div class='user_name'>" + userList[user].name + " </div>" +
                    "<div class='user_surname'>" + userList[user].surname + "</div>" +
                    "<div class='user_age'>" + userList[user].age + "</div>"+
                    "<div class='user_country'>" + userList[user].country + " </div>" +
                    "<div class='user_city'>" + userList[user].city + "</div>";
                    users.parentNode.appendChild(div);
            }
        }
    });
}

function buildSearchObject(){


}