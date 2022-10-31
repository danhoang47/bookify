import { Grid, Box } from "@mui/material";
import { BannerCarousel, TabItem } from "./components";
import homeStyles from "./Home.module.scss";
import categories from "./categories";
import { useState, useContext, lazy, Suspense, useEffect } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSliders } from "@fortawesome/free-solid-svg-icons";
import { CoordinatesContext } from "@/utils/contexts";

//testing purpose only
import hotels from "./hotels";
const HotelCards = lazy(() => import("./components/HotelCards"));
const AdvanceFilter = lazy(() => import("./components/AdvanceFilter"));

// testing purpose only
const trendingHotels = [
  {
    backgroundImage:
      "photo/Hotel-Gardens-The-10-Most-Beautiful-Around-the-World-1.jpg",
    name: "Hotel 1",
  },
  {
    backgroundImage:
      "photo/Hotel-Gardens-The-10-Most-Beautiful-Around-the-World-1.jpg",
    name: "Hotel 2",
  },
  {
    backgroundImage:
      "photo/Hotel-Gardens-The-10-Most-Beautiful-Around-the-World-1.jpg",
    name: "Hotel 3",
  },
];

function Home() {
  const [type, setType] = useState({});
  const currentCoordinates = useContext(CoordinatesContext);
  const [isAdvanceFilterOpen, setAdvanceFilterOpen] = useState(false);
  const [hotelsList, setHotelsList] = useState([]);

  useEffect(() => {
    if (type.filterType || type.filterTypeId) {
      fetch(
        "http://localhost:8080/bookify/api/hotel/filter?type=" +
          type.filterType +
          "&id=" +
          type.filterTypeId,
        {
          method: "GET",
        }
      )
        .then((res) => res.json())
        .then((result) => {
          console.log(result);
          setHotelsList(result);
        });
    } else {
      fetch("http://localhost:8080/bookify/api/hotel/all", {
        method: "GET",
      })
        .then((res) => res.json())
        .then((result) => setHotelsList(result));
    }
  }, [type]);

  return (
    <div id={homeStyles["home"]} className={[].join(" ")}>
      <Grid container spacing={0}>
        <Grid item xs={12}>
          <Box
            sx={{
              borderRadius: "1.6em",
              overflow: "hidden",
            }}
          >
            <BannerCarousel trendingHotels={trendingHotels} />
          </Box>
        </Grid>
        <Grid item xs={12}>
          <div className={homeStyles["filter-bar-container"]}>
            <div className={homeStyles["filter-bar"]}>
              <div className={homeStyles["category-tab"]}>
                {categories.map(({ filterType, filterTypeId, icon, name }) => (
                  <TabItem
                    key={name}
                    type={type}
                    filterType={filterType}
                    filterTypeId={filterTypeId}
                    icon={icon}
                    name={name}
                    handleClick={setType}
                  />
                ))}
              </div>
            </div>
            <button
              className={homeStyles["filter-button"]}
              onClick={() => {
                setAdvanceFilterOpen(true);
              }}
            >
              <FontAwesomeIcon icon={faSliders} />
              <span>Bộ lọc</span>
            </button>
          </div>
        </Grid>
        <div className={homeStyles["hotel-cards"]}>
          <Grid container spacing={1.5} overflow={"hidden"}>
            <Suspense fallback={<div>Loading...</div>}>
              {" "}
              <HotelCards hotels={hotelsList} type={type} />
            </Suspense>
          </Grid>
        </div>
        {
          <Suspense fallback={<div>Loading...</div>}>
            {isAdvanceFilterOpen && (
              <AdvanceFilter
                isAdvanceFilterOpen={isAdvanceFilterOpen}
                setAdvanceFilterOpen={setAdvanceFilterOpen}
              />
            )}
          </Suspense>
        }
      </Grid>
      {isAdvanceFilterOpen && (
        <Box
          sx={{
            position: "fixed",
            height: "100vh",
            top: "0",
            left: "0",
            right: "0",
            zIndex: "2",
            backgroundColor: "#000",
            opacity: "0.5",
          }}
        />
      )}
    </div>
  );
}

export default Home;
