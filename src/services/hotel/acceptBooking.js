export default async function acceptBooking(bookingId, type) {
  const url = `http://localhost:${process.env.REACT_APP_BACK_END_PORT}/dashboard/booking/${type}/${bookingId}`;
  const data = await fetch(url, {
    method: "PUT",
    credentials: "include",
    withCredentials: true,
  })
    .then((res) => res.json())
    .then((data) => data);
  return data;
}
