import { faEye, faKey } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import ViewStyle from "./Views.module.scss";
import { viewsStatic } from "./FakeViewData";
import { lazy, useState, Suspense, useEffect } from "react";
import MonthPicker from "./components/MonthPicker";
import { useOutletContext } from "react-router-dom";

const Chart = lazy(() => import("./components/Chart"));

function Views() {
  let date = new Date();
  const [staticView, setStaticView] = useState("booking");
  const [monthChanged, setMonthChanged] = useState(date.getMonth() + 1);

  const [viewData, setViewData] = useState();
  const [bookingday, setBookingday] = useState([]);
  const [bookingValue, setBookingValue] = useState([]);
  const [hotel, setHotel] = useOutletContext();
  const monthChanging = (data) => {
    setMonthChanged(data);
  };

  useEffect(() => {
    fetch(
      `http://localhost:8080/bookify/api/hotel/manage/views?hotelid=${hotel?.hotelId}&month=${monthChanged}`
    )
      .then((res) => res.json())
      .then((result) => {
        setViewData(result);
        setBookingday(result.bookingDays);
        setBookingValue(result.totalBookingPerDay);
      });
  }, [monthChanged]);

  return (
    <div className={ViewStyle["view-wrapper"]}>
      <div className={ViewStyle["static-wrapper"]}>
        <div className={ViewStyle["view-number"]}>
          <FontAwesomeIcon icon={faEye} />
          <div className={ViewStyle["static"]}>
            <h6>Lượt xem trong tháng</h6>
            <h1>{viewData?.accessNumber}</h1>
          </div>
        </div>
        <div className={ViewStyle["book-number"]}>
          <FontAwesomeIcon
            icon={faKey}
            onClick={() => {
              setStaticView("booking");
            }}
          />
          <div className={ViewStyle["static"]}>
            <h6>Lượt đặt phòng trong tháng</h6>
            <h1>
              {viewData?.totalBookingPerDay.reduce((prev, cur) => {
                return prev + cur;
              }, 0) || 0}
            </h1>
          </div>
        </div>
      </div>
      <div className={ViewStyle["info-detail"]}>
        <h1>{staticView === "views" ? "Lượt xem" : "Lượt đặt phòng"}</h1>
        <div className={ViewStyle["chart-wrapper"]}>
          <Suspense fallback={<div>Loading...</div>}>
            <Chart
              days={
                bookingday
                  ? bookingday
                  : [1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25]
              }
              label={"Lượt đặt phòng"}
              data={bookingValue}
            />
          </Suspense>
        </div>
      </div>
      <div className={ViewStyle["month-picker"]}>
        <MonthPicker monthChanging={monthChanging} />
      </div>
    </div>
  );
}

export default Views;
