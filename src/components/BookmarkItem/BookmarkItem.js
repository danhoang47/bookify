import { Link } from "react-router-dom";
import bookmarkItemStyles from "./BookmarkItem.module.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStar, faXmark } from "@fortawesome/free-solid-svg-icons";

function BookmarkItem({
    hotelId,
    hotelName,
    backgroundImg,
    country,
    district,
    city,
    address,
    price,
    rating,
    isBookmarked,
}) {
    const unBookmarkHotel = (event) => {
        event.preventDefault();
    };

    return (
        <Link to={`hotel/${hotelId}`}>
            <div
                className={bookmarkItemStyles["bookmark-item"]}
                style={{
                    backgroundImage: `url(${backgroundImg})`,
                    backgroundPosition: "center",
                    backgroundRepeat: "no-repeat",
                    backgroundSize: "cover",
                }}
            >
                <div className={bookmarkItemStyles["item-infor"]}>
                    <div className={bookmarkItemStyles["infor-left"]}>
                        <h4 className={bookmarkItemStyles["hotel-name"]}>
                            {hotelName}
                        </h4>
                        <p className={bookmarkItemStyles["hotel-address"]}>
                            {`${address}, ${district}, ${city}, ${country}`}
                        </p>
                        <p className={bookmarkItemStyles["price"]}>
                            {`$${price}`}
                        </p>
                    </div>
                    <div className={bookmarkItemStyles["infor-right"]}>
                        <button
                            className={bookmarkItemStyles["unbookmark-button"]}
                            onClick={unBookmarkHotel}
                        >
                            <FontAwesomeIcon icon={faXmark} />
                        </button>
                    </div>
                </div>
            </div>
        </Link>
    );
}

export default BookmarkItem;
