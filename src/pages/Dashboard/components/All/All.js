import React, { Suspense, useEffect, useMemo } from "react";
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
  getIncreasePercent,
} from "./AllService";
import { createContext } from "react";
import { useState } from "react";

export const MonthContext = createContext();

function All() {
  const [month, setMonth] = useState(new Date().getMonth() + 1)
  let staticTracking = getIncreasePercent(month);
  let typeBooking = typeBookingData(month);
  let bookingNumber = BookingNumberData(month);
  let reportData = ReportData(month);
  const staticData = useMemo(() => getStatic(month), [month]);

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
