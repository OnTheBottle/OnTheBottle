function showUsers() {

    $.ajax({
        type: "GET",
        url:"/showUsers",
        dataType: "json",
        success: function(data) {
//            var responce = data.result;
//            var table = document.createElement("table");

//            var rowHeader = table.insertRow();
/*            rowHeader.setAttribute("class", "tableheader");
            var cellHeader1 = rowHeader.insertCell(0);
            var cellHeader2 = rowHeader.insertCell(1);
            var cellHeader3 = rowHeader.insertCell(2);

            cellHeader1.innerText = "Name";
            cellHeader2.innerText = "Age";
            cellHeader3.innerText = "Class";

            for (var animalNum in responce) {

                var row = table.insertRow();
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);

                cell1.innerText = responce[animalNum].name;
                cell2.innerText = responce[animalNum].age;
                cell3.innerText = responce[animalNum].class;
            }
            document.getElementById("tablearea").appendChild(table);*/
        }
    })
}