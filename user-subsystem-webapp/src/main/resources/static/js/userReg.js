function userReg(log, email, pass) {
    $.ajax({
        type: "POST",
        url: "/registration",
        dataType: "json",
        data: {requestType: "userReg", login: log, email: email, password: pass},
        success: function (data) {
            if (data.result == "exists") {
                alert('Такой аккаунт уже существует');
                location.reload(true);
            } else if (data.result == "mail_exists") {
                alert('Этот email уже используется');
                location.reload(true);
            } else if (data.result == "complete") {
                alert('Вы успешно зарегистрировались');
                document.location.href = '../../authorization.html';
            } else if (data.result == "error") {
                alert('Что-то пошло не так!');
                location.reload(true);
            }
        }
    });

}