
export default async function getAmount(userId) {
    const url = `http://localhost:8080/bookify/api/user/amount/${userId}`;
    const data = await fetch(url).then(res => res.json()).then(data => data);
    return data;
}