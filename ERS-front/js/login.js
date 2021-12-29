
document.getElementById("submitBtn").addEventListener("click", login);
let api = "http://localhost:8080";

function login(){
    document.getElementById("error").innerHTML = "";

    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    let xhr = new XMLHttpRequest();
    xhr.open("POST", `${api}/auth`);

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            
            let authToken = xhr.getResponseHeader("Authorization");

            sessionStorage.setItem("token", authToken);

            let tok = sessionStorage.getItem("token");

         
    
            if(tok.split(":")[1] === "2"){

                window.location.href = "manager.html";
            } else {
                window.location.href="employee.html";
            }
            

        } else if (xhr.readyState === 4){
            document.getElementById("error").innerHTML = "Unable to login.";
           }

        }

        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        let requestBody = `username=${username}&password=${password}`;

        xhr.send(requestBody);
       
}



