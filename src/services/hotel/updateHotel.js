const varToString = (varObj) => Object.keys(varObj)[0];
const mergeTime = (timeObj) => {
    const { hour, minutes } = timeObj;
    return `${hour}:${minutes}`;
};
const pushFilesToFormData = (formData, fileList, name) => {
  if ( fileList !== null ) {
    Array.from(fileList).forEach((file, index) => {
      console.log(index);
      formData.append(name, file);
    })
  }
}

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
    console.log(extraInforModified);
    const hotelUpdateForm = new FormData();
    hotelUpdateForm.append(
        varToString({ amenities }),
        JSON.stringify(amenities)
    );
    hotelUpdateForm.append(
        varToString({ basicHotelInfor }),
        JSON.stringify(basicHotelInfor)
    );
    hotelUpdateForm.append(
        varToString({ extraInfor }),
        JSON.stringify(extraInforModified)
    );
    hotelUpdateForm.append(varToString({ backgroundImage }), backgroundImage);
    hotelUpdateForm.append(
      varToString({ roomInfor }), JSON.stringify(roomInfor)
    );
    hotelUpdateForm.append(
      varToString({ deletedImages }), JSON.stringify(deletedImages)
    );
    pushFilesToFormData(hotelUpdateForm, updatedViewImages, varToString({ updatedViewImages }))
    // pushFilesToFormData(hotelUpdateForm, updatedRoomImages, varToString({ updatedRoomImages }))

    const data = await fetch(url, {
        method: "PUT",
        body: hotelUpdateForm,
    });
}
