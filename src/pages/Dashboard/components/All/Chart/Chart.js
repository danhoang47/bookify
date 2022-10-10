import ChartStyle from "./Chart.module.scss";
import SingleLineChartWithoutY from "@/components/Chart/SingleLineChartWithoutY";
import BarChart from "@/components/Chart/BarChartWithoutYScale";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";

const barChartLabel = [
  "Bãi biển",
  "Nhà gỗ",
  "Homestay",
  "Nhà nhỏ",
  "Thiên hiên",
];

function Chart() {
  return (
    <Box sx={{ flexGrow: 1 }}>
      <Grid container spacing={1}>
        <Grid item xs={12} md={6}>
          <div className={ChartStyle["chart-1"]}>
            <BarChart labels={barChartLabel} />
          </div>
        </Grid>
        <Grid item xs={12} md={6}>
          <div className={ChartStyle["chart-2"]}>
            <SingleLineChartWithoutY labels={barChartLabel} isY={false} />
          </div>
        </Grid>
      </Grid>
    </Box>
    // <div className={ChartStyle["chart-wrapper"]}>
    //   <div className={ChartStyle["chart-1"]}>
    //     <BarChart labels={barChartLabel} />
    //   </div>
    //   <div className={ChartStyle["chart-2"]}>
    //     <SingleLineChart />
    //   </div>
    // </div>
  );
}

export default Chart;
