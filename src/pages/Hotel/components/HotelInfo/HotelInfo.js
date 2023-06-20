import HotelInfoStyle from "./HotelInfo.module.scss";
import InfoHeader from "../InfoHeader";
import Details from "../Details";
import { useContext, useEffect, useState } from "react";
import { reviewDataContext } from "../../Hotel";

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
    signAt,
    isVerified,
  } = hotelInfo;
  console.log(isVerified);
  console.log(hotelInfo);
  return (
    <div>
      <div className={HotelInfoStyle["header"]}>
        {/* <InfoHeader
          reviews={reviews}
          roomType={roomType}
          hotelName={hotelName}
          rating={rating}
          isBookmarked={isBookmarked}
          hotelId={hotelId}
          isVerified={isVerified}
        /> */}
      </div>
      <div className={HotelInfoStyle["details"]}>
        <Details
          reviews={reviews}
          description={description}
          hotelAmenities={hotelAmenities}
          hotelOwner={hotelOwner}
          roomType={roomType}
          rating={rating}
          hotelId={hotelId}
          signAt={signAt}
        />
      </div>
    </div>
  );
}

export default HotelInfo;
