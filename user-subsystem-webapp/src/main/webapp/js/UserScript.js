function showUsers() {

    $.ajax({
        type: "GET",
        url:"/showUsers",
        dataType: "json",
        success: function(data) {

            var responce = data.result;
            //      document.getElementById("user-surname-label").innerText = responce.surname;
            var table = document.createElement("table");

            var nameRow = table.insertRow();
            var nameRowTitle = nameRow.insertCell(0);
            var nameRowData = nameRow.insertCell(1);

            nameRowTitle.innerText = "name";
            nameRowData.innerText = responce.name;

            var surnameRow = table.insertRow();
            var surnameRowTitle = surnameRow.insertCell(0);
            var surnameRowData = surnameRow.insertCell(1);

            surnameRowTitle.innerText = "surname";
            surnameRowData.innerText = responce.surname;

            var ageRow = table.insertRow();
            var ageRowTitle = ageRow.insertCell(0);
            var ageRowData = ageRow.insertCell(1);

            ageRowTitle.innerText = "age";
            ageRowData.innerText = responce.age;

            document.getElementById("table-area").appendChild(table);
        }
    })
}