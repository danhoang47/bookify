
export default async function markNotifAsRead(notifId) {
    console.log(notifId);
    const url = `http://localhost:8080/bookify/api/user/notification/${notifId}`;
    const data = await fetch(url, { method: 'PUT' }).then(res => res.json()).then(data => data);
    return data;
}