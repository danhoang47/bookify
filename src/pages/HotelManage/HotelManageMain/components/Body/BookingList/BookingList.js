import BookListStyle from "../../../HotelManage.module.scss";
import Box from "@mui/material/Box";
import Tab from "@mui/material/Tab";
import TabContext from "@mui/lab/TabContext";
import TabList from "@mui/lab/TabList";
import TabPanel from "@mui/lab/TabPanel";
import { useMemo, useState } from "react";
import BookingTabs from "@/components/Tabs/BookingTabs";
function BookingList({ width, bookList }) {
  const [value, setValue] = useState("1");

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  return (
    <>
      <div className={BookListStyle["tabs"]}>
        <Box sx={{ width: width, typography: "body1" }}>
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
                <Tab label="Checking out" value="1" />
                <Tab label="Đang được đặt" value="2" />
                <Tab label="Sắp đến" value="3" />
              </TabList>
            </Box>
            <TabPanel value="1">
              <BookingTabs list={bookList.tabs} category={{ status: 1 }} />
            </TabPanel>
            <TabPanel value="2">
              <BookingTabs list={bookList.tabs} category={{ status: 1 }} />
            </TabPanel>
            <TabPanel value="3">
              <BookingTabs list={bookList.tabs} category={{ status: 1 }} />
            </TabPanel>
          </TabContext>
        </Box>
      </div>
    </>
  );
}
export default BookingList;
