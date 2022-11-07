
const getType = (type) => {
    switch(type) {
        case 1: return "all";
        default: throw new Error("Invalid Notification Type");
    }
}


export default async function getNotification(userId, type) {
    const url = `http://localhost:8080/bookify/api/user/notification/?userId=${userId}&type=${getType(type)}`
    const data = await fetch(url).then(res => res.json()).then(data => data);
    return data; 
}