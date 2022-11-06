import HeaderStyle from "../../HotelManage.module.scss";
import { Grid } from "@mui/material";

function Header({ hotel }) {
  const hotelexample = {
    name: "Khách sạn Vin Pearl Nam Hội An",
  };
  return (
    <div className={HeaderStyle["header"]}>
      <Grid container justifyContent={"center"}>
        <Grid item xs={10}>
          <div className={HeaderStyle["header-text"]}>
            <h2>{hotelexample.name}</h2>
            <p className={HeaderStyle["sub-title"]}>
              Xem xét hoạt động và đánh giá mức độ yêu thích của khách hàng dành cho
              khách sạn.
            </p>
          </div>
        </Grid>
      </Grid>
    </div>
  );
}
export default Header;
