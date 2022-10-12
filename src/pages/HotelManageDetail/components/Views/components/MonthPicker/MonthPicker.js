import { faArrowRight, faArrowLeft } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useEffect, useRef } from "react";
import { useState } from "react";
import MonthPickerStyle from "./MonthPicker.module.scss";

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

function MonthPicker({ monthChanging }) {
  const date = new Date();
  const [month, setMonth] = useState(date.getMonth());
  const [monthName, setMonthName] = useState(months[month]);
  const refLeft = useRef();
  const refRight = useRef();

  useEffect(() => {
    if (month <= 0) {
      setMonth(0);
      refLeft.current.style.display = "none";
      setMonthName(months[month]);
    } else if (month >= date.getMonth()) {
      setMonth(date.getMonth());
      refRight.current.style.display = "none";
      setMonthName(months[month]);
    } else {
      setMonthName(months[month]);
      refRight.current.style.display = "block";
      refLeft.current.style.display = "block";
    }
  }, [month]);

  const monthChangingHandle = (data) => {
    // console.log(data);
    const date2 = new Date();
    date2.setMonth(data);

    monthChanging(
      date2.toLocaleString("en-US", {
        month: "short",
      })
    );
  };

  return (
    <div className={MonthPickerStyle["month-picker-wrapper"]}>
      <div>
        <FontAwesomeIcon
          icon={faArrowLeft}
          onClick={() => {
            setMonth(month - 1);
            monthChangingHandle(month - 1);
          }}
          className={MonthPickerStyle["arrow-left"]}
          ref={refLeft}
        />
      </div>
      <div className={MonthPickerStyle["month-label"]}>{monthName}</div>
      <div>
        <FontAwesomeIcon
          icon={faArrowRight}
          onClick={() => {
            setMonth(month + 1);
            monthChangingHandle(month + 1);
          }}
          className={MonthPickerStyle["arrow-right"]}
          ref={refRight}
        />
      </div>
    </div>
  );
}

export default MonthPicker;
