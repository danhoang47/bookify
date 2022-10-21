 
export default async function searchProvinces(searchTerm) {
    const url = `https://provinces.open-api.vn/api/p/search/?q=${searchTerm}`;
    return await fetch(url).then(response => response.json());
}