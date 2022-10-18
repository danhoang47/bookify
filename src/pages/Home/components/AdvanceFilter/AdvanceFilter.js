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
import { faXmark } from "@fortawesome/free-solid-svg-icons";

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
        length: 9,
    },
    {
        title: "Giường",
        length: 9,
    },
    {
        title: "Phòng tắm",
        length: 9,
    },
];

const houseAndRoomTypes = [
    {
        id: 1,
        type: "Nhà",
        icon: faHouse,
    },
    {
        id: 2,
        type: "Căn hộ",
        icon: faBuilding,
    },
    {
        id: 3,
        type: "Khách sạn",
        icon: faHotel,
    },
];

const roomAndBedRoomInitialState = {
    "Phòng ngủ": null,
    Giường: null,
    "Phòng tắm": null,
};

function AdvanceFilter({ isAdvanceFilterOpen, setAdvanceFilterOpen }) {
    const [roomAndBedRoom, setRoomAndBedRoom] = useState(
        roomAndBedRoomInitialState
    );
    const [houseType, setHouseType] = useState(null);
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

    return (
        <div
            id={advanceFilterStyles["advance-filter"]}
            ref={containerRef}
            tabIndex={-1}
        >
            <div className={advanceFilterStyles["heading"]} tabIndex={-1}>
                <h4 className={advanceFilterStyles["filter-heading"]}>
                    Bộ lọc
                </h4>
            </div>
            <Box
                sx={{
                    marginTop: "54.11px",
                    overflowY: "scroll",
                    padding: "0 2em",
                    height: "46.5em",
                }}
            >
                <PriceRangePicker prices={prices} />
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
                    <button className={advanceFilterStyles["find-button"]}>
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
