import { format } from "date-fns";

export default async function bookingRoom(selectDays, guests, userId, hotelId) {
    const { from, to } = selectDays;
    const { adult, child, infant, pet } = guests;
    const dateFormatOption = "yyyy-MM-dd";
    const queryParams = `userId=${userId}&checkin=${format(
        from,
        dateFormatOption
    )}&checkout=${format(
        to,
        dateFormatOption
    )}&adult=${adult}&child=${child}&infant=${infant}&pet=${pet}`;
    const url = `http://localhost:8080/bookify/api/hotel/booking/${hotelId}/?${queryParams}`.trim();
    const data = await fetch(url, { method: 'POST' }).then(res => res.json()).then(data => data);
    return data;
}
