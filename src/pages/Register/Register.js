import { Jumbotron, TabBar } from "./components";
import { Grid, Box } from "@mui/material";
import { RegisterContext } from "@/utils/contexts";
import registerStyles from "./Register.module.scss";
import { useState, useMemo, Suspense } from "react";
import tabs from "./tabs";
import {
  basicHotelInforInitState,
  roomInfoInitState,
  extraInforInitState,
} from "./registerInitStates";

function Register() {
  // show BasicInformation first
  const [inputTabIndex, setInputTabIndex] = useState(0);
  const [basicHotelInfor, setBasicHotelInfo] = useState(
    basicHotelInforInitState
  );
  const [isNextTabValid, setNextTabValid] = useState(false);
  const [amenities, setAmenities] = useState([]);
  const [roomInfor, setRoomInfor] = useState(roomInfoInitState);
  const [viewImages, setViewImages] = useState();
  const [roomImages, setRoomImages] = useState();
  const [backgroundImage, setBackgroundImage] = useState();
  const [extraInfor, setExtraInfor] = useState(extraInforInitState);
  const registerContextValue = useMemo(
    () => ({
      basicHotelInfor,
      setBasicHotelInfo,
      amenities,
      setAmenities,
      roomInfor,
      setRoomInfor,
      viewImages,
      setViewImages,
      roomImages,
      setRoomImages,
      backgroundImage,
      setBackgroundImage,
      extraInfor,
      setExtraInfor,
    }),
    [
      basicHotelInfor,
      amenities,
      roomInfor,
      viewImages,
      roomImages,
      backgroundImage,
      extraInfor,
    ]
  );

  //   console.log("basic-form re-render");

  const registerSubmit = (e) => {
    e.preventDefault();
    const hotelForm = new FormData();
    const amenitiesId = [];
    const amenitiesNames = [];
    const amenitiesTypes = [];

    amenities.forEach((item, index) => {
      amenitiesId.push(item.amenity_id);
      amenitiesNames.push(item.amenity_name);
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

    console.log(hotelForm);

    // console.log(
    // basicHotelInfor
    // amenities
    // roomInfor
    // viewImages,
    // roomImages,
    // backgroundImage
    // extraInfor.checkout.hour + ":" + extraInfor.checkout.minutes
    // );

    // console.log(viewImages, roomImages);

    fetch("http://localhost:8080/testUpload/rest/hotel/signhotel", {
      method: "POST",
      body: hotelForm,
    })
      .then((res) => res.json())
      .then((result) => console.log(result));
  };

  return (
    <RegisterContext.Provider value={registerContextValue}>
      <div id={registerStyles["register"]}>
        <Grid container>
          <Grid item xs={4} className={registerStyles["left"]}>
            <Jumbotron />
          </Grid>
          <Grid item xs={8} className={registerStyles["right"]}>
            <Box
              sx={{
                width: "50%",
                margin: "0 auto",
                paddingTop: "10em",
              }}
            >
              <Suspense fallback={<div>Loading...</div>}>
                {tabs[inputTabIndex].render(setNextTabValid)}
              </Suspense>
            </Box>
            <TabBar
              inputTabIndex={inputTabIndex}
              handleChangeTab={setInputTabIndex}
              tabIdList={tabs.map(({ id }) => id)}
              isNextTabValid={isNextTabValid}
            />
            <button
              className={registerStyles["btn-submit"]}
              onClick={(e) => {
                registerSubmit(e);
              }}
            >
              Submit
            </button>
          </Grid>
        </Grid>
      </div>
    </RegisterContext.Provider>
  );
}

export default Register;
