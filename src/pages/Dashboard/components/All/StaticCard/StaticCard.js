import StaticStyle from "./StaticCard.module.scss";
import { faArrowUp } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import { useMemo } from "react";

function StaticCard({ staticData }) {
  const options = useMemo(
    () => [
      {
        class: "booking-number",
        title: "Lượt đặt phòng",
        data: staticData.booking,
      },
      {
        class: "views-number",
        title: "Lượt truy cập",
        data: staticData.views,
      },
      {
        class: "checkout-number",
        title: "Lượt thanh toán",
        data: staticData.checkOut,
      },
      {
        class: "rate-number",
        title: "Lượt đánh giá",
        data: staticData.rating,
      },
      {
        class: "register-number",
        title: "Lượt đăng ký",
        data: staticData.register,
      },
    ],
    []
  );
  return (
    <div className={StaticStyle["static-wrapper"]}>
      <Box sx={{ flexGrow: 1 }}>
        <Grid container spacing={1}>
          {options.map((option, index) => {
            return (
              <Grid item xs={12} md={2.4} key={index}>
                <div
                  className={[
                    StaticStyle["static-card"],
                    StaticStyle[`${option.class}`],
                  ].join(" ")}
                >
                  <div className={StaticStyle["static"]}>
                    <h1 className={StaticStyle["number"]}>{option.data}</h1>
                    <span className={StaticStyle["increase-number"]}>
                      <FontAwesomeIcon icon={faArrowUp} /> 9%
                    </span>
                  </div>
                  <p className={StaticStyle["static-des"]}>{option.title}</p>
                </div>
              </Grid>
            );
          })}
        </Grid>
      </Box>
    </div>
  );
}

export default StaticCard;
