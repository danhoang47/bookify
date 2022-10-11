import { faFacebook, faTwitter } from "@fortawesome/free-brands-svg-icons";
import { faPaperPlane } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import footerStyle from "./Footer.module.scss";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";

function Footer() {
  return (
    <div className={footerStyle["footer"]}>
      <div className={footerStyle["container"]}>
        <div className={footerStyle["support"]}>
          <Box sx={{ flexGrow: 1 }}>
            <Grid container spacing={2}>
              <Grid item xs={0} md={3}>
                <div className={footerStyle["item"]}>
                  <h3 className={footerStyle["item-title"]}>Hỗ trợ</h3>
                  <ul>
                    <li>
                      <a href="">Trung tâm trợ giúp</a>
                    </li>
                  </ul>
                </div>
              </Grid>
              <Grid item xs={0} md={3}>
                <div className={footerStyle["item"]}>
                  <h3 className={footerStyle["item-title"]}>Khách sạn</h3>
                  <ul>
                    <li>
                      <a href="">Bắt đầu tạo khách sạn của bạn</a>
                    </li>
                  </ul>
                </div>
              </Grid>
              <Grid item xs={0} md={3}>
                <div className={footerStyle["item"]}>
                  <h3 className={footerStyle["item-title"]}>Địa chỉ</h3>
                  <ul>
                    <li>
                      <a href="">
                        Khu đô thị FPT, Hòa Hải, Ngũ Hành Sơn, Đà Nẵng
                      </a>
                    </li>
                  </ul>
                </div>
              </Grid>
            </Grid>
          </Box>
        </div>
        <div className={footerStyle["social"]}>
          <p className={footerStyle["copyright"]}>
            © 2022 Bookify - Practical Project
          </p>

          <div className={footerStyle["social-icon"]}>
            <p>(+84) 922340578</p>
            <div className={footerStyle["icons"]}>
              <a href="#">
                <FontAwesomeIcon icon={faFacebook} />
              </a>
              <a href="#">
                <FontAwesomeIcon icon={faTwitter} />
              </a>
              <a href="#">
                <FontAwesomeIcon icon={faPaperPlane} />
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Footer;
