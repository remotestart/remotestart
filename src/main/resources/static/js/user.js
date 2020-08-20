"use strict"

let bucket = document.querySelector("#bucket");
let userSearch = document.querySelector("#user-search");

let showUsers = (users) => {
    for (let i = 0; i < users.length; i++){
        bucket.innerHTML +=
            "<div class='w-100 d-flex justify-content-center'>" +
            "<div class='card w-50 my-3' style='width: 18rem'>" +
            "<div class='card-header text-center font-weight-bold'>" + users[i].firstName + "</div>" +
            "<div class='card-body d-flex flex-column'>" +
            "<a href='/team/" + team + "/add/" + users[i].id + "' " + "class='btn btn-outline-info align-self-end'>Add User</a>" +
            "</div>" +
            "</div>" +
            "</div>";
    }
}
showUsers(users);

let updateUsers = () => {
    let filteredUsers = []
    users.forEach(function (user) {
        if (user.firstName.includes(userSearch.value)){
            filteredUsers.push(user)
        }
    })
    bucket.innerHTML =  "";
    showUsers(filteredUsers);
}