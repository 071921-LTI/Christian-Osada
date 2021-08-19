async function showEmployees (){
    console.log('here')
    let authToken = sessionStorage.getItem("token");
    let apiURL = 'http://localhost:8080/project-1/users';
    console.log('Employees Works')
    getEmployees(apiURL, authToken)
}

async function getEmployees(url, authToken) {
    
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
        let newText0 = document.createTextNode(data.firstName);
        newCell0.appendChild(newText0);
    
        let newCell1 = newRow.insertCell(2);
        let newText1 = document.createTextNode(data.lastName);
        newCell1.appendChild(newText1);
    
        let newCell2 = newRow.insertCell(3);
        let newText2 = document.createTextNode(data.email);
        newCell2.appendChild(newText2);
      }