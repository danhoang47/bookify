import { Carousel } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "./HotelCard.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStar } from "@fortawesome/free-solid-svg-icons";
import { faHeart } from "@fortawesome/free-regular-svg-icons";
import { faHeart as faHeartSolid } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import { useContext, useState, memo } from "react";
import {
  BookmarkContext,
  ToastMessageContext,
  UserContext,
} from "@/utils/contexts";
import { addHotelToBookmark, deleteHotelFromBookmark } from "@/services/user";
import {
  getFailureToastMessage,
  getSuccessToastMessage,
} from "@/utils/reducers/toastMessageReducer";

function HotelCard({
  hotelId,
  hotelName,
  country,
  city,
  district,
  address,
  backgroundImg,
  images,
  averagePirce,
  rating,
  isBookmarked,
}) {
  const backgroundImg2 = backgroundImg.split("/");
  const allImages = [backgroundImg2[backgroundImg2.length - 1]];
  const { user } = useContext(UserContext);
  const [bookmarked, setBookmarked] = useState(isBookmarked);
  const { setToastMessages } = useContext(ToastMessageContext);
  const setBookmarkedHotels = useContext(BookmarkContext);

  images.forEach((image) => {
    let imgName = image.src.split("/");
    allImages.push(imgName[imgName.length - 1]);
  });

  const addToBookmark = () => {
    setBookmarkedHotels((prev) => [
      {
        hotelId,
        hotelName,
        backgroundImg,
        country,
        district,
        city,
        address,
        roomType: {
          price: averagePirce,
        },
      },
      ...prev,
    ]);
    setToastMessages(
      getSuccessToastMessage({ message: "Đã thêm vào mục yêu thích" })
    );
    setBookmarked(true);
  };

  const removeFromBookmark = () => {
    setBookmarkedHotels((prev) => {
      const thisHotelId = hotelId;
      return prev.filter(({ hotelId }) => hotelId !== thisHotelId);
    });
    setToastMessages(
      getSuccessToastMessage({ message: "Đã xóa khỏi mục yêu thích" })
    );
    setBookmarked(false);
  };

  const handleBookmark = async (event) => {
    event.preventDefault();
    if (!user.user_id) {
      setToastMessages(
        getFailureToastMessage({ message: "Bạn cần phải đăng nhập" })
      );
      return;
    }
    if (bookmarked) {
      const res = await deleteHotelFromBookmark(hotelId, user.user_id).then(
        (res) => res
      );
      if (res?.ok) {
        removeFromBookmark();
      } else {
        setBookmarked(false);
      }
    } else {
      const res = await addHotelToBookmark(hotelId, user.user_id).then(
        (res) => res
      );
      if (res?.ok) {
        addToBookmark();
      } else {
        setBookmarked(true);
      }
    }
  };

  return (
    <Link to={`hotel/${hotelId}`}>
      <div className={"hotel-card"}>
        <div className={"carousel"}>
          <Carousel controls={true} interval={null}>
            {allImages.map((src, index) => (
              <Carousel.Item key={index}>
                <img
                  className={"carousel-image"}
                  src={"http://localhost:8080/bookify/images/hotels/" + src}
                  alt={hotelName}
                />
              </Carousel.Item>
            ))}
          </Carousel>
        </div>
        <div className={"hotel-info"}>
          <div className={"basic-info"}>
            <div className={"name-and-point"}>
              <h3 className={"hotel-name"}>{hotelName}</h3>
              <div className={"average-point"}>
                <FontAwesomeIcon icon={faStar} />
                <span>{rating}</span>
              </div>
            </div>
            <p
              className={"hotel-address"}
            >{`${country}, ${city}, ${district}, ${address}`}</p>
            <p className={"hotel-price-per-night"}>{`$${averagePirce}`}</p>
          </div>
        </div>
        <div
          className={["bookmark-icon", bookmarked ? "bookmarked" : ""].join(
            " "
          )}
          onClick={handleBookmark}
        >
          <FontAwesomeIcon icon={bookmarked ? faHeartSolid : faHeart} />
        </div>
      </div>
    </Link>
  );
}

export default memo(HotelCard);
