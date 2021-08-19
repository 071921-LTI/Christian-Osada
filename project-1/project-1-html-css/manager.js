document.getElementById('getData').addEventListener("click", getData);

async function getData() {

    let token = sessionStorage.getItem("token");
    let tArr = token.split(":");
    let apiURL = 'http://localhost:8080/project-1/users/' + tArr[0];
    
    var header = new Headers();
    header.append('Authorization', token);
    
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