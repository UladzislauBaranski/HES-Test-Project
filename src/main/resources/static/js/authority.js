'use strict';

export let role;
let roleList;
fetch('./api/cur')
    .then(response => response.json())
    .then(data => {
        roleList = data.authorities;
        roleList.forEach(el => {
            role = el.authority
        })
    });
