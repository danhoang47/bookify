import { Grid, Box } from "@mui/material";
import hotelStyles from "./Hotel.module.scss";
import { Album } from "./components";
import { room } from "./datas";
import Booking from "./components/Booking";
import HotelInfo from "./components/HotelInfo";
import { useParams } from "react-router-dom";
import { useContext, useState } from "react";
import { UserContext } from "@/utils/contexts";
import { useEffect } from "react";
import { useClsx } from "@/utils/hooks";

function Hotel() {
  const { id } = useParams();
  const { user } = useContext(UserContext);
  const [hotelInfo, setHotelInfo] = useState({});
  const [isAllImageOpen, setAllImageOpen] = useState(false);

  useEffect(() => {
    fetch(
      `http://localhost:8080/bookify/api/hotel/?id=${id}&userid=${user.user_id}`
    )
      .then((res) => res.json())
      .then((result) => {
        setHotelInfo(result);
      });
    //eslint-disable-next-line
  }, [id]);

  return (
    <div
      id={hotelStyles["hotel"]}
      className={useClsx(isAllImageOpen ? hotelStyles["no-scroll"] : "")}
    >
      <Grid container justifyContent={"center"}>
        <Grid item xs={10}>
          <Album
            backgroundImage={hotelInfo.backgroundImg}
            images={hotelInfo.images || []}
            isAllImageOpen={isAllImageOpen}
            setAllImageOpen={setAllImageOpen}
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
              <Booking
                room={room}
                data={hotelInfo.roomType}
                isAllowPet={hotelInfo.isAllowPet}
              />
            </div>
          </Box>
        </Grid>
      </Grid>
    </div>
  );
}

export default Hotel;
