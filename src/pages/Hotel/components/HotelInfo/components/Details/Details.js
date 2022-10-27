import DetailStyle from "./Details.module.scss";
import Box from "@mui/material/Box";
import Tab from "@mui/material/Tab";
import TabContext from "@mui/lab/TabContext";
import TabList from "@mui/lab/TabList";
import TabPanel from "@mui/lab/TabPanel";
import { useState } from "react";
import Description from "./components/Description";
import Amenities from "./components/Amenities";
import Rating from "./components/Rating";

function Details() {
  const [value, setValue] = useState("1");

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <div className={DetailStyle["tabs"]}>
      <div className={DetailStyle["sign_time"]}>
        <h4>Đăng ký vào 25/12/2022</h4>
      </div>
      <div className={DetailStyle["tabs_list"]}>
        <Box sx={{ width: "100%", typography: "body1" }}>
          <TabContext value={value}>
            <Box sx={{ borderBottom: 1, borderColor: "divider" }}>
              <TabList
                onChange={handleChange}
                aria-label="lab API tabs example"
                className={DetailStyle["task1"]}
                TabIndicatorProps={{
                  sx: {
                    backgroundColor: "black",
                    borderRadius: "10px",
                    marginTop: "5px",
                    height: 3,
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
                <Tab label="Giới thiệu" value="1" />
                <Tab label="Tiện nghi" value="2" />
                <Tab label="Đánh giá" value="3" />
              </TabList>
            </Box>
            <TabPanel value="1" className={DetailStyle["panel"]}>
              <Description />
            </TabPanel>
            <TabPanel value="2" className={DetailStyle["panel"]}>
              <Amenities />
            </TabPanel>
            <TabPanel value="3" className={DetailStyle["panel"]}>
              <Rating />
            </TabPanel>
          </TabContext>
        </Box>
      </div>
    </div>
  );
}

export default Details;