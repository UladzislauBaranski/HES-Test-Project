export async function getDetailsWindowForUsers(value) {
    let modalDetails = document.getElementById('modal_details');
    let spanDetails = document.getElementById("close_details");

    let id = document.getElementById("id_details");
    let username = document.getElementById("username_details");
    let password = document.getElementById("password_details");
    let firstName = document.getElementById("first_name_details");
    let lastName = document.getElementById("last_name_details");
    let role = document.getElementById("role_details");
    let status = document.getElementById("status_details");


    modalDetails.style.display = "block";
    let response = await fetch(`./api/user/${value}`, {
        method: 'get',
    });
    let result = await response.json();

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
    firstName.value = result.firstName;
    lastName.value = result.lastName;
    role.value = result.role;
    status.value = result.status;

    spanDetails.onclick = function () {
        modalDetails.style.display = "none";
    };
}