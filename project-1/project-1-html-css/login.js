document.getElementById("form").addEventListener("submit", login, false);
document.getElementById('logout').addEventListener("click", logout);

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
            let tArr = authToken.split(":");

            console.log(tArr[1]);

            if (tArr[1] = 'Employee') {
                window.location.href = 'employee.html';
            } else if (tArr[1] = 'Manager'){
                window.location.href = 'manager.html';
            }
            

        } else if (xhr.readyState === 4){
            console.log('Something went wrong...');
        }
    } 

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    let requestBody = `username=${username}&password=${password}`;
    xhr.send(requestBody);
}

function logout() {
    window.location.href = 'login.html';
}