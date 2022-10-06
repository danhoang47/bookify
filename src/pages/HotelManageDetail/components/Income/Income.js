import IncomeStyle from "./Income.module.scss";
import SelectBox from "./components/SelectBox";
import Chart from "./components/Chart";

const income = [
  {
    month: "Jan",
    income: 7000,
    expected: 8000,
  },
  {
    month: "Feb",
    income: 6000,
    expected: 5000,
  },
  {
    month: "Mar",
    income: 8000,
    expected: 7000,
  },
  {
    month: "Apr",
    income: 5000,
    expected: 9000,
  },
  {
    month: "May",
    income: 10000,
    expected: 6000,
  },
  {
    month: "Jun",
    income: 12000,
    expected: 10000,
  },
  {
    month: "Jul",
    income: 8000,
    expected: 6000,
  },
  {
    month: "Aug",
    income: 5000,
    expected: 5000,
  },
  {
    month: "Sep",
    income: 6000,
    expected: 7000,
  },
  {
    month: "Oct",
    income: 9000,
    expected: 8000,
  },
  {
    month: "Nov",
    income: 10000,
    expected: 12000,
  },
  {
    month: "Dec",
    income: 15000,
    expected: 13000,
  },
];

function Income() {
  return (
    <div className={IncomeStyle["income-wrapper"]}>
      <SelectBox />
      <Chart income={income} />
    </div>
  );
}

export default Income;
