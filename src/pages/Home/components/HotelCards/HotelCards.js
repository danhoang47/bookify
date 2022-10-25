import { HotelCard } from "@/components";
import { Grid } from '@mui/material';
import { useMemo, memo } from 'react';


function HotelCards({ hotels, type = null }) {
    const hotelList = useMemo(() => {
        if (Object.keys(type).length !== 0) {
            return hotels.filter(({ hotelType }) => hotelType === type.filterTypeId )
        }
        else {
            return hotels;
        }
    //eslint-disable-next-line
    }, [type])

    return ( 
        <>
            {   hotelList.map((hotel) => (
                <Grid
                    item
                    xs={12}
                    sm={6}
                    md={6}
                    lg={3}
                    xl={3}
                    key={hotel.id}
                >
                    <HotelCard {...hotel} />
                </Grid>))
        } 
        </>
    );
}

export default memo(HotelCards);
