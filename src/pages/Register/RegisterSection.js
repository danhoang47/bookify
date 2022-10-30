import { Jumbotron, TabBar } from "./components";
import { Grid, Box } from "@mui/material";
import { RegisterContext } from "@/utils/contexts";
import registerStyles from "./Register.module.scss";
import { useState, useMemo, Suspense, useEffect } from "react";
import registerHotel from "@/services/hotel/registerHotel";
import tabs from "./tabs";
import { useClsx } from "@/utils/hooks";

function RegisterSection({
    basicHotelInforInitState,
    roomInfoInitState,
    extraInforInitState,
    viewImagesInitState,
    backgroundImageInitState,
    roomImagesInitState,
    amenitiesInitState,
    displayAmenitiesInitState = null,
    updatedImagesState,
    deletedImagesState,
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
    const [extraInfor, setExtraInfor] = useState(extraInforInitState);
    const [displayAmenities, setDisplayAmenities] = useState(
        displayAmenitiesInitState || []
    );
    const [displayAmenitiesType, setDisplayAmenitiesType] = useState([]);
    const [updatedViewImages, setUpdatedViewImages] = useState([]);
    const [updatedRoomImages, setUpdatedRoomImages] = useState([]);
    const [deletedImages, setDeletedImages] = useState([]);

    useEffect(() => {
        if (!displayAmenitiesInitState) {
            fetch("http://localhost:8080/bookify/api/amenity")
                .then((res) => res.json())
                .then((defautlAmenities) => {
                    setDisplayAmenities(defautlAmenities);
                });
        }
        //eslint-disable-next-line
    }, []);

    useEffect(() => {
        fetch("http://localhost:8080/bookify/api/amenity/type")
            .then((res) => res.json())
            .then((result) => {
                setDisplayAmenitiesType(result);
            });
    }, []);

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
        const data = await registerHotel(
            amenities,
            basicHotelInfor,
            backgroundImage,
            roomImages,
            viewImages,
            extraInfor,
            roomInfor
        );
        console.log(data);
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
                            <div className={registerStyles["nav-buttons"]}>
                                <button
                                    className={useClsx()}
                                    onClick={toNextTab}
                                >
                                    {inputTabIndex + 1 === tabs.length
                                        ? "Đăng ký"
                                        : "Tiếp theo"}
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
