import IncomeStyle from "./Income.module.scss";
import SelectBox from "./components/SelectBox";
import { income } from "./fakeIncomeData";
import { lazy, useState, Suspense, useEffect } from "react";

const Chart = lazy(() => import("./components/Chart"));

function Income() {
  const [month, setMonth] = useState(new Date().getMonth() + 1);
  const months = [];
  const days = [];
  const dayIncome = [];
  let daysTotal = 0;
  const incomeByMonth = [];
  const expected = [];
  const total = income.reduce((prev, curr) => {
    return curr.income + prev;
  }, 0);
  const expectIncome = income.reduce((prev, curr) => {
    return curr.expected + prev;
  }, 0);

  income.forEach((data) => {
    months.push(data.month);
    incomeByMonth.push(data.income);
    expected.push(data.expected);
  });

  const onChangeMonth = (data) => {
    setMonth(data);
  };

  if (month) {
    const dayData = income.filter((data) => data.month === parseInt(month));
    dayData[0]?.details.forEach((data) => {
      days.push(data.day);
      dayIncome.push(data.dayIncome);
    });
    daysTotal = dayIncome.reduce((prev, cur) => {
      return cur + prev;
    }, 0);
  }

  useEffect(() => {}, []);

  return (
    <div className={IncomeStyle["income-wrapper"]}>
      <SelectBox onChangeMonth={onChangeMonth} />
      <div className={IncomeStyle["chart-wrapper"]}>
        <Suspense fallback={<div>Loading...</div>}>
          <Chart
            monthSelected={month}
            total={total}
            days={days}
            dayIncome={dayIncome}
            daysTotal={daysTotal}
            expectIncome={expectIncome}
            months={months}
            incomeByMonth={incomeByMonth}
            expected={expected}
          />
        </Suspense>
      </div>
    </div>
  );
}

export default Income;
