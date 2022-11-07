import Header from "../components/Header";
import Footer from "../components/Footer";
import styles from "./DefaultLayout.module.scss";
import { memo, useEffect, useRef, useState } from "react";
import { Outlet, useHref } from "react-router-dom";
import { Box } from "@mui/material";
import { Suspense, useContext } from "react";
import { UserContext, WebSocketContext } from "@/utils/contexts";
import { getAllBookmarkedHotel } from "@/services/hotel";
import getNotification from "@/services/hotel/getNotification";


function DefaultLayout() {
    const href = useHref();
    const { user } = useContext(UserContext);
    const current = useContext(WebSocketContext);
    const [bookmarkedHotels, setBookmarkedHotels] = useState([]);
    const [notifs, setNotifs] = useState([]);
    const [type, setType] = useState(1);

    const getBookmarkedHotel = () => {
        getAllBookmarkedHotel(user.user_id).then((data) => {
            setBookmarkedHotels(data);
        });
    };

    const getNotifications = () => {
        getNotification(user.user_id, type).then((data) => {
            setNotifs(data)
        });
    };

    useEffect(() => {
        getBookmarkedHotel();
        getNotifications();

        //eslint-disable-next-line
    }, [user]);

    const handleOnMessage = (event) => {
        const newNotif = JSON.parse(event.data);
        setNotifs(prev => [newNotif, ...prev]);
    }

    const handleClose = (event) => {
        console.log(event.data);
    }

    useEffect(() => {
        if (current) {
            current.addEventListener("message", handleOnMessage)
            current.addEventListener("close", handleClose)
        }
        return () => current?.removeEventListener("message", handleOnMessage);
    }, [current])

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
                    <Outlet context={setBookmarkedHotels} />
                </Suspense>
            </Box>
            {/* <Footer /> */}
        </div>
    );
}

export default memo(DefaultLayout);
