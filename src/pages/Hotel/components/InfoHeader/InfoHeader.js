import { UserContext } from "@/utils/contexts";
import { faFlag, faHeart, faStar } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useContext } from "react";
import { reportContext } from "../../Hotel";
import { ToastMessageContext } from "@/utils/contexts";
import { getFailureToastMessage } from "@/utils/reducers/toastMessageReducer";

import InfoHeaderStyle from "./InfoHeader.module.scss";

function InfoHeader({
  reviews = 0,
  roomType = null,
  hotelName = null,
  rating = 0,
  isBookmarked = false,
  hotelId = null,
}) {
  const [isAdvanceFilterOpen, setAdvanceFilterOpen] = useContext(reportContext);
  const { user } = useContext(UserContext);
  const { setToastMessages } = useContext(ToastMessageContext);

  const checkUser = () => {
    if (user.user_id) {
      fetch(
        `http://localhost:8080/bookify/api/hotel/report?hotelid=${hotelId}&userid=${user.user_id}`
      )
        .then((res) => res.json())
        .then((result) => {
          console.log(result);
          if (result.require) {
            setToastMessages(
              getFailureToastMessage({
                message: "Bạn chưa từng ở khách sạn này",
              })
            );
          }
          if (result.success) {
            setAdvanceFilterOpen(true);
          }
        });
    } else {
      setToastMessages(
        getFailureToastMessage({
          message: "Đăng nhập để thực hiện",
        })
      );
    }
  };

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
          <span
            onClick={() => {
              checkUser();
            }}
          >
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
