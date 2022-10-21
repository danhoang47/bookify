async function signIn(username, password) {
  const url = "http://localhost:8080/testUpload/rest/user_detail/login";
  const accountForm = new FormData();
  accountForm.append("username", username);
  accountForm.append("password", password);
  const options = {
    method: "POST",
    body: accountForm,
  };

  return await fetch(url, options).then((response) => response.json());
}

export default signIn;
