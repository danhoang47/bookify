import SelectBoxStyle from "./SelectBox.module.scss";
import { MonthContext } from "../All";
import { useContext, useState } from "react";

const months = [
  "January",
  "February",
  "March",
  "April",
  "May",
  "June",
  "July",
  "August",
  "September",
  "October",
  "November",
  "December",
];

const monthsKey = [
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

function SelectBox() {
  let date = new Date();
  const [month, setMonth] = useContext(MonthContext);
  let currentMonth = date.toLocaleString("default", { month: "short" });

  console.log();

  return (
    <div className={SelectBoxStyle["select-wrapper"]}>
      <div className={SelectBoxStyle["styled-select"]}>
        <select
          name="months"
          id="months"
          onChange={(e) => {
            setMonth(e.target.value);
          }}
          className={SelectBoxStyle["months"]}
        >
          <option value={currentMonth}>Tháng này</option>
          {months.map((data, index) => {
            return (
              <option
                disabled={months.indexOf(data) > date.getMonth() ? true : false}
                value={monthsKey[index]}
                key={index}
              >
                {data}
              </option>
            );
          })}
        </select>
      </div>
    </div>
  );
}

export default SelectBox;
