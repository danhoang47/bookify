
async function getExplorePlaces(searchTerm) {
    const url = `https://provinces.open-api.vn/api/p/search/?q=${searchTerm}`;
    const data = await fetch(url).then(response => response.json());

    return await data;
}

export default getExplorePlaces;