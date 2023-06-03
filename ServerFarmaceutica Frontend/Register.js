function register() {
    let http = new XMLHttpRequest();

    if (document.getElementById("password").value == document.getElementById("Cpassword").value) {
        let url =
        "user=" +
        document.getElementById("user").value +
        "&pass=" +
        document.getElementById("password").value +
        "&name="+
        document.getElementById("name").value;

        http.open("POST", "http://localhost:3003/ServerFarmaceutica/Login");
        http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        http.send(url);

        http.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
            window.confirm(http.responseText);
            }
        };  
    } else {
        window.confirm("Las contraseñas no son iguales");
    }
}

function loginPagina() {
    location.href = "Login.html";
}
