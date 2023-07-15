// icon
import {
  faCampground,
  faBowlFood,
  faBowlRice,
  faSwimmingPool,
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
    id: "64724a36c56247dbde3ca5e9",
    type: "Cam trai 5",
    icon: faCampground,
  },
  {
    id: "64724a36c56247dbde3ca5ea",
    type: "BBQ 2",
    icon: faBowlFood,
  },

  {
    id: "6475ca1dcc850fed9ef393da",
    type: "Restaurant",
    icon: faBowlRice,
  },
  {
    id: "649a51ae5c7e584fe505376b",
    type: "Pool",
    icon: faSwimmingPool,
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
};

export {
  pickers,
  houseAndRoomTypes,
  roomAndBedRoomInitialState,
  priceInitState,
};
