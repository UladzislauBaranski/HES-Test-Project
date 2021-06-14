
export async function getRefWindowForUsers(value) {
    let modal1 = document.getElementById('modal_ref');
    let span1 = document.getElementById("close1");

    let id = document.getElementById("id_ref");
    let username = document.getElementById("username_ref");
    let password = document.getElementById("password_ref");
    let passwordConfirm = document.getElementById("confirm_password_ref");
    let firstName = document.getElementById("first_name_ref");
    let lastName = document.getElementById("last_name_ref");
    let role = document.getElementById("role_ref");
    let status = document.getElementById("status_ref");


    modal1.style.display = "block";
    let response = await fetch(`./api/user/${value}`, {
        method: 'get',
    });
    let result = await response.json();

    let responseRole = await fetch('./role', {
        method: 'get'
    });
    let resultRole = await responseRole.json();

    role.textContent = '';
    for (let i = 0; i < resultRole.length; i++) {
        let option = document.createElement("option");
        option.value = resultRole[i];
        option.text = resultRole[i];
        role.appendChild(option);
    }

    let responseStatus = await fetch('./status', {
        method: 'get'
    });
    let resultStatus = await responseStatus.json();

    status.textContent = '';
    for (let i = 0; i < resultStatus.length; i++) {
        let option = document.createElement("option");
        option.value = resultStatus[i];
        option.text = resultStatus[i];
        status.appendChild(option);
    }

    id.value = result.id;
    username.value = result.username;
    password.value = result.password;
    passwordConfirm.value = result.password;
    firstName.value = result.firstName;
    lastName.value = result.lastName;
    role.value = result.role;
    status.value = result.status;

    span1.onclick = function () {
        modal1.style.display = "none";
    };
}







