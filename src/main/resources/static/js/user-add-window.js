'use strict';
import TableUsers from './table-users.js';

fetch('./api/user')
    .then(response => response.json())
    .then(data => {
        new TableUsers(data);
    });


let elementById = document.getElementById('btn');
if (elementById!=null) {
    elementById.addEventListener('click', () => {
        let modal = document.getElementById('modal');
        let span = document.getElementById("close");
        modal.style.display = "block";

        dropDownUserAdd();
        span.addEventListener('click', () => {
            modal.style.display = "none";
        });

    });
}

async function dropDownUserAdd() {
    let role = document.getElementById("role_add");
    let status = document.getElementById("status_add");

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
}



