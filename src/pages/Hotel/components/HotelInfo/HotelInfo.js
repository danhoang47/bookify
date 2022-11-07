import HotelInfoStyle from "./HotelInfo.module.scss";
import InfoHeader from "../InfoHeader";
import Details from "../Details";
import { useState } from "react";

function HotelInfo({ hotelInfo }) {
  const {
    hotelId,
    description,
    hotelAmenities,
    hotelOwner,
    hotelName,
    rating,
    reviews,
    roomType,
    isBookmarked,
  } = hotelInfo;

  return (
    <div>
      <div className={HotelInfoStyle["header"]}>
        <InfoHeader
          reviews={reviews}
          roomType={roomType}
          hotelName={hotelName}
          rating={rating}
          isBookmarked={isBookmarked}
          hotelId={hotelId}
        />
      </div>
      <div className={HotelInfoStyle["details"]}>
        <Details
          reviews={reviews}
          description={description}
          hotelAmenities={hotelAmenities}
          hotelOwner={hotelOwner}
          roomType={roomType}
          rating={rating}
        />
      </div>
    </div>
  );
}

export default HotelInfo;
