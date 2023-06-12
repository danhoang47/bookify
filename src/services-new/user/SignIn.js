async function SignIn(username, password) {
    const url = "http://localhost:3001/user/login";
    const accountForm = new FormData();
    console.log("pass:"+password);
    accountForm.append("username", username);
    accountForm.append("password", password);
    const options = {
      method: "POST",
      body: accountForm,
    };
  
    try {
      return await fetch(url, options).then((response) => response.json());
    } catch (error) {
      console.log(error);
    }
  }
  
  export default SignIn;
  