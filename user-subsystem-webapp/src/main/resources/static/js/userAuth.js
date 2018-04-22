function userAuth() {
    var doc = document;
    var login = doc.getElementById("login").value;
    var password = doc.getElementById("password").value;
    $.ajax({
        type: "POST",
        url: "/authorize",
        dataType: "json",
        data: {requestType: "userAuth",
            login: login, password: password},
        success: function (data) {
            if (data.result == "error") {
                alert("Неправильный пароль");
                location.reload(true);
            }
            else if (data.result == "not_exist") {
                alert("Такого пользователя не существует");
                location.reload(true);
            }
            else if (data.result == "in_use") {
                alert("Такой аккаунт уже используется");
                location.reload(true);
            }
            else if (data.result == "complete") {
                doc.location.href = '../../user/main.html';
                alert('Вы вошли как ' + login);
            }
        }
    });
}