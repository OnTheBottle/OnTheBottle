function addUser() {
    var name = document.getElementById("name").value;
    var status = document.getElementById("status").value;
    var user = {"name":name,"surname":status};
    var url = "/addUser";

    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(user),
        success: function (response) {
            if(response.status == "Done"){
                var mess ="User has been added successfully.";
                document.getElementById("message1").innerText = mess;
            }
        },
        error: function (message) {
            console.log(message);
        }

    });
}