'use strict';
import TableUsers from './table-users.js';

let userAdd = document.getElementById('user_add');
let modal = document.getElementById('modal');
userAdd.addEventListener('submit', async (e) => {
    e.preventDefault();
    let formData = new FormData(userAdd);
    let response = await fetch('./api/user/new', {
        method: 'POST',
        body: formData
    });
    let result = await response.json();
    modal.style.display = "none";
    new TableUsers(result);

});

