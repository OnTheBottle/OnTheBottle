window.onload = showUsers();

function showUsers() {
    $.ajax({
        type: "GET",
        url: "/showUsers",
        dataType: "json",
        data:{
            // id : 'eddf1b92-79ff-40c0-95d5-df197c8162e7'
            id:(document.location.search.split('=')[1])
        },
        success: function (data) {
            // document.getElementById("user-surname-label").innerText = data;
            var table = document.createElement("table");

            var nameRow = table.insertRow();
            var nameRowTitle = nameRow.insertCell(0);
            var nameRowData = nameRow.insertCell(1);

            nameRowTitle.innerText = "name";
            nameRowData.innerText = data.name;
            // nameRowData.innerText = response.name;

            var surnameRow = table.insertRow();
            var surnameRowTitle = surnameRow.insertCell(0);
            var surnameRowData = surnameRow.insertCell(1);

            surnameRowTitle.innerText = "surname";
            surnameRowData.innerText = data.surname;

            var ageRow = table.insertRow();
            var ageRowTitle = ageRow.insertCell(0);
            var ageRowData = ageRow.insertCell(1);

            ageRowTitle.innerText = "age";
            ageRowData.innerText = data.age;

            document.getElementById("table-area").appendChild(table);
        }
    })
}