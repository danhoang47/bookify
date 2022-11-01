// fake data
import prices from "./prices";
import amenities from "./amenities";

// Components
import PriceRangePicker from "../PriceRangePicker";
import RoomAndBedRoomPicker from "../RoomAndBedRoomPicker";
import HouseAndRoomType from "../HouseAndRoomType";
import AmenitiesPicker from "../AmenitiesPicker";
import { useEffect, useState } from "react";
import { Box } from "@mui/material";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faBowlRice,
  faHouseChimney,
  faHouseCircleXmark,
  faTreeCity,
  faXmark,
} from "@fortawesome/free-solid-svg-icons";

// styles
import advanceFilterStyles from "./AdvanceFilter.module.scss";

// icon
import {
  faHouse,
  faBuilding,
  faHotel,
} from "@fortawesome/free-solid-svg-icons";

import { usePopup } from "@/utils/hooks";

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

function AdvanceFilter({
  isAdvanceFilterOpen,
  setAdvanceFilterOpen,
  setHotelsList,
}) {
  const [roomAndBedRoom, setRoomAndBedRoom] = useState(
    roomAndBedRoomInitialState
  );
  const [houseType, setHouseType] = useState(null);
  const [price, setPrice] = useState({});
  const [amenitiesPicked, setAmenitiesPicked] = useState([]);
  const [isOpen, handleClick, containerRef] = usePopup(isAdvanceFilterOpen);

  useEffect(() => {
    setAdvanceFilterOpen(isOpen);
    //eslint-disable-next-line
  }, [isOpen]);

  const removeAllSelected = (event) => {
    event.stopPropagation();
    setRoomAndBedRoom(roomAndBedRoomInitialState);
    setHouseType(null);
    setAmenitiesPicked([]);
  };

  const showRes = async () => {
    // console.log(houseType);
    // console.log(amenitiesPicked);
    // console.log(roomAndBedRoom);
    // console.log(price);
    const advanceFilterFormData = new FormData();
    advanceFilterFormData.append(
      "houseType",
      houseType != null ? houseType : ""
    );
    advanceFilterFormData.append(
      "amenitiesPicked",
      amenitiesPicked.length > 0 ? amenitiesPicked : []
    );
    advanceFilterFormData.append(
      "rooms",
      roomAndBedRoom.rooms > 0 ? roomAndBedRoom.rooms : 0
    );
    advanceFilterFormData.append(
      "numberOfBed",
      roomAndBedRoom.numberOfBed > 0 ? roomAndBedRoom.numberOfBed : 0
    );
    advanceFilterFormData.append(
      "numberOfBathroom",
      roomAndBedRoom.numberOfBathroom > 0 ? roomAndBedRoom.numberOfBathroom : 0
    );
    advanceFilterFormData.append("min", price.min ? parseInt(price.min) : 0);
    advanceFilterFormData.append("max", price.max ? parseInt(price.max) : 0);

    const data = await fetch(
      "http://localhost:8080/bookify/api/hotel/filteradvanced",
      {
        method: "POST",
        body: advanceFilterFormData,
      }
    )
      .then((res) => res.json())
      .then((result) => result);

    setHotelsList(data);
    setAdvanceFilterOpen(false);
  };

  return (
    <div
      id={advanceFilterStyles["advance-filter"]}
      ref={containerRef}
      tabIndex={-1}
    >
      <div className={advanceFilterStyles["heading"]} tabIndex={-1}>
        <h4 className={advanceFilterStyles["filter-heading"]}>Bộ lọc</h4>
      </div>
      <Box
        sx={{
          marginTop: "54.11px",
          overflowY: "scroll",
          padding: "0 2em",
          height: "76vh",
        }}
      >
        <PriceRangePicker prices={prices} setPrice={setPrice} />
        <RoomAndBedRoomPicker
          pickers={pickers}
          roomAndBedRoom={roomAndBedRoom}
          onSelect={setRoomAndBedRoom}
        />
        <HouseAndRoomType
          houseAndRoomTypes={houseAndRoomTypes}
          currentType={houseType}
          handlePicked={setHouseType}
        />
        <AmenitiesPicker
          amenitiesList={amenities}
          amenitiesPicked={amenitiesPicked}
          setAmenitiesPicked={setAmenitiesPicked}
        />
      </Box>
      <div className={advanceFilterStyles["footer"]}>
        <Box>
          <button
            className={advanceFilterStyles["reset-button"]}
            onClick={removeAllSelected}
          >
            Xóa tất cả
          </button>
        </Box>
        <Box
          sx={{
            justifyContent: "flex-end",
          }}
        >
          <button
            className={advanceFilterStyles["find-button"]}
            onClick={showRes}
          >
            Hiển thị kết quả
          </button>
        </Box>
      </div>
      <button
        className={advanceFilterStyles["close-button"]}
        onClick={handleClick}
      >
        <FontAwesomeIcon icon={faXmark} />
      </button>
    </div>
  );
}

export default AdvanceFilter;
