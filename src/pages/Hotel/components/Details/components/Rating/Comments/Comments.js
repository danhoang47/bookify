import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import CommentStyle from "./Comments.module.scss";
import { useMemo } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStar } from "@fortawesome/free-solid-svg-icons";

function Comments() {
  const commentsContent = useMemo(
    () => [
      {
        Comments_name: "Mức độ chính xác",
        Comments: 3,
      },
      {
        Comments_name: "Giá trị",
        Comments: 4,
      },
      {
        Comments_name: "Mức độ sạch sẽ",
        Comments: 5,
      },
      {
        Comments_name: "Phục vụ",
        Comments: 5,
      },
    ],
    []
  );
  return (
    <div>
      <Box sx={{ flexGrow: 1 }}>
        <Grid container spacing={1} columnSpacing={{ xs: 2, sm: 2, md: 10 }}>
          <Grid item xs={12} md={6}>
            <div className={CommentStyle["item"]}>
              <div className={CommentStyle["item-header"]}>
                <div className={CommentStyle["user"]}>
                  <div className={CommentStyle["avatar-container"]}>
                    <img
                      src="https://variety.com/wp-content/uploads/2021/04/Avatar.jpg"
                      alt=""
                    />
                  </div>
                  <div className={CommentStyle["user-info"]}>
                    <h6 className={CommentStyle["user-name"]}>Le Duc</h6>
                    <p className={CommentStyle["user-comment-time"]}>
                      Thang 9 nam 2022
                    </p>
                  </div>
                </div>
                <div className={CommentStyle["user-rating"]}>
                  <span className={CommentStyle["point"]}>5</span>{" "}
                  <span className={CommentStyle["icon"]}>
                    <FontAwesomeIcon icon={faStar} />
                  </span>
                </div>
              </div>
              <div className={CommentStyle["item-content"]}>
                <p>
                  Toi rat thich khach san nay. No cho toi mot cam giac giong nhu
                  dang o nha vay. Canh vat xung quanh khach san rat dep. Bai
                  bien rat trong xanh va sach se. Toi se quay lai day khi co co
                  hoi
                </p>
              </div>
            </div>
          </Grid>
          <Grid item xs={12} md={6}>
            <div className={CommentStyle["item"]}>
              <div className={CommentStyle["item-header"]}>
                <div className={CommentStyle["user"]}>
                  <div className={CommentStyle["avatar-container"]}>
                    <img
                      src="https://variety.com/wp-content/uploads/2021/04/Avatar.jpg"
                      alt=""
                    />
                  </div>
                  <div className={CommentStyle["user-info"]}>
                    <h6 className={CommentStyle["user-name"]}>Le Duc</h6>
                    <p className={CommentStyle["user-comment-time"]}>
                      Thang 9 nam 2022
                    </p>
                  </div>
                </div>
                <div className={CommentStyle["user-rating"]}>
                  <span className={CommentStyle["point"]}>5</span>{" "}
                  <span className={CommentStyle["icon"]}>
                    <FontAwesomeIcon icon={faStar} />
                  </span>
                </div>
              </div>
              <div className={CommentStyle["item-content"]}>
                <p>
                  Toi rat thich khach san nay. No cho toi mot cam giac giong nhu
                  dang o nha vay. Canh vat xung quanh khach san rat dep. Bai
                  bien rat trong xanh va sach se. Toi se quay lai day khi co co
                  hoi
                </p>
              </div>
            </div>
          </Grid>
          <Grid item xs={12} md={6}>
            <div className={CommentStyle["item"]}>
              <div className={CommentStyle["item-header"]}>
                <div className={CommentStyle["user"]}>
                  <div className={CommentStyle["avatar-container"]}>
                    <img
                      src="https://variety.com/wp-content/uploads/2021/04/Avatar.jpg"
                      alt=""
                    />
                  </div>
                  <div className={CommentStyle["user-info"]}>
                    <h6 className={CommentStyle["user-name"]}>Le Duc</h6>
                    <p className={CommentStyle["user-comment-time"]}>
                      Thang 9 nam 2022
                    </p>
                  </div>
                </div>
                <div className={CommentStyle["user-rating"]}>
                  <span className={CommentStyle["point"]}>5</span>{" "}
                  <span className={CommentStyle["icon"]}>
                    <FontAwesomeIcon icon={faStar} />
                  </span>
                </div>
              </div>
              <div className={CommentStyle["item-content"]}>
                <p>
                  Toi rat thich khach san nay. No cho toi mot cam giac giong nhu
                  dang o nha vay. Canh vat xung quanh khach san rat dep. Bai
                  bien rat trong xanh va sach se. Toi se quay lai day khi co co
                  hoi
                </p>
              </div>
            </div>
          </Grid>
        </Grid>
      </Box>
    </div>
  );
}

export default Comments;
