
async function signIn(username, password) {
    const url = "https://jsonplaceholder.typicode.com/posts";
    const accountForm = new FormData();
    accountForm.append('username', username);
    accountForm.append('password', password);
    const options = {
        method: 'POST',
        mode: 'no-cors',
        body: accountForm
    }

    return await fetch(url).then(response => response.json())
}

export default signIn;