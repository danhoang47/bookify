
export default async function markAllNotifAsRead(userId) {
    const url = `http://localhost:8080/bookify/api/user/notification/all/${userId}`;
    const data = await fetch(url, { method: 'PUT' }).then(res => res.json()).then(data => data);
    return data;
} 