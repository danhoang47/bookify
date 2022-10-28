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

    // testing purpose only
    const handleSubmit = (e) => {
        const formData = new FormData();
        
        
        fetch(
            "http://localhost:8080/bookify/api/hotel/fe1f3fd7-6b6f-4450-b8c5-9f1ccee123a9",
            {
                method: "POST",
                contentType: 'multipart/form-data',
                body: formData,
            }
        )
            .then((res) => res.json())
            .then((data) => console.log(data));
    };

    console.log(viewImages);

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
                            <div>
                                <button onClick={handleSubmit}>Submit</button>
                                <button>Back</button>
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

export default Register;
