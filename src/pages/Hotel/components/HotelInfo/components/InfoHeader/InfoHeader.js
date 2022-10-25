import { faFlag, faHeart, faStar } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import InfoHeaderStyle from "./InfoHeader.module.scss";

function InfoHeader() {
  return (
    <div className={InfoHeaderStyle["hotel_header"]}>
      <div className={InfoHeaderStyle["hotel_banner"]}>
        <h6 className={InfoHeaderStyle["rating_and_stars"]}>
          5 <FontAwesomeIcon icon={faStar} />{" "}
          <span className={InfoHeaderStyle["rating_number"]}>144 Đánh giá</span>
        </h6>
        <h1 className={InfoHeaderStyle["hotel_name"]}>
          Khách sạn Vinpearl Nam Hội An
        </h1>
        <h5 className={InfoHeaderStyle["hotel_guest_limit"]}>
          3 người - 2 phòng ngủ - 1 phòng tắm
        </h5>
      </div>
      <div className={InfoHeaderStyle["hotel_options"]}>
        <div className={InfoHeaderStyle["option_icon"]}>
          <FontAwesomeIcon icon={faFlag} />
        </div>
        <div className={InfoHeaderStyle["option_icon"]}>
          <FontAwesomeIcon icon={faHeart} />
        </div>
      </div>
    </div>
  );
}

export default InfoHeader;
