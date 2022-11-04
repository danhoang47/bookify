export default async function deleteHotelFromBookmark(hotelId, userId) {
    const url = `http://localhost:8080/bookify/api/user/bookmark/?hotelId=${hotelId}&userId=${userId}`
    const data = await fetch(url, { method: 'DELETE' })
                        .then(res => res.json())
                        .then(data => data);

    return data;
}