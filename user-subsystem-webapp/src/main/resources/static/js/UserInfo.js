window.onload = showUsers();

function showUsers() {
    $.ajax({
        type: "GET",
        url: "/showUsers",
        dataType: "json",
        success: function (data) {

            // document.getElementById("user-surname-label").innerText = data;
            var table = document.createElement("table");

            var nameRow = table.insertRow();
            var nameRowTitle = nameRow.insertCell(0);
            var nameRowData = nameRow.insertCell(1);

            nameRowTitle.innerText = "name";
            nameRowData.innerText = data.user.name;
            // nameRowData.innerText = response.name;

            var surnameRow = table.insertRow();
            var surnameRowTitle = surnameRow.insertCell(0);
            var surnameRowData = surnameRow.insertCell(1);

            surnameRowTitle.innerText = "surname";
            surnameRowData.innerText = data.user.surname;

            var ageRow = table.insertRow();
            var ageRowTitle = ageRow.insertCell(0);
            var ageRowData = ageRow.insertCell(1);

            ageRowTitle.innerText = "age";
            ageRowData.innerText = data.user.age;

            document.getElementById("table-area").appendChild(table);
        }
    })
}