document.getElementById('getData').addEventListener("click", getData);
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
        //populateData(data);
    } else{
        console.log('Unable to retrieve data.')
    }
}

function populateData(response) {


//     let dataSection = document.getElementById('data');
   
//    // Resets the innerHTML before loading new data
//    dataSection.innerHTML ='';

//    let nameTag = document.createElement('h3');
//    nameTag.innerHTML = response.name.toUpperCase();
   
//    let abilitiesArray = response.abilities;
//    let abilities = document.createElement('ul');
//    // Appending list elements to a ul
//    for(let ability of abilitiesArray){
//        let abilityLi = document.createElement('li');
//        abilityLi.innerHTML = ability.ability.name;
//        abilities.appendChild(abilityLi);
//    }

//    // Appending h3 and List to the section tag
//    dataSection.appendChild(nameTag);
//    dataSection.appendChild(abilities);

//    // Appending sprites to section
//    let spritesObject = response.sprites;
//    for(let sprite in spritesObject){
//        if(spritesObject[sprite] && spritesObject[sprite].length > 2){
//            let spriteImg = document.createElement('img');
//            spriteImg.src = spritesObject[sprite];
//            dataSection.appendChild(spriteImg);
//        }
//    }
}

function logout() {
    window.location.href = 'login.html';
}