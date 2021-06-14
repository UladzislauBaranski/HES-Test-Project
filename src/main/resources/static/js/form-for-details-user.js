'use strict';
import TableUsers from './table-users.js';

let userDetails = document.getElementById('user_details');
let modal = document.getElementById('modal_details');
userDetails.addEventListener('submit', async (e) => {
    e.preventDefault();
    let formData = new FormData(userDetails);
    let response = await fetch('./api/user/edit', {
        method: 'POST',
        body: formData
    });
    let result = await response.json();
    modal.style.display = "none";
    new TableUsers(result);
});

