import Header from "../components/Header";
import Footer from "../components/Footer";
import styles from "./DefaultLayout.module.scss";
import { memo, useEffect, useState } from "react";
import { Outlet, useHref } from "react-router-dom";
import { Box } from "@mui/material";
import { Suspense, useContext } from "react";
import { UserContext } from "@/utils/contexts";
import { getAllBookmarkedHotel } from "@/services/hotel";

//testing purpose only
import notifsInitState from "./notifs";

function DefaultLayout() {
    const href = useHref();
    const { user } = useContext(UserContext);
    const [bookmarkedHotels, setBookmarkedHotels] = useState([]);
    const [notifs, setNotifs] = useState(notifsInitState);
        
    const getBookmarkedHotel = () => {
        getAllBookmarkedHotel(user.user_id).then((data) => {
            setBookmarkedHotels(data);
        });
    };

    useEffect(() => {
        getBookmarkedHotel();
        //eslint-disable-next-line
    }, [user]);

    return (
        <div className={styles["default-layout"]}>
            <Header
                location={href}
                bookmarkedHotels={bookmarkedHotels}
                setBookmarkedHotels={setBookmarkedHotels}
                notifs={notifs}
                setNotifs={setNotifs}
            />
            <Box
                sx={{
                    position: "relative",
                    top: "72.78px",
                }}
            >
                <Suspense fallback={<div>Loading...</div>}>
                    <Outlet />
                </Suspense>
            </Box>
            {/* <Footer /> */}
        </div>
    );
}

export default memo(DefaultLayout);
