import { faAirbnb } from "@fortawesome/free-brands-svg-icons";
import { faGrin } from "@fortawesome/free-regular-svg-icons";
import {
  faUmbrellaBeach,
  faPanorama,
  faHouseChimney,
  faHotel,
  faWaterLadder,
  faMugHot,
  faBuilding,
  faHouseCircleCheck,
  faBuildingNgo,
  faHouseChimneyMedical,
  faFireBurner,
  faFireFlameSimple,
  faDumbbell,
  faAirFreshener,
  faWind,
  faPooBolt,
  faBowlFood,
  faBraille,
} from "@fortawesome/free-solid-svg-icons";

/**
 * Căn hộ / Nhà / Phục vụ bữa sáng / Thiết kế riêng /
 */

const categories = [
  {
    filterType: "hotel",
    name: "Căn hộ",
    filterTypeId: "91bb91b4-adcb-45ba-a0bf-1669a4d5878f",
    icon: faBuilding,
  },
  {
    filterType: "hotel",
    name: "Nhà nhỏ",
    filterTypeId: "314ec1bd-204b-4e8e-8b3a-098b4cc110fa",
    icon: faHouseChimney,
  },
  {
    filterType: "hotel",
    name: "Phục vụ bữa sáng",
    filterTypeId: "5403d2ea-0c94-44b8-81f7-7b4986051570",
    icon: faMugHot,
  },
  {
    filterType: "hotel",
    name: "Thiết kế riêng",
    filterTypeId: "9d5050f0-3d2f-4c80-a6f5-d82a215dda21",
    icon: faBuildingNgo,
  },
  {
    filterType: "hotel",
    name: "Khách sạn",
    filterTypeId: "afbd51cf-8353-4303-ab47-f0785dc9c34f",
    icon: faHotel,
  },
  {
    filterType: "hotel",
    name: "Resort",
    filterTypeId: "39ed093c-720a-4fc7-aca5-70b15d338da8",
    icon: faHouseChimneyMedical,
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
