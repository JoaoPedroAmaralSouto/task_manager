
function takeUserEmail(){

}

fetch("http://localhost:8080/api/user", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
                title: title,
                description: description,
                status: status,
                email: email
            }
        )
    }
)
.then(response => {
        if(!response.ok)
            throw new Error ("Could not create the task")
        return response.json()
    }   
)
.then(data => {
        console.log("Task created successfully: ", data)
    }
)
.catch(error => {
          console.log("Error: ", error)
    }
)