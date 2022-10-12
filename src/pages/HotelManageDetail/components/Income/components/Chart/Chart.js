import ChartStyle from "./Chart.module.scss";
import DualLineChart from "@/components/Chart/DualLineChart/";
import SingleLineChart from "@/components/Chart/SingleLineChart";
import { useEffect, useState } from "react";

function ChartComponent({
  monthSelected,
  total,
  days,
  dayIncome,
  daysTotal,
  expectIncome,
  months,
  incomeByMonth,
  expected,
}) {
  const [monthIncome, setMonthIncome] = useState();

  useEffect(() => {
    setMonthIncome(monthSelected);
  }, [monthSelected]);

  return (
    <div>
      <div className={ChartStyle["static"]}>
        <h1>${total}</h1>
        <p>Đã thu được trong năm 2022</p>
      </div>

      {!monthIncome ? (
        <>
          <div className={ChartStyle["sub-static"]}>
            <h4 className={ChartStyle["realistic"]}>${total}</h4>
            <h4 className={ChartStyle["expected"]}>${expectIncome}</h4>
          </div>
          <DualLineChart
            labels={months}
            label1="Đã thu"
            label2="Ước tính"
            data1={incomeByMonth}
            data2={expected}
          />
        </>
      ) : (
        <>
          <div className={ChartStyle["sub-static"]}>
            <h4 className={ChartStyle["realistic"]}>${daysTotal}</h4>
          </div>
          <div className={ChartStyle["signleChart-static"]}>
            <SingleLineChart labels={days} label="Đã thu" data={dayIncome} />
          </div>
        </>
      )}
    </div>
  );
}

export default ChartComponent;
