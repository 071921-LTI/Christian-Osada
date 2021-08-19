document.getElementById('getData').addEventListener("click", getData);
document.getElementById('updateUser').addEventListener("click", updateUser);
document.getElementById('logout').addEventListener("click", logout);


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
        populateUserInfo(data);
    } else{
        console.log('Unable to retrieve data.')
    }
}

function populateUserInfo(response) {
    document.getElementById("idp").innerHTML = response.id;
    document.getElementById("firstNamep").value = response.firstName;
    document.getElementById("lastNamep").value = response.lastName;
    document.getElementById("emailp").value = response.email;
    document.getElementById("rolep").innerHTML = response.role.role;
}

function updateUser() {

    let id = document.getElementById("idp").innerHTML;
    let firstName = document.getElementById("firstNamep").value;
    let lastName = document.getElementById("lastNamep").value;
    let email = document.getElementById("emailp").value;

    console.log(id);
    console.log(firstName);
    console.log(lastName);
    console.log(email);

    let xhr = new XMLHttpRequest();
    
    xhr.open("PUT", "http://localhost:8080/project-1/users");

    xhr.onreadystatechange = function() {
        if(xhr.readyState === 4 && xhr.status === 200){
            console.log('success')

        } else if (xhr.readyState === 4){
            console.log('Something went wrong...');
        }
    }
    
    let authToken = sessionStorage.getItem("token");

    let user = {
        id: id,
        firstName: firstName,
        lastName: lastName,
        email: email
    }

    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.setRequestHeader("Authorization", authToken);
    xhr.send(JSON.stringify(user));
}