document.getElementById('showTickets').addEventListener("click", showTickets);

async function showTickets() {

let authToken = sessionStorage.getItem("token");
let tArr = authToken.split(":");
let apiURL = 'http://localhost:8080/project-1/reimbursements/' + tArr[0];

var header = new Headers();
header.append('Authorization', authToken);

    let response = await fetch(apiURL, {
        headers: {
            'Authorization': header
          }
    });

    if(response.status >= 200 && response.status < 300){
        let data = await response.json();
        console.log(data)
        //populateData(data);
    } else{
        console.log('Unable to retrieve data.')
    }
}