import ChartStyle from "./Chart.module.scss";
import SingleLineChart from "@/components/Chart/SingleLineChart";
import BarChart from "@/components/Chart/BarChartWithoutYScale";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";

function Chart({ typeBooking, bookingNumber }) {
  return (
    <Box sx={{ flexGrow: 1 }}>
      <Grid container spacing={1}>
        <Grid item xs={12} md={6}>
          <div className={ChartStyle["chart-1"]}>
            <BarChart
              label="Xu hướng đặt phòng"
              labels={typeBooking.type}
              data={typeBooking.numberBooking}
            />
          </div>
          <div className={ChartStyle["chart-1-title"]}>
            <h5>Xu hướng đặt phòng</h5>
          </div>
        </Grid>
        <Grid item xs={12} md={6}>
          <div className={ChartStyle["chart-2"]}>
            <SingleLineChart
              label="Lượt đặt phòng"
              labels={bookingNumber.day}
              data={bookingNumber.numberBooking}
              isY={false}
              color={"#f72585"}
            />
            {/* <BarChart labels={barChartLabel} /> */}
          </div>
          <div className={ChartStyle["chart-2-title"]}>
            <h5>Lượt đặt phòng</h5>
          </div>
        </Grid>
      </Grid>
    </Box>
  );
}

export default Chart;
