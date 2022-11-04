import { types } from "@/services/hotel/searchHotelTypes";

const varToString = (varObj) => Object.keys(varObj)[0];
const mergeTime = (timeObj) => {
    const { hour, minutes } = timeObj;
    return `${hour}:${minutes}`;
};
const pushFilesToFormData = (formData, fileList, name) => {
    if (fileList !== null) {
        Array.from(fileList).forEach((file) => {
            formData.append(name, file);
        });
    }
};

export default async function updateHotel(
    hotelId,
    amenities,
    basicHotelInfor,
    backgroundImage,
    extraInfor,
    roomInfor,
    updatedViewImages,
    updatedRoomImages,
    deletedImages
) {
    const url = `http://localhost:8080/bookify/api/hotel/update/${hotelId}`;
    const extraInforModified = Object.keys(extraInfor).reduce((prev, key) => {
        if (typeof extraInfor[key] === "object") {
            return {
                ...prev,
                [key]: mergeTime(extraInfor[key]),
            };
        } else {
            return {
                ...prev,
                [key]: extraInfor[key],
            };
        }
    }, {});
    const updatedBasicHotelInfor = {
        hotelName: basicHotelInfor.name,
        hotelTypeId: types.find(({ name }) => name === basicHotelInfor.type)
            .code,
        country: basicHotelInfor.country,
        city: basicHotelInfor.province,
        district: basicHotelInfor.district,
        address: basicHotelInfor.address,
        description: basicHotelInfor.description,
    };

    const hotelUpdateForm = new FormData();
    const rawObject = {
        amenities,
        updatedBasicHotelInfor,
        extraInforModified,
        roomInfor,
        deletedImages,
    };
    Object.keys(rawObject).forEach((key) => {
        hotelUpdateForm.append(key, JSON.stringify(rawObject[key]));
    });

    hotelUpdateForm.append(varToString({ backgroundImage }), backgroundImage);
    pushFilesToFormData(
        hotelUpdateForm,
        updatedViewImages,
        varToString({ updatedViewImages })
    );
    pushFilesToFormData(
        hotelUpdateForm,
        updatedRoomImages,
        varToString({ updatedRoomImages })
    );

    const data = await fetch(url, {
        method: "PUT",
        body: hotelUpdateForm,
    });
}
