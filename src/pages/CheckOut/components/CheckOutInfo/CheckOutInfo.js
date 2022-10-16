import { faArrowLeft } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import CheckOutInfoStyle from "./CheckOutInfo.module.scss";

function CheckOutInfo() {
  return (
    <div>
      <h1 className={CheckOutInfoStyle["Check-out-title"]}>
        <span>
          <FontAwesomeIcon icon={faArrowLeft} />
        </span>{" "}
        Thanh toán đặt phòng
      </h1>
      <div>
        <h3 className={CheckOutInfoStyle["form-title"]}>Chuyến đi của bạn</h3>
        <div>
          <div className={CheckOutInfoStyle["basic-info"]}>
            <div>
              <div className={CheckOutInfoStyle["info-header"]}>
                <h4>Thời điểm</h4>
                <p className={CheckOutInfoStyle["info-alter"]}>Chỉnh sửa</p>
              </div>
              <div className={CheckOutInfoStyle["info-detail"]}>
                <p>5 tháng 12 - 12 tháng 12</p>
              </div>
            </div>
            <div>
              <div className={CheckOutInfoStyle["info-header"]}>
                <h4>Số người</h4>
                <p className={CheckOutInfoStyle["info-alter"]}>Chỉnh sửa</p>
              </div>
              <div className={CheckOutInfoStyle["info-detail"]}>
                <p>1 người</p>
              </div>
            </div>
          </div>
          <div>
            <h4 className={CheckOutInfoStyle["pay-title"]}>Trả với</h4>
            <div className={CheckOutInfoStyle["select-wrapper"]}>
              <select name="" id="" className={CheckOutInfoStyle["select-box"]}>
                <option value="">****-9123</option>
                <option value="">****-9123</option>
                <option value="">****-9123</option>
              </select>
            </div>
          </div>
        </div>
      </div>
      <div className={CheckOutInfoStyle["res-payment"]}>
        <div className={CheckOutInfoStyle["money-amount"]}>
          <h4>Tổng tiền phải trả</h4>
          <h4>$400</h4>
        </div>
        <button className={CheckOutInfoStyle["sub-payment-btn"]}>
          Thanh Toán
        </button>
      </div>
    </div>
  );
}

export default CheckOutInfo;
