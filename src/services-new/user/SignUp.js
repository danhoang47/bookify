async function SignUp(username, email, password) {
  const url = "http://localhost:3001/user";
  const accountForm = new FormData();
  accountForm.append("username", username);
  accountForm.append("email", email);
  accountForm.append("password", password);
  const options = {
    method: "POST",
    body: accountForm,
  };

  return await fetch(url, options).then((response) => response.json());
}

export default SignUp;
