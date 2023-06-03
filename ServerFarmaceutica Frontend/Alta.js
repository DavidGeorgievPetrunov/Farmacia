function enviar() {
    // esta es la funcion enviar del alta, no esta completa
    let http = new XMLHttpRequest();

    let mail = sessionStorage.getItem("mail");
    let session = sessionStorage.getItem("session");
    let xip = document.getElementById("xip").value;
    let medicine = document.getElementById("medicine").value;
    let date = document.getElementById("date").value;
    let patient = document.getElementById("patient").value;

    let Checkdate = new Date().toJSON().slice(0, 10);
    console.log(date);

    if ( date <= Checkdate ) {
        window.confirm("La fecha de caducidad es anterior a hoy");
    } else {

    let url =
        "mail=" +
        mail +
        "&session=" +
        session +
        "&idXip=" +
        xip +
        "&idMed=" +
        medicine +
        "&date=" +
        date +
        "&mailP=" +
        patient;

    http.open("POST", "http://localhost:3003/ServerFarmaceutica/Release");
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send(url);

    http.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            window.confirm(http.responseText);
        }
    };  
    }
}
function gestioPagina() {
    location.href = "Gestio.html"
}

function hacer_gets() {
    getPatients();
    getMedicines();
}

function getPatients() {
    let http = new XMLHttpRequest();
    
    let mail = sessionStorage.getItem("mail");
    let session = sessionStorage.getItem("session");

    http.open("GET","http://localhost:3003/ServerFarmaceutica/ServePatients?mail="+mail+"&session="+session,true);
    http.send();

    http.onreadystatechange=function(){
        if(this.readyState==4 && this.status==200){
            var json = JSON.parse(http.responseText);
            console.log(http.responseText);
            añadirOpcionesSelect("patient",json);
        }
    }
    
}

function getMedicines() {
    let http = new XMLHttpRequest();
    
    let mail = sessionStorage.getItem("mail");
    let session = sessionStorage.getItem("session");

    http.open("GET","http://localhost:3003/ServerFarmaceutica/ServeMedicines?mail="+mail+"&session="+session,true);
    http.send();

    http.onreadystatechange=function(){
        if(this.readyState==4 && this.status==200){
            var json = JSON.parse(http.responseText)
            añadirOpcionesSelect("medicine", json);
        }
    }
}

function añadirOpcionesSelect(selectA, json) {
    var select = document.getElementsByName(selectA)[0];

    if (select.length > 0){
        for (let i=select.option.length; 0 >= select.length; i--){
            select.remove(i);
        }    
    }

    for(let i=0; i < json.length; i++){ 
        let option = document.createElement("option"); 
        if (selectA == "patient") {
            option.innerHTML = json[i].mail;
        } else {
            option.value = json[i].id;
            option.textContent = json[i].name;            
        } 
        select.appendChild(option);
    }
}
