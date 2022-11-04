import { faFlag, faHeart, faStar } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import InfoHeaderStyle from "./InfoHeader.module.scss";

function InfoHeader({
  reviews = 0,
  roomType = null,
  hotelName = null,
  rating = 0,
  isBookmarked = false,
}) {

  return (
    <div className={InfoHeaderStyle["hotel_header"]}>
      <div className={InfoHeaderStyle["hotel_banner"]}>
        <h6 className={InfoHeaderStyle["rating_and_stars"]}>
          {rating} <FontAwesomeIcon icon={faStar} />{" "}
          <span className={InfoHeaderStyle["rating_number"]}>
            {reviews.length} Đánh giá
          </span>
        </h6>
        <h1 className={InfoHeaderStyle["hotel_name"]}>{hotelName}</h1>
        <h5 className={InfoHeaderStyle["hotel_guest_limit"]}>
          {roomType?.numberOfGuests} người - {roomType?.numberOfRoom} phòng ngủ
          - {roomType?.numberOfBathroom} phòng tắm
        </h5>
      </div>
      <div className={InfoHeaderStyle["hotel_options"]}>
        <div className={InfoHeaderStyle["option_icon"]}>
          <span>
            <FontAwesomeIcon icon={faFlag} />
          </span>
        </div>
        <div className={InfoHeaderStyle["option_icon"]}>
          <span className={isBookmarked ? InfoHeaderStyle["bookmarked"] : ""}>
            <FontAwesomeIcon icon={faHeart} />
          </span>
        </div>
      </div>
    </div>
  );
}

export default InfoHeader;
