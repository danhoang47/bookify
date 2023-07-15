import SingleLineChart from "@/components/Chart/SingleLineChart";
import {
  TransactionDataYear,
  TransactionDataYears,
  getYearExchange,
  getYearsSum,
  getMonthData,
  getYearSum,
  TransactionDataYears2,
} from "../AllService";
import YearPicker from "./YearPicker";
import { useState, useMemo } from "react";
import ChartStyle from "../Exchange.module.scss";
const months = [
  "Jan",
  "Feb",
  "Mar",
  "Apr",
  "May",
  "Jun",
  "Jul",
  "Aug",
  "Sep",
  "Oct",
  "Nov",
  "Dec",
];

function Chart({ exchangeData }) {
  const [year, setYear] = useState(2023);
  const [select, setSelect] = useState("months");

  let sumByYear = getYearSum(exchangeData, year);
  let transYears = getYearsSum(exchangeData);

  let monthsData = getMonthData(exchangeData, year);

  let transYearsNumber2 = TransactionDataYears2(exchangeData);

  const handleChange = (e) => {
    setSelect(e.target.value);
  };
  const yearChanging = (data) => {
    setYear(data);
  };

  return (
    <div className={ChartStyle["chart"]}>
      <div className={ChartStyle["chart-header"]}>
        <input
          type="radio"
          id="year"
          name="chart"
          onChange={handleChange}
          value="months"
          checked={select === "months"}
        />
        <label htmlFor="year">Theo tháng</label>
        <input
          type="radio"
          id="years"
          name="chart"
          onChange={handleChange}
          value="years"
        />
        <label htmlFor="years">Theo năm</label>

        {select === "months" ? (
          <h2>${sumByYear}.00</h2>
        ) : (
          <h2>${transYears}.00</h2>
        )}
        {select === "months" ? (
          <b>Thu được trong năm {year}</b>
        ) : (
          <b>Tổng thu nhập</b>
        )}
      </div>
      <div className={ChartStyle["chart-body"]}>
        {select === "months" ? (
          <>
            <SingleLineChart
              label={"Tổng tiền giao dịch"}
              labels={months}
              data={monthsData || []}
              isY={false}
              color={"#f72585"}
            />
            <div className={ChartStyle["month-picker"]}>
              <YearPicker yearChanging={yearChanging} />
            </div>
          </>
        ) : (
          <>
            <SingleLineChart
              label={"Tổng tiền giao dịch"}
              labels={transYearsNumber2.year}
              data={transYearsNumber2.transNumber}
              isY={false}
              color={"#f72585"}
            />
          </>
        )}
      </div>
    </div>
  );
}
export default Chart;
