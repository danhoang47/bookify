import Box from "@mui/material/Box";
import Tab from "@mui/material/Tab";
import TabContext from "@mui/lab/TabContext";
import TabList from "@mui/lab/TabList";
import TabPanel from "@mui/lab/TabPanel";
import DashboardStyle from "./Dashboard.module.scss";
import { useState, useMemo } from "react";
import All from "./components/All";
import Hotel from "./components/Hotel";
import Exchange from "./components/Exchange";
import HotelContext from "@/utils/contexts/HotelContext";

function Dashboard() {
  const [value, setValue] = useState("1");
  const hotelData = useMemo(
    () => [
      {
        hotelhostName: "Lê Quý Đức",
        ID: 1209231202121,
        ExchangeId: "OPD209231202123",
        hotelName: "Khách sạn Vinpearl Nam Hội An",
        Time: "10/24/2012 12:30:06",
        status: 1,
        totalMoney: 260,
      },
      {
        hotelhostName: "Lê Quý Đức",
        ID: 1209231202121,
        ExchangeId: "OPD209231202123",
        hotelName: "Khách sạn Vinpearl Nam Hội An",
        Time: "10/22/2022 12:30:06 ",
        status: 2,
        totalMoney: 260,
      },
      {
        hotelhostName: "Lê Quý Đức",
        hotelName: "Khách sạn Vinpearl Nam Hội An",
        ExchangeId: "OPD209231202123",
        ID: 1209231202121,
        Time: "09/04/2012 12:30:06",
        status: 3,
        totalMoney: 260,
      },
      {
        hotelhostName: "Lê Quý Đức",
        ID: 1209231202121,
        ExchangeId: "OPD209231202123",
        hotelName: "Khách sạn Vinpearl Nam Hội An",
        Time: "12/04/2002 12:30:06",
        status: 1,
        totalMoney: 260,
      },
      {
        hotelhostName: "Lê Quý Đức",
        ID: 1209231202121,
        ExchangeId: "OPD209231202123",
        hotelName: "Khách sạn Vinpearl Nam Hội An",
        Time: "10/21/2022 12:30:06",
        status: 1,
        totalMoney: 260,
      },
      {
        hotelhostName: "Lê Quý Đức",
        ID: 1209231202121,
        ExchangeId: "OPD209231202123",
        hotelName: "Khách sạn Vinpearl Nam Hội An",
        Time: "10/24/2022 12:30:06",
        status: 1,
        totalMoney: 260,
      },
      {
        hotelhostName: "Lê Quý Đức",
        ID: 1209231202121,
        ExchangeId: "OPD209231202123",
        hotelName: "Khách sạn Vinpearl Nam Hội An",
        Time: "10/24/2022 12:30:06",
        status: 1,
        totalMoney: 260,
      },
      {
        hotelhostName: "Lê Quý Đức",
        ID: 1209231202121,
        ExchangeId: "OPD209231202123",
        hotelName: "Khách sạn Vinpearl Nam Hội An",
        Time: "10/24/2022 12:30:06",
        status: 1,
        totalMoney: 260,
      },
      {
        hotelhostName: "Lê Quý Đức",
        ID: 1209231202121,
        ExchangeId: "OPD209231202123",
        hotelName: "Khách sạn Vinpearl Nam Hội An",
        Time: "10/24/2022 12:30:06",
        status: 1,
        totalMoney: 260,
      },
      {
        hotelhostName: "Lê Quý Đức",
        ID: 1209231202121,
        ExchangeId: "OPD209231202123",
        hotelName: "Khách sạn Vinpearl Nam Hội An",
        Time: "10/24/2022 12:30:06",
        status: 1,
        totalMoney: 260,
      },
    ],
    []
  );
  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <HotelContext.Provider value={hotelData}>
      <div className={DashboardStyle["container"]}>
        <Box sx={{ width: "100%", typography: "body1" }}>
          <TabContext value={value}>
            <Box sx={{ borderBottom: 1, borderColor: "divider" }}>
              <TabList
                onChange={handleChange}
                aria-label="lab API tabs example"
                className={DashboardStyle["task1"]}
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
                <Tab label="Thu nhập" value="1" />
                <Tab label="Giao dịch" value="2" />
                <Tab label="Khách sạn" value="3" />
              </TabList>
            </Box>
            <TabPanel value="1">
              <All />
            </TabPanel>
            <TabPanel value="2">
              <Exchange />
            </TabPanel>
            <TabPanel value="3">
              <Hotel />
            </TabPanel>
          </TabContext>
        </Box>
      </div>
    </HotelContext.Provider>
  );
}

export default Dashboard;