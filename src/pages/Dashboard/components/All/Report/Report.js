import ReportStyle from "./Report.module.scss";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";

function Report() {
  return (
    <div className={ReportStyle["wrap-report"]}>
      <h3 className={ReportStyle["header-title"]}>Báo cáo gần đây</h3>
      <Box sx={{ flexGrow: 1 }}>
        <Grid container spacing={1}>
          <Grid item xs={12} md={6}>
            <div className={ReportStyle["card"]}>
              <div className={ReportStyle["card-avatar"]}>
                <div className={ReportStyle["avatar-wrapper"]}>
                  <img
                    src="https://variety.com/wp-content/uploads/2022/02/Screen-Shot-2022-05-09-at-10.04.13-AM.png"
                    alt="avatar"
                    height={30}
                    width={30}
                  />
                </div>
              </div>
              <div className={ReportStyle["card-body"]}>
                <div className={ReportStyle["card-title"]}>
                  <h4>Le Duc</h4>
                  <h6>Khách sạn Vinpearl Nam Hội An</h6>
                </div>
                <div className={ReportStyle["card-content"]}>
                  <p>
                    Toi rat thich khach san nay. No cho toi mot cam giac giong
                    nhu dang o nha vay. Canh vat xung quanh khach san rat dep.
                    Bai bien rat trong xanh va sach se. Toi se quay lai day khi
                    co co hoi
                  </p>
                </div>
                <div className={ReportStyle["card-time"]}>
                  <p>10:20 13/07/2002</p>
                </div>
              </div>
            </div>
          </Grid>
        </Grid>
      </Box>
    </div>
  );
}

export default Report;
