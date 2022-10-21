import SelectBoxStyle from "./SelectBox.module.scss";

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

function SelectBox({ onChangeMonth }) {
  const currentMonth = new Date().getMonth() + 1;
  const monthSelect = (e) => {
    onChangeMonth(e.target.value);
  };

  return (
    <div className={SelectBoxStyle["select-wrapper"]}>
      <label htmlFor="months" className={SelectBoxStyle["months-label"]}>
        Chọn một tháng để xem doanh thu
      </label>
      <div className={SelectBoxStyle["styled-select"]}>
        <select
          name="months"
          id="months"
          onChange={monthSelect}
          className={SelectBoxStyle["months"]}
        >
          <option value="">Tất cả các tháng</option>
          {months.map((data, index) => {
            return (
              <option
                value={index + 1}
                key={index}
                disabled={index + 1 > currentMonth ? true : false}
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
