export default async function rejectBooking(bookingId) {
    const url = `http://localhost:8080/bookify/api/hotel/booking/?id=${bookingId}&action=reject`;
    const data = await fetch(url, { method: 'PUT' }).then(res => res.json()).then(data => data);
    return data;
}