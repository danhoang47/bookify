import { faEye, faKey } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import ViewStyle from "./Views.module.scss";
import { viewsStatic } from "./FakeViewData";
import { lazy, useState, Suspense } from "react";
import MonthPicker from "./components/MonthPicker";

const Chart = lazy(() => import("./components/Chart"));

function Views() {
  let date = new Date();
  const [staticView, setStaticView] = useState("views");
  const [monthChanged, setMonthChanged] = useState(date.getMonth() + 1);
  const days = [];
  const views = [];
  const booking = [];

  const monthChanging = (data) => {
    setMonthChanged(data);
  };

  const dayData = viewsStatic.filter(
    (data) => data.month === parseInt(monthChanged)
  );

  dayData[0]?.details.forEach((data) => {
    days.push(data.day);
    views.push(data.views);
    booking.push(data.booking);
  });

  let viewsNumber = views.reduce((prev, curr) => {
    return curr + prev;
  }, 0);
  let bookingNumber = booking.reduce((prev, curr) => {
    return prev + curr;
  }, 0);

  return (
    <div className={ViewStyle["view-wrapper"]}>
      <div className={ViewStyle["static-wrapper"]}>
        <div className={ViewStyle["view-number"]}>
          <FontAwesomeIcon
            icon={faEye}
            onClick={() => {
              setStaticView("views");
            }}
          />
          <div className={ViewStyle["static"]}>
            <h6>Lượt xem trong tháng</h6>
            <h1>{viewsNumber}</h1>
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
            <h1>{bookingNumber}</h1>
          </div>
        </div>
      </div>
      <div className={ViewStyle["info-detail"]}>
        <h1>{staticView === "views" ? "Lượt xem" : "Lượt đặt phòng"}</h1>
        <div className={ViewStyle["chart-wrapper"]}>
          <Suspense fallback={<div>Loading...</div>}>
            <Chart
              days={days}
              label={staticView === "views" ? "Lượt xem" : "Lượt đặt phòng"}
              data={staticView === "views" ? views : booking}
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
