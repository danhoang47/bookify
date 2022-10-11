import AllStyle from "./All.module.scss";
import StaticCard from "./StaticCard";
import Chart from "./Chart";
import Report from "./Report";
import {
  getStatic,
  typeBookingData,
  BookingNumberData,
  ReportData,
} from "./AllService";

function All() {
  const staticData = getStatic("Oct");
  const typeBooking = typeBookingData("Oct");
  const bookingNumber = BookingNumberData("Oct");
  const reportData = ReportData("Oct");
  ReportData("Oct");
  return (
    <div className={AllStyle["dashboard-all"]}>
      <div className={AllStyle["time-wrapper"]}>
        <div>
          <h2>Tháng 10, năm 2022</h2>
        </div>
        <div>
          <h4>Tháng này</h4>
        </div>
      </div>
      <div className={AllStyle["static"]}>
        <StaticCard staticData={staticData} />
      </div>
      <div className={AllStyle["charts"]}>
        <Chart typeBooking={typeBooking} bookingNumber={bookingNumber} />
      </div>
      <div>
        <Report reportData={reportData} />
      </div>
    </div>
  );
}

export default All;
