import BookingList from "./components/BookingList";
import BookingListStyle from "./HotelManage.module.scss";
function HotelManageBooking({ hotel }) {
  return (
    <div className={BookingListStyle["container"]}>
      <h2>Lịch đặt phòng</h2>
      <BookingList width="100%" />
    </div>
  );
}
export default HotelManageBooking;
