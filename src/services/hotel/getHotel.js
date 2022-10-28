
export default async function getHotel(id) {
    try {
        const data = await fetch(`http://localhost:8080/bookify/api/hotel/?id=${id}`)
                        .then(res => res.json())
                        .then(data => data);
        return data;
    } catch (error) {
        throw new Error(error);
    }
}