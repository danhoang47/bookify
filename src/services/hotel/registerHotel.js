import { types } from "./searchHotelTypes";

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

  amenities.forEach((item) => {
    amenitiesId.push(item.id);
    amenitiesNames.push(item.name);
    if (item.type) {
      amenitiesTypes.push(item.type);
    } else {
      amenitiesTypes.push(item.amenityTypeId);
    }
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
      console.log(file);
      hotelForm.append("hotelImage", file);
    }
  } else {
    hotelForm.append("hotelImage", null);
  }

  if (viewImages) {
    for (const filetest of viewImages) {
      console.log(filetest);
      hotelForm.append("viewImage", filetest);
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
  hotelForm.append("isCamera", extraInfor.isAllowPet);
  hotelForm.append("isAnimalAccept", extraInfor.isHasCamera);
  hotelForm.append("isbathPrivate", roomInfor.isPrivateBathRoom);
  hotelForm.append("bathNum", roomInfor.numberOfBathroom);
  hotelForm.append("bedNum", roomInfor.numberOfBed);
  hotelForm.append("roomPrice", roomInfor.price);
  hotelForm.append("maxGuest", roomInfor.numberOfGuests);
  hotelForm.append("bedroomNum", roomInfor.numberOfRoom);
  hotelForm.append("roomNum", roomInfor.rooms);

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
  //   amenitiesId,
  //   amenitiesNames,
  //   amenitiesTypes,
  // };

  // console.log(data);

  return data;
}
