import bookingItemStyles from "./BookingItemStyles.module.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCircleUser } from "@fortawesome/free-solid-svg-icons";
import { useState, memo } from "react";
import { acceptBooking } from "@/services/hotel";
import CircleLoading from "../CircleLoading";
import { format } from "date-fns";

const getBookingGuestsTitle = (guests) => {
  const bookingGuestsTitle = Object.keys(guests).reduce((prev, key) => {
    if (guests[key] === 0) {
      return prev;
    } else {
      switch (key) {
        case "adult":
          return [...prev, `${guests[key]} người lớn`];
        case "child":
          return [...prev, `${guests[key]} trẻ em`];
        case "infant":
          return [...prev, `${guests[key]} trẻ sơ sinh`];
        case "pet":
          return [...prev, `${guests[key]} thú cưng`];
        default:
          throw new Error("Invalid guests title");
      }
    }
  }, []);
  return bookingGuestsTitle.join(",");
};

function BookingItem({ booking, handleBookingAction }) {
  const { user, roomType } = booking;
  const [isLoading, setLoading] = useState(false);

  const handleAcceptedBooking = async (event) => {
    if (!isLoading) {
      setLoading(true);
      const data = await acceptBooking(booking?._id, "accept");
      if (data?.message == "Accept booking successfully") {
        setLoading(false);
        handleBookingAction(booking._id, "accept");
      }
    }
  };

  const handleRejectedBooking = async (event) => {
    if (!isLoading) {
      setLoading(true);
      const data = await acceptBooking(booking?._id, "disable");
      if (data?.message == "Accept booking successfully") {
        setLoading(false);
        handleBookingAction(booking._id, "reject");
      }
    }
  };

  return (
    <div className={bookingItemStyles["tab-card"]} key={booking?._id}>
      <div className={bookingItemStyles["card-header"]}>
        <b className={bookingItemStyles["color-blue"]}>Hôm nay</b>
        <p>
          {roomType?.bedType || "Normal"} -{" "}
          {getBookingGuestsTitle({
            adult: booking?.aldult,
            child: booking?.child,
            infant: booking?.infant,
            pet: booking?.pet,
          })}
        </p>
      </div>
      <div className={bookingItemStyles["card-body"]}>
        <div className={bookingItemStyles["info"]}>
          <p className={bookingItemStyles["user-name"]}>{user?.username}</p>
          <p>
            <span> {format(new Date(booking.checkin), "MM dd,yyyy")}</span> -{" "}
            <span> {format(new Date(booking.checkout), "MM dd,yyyy")}</span>
          </p>
        </div>
        {booking?.user ? (
          <img
            className={bookingItemStyles["image"]}
            src={
              user.avatar
                ? user.avatar
                : "https://th.bing.com/th/id/OIP.3IsXMskZyheEWqtE3Dr7JwHaGe?pid=ImgDet&rs=1"
            }
            alt="avatar"
          />
        ) : (
          <FontAwesomeIcon
            className={bookingItemStyles["image"]}
            icon={faCircleUser}
          />
        )}
      </div>
      <div className={bookingItemStyles["button-group"]}>
        {booking.status === false ? (
          <>
            <button
              onClick={handleAcceptedBooking}
              className={bookingItemStyles["accept-button"]}
            >
              {isLoading ? <CircleLoading /> : "Chấp nhận"}
            </button>
            <button
              onClick={handleRejectedBooking}
              className={bookingItemStyles["reject-button"]}
            >
              {isLoading ? <CircleLoading /> : "Hủy bỏ"}
            </button>
          </>
        ) : booking.status == true ? (
          <button className={bookingItemStyles["accept-button"]}>
            {"Đã chấp nhận"}
          </button>
        ) : (
          <button className={bookingItemStyles["accept-button"]}>
            {"Đã hủy"}
          </button>
        )}
      </div>
    </div>
  );
}

export default memo(BookingItem);
