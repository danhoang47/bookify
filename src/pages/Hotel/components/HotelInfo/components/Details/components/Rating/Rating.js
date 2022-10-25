import { faStar } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import RatingStyle from "./Rating.module.scss";
import Point from "./Point";
import Comments from "./Comments";

function Rating() {
  return (
    <div>
      <h4 className={RatingStyle["title"]}>
        5 <FontAwesomeIcon icon={faStar} /> - 144 đánh giá
      </h4>
      <div className={RatingStyle["Rating"]}>
        <Point />
      </div>
      <div className={RatingStyle["comment-container"]}>
        <Comments />
      </div>
    </div>
  );
}

export default Rating;
