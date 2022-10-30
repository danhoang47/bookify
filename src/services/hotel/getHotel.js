
const timeFormat = (time) => {
    const timeSplitted = time.split(":");
    return {
        hour: timeSplitted[0],
        minutes: timeSplitted[1]
    }
}

export default async function getHotel(id) {
    try {
        const data = await fetch(
            `http://localhost:8080/bookify/api/hotel/?id=${id}`
        )
            .then((res) => res.json())
            .then((data) => data);
        const {
            hotelId,
            userId,
            hotelTypeId,
            hotelName,
            backgroundImage,
            isVerified,
            isAllowPet,
            isHasCamera,
            description,
            country,
            district,
            city,
            address,
            closing, 
            opening,
            checkin,
            checkout,
            hotelAmenities,
            images,
            roomType
        } = data;
        const basicHotelInfor = {
            name: hotelName,
            // id: hotelId,
            type: hotelTypeId,
            country: country,
            district: district,
            province: city,
            address: address,
            description: description
        }
        const roomInfor = {
            guests: roomType.numberOfGuests,
            bedrooms: roomType.numberOfRoom,
            beds: roomType.numberOfBed,
            bathrooms: roomType.numberOfBathroom,
            price: roomType.price,
            numberOfRooms: roomType.rooms,
            isPrivateBathRoom: roomType.isPrivateBathroom,
        }
        const extraInfor = {
            isHasCamera,
            isAllowPet,
            checkin: timeFormat(checkin),
            checkout: timeFormat(checkout),
            closing: timeFormat(closing),
            opening: timeFormat(opening)
        }
        const viewImages = images.filter(({ type }) => type === 0);
        const roomImages = images.filter(({ type }) => type === 1);

        return {
            basicHotelInfor,
            roomInfor,
            extraInfor,
            viewImages,
            backgroundImage,
            roomImages,
            hotelAmenities
        };
    } catch (error) {
        throw new Error(error);
    }
}
