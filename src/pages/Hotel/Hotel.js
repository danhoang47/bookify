import { Grid, Box } from "@mui/material";
import hotelStyles from "./Hotel.module.scss";
import { Album } from "./components";
import { useHref, useParams, Outlet } from "react-router-dom";
import { useContext, useState, useMemo } from "react";
import { UserContext, BookingContext } from "@/utils/contexts";
import { useEffect, Suspense, lazy } from "react";
import { useClsx } from "@/utils/hooks";
import { guestsInitial } from "./hotelInitState";
import { Loading } from "../Home/components";
import Report from "./components/Report";
import { createContext } from "react";

const Booking = lazy(() => import("./components/Booking"));
const HotelInfo = lazy(() => import("./components/HotelInfo"));
export const reportContext = createContext();

function Hotel() {
  const { id } = useParams();
  const { user } = useContext(UserContext);
  const [hotelInfo, setHotelInfo] = useState({});
  const [selectDays, setSelectedDays] = useState({});
  const [guests, setGuests] = useState(guestsInitial);
  const [isAllImageOpen, setAllImageOpen] = useState(false);
  const [isAdvanceFilterOpen, setAdvanceFilterOpen] = useState(false);
  const href = useHref();

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

  const getAdvanceFilterHotel = () => {
    setAdvanceFilterOpen(false);
  };

  const bookingContextValue = useMemo(
    () => ({
      selectDays,
      setSelectedDays,
      guests,
      setGuests,
    }),
    [selectDays, guests]
  );

  useEffect(() => {
    document.title = hotelInfo.hotelName;
  }, [hotelInfo]);

  return (
    <BookingContext.Provider value={bookingContextValue}>
      <reportContext.Provider
        value={[isAdvanceFilterOpen, setAdvanceFilterOpen]}
      >
        <>
          <div
            id={hotelStyles["hotel"]}
            className={useClsx(
              isAllImageOpen ? hotelStyles["no-scroll"] : "",
              href.includes("/booking") ? hotelStyles["d-none"] : ""
            )}
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
                    <Suspense fallback={<div>Loading...</div>}>
                      <HotelInfo hotelInfo={hotelInfo} />
                    </Suspense>
                    {/* Hotel Information */}
                  </div>
                  <div className={hotelStyles["right"]}>
                    {/* Booking Form */}
                    <Suspense fallback={<div>Loading...</div>}>
                      <Booking
                        roomType={hotelInfo?.roomType}
                        isAllowPet={hotelInfo?.isAllowPet}
                        hotelId={hotelInfo?.hotelId}
                      />
                    </Suspense>
                  </div>
                </Box>
              </Grid>

              {
                <Suspense fallback={<div>Loading...</div>}>
                  {isAdvanceFilterOpen && (
                    <Report
                      isAdvanceFilterOpen={isAdvanceFilterOpen}
                      setAdvanceFilterOpen={setAdvanceFilterOpen}
                      getAdvanceFilterHotel={getAdvanceFilterHotel}
                      hotelInfo={hotelInfo}
                    />
                  )}
                </Suspense>
              }
            </Grid>
          </div>
          {href.includes("/booking") ? <Outlet context={hotelInfo} /> : ""}
        </>
      </reportContext.Provider>
    </BookingContext.Provider>
  );
}

export default Hotel;
