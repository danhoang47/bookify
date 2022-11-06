
export default async function getHotelByOwnerId(ownerId) {
    const url = `http://localhost:8080/bookify/api/hotel/owner/${ownerId}`;
    const data = await fetch(url).then(res => res.json()).then(data => data);
    return data;
}