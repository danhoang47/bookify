import IncomeStyle from "./Income.module.scss";
import SelectBox from "./components/SelectBox";
import Chart from "./components/Chart";
import { income } from "./fakeIncomeData";
import { useState } from "react";

function Income() {
  const [month, setMonth] = useState("");

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
    const dayData = income.filter((data) => data.month === month);
    dayData[0].details.forEach((data) => {
      days.push(data.day);
      dayIncome.push(data.dayIncome);
    });
    daysTotal = dayIncome.reduce((prev, cur) => {
      return cur + prev;
    });
  }

  return (
    <div className={IncomeStyle["income-wrapper"]}>
      <SelectBox onChangeMonth={onChangeMonth} />
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
    </div>
  );
}

export default Income;
