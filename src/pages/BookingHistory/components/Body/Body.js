import BodyStyle from "../../BookingHistory.module.scss";
import { Link, Outlet } from "react-router-dom";
function Body() {
  return (
    <div>
      <div>
        <div className={BodyStyle["nav-bar"]}>
          <Link to={``} className="link">
            Tất cả
          </Link>
          <Link to={`today`} className="link">
            Hôm nay
          </Link>
          <Link to={`cancle`} className="link">
            Đã hủy
          </Link>
          <Link to={`booked`} className="link">
            Đã đặt
          </Link>
        </div>
      </div>
      <Outlet />
    </div>
  );
}
export default Body;
