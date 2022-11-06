import BookListStyle from "../../HotelManage.module.scss";
import Box from "@mui/material/Box";
import Tab from "@mui/material/Tab";
import TabContext from "@mui/lab/TabContext";
import TabList from "@mui/lab/TabList";
import TabPanel from "@mui/lab/TabPanel";
import { useEffect, useState, Suspense, lazy } from "react";
import { useOutletContext } from "react-router-dom";
import { getAllBooking } from "@/services/hotel";

const tabPanelStyle = {
    padding: "1em 0",
};

const BookingTabs = lazy(() => import("@/components/Tabs/BookingTabs"));
function BookingList({ width, bookList }) {
    const [value, setValue] = useState("1");
    const [bookingList, setBookingList] = useState([]);
    const hotelInfo = useOutletContext();

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    const handleTabChange = async () => {
        if (!hotelInfo) {
            return;
        }
        getAllBooking(hotelInfo.hotelId, value).then(data => setBookingList(data));
    };

    useEffect(() => {
        handleTabChange();
        //eslint-disable-next-line
    }, [value, hotelInfo]);

    return (
        <>
            <div className={BookListStyle["tabs"]}>
                <Box sx={{ width: '50%', typography: "body1" }}>
                    <TabContext value={value}>
                        <Box sx={{ borderBottom: 1, borderColor: "divider" }}>
                            <TabList
                                onChange={handleChange}
                                aria-label="lab API tabs example"
                                className={BookListStyle["task1"]}
                                TabIndicatorProps={{
                                    sx: {
                                        backgroundColor: "black",
                                        borderRadius: "10px",
                                        height: 2,
                                    },
                                }}
                                sx={{
                                    "& button": {},

                                    "& button.Mui-selected": {
                                        color: "black",
                                        fontWeight: 550,
                                    },
                                }}
                            >
                                <Tab label="Sắp đến" value="1" />
                                <Tab label="Đang được đặt" value="2" />
                                <Tab label="Trả phòng" value="3" />
                            </TabList>
                        </Box>
                        <TabPanel value="1" sx={tabPanelStyle}>
                            <Suspense fallback={<div>Loading...</div>}>
                                <BookingTabs
                                    list={bookingList}
                                    setBookingList={setBookingList}
                                />
                            </Suspense>
                        </TabPanel>
                        <TabPanel value="2" sx={tabPanelStyle}>
                            <Suspense fallback={<div>Loading...</div>}>
                                <BookingTabs
                                    list={bookingList}
                                    setBookingList={setBookingList}
                                />
                            </Suspense>
                        </TabPanel>
                        <TabPanel value="3" sx={tabPanelStyle}>
                            <Suspense fallback={<div>Loading...</div>}>
                                <BookingTabs
                                    list={bookingList}
                                    setBookingList={setBookingList}
                                />
                            </Suspense>
                        </TabPanel>
                    </TabContext>
                </Box>
            </div>
        </>
    );
}
export default BookingList;
