import { Suspense, useState } from "react";
import { Outlet, useParams } from "react-router-dom";
import { Box } from "@mui/material";
import HotelManageHeader from "../components/HotelManageHeader";
import manageLayoutStyles from "./HotelManageLayout.module.scss";
import Footer from "../components/Footer";
import { useContext, useEffect } from "react";
import { UserContext } from "@/utils/contexts";

function HotelManageLayout() {
  const { user } = useContext(UserContext);
  // useOutletContext
  const [hotel, setHotel] = useState();

  useEffect(() => {
    fetch(
      `http://localhost:8080/bookify/api/hotel/manage/gethotel?userid=${user.user_id}`
    )
      .then((res) => res.json())
      .then((result) => setHotel(result));
  }, []);

  console.log(hotel);

  return (
    <div id={manageLayoutStyles["hotel-manage-layout"]}>
      <HotelManageHeader />
      <Box
        sx={{
          position: "relative",
          top: "72.78px",
        }}
      >
        <Suspense fallback={<div>Loading...</div>}>
          <Outlet context={[hotel, setHotel]} />
        </Suspense>
      </Box>
      {/* <Footer /> */}
    </div>
  );
}

export default HotelManageLayout;
