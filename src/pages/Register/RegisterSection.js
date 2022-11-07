// libraries
import { Grid, Box } from "@mui/material";
import { useState, useMemo, Suspense, useEffect, useContext } from "react";
import { useHref } from "react-router-dom";

// app defined
import { Jumbotron, TabBar } from "./components";
import { RegisterContext, UserContext } from "@/utils/contexts";
import registerStyles from "./Register.module.scss";
import {
  registerHotel,
  getDefaultAmenities,
  getDefaultAmenityTypes,
  updateHotel,
} from "@/services/hotel";
import { useClsx } from "@/utils/hooks";
import tabs from "./tabs";

function RegisterSection({
  hotelId,
  basicHotelInforInitState,
  roomInfoInitState,
  extraInforInitState,
  viewImagesInitState,
  backgroundImageInitState,
  roomImagesInitState,
  amenitiesInitState,
  displayAmenitiesInitState = null,
}) {
  // show BasicInformation first
  const [inputTabIndex, setInputTabIndex] = useState(0);
  const [basicHotelInfor, setBasicHotelInfo] = useState(
    basicHotelInforInitState
  );
  const [isNextTabValid, setNextTabValid] = useState(false);
  const [amenities, setAmenities] = useState(amenitiesInitState);
  const [roomInfor, setRoomInfor] = useState(roomInfoInitState);
  const [viewImages, setViewImages] = useState(viewImagesInitState);
  const [roomImages, setRoomImages] = useState(roomImagesInitState);
  const [backgroundImage, setBackgroundImage] = useState(
    backgroundImageInitState
  );
  const { user } = useContext(UserContext);
  const [extraInfor, setExtraInfor] = useState(extraInforInitState);
  const [displayAmenities, setDisplayAmenities] = useState(
    displayAmenitiesInitState || []
  );
  const [displayAmenitiesType, setDisplayAmenitiesType] = useState();
  const [updatedViewImages, setUpdatedViewImages] = useState([]);
  const [updatedRoomImages, setUpdatedRoomImages] = useState([]);
  const [deletedImages, setDeletedImages] = useState([]);
  const href = useHref();

  useEffect(() => {
    getDefaultAmenityTypes().then((defaultAmenityTypes) => {
      setDisplayAmenitiesType(defaultAmenityTypes);
    });

    getDefaultAmenities().then((defaultAmenties) => {
      setDisplayAmenities((prev) => {
        const mergedAmenities = [...prev];
        Array.from(defaultAmenties).forEach((defaultAmenity) => {
          let isIncluded = false;
          prev.forEach(({ name }) => {
            if (name === defaultAmenity.name) {
              isIncluded = true;
            }
          });
          if (!isIncluded) {
            mergedAmenities.push(defaultAmenity);
          }
        });

        return mergedAmenities;
      });
    });

    //eslint-disable-next-line
  }, []);

  const registerContextValue = useMemo(
    () => ({
      hotelId,
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
      displayAmenities,
      setDisplayAmenities,
      displayAmenitiesType,
      setDisplayAmenitiesType,
      updatedViewImages,
      setUpdatedViewImages,
      updatedRoomImages,
      setUpdatedRoomImages,
      deletedImages,
      setDeletedImages,
    }),
    [
      hotelId,
      basicHotelInfor,
      amenities,
      roomInfor,
      viewImages,
      roomImages,
      backgroundImage,
      extraInfor,
      displayAmenities,
      displayAmenitiesType,
      updatedViewImages,
      updatedRoomImages,
      deletedImages,
    ]
  );

  const registerSubmit = async (e) => {
    e.preventDefault();
    if (href.includes("/update")) {
      const response = await updateHotel(
        hotelId,
        amenities,
        basicHotelInfor,
        backgroundImage,
        extraInfor,
        roomInfor,
        updatedViewImages,
        updatedRoomImages,
        deletedImages
      );
    } else {
      const data = await registerHotel(
        amenities,
        basicHotelInfor,
        backgroundImage,
        roomImages,
        viewImages,
        extraInfor,
        roomInfor,
        user.user_id
      );
    }
  };

  const toNextTab = (e) => {
    if (inputTabIndex + 1 === tabs.length) {
      registerSubmit(e);
    } else {
      setInputTabIndex((prev) => prev + 1);
    }
  };

  const toPreviousTab = () => {
    if (inputTabIndex === 0) {
      return;
    } else {
      setInputTabIndex((prev) => prev - 1);
    }
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
                width: "60%",
                margin: "0 auto",
                overflowX: "hidden",
                paddingTop: "6em",
              }}
              className={registerStyles["form"]}
            >
              <Suspense fallback={<div>Loading...</div>}>
                {tabs[inputTabIndex].render(setNextTabValid)}
              </Suspense>
              {/* Buttons */}
              <div className={registerStyles["nav-buttons"]}>
                <button className={useClsx()} onClick={toNextTab}>
                  {inputTabIndex + 1 === tabs.length ? "Đăng ký" : "Tiếp theo"}
                </button>
                <button
                  className={useClsx(registerStyles["back"])}
                  onClick={toPreviousTab}
                >
                  Quay lại
                </button>
              </div>
            </Box>
            <TabBar
              inputTabIndex={inputTabIndex}
              handleChangeTab={setInputTabIndex}
              tabIdList={tabs.map(({ id }) => id)}
              isNextTabValid={isNextTabValid}
            />
          </Grid>
        </Grid>
      </div>
    </RegisterContext.Provider>
  );
}

export default RegisterSection;
