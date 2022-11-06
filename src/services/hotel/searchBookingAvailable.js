import { format } from "date-fns";

export default async function searchBookingAvailable(selectedDays, hotelId) {
    const options = "yyyy-MM-dd";
    const checkin = format(selectedDays.from, options);
    const checkout = format(selectedDays.to, options);
    const url = `http://localhost:8080/bookify/api/hotel/checkrange?checkin=${checkin}&checkout=${checkout}&hotelId=${hotelId}`;
    const data = await fetch(url)
        .then((res) => res.json())
        .then((data) => data);
    return data;
}
