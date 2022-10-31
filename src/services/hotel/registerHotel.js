export default async function registerHotel(amenities, basicHotelInfor, backgroundImage, roomImages, viewImages, extraInfor, roomInfor) {
    const hotelForm = new FormData();
    const amenitiesId = [];
    const amenitiesNames = [];
    const amenitiesTypes = [];

    amenities.forEach((item) => {
      amenitiesId.push(item.id);
      amenitiesNames.push(item.name);
      amenitiesTypes.push(item.type);
    });

    hotelForm.append("hotelType", basicHotelInfor.type);
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
    for (const file of roomImages) {
      hotelForm.append("hotelImage", file);
    }

    for (const file of viewImages) {
      hotelForm.append("viewImage", file);
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
    hotelForm.append("roomNum", roomInfor.bedrooms);
    hotelForm.append("bathNum", roomInfor.bathrooms);
    hotelForm.append("bedNum", roomInfor.beds);
    hotelForm.append("isbathPrivate", roomInfor.isPrivateBathRoom);
    hotelForm.append("userId", "f96e5e7e-7542-48be-8829-5ae701431d29");

    const data = await fetch("http://localhost:8080/testUpload/rest/hotel/signhotel", {
      method: "POST",
      body: hotelForm,
    })
      .then((res) => res.json())
      .then((result) => result);

    return data;
}
