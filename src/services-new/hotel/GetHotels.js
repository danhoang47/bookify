export default async function GetHotels() {
  const url = `http://localhost:3001/hotel/`;
  const option = {
    method: "GET",
    credentials: "include",
    withCredentials: true,
  };

  try {
    return await fetch(url, option).then((resp) => {
      return resp.json();
    });
  } catch (e) {
    console.log(e);
  }
}
