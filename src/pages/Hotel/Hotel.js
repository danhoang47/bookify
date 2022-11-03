import { Grid, Box } from "@mui/material";
import hotelStyles from "./Hotel.module.scss";
import { Album } from "./components";
import { images, room } from "./datas";
import Booking from "./components/Booking";
import HotelInfo from "./components/HotelInfo";
import { useParams } from "react-router-dom";
import { useContext, useState } from "react";
import { UserContext } from "@/utils/contexts";
import { useEffect } from "react";

function Hotel() {
  const backgroundImage =
    "photo/so-dien-thoai-le-tan-dat-phong-vinpearl-nam-hoi-an-1.jpg";
  const hotelId = useParams();
  const { user } = useContext(UserContext);
  const [hotelInfo, setHotelInfo] = useState({});

  useEffect(() => {
    fetch(
      `http://localhost:8080/bookify/api/hotel?id=${hotelId.id}&userid=${user.user_id}`
    )
      .then((res) => res.json())
      .then((result) => {
        console.log(result);
        setHotelInfo(result);
      });
  }, []);

  return (
    <div id={hotelStyles["hotel"]}>
      <Grid container justifyContent={"center"}>
        <Grid item xs={10}>
          <Album
            backgroundImage={hotelInfo.backgroundImg}
            images={hotelInfo.images}
          />
          <Box
            sx={{
              marginTop: "2em",
              position: "relative",
              display: "flex",
              gap: "0.6em",
            }}
          >
            <div className={hotelStyles["left"]}>
              <HotelInfo hotelInfo={hotelInfo} />
              {/* Hotel Information */}
            </div>
            <div className={hotelStyles["right"]}>
              {/* Booking Form */}
              <Booking room={room} />
            </div>
          </Box>
        </Grid>
      </Grid>
    </div>
  );
}

export default Hotel;
