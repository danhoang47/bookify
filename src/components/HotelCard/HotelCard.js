import { Carousel } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "./HotelCard.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStar } from "@fortawesome/free-solid-svg-icons";
import { faHeart } from "@fortawesome/free-regular-svg-icons";
import { faHeart as faHeartSolid } from "@fortawesome/free-solid-svg-icons";
import { Link, useOutletContext } from "react-router-dom";
import { useContext, useState, memo, useEffect } from "react";
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
import { useUser } from "@/utils/hooks";

function HotelCard({
  _id: hotelId,
  hotelName,
  country,
  district,
  address,

  images,
  averagePrice,
  rating,
  isBookmarked,
}) {
  const { user } = useContext(UserContext);
  const [bookmarked, setBookmarked] = useState(isBookmarked);
  const { setToastMessages } = useContext(ToastMessageContext);
  const setBookmarkedHotels = useOutletContext(BookmarkContext);
  const { addBookMarked } = useUser();

  const addToBookmark = () => {
    setBookmarkedHotels((prev) => [
      {
        hotelId,
        hotelName,
        country,
        district,
        address,
        roomType: {
          price: averagePrice,
        },
      },
      ...prev,
    ]);
    setToastMessages(
      getSuccessToastMessage({ message: "Đã thêm vào mục yêu thích" })
    );
    setBookmarked(true);
  };
  useEffect(() => {
    setBookmarked(isBookmarked);
  }, [isBookmarked]);

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
    if (!user._id) {
      setToastMessages(
        getSuccessToastMessage({ message: "Đã thêm vào mục yêu thích" })
      );
      return;
    }
    addBookMarked(hotelId, {
      onSuccess: () => {
        if (!isBookmarked) {
          setToastMessages(
            getSuccessToastMessage({ message: "Đã thêm vào mục yêu thích" })
          );
        } else {
          setToastMessages(
            getSuccessToastMessage({ message: "Đã xóa khỏi mục yêu thích" })
          );
        }
      },
    });
  };

  return (
    <Link to={`hotel/${hotelId}`}>
      <div className={"hotel-card"}>
        <div className={"carousel"}>
          <Carousel controls={true} interval={null}>
            {images.map((element, index) => (
              <Carousel.Item key={index}>
                <img
                  className={"carousel-image"}
                  src={`http://localhost:${process.env.REACT_APP_BACK_END_PORT}${element.imagePath}`}
                  alt={hotelName}
                  loading="lazy"
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
                <span>{rating.communicationPoint}</span>
                <span>{rating.accuracyPoint}</span>
                <span>{rating.locationPoint}</span>
                <span>{rating.valuePoint}</span>
              </div>
            </div>
            <p
              className={"hotel-address"}
            >{`${country},  ${district}, ${address}`}</p>
            <p className={"hotel-price-per-night"}>{`$${averagePrice}`}</p>
          </div>
        </div>
        {user?.role === 0 ? (
          ""
        ) : (
          <div
            className={["bookmark-icon", bookmarked ? "bookmarked" : ""].join(
              " "
            )}
            onClick={handleBookmark}
          >
            <FontAwesomeIcon icon={bookmarked ? faHeartSolid : faHeart} />
          </div>
        )}
      </div>
    </Link>
  );
}

export default memo(HotelCard);
