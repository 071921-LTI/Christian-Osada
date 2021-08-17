document.getElementById("form").addEventListener("submit", login, false);

function login(event) {
    event.preventDefault();
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    console.log(username);
    console.log(password);
    let xhr = new XMLHttpRequest();
    
    xhr.open("POST", "http://localhost:8080/project-1/users");

    xhr.onreadystatechange = function() {
        if(xhr.readyState === 4 && xhr.status === 200){
            let authToken = xhr.getResponseHeader("Authorization");
            
            sessionStorage.setItem("token", authToken);
            console.log(authToken);
            window.location.href = 'employee.html';

        } else if (xhr.readyState === 4){
            console.log('Something went wrong...');
        }
    } 

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    let requestBody = `username=${username}&password=${password}`;
    xhr.send(requestBody);
}