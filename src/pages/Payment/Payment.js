import HeaderInfo from "./components/headerInfo";
import PaymentStyle from "./Payment.module.scss";
import Box from "@mui/material/Box";
import Tab from "@mui/material/Tab";
import TabContext from "@mui/lab/TabContext";
import TabList from "@mui/lab/TabList";
import TabPanel from "@mui/lab/TabPanel";
import { useState } from "react";
import Grid from "@mui/material/Grid";
import BankCard from "./components/BankCard";
import Overall from "./components/Overrall/Overall";

function Payment() {
  const [value, setValue] = useState("1");

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  return (
    <div className={PaymentStyle["container"]}>
      <HeaderInfo />
      <Box sx={{ flexGrow: 1 }}>
        <Grid
          container
          spacing={1}
          // direction={{
          //   xs: "column-reverse",
          //   sm: "column-reverse",
          //   md: "row",
          //   lg: "row",
          // }}
        >
          <Grid item xs={12} md={6}>
            <Box sx={{ width: "100%", typography: "body1" }}>
              <TabContext value={value}>
                <Box sx={{ borderBottom: 1, borderColor: "divider" }}>
                  <TabList
                    onChange={handleChange}
                    aria-label="lab API tabs example"
                    className={PaymentStyle["task1"]}
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
                    <Tab label="Thanh toán" value="1" />
                    <Tab label="Phiếu giảm giá" value="2" />
                  </TabList>
                </Box>
                <TabPanel value="1">
                  <Overall />
                </TabPanel>
                <TabPanel value="2">
                  <h1>Giao dichj</h1>
                </TabPanel>
              </TabContext>
            </Box>
          </Grid>
          <Grid item xs={12} md={6}>
            <div className={PaymentStyle["bank-card"]}>
              <BankCard />
            </div>
          </Grid>
        </Grid>
      </Box>
    </div>
  );
}

export default Payment;
