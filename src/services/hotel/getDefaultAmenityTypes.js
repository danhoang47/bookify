
export default async function getDefaultAmenityTypes() {
    const data = await fetch("http://localhost:8080/bookify/api/amenity/type")
        .then((res) => res.json())
        .then(defaultAmenityTypes => defaultAmenityTypes);
    return data;
}
