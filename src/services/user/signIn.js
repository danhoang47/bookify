
async function signIn(username, password) {
    const url = "http://localhost:8080/testUpload/api/user/login";
    const accountForm = new FormData();
    accountForm.append('username', username);
    accountForm.append('password', password);
    const options = {
        method: 'POST',
        mode: 'no-cors',
        body: accountForm
    }

    try {
        return await fetch(url, options).then(response => response.json())
    } catch(error) {
        console.log(error);
    }
}

export default signIn;