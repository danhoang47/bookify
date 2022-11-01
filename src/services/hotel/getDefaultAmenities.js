
export default async function getDefaultAmenities() {
    const data = await fetch("http://localhost:8080/bookify/api/amenity")
        .then((res) => res.json())
        .then((defautlAmenities) => defautlAmenities);
    return data;
}
