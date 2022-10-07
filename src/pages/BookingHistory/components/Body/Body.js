import BodyStyle from "../../BookingHistory.module.scss";
import { Link, Outlet } from "react-router-dom";
import { faExclamationCircle } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
function Body() {
  return (
    <div className={BodyStyle["body"]}>
      <div className={BodyStyle["nav-bar"]}>
        <Link to={``} className="link">
          Tất cả
        </Link>
        <Link
          to={`today`}
          className={BodyStyle["link"]}
          state={{ checkinDate: "16/8/2022" }}
        >
          Hôm nay
        </Link>
        <Link
          to={`cancel`}
          className={BodyStyle["link"]}
          state={{ status: false }}
        >
          Đã hủy
        </Link>
        <Link
          to={`booked`}
          className={BodyStyle["link"]}
          state={{ status: true }}
        >
          Đã đặt
        </Link>
      </div>

      <div className={BodyStyle["grid-container"]}>
        <Outlet />
        <div className={BodyStyle["alert"]}>
          <div className={BodyStyle["alert-body"]}>
            <h3>
              <span>
                <FontAwesomeIcon icon={faExclamationCircle} />
              </span>
              Lưu ý
            </h3>
            <p>
              Bạn chỉ có thể hủy phòng trong vòng 4 giờ đồng hồ kể từ lúc đặt
              phòng, mọi thanh toán sẽ được hoàn trả về tài khoản của bạn trong
              vòng 24 tiếng. Nếu quá thời gian hủy phòng quy định, bạn sẽ không
              thể hủy phòng đã đặt. <span> - Bookify</span>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}
export default Body;
