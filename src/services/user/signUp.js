async function signUp(username, email, password) {
  const url = "http://localhost:8080/bookify/api/user/signup";
  const accountForm = new FormData();
  accountForm.append("username", username);
  accountForm.append("password", password);
  accountForm.append("email", email);
  const options = {
    method: "POST",
    body: accountForm,
  };

  return await fetch(url, options).then((response) => response.json());
}

export default signUp;
