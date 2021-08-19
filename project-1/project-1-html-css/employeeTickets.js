document.getElementById('newTicket').addEventListener("click", newTicket);

//Set up url for call to database for ticket info
function showMyTickets() {
    let authToken = sessionStorage.getItem("token");
    let tArr = authToken.split(":");
    let apiURL = 'http://localhost:8080/project-1/reimbursements/' + tArr[0];
    console.log('My Works');
    showTickets(apiURL, authToken);
}

//Make a call to database for all current ticket info
async function showTickets(url, authToken) {

let apiURL = url;

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
        createTable(data);
    } else{
        console.log('Unable to retrieve data.')
    }
}

//Create a new ticket using given information
function newTicket() {
    let amount = document.getElementById("amountp").value;
    let type = document.getElementById("typep").value;
    let description = document.getElementById("descriptionp").value;
    document.getElementById('amountp').value = "";
    document.getElementById('typep').value = "";
    document.getElementById('descriptionp').value = "";

    console.log(amount);
    console.log(type);
    console.log(description);
    
    let xhr = new XMLHttpRequest();
    
    xhr.open("POST", "http://localhost:8080/project-1/reimbursements");

    xhr.onreadystatechange = function() {
        if(xhr.readyState === 4 && xhr.status === 200){
            console.log('success')

        } else if (xhr.readyState === 4){
            console.log('Something went wrong...');
        }
    }
    
    let authToken = sessionStorage.getItem("token");

    let ticket = {
        amount: amount,
        type: type,
        description: description,
    }

    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.setRequestHeader("Authorization", authToken);
    xhr.send(JSON.stringify(ticket));
    setTimeout(showMyTickets, 1000);
}


//Replace current table body with new one based on given JSON data
function createTable(data) {
    let oldBody = document.getElementById('tbody');

    var mytbl = document.getElementById("table");
    mytbl.getElementsByTagName("tbody")[0].innerHTML = mytbl.rows[0].innerHTML;
    // document.getElementById("myTable").deleteRow(0);

    //Insert JSON array data [i] from GET onto table
    for (let i = 0; i < data.length; i++) {
        addRow(oldBody, data[i])
    }
}

//Function that creates a new row, inserts to JSON data into it, then appends the row to the botom ofthe table
function addRow(tableID, data) {
    let newRow = tableID.insertRow(-1);
    
  
    let newCell0 = newRow.insertCell(0);
    let newText0 = document.createTextNode(data.id);
    newCell0.appendChild(newText0);

    let newCell1 = newRow.insertCell(1);
    let newText1 = document.createTextNode(data.amount);
    newCell1.appendChild(newText1);

    let newCell2 = newRow.insertCell(2);
    let newText2 = document.createTextNode(data.type.type);
    newCell2.appendChild(newText2);

    let newCell3 = newRow.insertCell(3);
    let newText3 = document.createTextNode(data.description);
    newCell3.appendChild(newText3);

    let newCell4 = newRow.insertCell(4);
    var date4 = new Date(data.submitted);
    let newText4 = document.createTextNode(date4);
    newCell4.appendChild(newText4);

    let newCell5 = newRow.insertCell(5);
    let newCell6 = newRow.insertCell(6);
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

    let newCell7 = newRow.insertCell(7);
    let newText7 = document.createTextNode(data.status.status);
    newCell7.appendChild(newText7);
  }