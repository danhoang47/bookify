export async function VerifyJwt() {
  const url = "http://localhost:3001/user/verifyjwt";
  const options = {
    method: "POST",
    credentials: "include",
    withCredentials: true,
  };
  try {
    return await fetch(url, options).then((response) => {
      // console.log(response);()
      if (response.status !== 200) return response.status;
      return response.json();
    });
  } catch (error) {
    console.log(error);
  }
}
