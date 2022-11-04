import './NotifyItem.scss';

function NotifItem({
    notif,
    handleClick
}) {
    return (
        <div className="notif-item">
            
        </div>
    );
}

export default NotifItem;

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
