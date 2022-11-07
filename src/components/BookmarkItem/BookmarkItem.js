import { Link } from "react-router-dom";
import bookmarkItemStyles from "./BookmarkItem.module.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faXmark } from "@fortawesome/free-solid-svg-icons";
import { deleteHotelFromBookmark } from "@/services/user";
import { useContext } from "react";
import { UserContext } from "@/utils/contexts";

function BookmarkItem({
    hotelId,
    hotelName,
    backgroundImg,
    country,
    district,
    city,
    address,
    roomType,
    handleDeleted,
}) {
    const { user } = useContext(UserContext);

    const unBookmarkHotel = (event) => {
        event.preventDefault();
        event.stopPropagation();
        deleteHotelFromBookmark(hotelId, user.user_id);
        handleDeleted(hotelId);
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
                tabIndex="-1"
            >
                <div className={bookmarkItemStyles["item-infor"]} tabIndex="-1">
                    <div className={bookmarkItemStyles["infor-left"]}>
                        <h4 className={bookmarkItemStyles["hotel-name"]}>
                            {hotelName}
                        </h4>
                        <p className={bookmarkItemStyles["hotel-address"]}>
                            {`${address}, ${district}, ${city}, ${country}`}
                        </p>
                        <p className={bookmarkItemStyles["price"]}>
                            {`$${roomType?.price}`}
                        </p>
                    </div>
                    <div
                        className={bookmarkItemStyles["infor-right"]}
                        tabIndex="-1"
                    >
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
