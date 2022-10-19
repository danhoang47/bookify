import { Grid, Box } from "@mui/material";
import { BannerCarousel, TabItem } from "./components";
import homeStyles from "./Home.module.scss";
import categories from "./categories";
import { useState, useContext, useEffect } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSliders } from "@fortawesome/free-solid-svg-icons";
import { CoordinatesContext } from "@/utils/contexts";

//testing purpose only
import hotels from "./hotels";
import HotelCards from "./components/HotelCards";

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

  return (
    <div id={homeStyles["home"]}>
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
            <button className={homeStyles["filter-button"]}>
              <FontAwesomeIcon icon={faSliders} />
              <span>Bộ lọc</span>
            </button>
          </div>
        </Grid>
        <div className={homeStyles["hotel-cards"]}>
          <Grid container spacing={1.5} overflow={"hidden"}>
            <HotelCards hotels={hotels} type={type} />
          </Grid>
        </div>
      </Grid>
    </div>
  );
}

export default Home;
