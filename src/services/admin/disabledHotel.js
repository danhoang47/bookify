export default async function disabledHotel(hotelId) {
  const url = `http://localhost:8080/bookify/api/dashboard/disabled/${hotelId}`;
  const data = await fetch(url, { method: "PUT" })
    .then((res) => res.json())
    .then((data) => data);
  return data;
}
