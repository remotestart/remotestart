"use strict"

//Team Search

let test = document.querySelector("#test");
let search = document.querySelector("#team-search");

let showTeams = (teams) => {
    for (let i = 0; i < teams.length; i++) {
        test.innerHTML +=
            "<div class='d-flex justify-content-between w-50 my-2' id='team" + teams[i].id + "'>" +
            "<h2 class='align-self-start'>" + teams[i].name + "</h2>" +
            "<a href=" + "/team/request/" + teams[i].id + " " + "class='btn btn-outline-info align-self-end'>Request to Join Team</a>" +
            "</div>" +
            "<div class='w-50'><hr></div>";
    }
}
showTeams(teams);

let updateTeams = () => {
    let filteredTeams = []
    teams.forEach(function(team) {
        if (team.name.toUpperCase().includes(search.value.toUpperCase())) {
            filteredTeams.push(team)
        }
    })
    test.innerHTML = "";
    showTeams(filteredTeams);
}