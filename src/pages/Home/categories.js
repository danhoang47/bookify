import { faAirbnb } from "@fortawesome/free-brands-svg-icons";
import { faGrin } from "@fortawesome/free-regular-svg-icons";
import {
  faHotel,
  faWaterLadder,
  faCampground,
  faBowlFood,
  faBowlRice,
  faSwimmingPool,
  faFireFlameSimple,
  faDumbbell,
  faAirFreshener,
  faWind,
  faPooBolt,
  faBraille,
} from "@fortawesome/free-solid-svg-icons";

/**
 * Căn hộ / Nhà / Phục vụ bữa sáng / Thiết kế riêng /
 */

const categories = [
  {
    filterType: "hotel",
    filterTypeId: "64724a36c56247dbde3ca5e9",
    name: "Cam trai 5",
    icon: faCampground,
  },
  {
    filterType: "hotel",
    name: "BBQ 2",
    filterTypeId: "64724a36c56247dbde3ca5ea",
    icon: faBowlFood,
  },
  {
    filterType: "hotel",
    name: "Restaurant",
    filterTypeId: "6475ca1dcc850fed9ef393da",
    icon: faBowlRice,
  },
  {
    filterType: "hotel",
    name: "Pool",
    filterTypeId: "649a51ae5c7e584fe505376b",
    icon: faSwimmingPool,
  },
  {
    filterType: "hotel",
    name: "Khách sạn",
    filterTypeId: "afbd51cf-8353-4303-ab47-f0785dc9c34f",
    icon: faHotel,
  },
  {
    filterType: "amenity",
    name: "BBQ",
    filterTypeId: "6ac90bf9-0a08-48c2-a2aa-3092c6f3574f",
    icon: faFireFlameSimple,
  },
  {
    filterType: "amenity",
    name: "Phòng Gym",
    filterTypeId: "56b783ce-d881-4287-b0e7-0a8f8f6140cf",
    icon: faDumbbell,
  },
  {
    filterType: "amenity",
    name: "Hồ bơi",
    filterTypeId: "01d8e6d4-bd0e-49d4-b21a-660cd2809184",
    icon: faWaterLadder,
  },
  {
    filterType: "amenity",
    name: "Điều Hòa",
    filterTypeId: "d26df333-4df2-4e84-94ee-80eaa6bd97a9",
    icon: faWind,
  },
  {
    filterType: "amenity",
    name: "Bàn Bia",
    filterTypeId: "fe384dc7-b633-4ed8-a86e-60a2fb4705f0",
    icon: faBraille,
  },
];

export default categories;
