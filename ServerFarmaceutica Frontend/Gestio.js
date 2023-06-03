function logOut() {
    sessionStorage.removeItem("mail");
    sessionStorage.removeItem("session");
    location.href = "Login.html";
}

function getTable() {
    let http = new XMLHttpRequest();
    
    let mail = sessionStorage.getItem("mail");
    let session = sessionStorage.getItem("session");

    http.open("GET","http://localhost:3003/ServerFarmaceutica/ServeXips?mail="+mail+"&session="+session,true);
    http.send();

    http.onreadystatechange=function(){
        if(this.readyState==4 && this.status==200){
            document.getElementById("tabla").innerHTML=http.responseText;
        }
    }
}

function altaPagina() {
    location.href = "Alta.html";
}
