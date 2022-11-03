import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import CommentStyle from "./Comments.module.scss";
import { useMemo } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStar } from "@fortawesome/free-solid-svg-icons";

function Comments({ reviews }) {
  console.log(reviews);

  return (
    <div>
      <Box sx={{ flexGrow: 1 }}>
        <Grid container spacing={1} columnSpacing={{ xs: 2, sm: 2, md: 10 }}>
          {reviews?.map((review, index) => {
            return (
              <Grid item xs={12} md={6} key={index}>
                <div className={CommentStyle["item"]}>
                  <div className={CommentStyle["item-header"]}>
                    <div className={CommentStyle["user"]}>
                      <div className={CommentStyle["avatar-container"]}>
                        <img src={review.avatar} alt="" />
                      </div>
                      <div className={CommentStyle["user-info"]}>
                        <h6 className={CommentStyle["user-name"]}>
                          {review.username}
                        </h6>
                        <p className={CommentStyle["user-comment-time"]}>
                          Thang 9 nam 2022
                        </p>
                      </div>
                    </div>
                    <div className={CommentStyle["user-rating"]}>
                      <span className={CommentStyle["point"]}>
                        {Math.floor(
                          (review.accuracy_point +
                            review.communication_point +
                            review.location_point +
                            review.value_point) /
                            4
                        )}
                      </span>{" "}
                      <span className={CommentStyle["icon"]}>
                        <FontAwesomeIcon icon={faStar} />
                      </span>
                    </div>
                  </div>
                  <div className={CommentStyle["item-content"]}>
                    <p>{review.content}</p>
                  </div>
                </div>
              </Grid>
            );
          })}
        </Grid>
      </Box>
    </div>
  );
}

export default Comments;
