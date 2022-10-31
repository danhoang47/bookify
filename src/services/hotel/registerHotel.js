import { types } from "@/services/hotel/searchHotelTypes";

export default async function registerHotel(
  amenities,
  basicHotelInfor,
  backgroundImage,
  roomImages,
  viewImages,
  extraInfor,
  roomInfor
) {
  const hotelForm = new FormData();
  const amenitiesId = [];
  const amenitiesNames = [];
  const amenitiesTypes = [];

  amenities.forEach((item, index) => {
    amenitiesId.push(item.id);
    amenitiesNames.push(item.name);
    amenitiesTypes.push(item.type);
  });

  const typeId = types.filter((item) => item.name === basicHotelInfor.type)[0]
    .code;

  hotelForm.append("hotelType", typeId);
  hotelForm.append("hotelName", basicHotelInfor.name);
  hotelForm.append("backgroundImage", backgroundImage);
  hotelForm.append("description", basicHotelInfor.description);
  hotelForm.append("country", basicHotelInfor.country);
  hotelForm.append("district", basicHotelInfor.province);
  hotelForm.append("city", basicHotelInfor.district);
  hotelForm.append("address", basicHotelInfor.address);
  hotelForm.append("amenitiesId", amenitiesId);
  hotelForm.append("amenitiesName", amenitiesNames);
  hotelForm.append("amenitiesTypes", amenitiesTypes);
  if (roomImages) {
    for (const file of roomImages) {
      hotelForm.append("hotelImage", file);
    }
  } else {
    hotelForm.append("hotelImage", null);
  }

  if (viewImages) {
    for (const file of viewImages) {
      hotelForm.append("viewImage", file);
    }
  } else {
    hotelForm.append("viewImage", null);
  }
  hotelForm.append(
    "checkin",
    extraInfor.checkin.hour + ":" + extraInfor.checkin.minutes
  );
  hotelForm.append(
    "checkout",
    extraInfor.checkout.hour + ":" + extraInfor.checkout.minutes
  );
  hotelForm.append(
    "closing",
    extraInfor.closing.hour + ":" + extraInfor.closing.minutes
  );
  hotelForm.append(
    "opening",
    extraInfor.opening.hour + ":" + extraInfor.opening.minutes
  );
  hotelForm.append("roomPrice", roomInfor.price);
  hotelForm.append("maxGuest", roomInfor.guests);
  hotelForm.append("bedroomNum", roomInfor.bedrooms);
  hotelForm.append("bathNum", roomInfor.bathrooms);
  hotelForm.append("bedNum", roomInfor.beds);
  hotelForm.append("isbathPrivate", roomInfor.isPrivateBathRoom);
  hotelForm.append("roomNum", roomInfor.numberOfRooms);
  hotelForm.append("userId", "b955c796-027c-4e11-92ff-1bb942a102c8");

  const data = await fetch(
    "http://localhost:8080/bookify/api/hotel/signhotel",
    {
      method: "POST",
      body: hotelForm,
    }
  )
    .then((res) => res.json())
    .then((result) => result);

  // const data = {
  //   amenities,
  //   basicHotelInfor,
  //   backgroundImage,
  //   roomImages,
  //   viewImages,
  //   extraInfor,
  //   roomInfor,
  // };

  return data;
}
