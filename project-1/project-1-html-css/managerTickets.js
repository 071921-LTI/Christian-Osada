document.getElementById('showAllTickets').addEventListener("click", showAllTickets);
document.getElementById('showSomeTickets').addEventListener("click", showSomeTickets);

async function showAllTickets() {

let authToken = sessionStorage.getItem("token");
let apiURL = 'http://localhost:8080/project-1/reimbursements';

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



async function showSomeTickets() {

    let authToken = sessionStorage.getItem("token");
    const val = document.querySelector('input').value;
    
    let apiURL = 'http://localhost:8080/project-1/reimbursements/' + val;
    
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