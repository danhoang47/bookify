
const getType = (type) => {
    switch(type) {
        case "1": return 'pending';
        case "2": return 'booked';
        case "3": return 'checkout';
        default: return ''
    }
}

export default async function getAllBooking(hotelId, type) {
   const url = `http://localhost:8080/bookify/api/hotel/allbooking/?id=${hotelId}&type=${getType(type)}`
   const data = await fetch(url).then(res => res.json()).then(data => data);
   return data;
}