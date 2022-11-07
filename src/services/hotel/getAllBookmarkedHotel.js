
export default async function getAllBookmarkedHotel(userId) {
    const url = `http://localhost:8080/bookify/api/hotel/bookmark/${userId}`;
    const data = fetch(url).then(res => res.json()).then(data => data);
    return data;
}