// icon
import {
    faHouse,
    faBuilding,
    faHotel,
    faBowlRice,
    faHouseCircleXmark,
    faTreeCity,
} from "@fortawesome/free-solid-svg-icons";

const pickers = [
    {
        title: "Phòng ngủ",
        name: "rooms",
        length: 9,
    },
    {
        title: "Giường",
        name: "numberOfBed",
        length: 9,
    },
    {
        title: "Phòng tắm",
        name: "numberOfBathroom",
        length: 9,
    },
];

const houseAndRoomTypes = [
    {
        id: "314ec1bd-204b-4e8e-8b3a-098b4cc110fa",
        type: "Nhà",
        icon: faHouse,
    },
    {
        id: "91bb91b4-adcb-45ba-a0bf-1669a4d5878f",
        type: "Căn hộ",
        icon: faBuilding,
    },
    {
        id: "afbd51cf-8353-4303-ab47-f0785dc9c34f",
        type: "Khách sạn",
        icon: faHotel,
    },
    {
        id: "39ed093c-720a-4fc7-aca5-70b15d338da8",
        type: "Resort",
        icon: faHouseCircleXmark,
    },
    {
        id: "5403d2ea-0c94-44b8-81f7-7b4986051570",
        type: "Phục vụ bữa sáng",
        icon: faBowlRice,
    },
    {
        id: "9d5050f0-3d2f-4c80-a6f5-d82a215dda21",
        type: "Thiết kế riêng",
        icon: faTreeCity,
    },
];

const roomAndBedRoomInitialState = {
    rooms: 0,
    numberOfBed: 0,
    numberOfBathroom: 0,
};

const priceInitState = {
    min: 0,
    max: 1000,
}

export { pickers, houseAndRoomTypes, roomAndBedRoomInitialState, priceInitState };
