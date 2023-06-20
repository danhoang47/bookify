import { CheckStatus } from "@/utils/validation";
export async function VerifyJwt() {
  const url = "http://localhost:3001/user/verifyjwt";
  const options = {
    method: "POST",
    credentials: "include",
    withCredentials: true,
  };
  try {
    return await fetch(url, options).then((response) => {
      // console.log(response);
      if (CheckStatus(response.status)) return response.json();
      return CheckStatus(response.status);
    });
  } catch (error) {
    console.log(error);
  }
}
