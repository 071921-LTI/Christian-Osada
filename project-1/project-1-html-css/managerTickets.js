document.getElementById('showAllTickets').addEventListener("click", showAllTickets);
document.getElementById('showSomeTickets').addEventListener("click", showSomeTickets);
document.getElementById('updateTicket').addEventListener("click", updateTicket);

var signal = 2;

function updateTicket() {

    let id = document.getElementById("ticketId").value;
    let status = document.getElementById("ticketStatus").value;
    document.getElementById('ticketId').value = "";
    document.getElementById('ticketStatus').value = "";

    console.log(id);
    console.log(status);

    let xhr = new XMLHttpRequest();
    
    xhr.open("PUT", "http://localhost:8080/project-1/reimbursements");

    xhr.onreadystatechange = function() {
        if(xhr.readyState === 4 && xhr.status === 200){
            console.log('success')

        } else if (xhr.readyState === 4){
            console.log('Something went wrong...');
        }
    }
    
    let authToken = sessionStorage.getItem("token");

    let ticket = {
        id: id,
        status: status,
    }

    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.setRequestHeader("Authorization", authToken);
    xhr.send(JSON.stringify(ticket));

    if (signal == 2) {
        setTimeout(showAllTickets, 1000);
    } else if (signal == 1) {
        setTimeout(showSomeTickets, 1000);
    }
}

async function showSomeTickets (){
    signal = 1;
    console.log('here')
    let number = document.getElementById('chosenId').value;
    console.log(number)
    let authToken = sessionStorage.getItem("token");
    let apiURL = 'http://localhost:8080/project-1/reimbursements/' + number;
    console.log('Some Works')
    showTickets(apiURL, authToken)
}

async function showAllTickets () {
    signal = 2;
    let authToken = sessionStorage.getItem("token");
    let apiURL = 'http://localhost:8080/project-1/reimbursements';
    console.log('All Works')
    showTickets(apiURL, authToken)
}

async function showTickets(url, authToken) {

    let apiURL = url;
    
    var header = new Headers();
    header.append('Authorization', authToken);
    
        let response = await fetch(url, {
            headers: {
                'Authorization': header
              }
        });
    
        if(response.status >= 200 && response.status < 300){
            let data = await response.json();
            console.log(data)
            createTable(data);
        } else{
            console.log('Unable to retrieve data.')
        }
    }
    
    function createTable(data) {
        let oldBody = document.getElementById('tbody');

        var mytbl = document.getElementById("table");
        mytbl.getElementsByTagName("tbody")[0].innerHTML = mytbl.rows[0].innerHTML;
    
    
        for (let i = 0; i < data.length; i++) {
            addRow(oldBody, data[i])
        }
    }
    
    function addRow(tableID, data) {
        // Insert a row at the end of the table
        let newRow = tableID.insertRow(-1);
      
        // Insert a cell in the row at index 0
        let newCell8 = newRow.insertCell(0);
        let newText8 = document.createTextNode(data.id);
        newCell8.appendChild(newText8);

        let newCell0 = newRow.insertCell(1);
        let newText0 = document.createTextNode(data.author.id);
        newCell0.appendChild(newText0);
    
        let newCell1 = newRow.insertCell(2);
        let newText1 = document.createTextNode(data.amount);
        newCell1.appendChild(newText1);
    
        let newCell2 = newRow.insertCell(3);
        let newText2 = document.createTextNode(data.type.type);
        newCell2.appendChild(newText2);
    
        let newCell3 = newRow.insertCell(4);
        let newText3 = document.createTextNode(data.description);
        newCell3.appendChild(newText3);
    
        let newCell4 = newRow.insertCell(5);
        var date4 = new Date(data.submitted);
        let newText4 = document.createTextNode(date4);
        newCell4.appendChild(newText4);
    
        let newCell5 = newRow.insertCell(6);
        let newCell6 = newRow.insertCell(7);
        if(data.resolver === null) {
            let newText5 = document.createTextNode(" ");
            newCell5.appendChild(newText5);
    
            let newText6 = document.createTextNode(" ");
            newCell6.appendChild(newText6);
        } else {
            let newText5 = document.createTextNode(data.resolver.firstName + " " + data.resolver.lastName);
            newCell5.appendChild(newText5);
    
            var date6 = new Date(data.resolved);
            let newText6 = document.createTextNode(date6);
            newCell6.appendChild(newText6);
        }
    
        let newCell7 = newRow.insertCell(8);
        let newText7 = document.createTextNode(data.status.status);
        newCell7.appendChild(newText7);
      }