export default async function FilterHotel(
    roomAndBedRoom,
    houseType,
    price,
    amenitiesPicked
) {
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
        roomAndBedRoom.numberOfBathroom > 0
            ? roomAndBedRoom.numberOfBathroom
            : 0
    );
    advanceFilterFormData.append("min", price.min ? parseInt(price.min) : 0);
    advanceFilterFormData.append("max", price.max ? parseInt(price.max) : 0);

    const data = await fetch(
        "http://localhost:3001/bookify/api/hotel/filteradvanced",
        {
            method: "POST",
            body: advanceFilterFormData,
        }
    )
        .then((res) => res.json())
        .then((result) => result);
    
    return data;
}
