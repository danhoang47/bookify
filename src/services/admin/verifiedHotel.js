export default async function verifiedHotel(hotelId) {
  const url = `http://localhost:8080/bookify/api/dashboard/verified/${hotelId}`;
  const data = await fetch(url, { method: "PUT" })
    .then((res) => res.json())
    .then((data) => data);
  return data;
}
