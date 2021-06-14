const username = document.getElementById("username_add");

function getLoginAndCheck(login) {
    let value = login.value;
    fetch(`./api/user/exists?username=${value}`)
        .then(response => response.text())
        .then(data => {
            checkUserForExists(value, data, login)
        });
}

function checkUserForExists(value, data, login) {
    if (value === data) {
        login.setCustomValidity("Пользователь с таким логином уже существует");
    } else {
        login.setCustomValidity('');
    }
}

username.onchange = () => {
    getLoginAndCheck(username)
};
