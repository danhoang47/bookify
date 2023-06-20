export default async function LogOut() {
    console.log(_id);
    const url = `http://localhost:3001/user/logout`;
    const option = {
      method: "GET",
      credentials: "include",
      withCredentials: true,
    };
  
    try {
      return await fetch(url, option).then((response) => {
        if (CheckStatus(response.status)) return response.json();
        return CheckStatus(response.status);
      });
    } catch (error) {
      console.log(error);
    }
  }
  