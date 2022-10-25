import AmenitiesStyle from "./Amenities.module.scss";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faBed,
  faBowlRice,
  faParking,
  faPersonSwimming,
} from "@fortawesome/free-solid-svg-icons";
import { useMemo } from "react";

function Amenities() {
  const amenitiesItem = useMemo(
    () => [
      {
        name: "Có chỗ đậu xe rộng rãi",
        icon: faParking,
      },
      {
        name: "Có hồ bơi rộng lớn",
        icon: faPersonSwimming,
      },
      {
        name: "Có bếp riêng trong phòng",
        icon: faBowlRice,
      },
      {
        name: "Có chỗ đậu xe rộng rãi",
        icon: faParking,
      },
      {
        name: "Có hồ bơi rộng lớn",
        icon: faPersonSwimming,
      },
      {
        name: "Có bếp riêng trong phòng",
        icon: faBowlRice,
      },
      {
        name: "Có chỗ đậu xe rộng rãi",
        icon: faParking,
      },
    ],
    []
  );

  return (
    <div>
      <h3 className={AmenitiesStyle["title"]}>
        Những tiện nghi bạn được cung cấp
      </h3>
      <div className={AmenitiesStyle["Amenities"]}>
        <Box sx={{ flexGrow: 1 }}>
          <Grid container spacing={1}>
            {amenitiesItem?.map((item, index) => {
              return (
                <Grid item xs={12} md={4} key={index}>
                  <div className={AmenitiesStyle["item"]}>
                    <div className={AmenitiesStyle["icon"]}>
                      <FontAwesomeIcon icon={item.icon} />
                    </div>
                    <h6 className={AmenitiesStyle["name"]}>{item.name}</h6>
                  </div>
                </Grid>
              );
            })}
          </Grid>
        </Box>
      </div>
      {/* ---------------------------------------------------------------------------------------------------- */}
      <h3 className={AmenitiesStyle["sub-title"]}>Nơi bạn sẽ ngủ nghỉ</h3>
      <div className={AmenitiesStyle["Amenities"]}>
        <Box sx={{ flexGrow: 1 }}>
          <Grid container spacing={1}>
            <Grid item xs={12} md={4}>
              <div className={AmenitiesStyle["sub-item"]}>
                <div className={AmenitiesStyle["sub-icon"]}>
                  <FontAwesomeIcon icon={faBed} />
                </div>
                <h6 className={AmenitiesStyle["sub-name"]}>Phòng ngủ</h6>
                <p className={AmenitiesStyle["sub-des"]}>1 giường đôi</p>
              </div>
            </Grid>
          </Grid>
        </Box>
      </div>
    </div>
  );
}

export default Amenities;
