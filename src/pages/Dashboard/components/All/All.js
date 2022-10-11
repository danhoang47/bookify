import AllStyle from "./All.module.scss";
import StaticCard from "./StaticCard";
import Chart from "./Chart";
import Report from "./Report";
import MonthPicker from "./MonthPicker";

import {
  getStatic,
  typeBookingData,
  BookingNumberData,
  ReportData,
} from "./AllService";
import { createContext } from "react";
import { useState } from "react";

export const MonthContext = createContext();

function All() {
  let date = new Date();
  const [month, setMonth] = useState(
    date.toLocaleString("default", { month: "short" })
  );
  let staticData = getStatic(month);
  let typeBooking = typeBookingData(month);
  let bookingNumber = BookingNumberData(month);
  let reportData = ReportData(month);
  ReportData(month);
  return (
    <div className={AllStyle["dashboard-all"]}>
      <MonthContext.Provider value={[month, setMonth]}>
        <div>
          <MonthPicker />
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
      </MonthContext.Provider>
    </div>
  );
}

export default All;
