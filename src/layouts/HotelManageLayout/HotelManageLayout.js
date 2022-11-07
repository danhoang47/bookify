import { Suspense, useContext, useEffect, useState } from "react";
import { Outlet } from "react-router-dom";

import { Box } from "@mui/material";
import HotelManageHeader from "../components/HotelManageHeader";
import manageLayoutStyles from "./HotelManageLayout.module.scss";
import Footer from "../components/Footer";
import { UserContext } from "@/utils/contexts";
import { getHotelByOwnerId } from "@/services/hotel";

function HotelManageLayout() {
  const { user } = useContext(UserContext);
  const [hotelInfo, setHotelInfo] = useState();

  useEffect(() => {
    getHotelByOwnerId(user.user_id).then((hotel) => setHotelInfo(hotel));
  }, [user]);

  return (
    <div id={manageLayoutStyles["hotel-manage-layout"]}>
      <HotelManageHeader />
      <Box
        sx={{
          position: "relative",
          top: "70.81px",
        }}
      >
        <Suspense fallback={<div>Loading...</div>}>
          <Outlet context={hotelInfo} />
        </Suspense>
      </Box>
      {/* <Footer /> */}
    </div>
  );
}

export default HotelManageLayout;
