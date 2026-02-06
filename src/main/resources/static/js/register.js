let register = document.getElementById("registerForm");

register.addEventListener("submit", registerFunciton)

function registerFunciton(event){
    event.preventDefault();

    const email = document.getElementById("emailRegister").value;
    const password = document.getElementById("passwordRegister").value;
    const username = document.getElementById("usernameRegister").value;
    const companyName = document.getElementById("companyNameRegister").value
    fetch("http://localhost:8080/api/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            user: {
                username: username,
                email: email,
                password: password
            },
            company: {
                companyName: companyName
            }
        })
    })
    .then(response => {
            if (!response.ok) {
                throw new Error("Erro na requisição");
            }

            console.log("Deu tudo certo!");
        }   
    )
    .catch(error => {
            console.log("Error:", error);
        }
    )
}