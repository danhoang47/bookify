import Box from "@mui/material/Box";
import Tab from "@mui/material/Tab";
import TabContext from "@mui/lab/TabContext";
import TabList from "@mui/lab/TabList";
import TabPanel from "@mui/lab/TabPanel";
import DashboardStyle from "./Dashboard.module.scss";
import { useState } from "react";
import All from "./components/All";
import Hotel from "./components/Hotel";

function Dashboard() {
  const [value, setValue] = useState("1");

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
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
            <h1>Giao dichj</h1>
          </TabPanel>
          <TabPanel value="3">
            <Hotel />
          </TabPanel>
        </TabContext>
      </Box>
    </div>
  );
}

export default Dashboard;
