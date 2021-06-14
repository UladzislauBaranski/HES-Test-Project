'use strict';
import {pagination} from './utils.js';
import {role} from './authority.js';
import {getRefWindowForUsers} from './user-refactor-window.js';
import {getDetailsWindowForUsers} from './user-details-window.js';


export default class TableUsers {
    constructor(data) {
        if (data) {
            this.setData(data);
        }
    }

    setData = data => {
        this.initialData = data.slice();
        this.data = data.slice();
        this.currentPage = 1;
        this.rowsPerPage = 10;
        this.pageQuantity = Math.ceil(this.data.length / this.rowsPerPage);

        this.buildStaticContent();
        this.buildDynamicContent();
    };

    buildStaticContent = () => {
        let searchInput = document.getElementById('searchInput');
        searchInput.onkeyup = (event) => {
            this.search(event.target.value)
        };

        const headers = document.getElementById('headers');
        headers.innerHTML = '';

        const title = {
            'username': 'Username',
            'firstName': 'First Name',
            'lastName': 'Last Name',
            'role': 'Role',
            'status': 'Status',
            'createdAt': 'User creation date'
        };

        Object.keys(title).forEach(key => {
            const th = document.createElement('th');
            //  const a = document.createElement('a');
            // a.innerText = title[key];
            th.innerText = title[key];
            //   th.appendChild(a);
            headers.appendChild(th);
        });
    };
    buildDynamicContent = () => {
        const start = (this.currentPage - 1) * this.rowsPerPage;
        const end = start + this.rowsPerPage;

        const onePageData = this.data.slice(start, end);

        this.buildTableRows(onePageData);
        this.buildPaginationLinks();
    };

    buildTableRows = onePageData => {

        /*  /!* fetch('./api/cur')
               .then(response => response.json())
               .then(data => {
                  a=data.authorities;
                  a.forEach(ac=>{b=ac.authority})
                  console.log(b);
               });*!/
          let a;

          async function f() {
              const response = await fetch('./api/cur', {
                  method: 'get'
              });
              const newVar = await response.json();
              console.log('newvar', newVar);
              let authorities = await newVar.authorities;

              return await authorities;
          };

          const promise = f();
          promise.then((result) => {
              a = result;
          })
  */

        let tr, td, button;
        const table = document.getElementById('table_data');
        table.innerHTML = '';

        onePageData.forEach(obj => {
            tr = document.createElement('tr');
            td = document.createElement('td');
            td.textContent = obj.username;
            tr.appendChild(td);

            td = document.createElement('td');
            td.textContent = obj.firstName;
            tr.appendChild(td);

            td = document.createElement('td');
            td.textContent = obj.lastName;
            tr.appendChild(td);

            td = document.createElement('td');
            td.textContent = obj.role;
            tr.appendChild(td);


            td = document.createElement('td');
            td.textContent = obj.status;
            tr.appendChild(td);


            td = document.createElement('td');
            td.textContent = moment(obj.createdAt, 'YYYY-MM-DD HH:mm:ss').format('DD-MM-YYYY HH:mm:ss');
            tr.appendChild(td);


            td = document.createElement('td');
            button = document.createElement('button');
            button.setAttribute('type', 'submit');
            button.setAttribute('name', 'btn');
            button.setAttribute('value', obj.id);
            button.classList.toggle('change_btn');

            button.onclick = function () {
                getDetailsWindowForUsers(this.value)
            };
            button.textContent = 'Details';
            td.appendChild(button);
            tr.appendChild(td);
            table.appendChild(tr);

            if (role.includes('ROLE_Admin')) {
                td = document.createElement('td');
                button = document.createElement('button');
                button.setAttribute('type', 'submit');
                button.setAttribute('name', 'btn');
                button.setAttribute('value', obj.id);
                button.classList.toggle('change_btn');

                button.onclick = function () {
                    getRefWindowForUsers(this.value)
                };
                button.textContent = 'Refactor';
                td.appendChild(button);
                tr.appendChild(td);
                table.appendChild(tr);
            }
        });
    };
    buildPaginationLinks = () => {
        const pages = document.getElementById('pagination');
        pages.innerHTML = '';

        const previousLink = document.createElement('a');
        previousLink.addEventListener('click', this.updateCurrentPage);
        previousLink.innerText = 'Предыдущая';
        pages.appendChild(previousLink);

        pagination(this.currentPage, this.pageQuantity).forEach(page => {
            if (page !== '...') {
                const href = document.createElement('a');
                href.addEventListener('click', this.updateCurrentPage);
                href.innerText = page;
                if (page === this.currentPage) {
                    href.classList.add('active');
                }
                pages.appendChild(href);
            } else {
                const href = document.createElement('a');
                href.innerText = page;
                pages.appendChild(href);
            }
        });

        const nextLink = document.createElement('a');
        nextLink.addEventListener('click', this.updateCurrentPage);
        nextLink.innerText = 'Следующая';
        pages.appendChild(nextLink);
    };

    updateCurrentPage = pageHref => {
        const pageValue = pageHref.target.innerText;

        if (pageValue === 'Следующая' && this.currentPage < this.pageQuantity) {
            this.currentPage++;
        } else if (pageValue === 'Предыдущая' && this.currentPage > 1) {
            this.currentPage--;
        } else if (!isNaN(parseInt(pageValue))) {
            this.currentPage = parseInt(pageValue);
        }

        this.buildDynamicContent();
    };

    search = searchValue => {
        if (searchValue !== '') {
            this.data = this.initialData.filter(obj => {
                const data = {
                    ...obj,
                    id: '',
                    startDate: moment(obj.startDate).format('DD-MM-YYYY HH:mm:ss'),
                    changeTime: moment(obj.changeTime).format('DD-MM-YYYY HH:mm:ss')
                }
                return Object.values(data).some(value => {
                    if (value !== null) {
                        return value.toString().toUpperCase().includes(searchValue.toString().trim().toUpperCase());
                    }
                });
            });
        } else {
            this.data = this.initialData.slice();
        }
        this.pageQuantity = Math.ceil(this.data.length / this.rowsPerPage);
        this.currentPage = 1;
        this.buildDynamicContent();
    };


}

