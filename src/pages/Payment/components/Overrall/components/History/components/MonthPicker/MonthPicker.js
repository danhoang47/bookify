import MonthPickerStyle from "./MonthPicker.module.scss";
import { useMemo } from "react";

function MonthPicker() {
  let date = new Date();
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
        <select name="filter" id="">
          <option value="">Tháng này</option>
          {months.map((month, index) => {
            return (
              <option
                disabled={
                  months.indexOf(month) > date.getMonth() ? true : false
                }
                value=""
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
