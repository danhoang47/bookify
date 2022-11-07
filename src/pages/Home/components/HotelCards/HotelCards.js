import { HotelCard } from "@/components";
import { Grid } from "@mui/material";
import { memo } from "react";

function HotelCards({ hotels, type = null }) {
  return (
    <>
      {hotels.map((hotel) => (
        <Grid item xs={12} sm={6} md={6} lg={3} xl={3} key={hotel.hotelId}>
          <HotelCard {...hotel} />
        </Grid>
      ))}
    </>
  );
}

export default memo(HotelCards);
