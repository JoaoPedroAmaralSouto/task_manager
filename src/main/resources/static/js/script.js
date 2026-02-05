let buttonLogin = document.getElementById("loginForm");

buttonLogin.addEventListener("submit", Login);

function Login(event){
    event.preventDefault();

    let email = document.getElementById("emailLogin").value;
    let password = document.getElementById("passwordLogin").value;

    fetch("http://localhost:8080/api/login", {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify({
                email: email,
                password: password
            })
        }
    )
    .then(response =>{
            if(!response.ok){
                throw new Error ("Invalid login");
            }
            changePage();
            return response.json();
        }
    )
    .then(data => {
            console.log("Valid login:", data)
        }
    )
    .catch(error =>{
            console.log("Error: ", error)
        }
    )
}   

function changePage(){
    window.location.href = "/Pages/minhasTarefas.html";
}