function send() {
    let http = new XMLHttpRequest();
    
    let user = document.getElementById("user").value;
    let pass = document.getElementById("password").value;

    http.open("GET","http://localhost:3003/ServerFarmaceutica/Login?user="+user+"&pass="+pass,true);
    http.send();

    http.onreadystatechange=function(){
        if(this.readyState==4 && this.status==200){
            if (http.responseText == "Error") {
                window.confirm("Hay un error en los datos")
            } else {
                sessionStorage.setItem("session", http.responseText);
                sessionStorage.setItem("mail", document.getElementById("user").value);
                location.href = "Gestio.html";
            }
        }
    }
}

function registerPagina() {
    location.href = "Register.html";    
}
