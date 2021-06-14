'use strict';
import TableUsers from './table-users.js';

let userRef = document.getElementById('user_ref');
let modal = document.getElementById('modal_ref');
userRef.addEventListener('submit', async (e) => {
    e.preventDefault();
    let formData = new FormData(userRef);
    let response = await fetch('./api/user/edit', {
        method: 'POST',
        body: formData
    });
    let result = await response.json();
    modal.style.display = "none";
    new TableUsers(result);
});

