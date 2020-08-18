"use strict"

let test = document.querySelector("#test");
let search = document.querySelector("#team-search");

let showTeams = (teams) => {
    for (let i = 0; i < teams.length; i++) {
        test.innerHTML +=
            "<div class='w-100 d-flex justify-content-center'>" +
                "<div class='card w-50 my-3' style='width: 18rem'>" +
                    "<div class='card-header text-center font-weight-bold'>" + teams[i].name + "</div>" +
                    "<div class='card-body d-flex flex-column'>" +
                        "<a href=" + "/team/" + teams[i].id + " " + "class='btn btn-outline-info align-self-end'>View Team</a>" +
                    "</div>" +
                "</div>" +
            "</div>";
    }
}
showTeams(teams);

let updateTeams = () => {
    let filteredTeams = []
    teams.forEach(function(team) {
        if (team.name.includes(search.value)) {
            filteredTeams.push(team)
        }
    })
    test.innerHTML = "";
    showTeams(filteredTeams);
}