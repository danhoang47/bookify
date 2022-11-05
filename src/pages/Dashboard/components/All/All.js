import React, {
  Suspense,
  createContext,
  useState,
  lazy,
  useMemo,
  useEffect,
} from "react";
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
const currentMonthInitialData = {
  accessNumber: 0,
  bookingDate: [],
  bookingDateNumber: [],
  bookingHotelType: [],
  bookingHotelTypeNumber: [],
  bookingNumber: 0,
  listReport: [],
  month: 0,
  paymentNumber: 0,
  reviewNumber: 0,
  userRegisNumber: 0,
};

function All() {
  const [month, setMonth] = useState(new Date().getMonth() + 1);
  const [currentMonthData, setCurrentMonthData] = useState(
    currentMonthInitialData
  );
  const [prevMonthData, setPrevMonthData] = useState(currentMonthInitialData);

  useEffect(() => {
    fetch("http://localhost:8080/bookify/api/dashboard?month=" + month)
      .then((res) => res.json())
      .then((result) => {
        setCurrentMonthData(result[0]);
        setPrevMonthData(result[1]);
      });
  }, [month]);

  return (
    <div className={AllStyle["dashboard-all"]}>
      <Suspense fallback={<div>Loading</div>}>
        <MonthContext.Provider value={[month, setMonth]}>
          <div>
            <MonthPicker />
          </div>
          <div className={AllStyle["static"]}>
            <StaticCard
              prevMonthData={prevMonthData ? prevMonthData : {}}
              currentMonthData={currentMonthData ? currentMonthData : {}}
              month={month}
            />
          </div>
          <div className={AllStyle["charts"]}>
            <Chart
              typeBooking={{
                type: currentMonthData.bookingHotelType,
                numberBooking: currentMonthData.bookingHotelTypeNumber,
              }}
              bookingNumber={{
                day: currentMonthData.bookingDate,
                numberBooking: currentMonthData.bookingDateNumber,
              }}
            />
          </div>
          <div>
            <Suspense fallback={<div>Loading</div>}>
              <Report
                reportData={
                  currentMonthData.listReport
                    ? currentMonthData.listReport
                    : null
                }
              />
            </Suspense>
          </div>
        </MonthContext.Provider>
      </Suspense>
    </div>
  );
}

export default All;
