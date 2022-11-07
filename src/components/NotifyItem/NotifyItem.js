import "./NotifyItem.scss";
import { memo } from "react";
import differenceInHours from "date-fns/differenceInHours";
import { differenceInBusinessDays } from "date-fns";

const getNotifAction = (notif) => {
  switch (notif.notifyType) {
    case 0:
      return (
        <p className="action-infor">
          Khách sạn của bạn vừa nhận được 1 đơn đặt phòng bởi&nbsp;
          <span className="notif-hightlight">{notif.actorName}</span>
        </p>
      );
    case 1:
      return (
        <p className="action-infor">
          Bạn vừa hoàn thành chuyến đi tới&nbsp;
          <span className="notif-hightlight">{notif.hotelName}</span>, hãy để
          lại đánh giá của bạn về khách sạn.
        </p>
      );
    case 2:
      return (
        <p className="action-infor">
          Bạn vừa nhận được một đánh giá từ&nbsp;
          <span className="notif-hightlight">{notif.actorName}</span>, hãy kiểm
          tra ngay
        </p>
      );
    case 3:
      return (
        <p className="action-infor">
          Khách sạn của bạn vừa nhận được 1 đơn đặt phòng bởi&nbsp;
          <span className="notif-hightlight">{notif.actorName}</span>
        </p>
      );
    case 4:
      return (
        <p className="action-infor">
          Đơn đặt phòng của bạn vừa được&nbsp;
          <span className="notif-hightlight">{notif.hotelName}</span> chấp nhận.
          Hãy kiểm tra ngay
        </p>
      );
    default:
      throw new Error("Invalid Notification type");
  }
};

const getDateDiff = (notifDate) => {
  const today = new Date();
  const diffInHours = differenceInHours(today, new Date(notifDate));
  if (diffInHours < 24) {
    return {
      diff: diffInHours,
      type: "giờ",
    };
  } else {
    return {
      diff: differenceInBusinessDays(today, new Date(notifDate)),
      type: "ngày",
    };
  }
};

function NotifItem({ notif, handleClick }) {
  const dateDiff = getDateDiff(notif.notifyDate);
  const handleAccepted = (event) => {};

  const handleRejected = (event) => {};

  return (
    <div className="notif-item">
      <div className="notif-actor-avatar">
        <div className="actor-avatar">
          <img
            src="photo/Hotel-Gardens-The-10-Most-Beautiful-Around-the-World-1.jpg"
            alt=""
            className="actor-avatar-img"
          />
        </div>
      </div>
      <div className="notif-infor">
        <div className="notif-action">{getNotifAction(notif)}</div>
        <div className="notif-date-diff">
          <p className="date-diff">
            {`${dateDiff.diff} ${dateDiff.type}`} trước
          </p>
        </div>
        {notif.notifyType === 0 && (
          <div className="hotel-function-buttons">
            <button
              className={["notif-func-button", "accept"].join(" ")}
              onClick={handleAccepted}
            >
              Chấp nhận
            </button>
            <button
              className={["notif-func-button", "reject"].join(" ")}
              onClick={handleRejected}
            >
              Từ chối
            </button>
          </div>
        )}
      </div>
    </div>
  );
}

export default memo(NotifItem);

/**
 * type 0 - for: HotelOwner, action: User book room
 *          message: Khách sạn của bạn vừa nhận được 1 đơn đặt phòng bởi {actorName}
 * type 1 - for: User, action: User get review request
 *          message: Bạn vừa hoàn thành chuyến đi tới {hotelName},
 *                      hãy để lại đánh giá của bạn về khách sạn.
 * type 2 - for: HotelOwner, action: get review from User
 *          message: Bạn vừa nhận được một đánh giá từ {actorName}
 *                      Hãy kiểm tra ngay
 * type 3 - for: User, action: get infor from booked Room after hotelOwner accept
 *          message: Đơn đặt phòng của bạn vừa được {hotelName} chấp nhận 
 *                      Hãy kiểm tra ngay
 * type 4 - for: User, action: như type 3, nhưng là reject
 * 
 * {
    id,
    userId,
    hotelId,
    notifyType,
    notifyDate,
    hotelName,
    actorName,
 * }
 */
