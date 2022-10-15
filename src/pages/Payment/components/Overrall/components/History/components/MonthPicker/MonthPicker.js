import MonthPickerStyle from "./MonthPicker.module.scss";
import { useContext, useMemo } from "react";
import { OverrallContext } from "../../../../Overall";

function MonthPicker() {
  const [month, setMonth] = useContext(OverrallContext);
  let date = new Date();
  let currentMonth = date.toLocaleString("default", { month: "short" });
  const months = useMemo(
    () => [
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
    ],
    []
  );
  return (
    <div className={MonthPickerStyle["header-list"]}>
      <h3>Lịch sử giao dịch</h3>
      <div className={MonthPickerStyle["header-select"]}>
        <select
          name="filter"
          id=""
          onChange={(e) => {
            setMonth(e.target.value);
          }}
        >
          <option value={currentMonth}>Tháng này</option>
          {months.map((month, index) => {
            return (
              <option
                disabled={
                  months.indexOf(month) > date.getMonth() ? true : false
                }
                value={month}
                key={index}
              >
                {month}
              </option>
            );
          })}
        </select>
      </div>
    </div>
  );
}

export default MonthPicker;
