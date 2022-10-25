import React, { Suspense, createContext, useState, lazy, useMemo } from "react";
import AllStyle from "./All.module.scss";
import MonthPicker from "./MonthPicker";

import {
  getStatic,
  typeBookingData,
  BookingNumberData,
  ReportData,
  getIncreasePercent,
} from "./AllService";

const StaticCard = lazy(() => import("./StaticCard"));
const Chart = lazy(() => import("./Chart"));
const Report = lazy(() => import("./Report"));

export const MonthContext = createContext();

function All() {
  const [month, setMonth] = useState(new Date().getMonth() + 1);

  let staticData = useMemo(() => getStatic(month), [month]);
  let staticTracking = useMemo(() => getIncreasePercent(month), [month]);
  let typeBooking = useMemo(() => typeBookingData(month), [month]);
  let bookingNumber = useMemo(() => BookingNumberData(month), [month]);
  let reportData = useMemo(() => ReportData(month), [month]);

  return (
    <div className={AllStyle["dashboard-all"]}>
      <Suspense fallback={<div>Loading</div>}>
        <MonthContext.Provider value={[month, setMonth]}>
          <div>
            <MonthPicker />
          </div>
          <div className={AllStyle["static"]}>
            <StaticCard
              staticData={staticData}
              staticTracking={staticTracking}
            />
          </div>
          <div className={AllStyle["charts"]}>
            <Chart typeBooking={typeBooking} bookingNumber={bookingNumber} />
          </div>
          <div>
            <Report reportData={reportData ? reportData : null} />
          </div>
        </MonthContext.Provider>
      </Suspense>
    </div>
  );
}

export default All;
