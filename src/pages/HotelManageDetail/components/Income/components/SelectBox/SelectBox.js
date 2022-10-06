import SelectBoxStyle from "./SelectBox.module.scss";

function SelectBox() {
  return (
    <div className={SelectBoxStyle["select-wrapper"]}>
      <label htmlFor="months" className={SelectBoxStyle["months-label"]}>
        Chọn một tháng để xem doanh thu
      </label>
      <div className={SelectBoxStyle["styled-select"]}>
        <select name="months" id="months" className={SelectBoxStyle["months"]}>
          <option value="">Chọn một tháng</option>
          <option value="january">January</option>
          <option value="february">February</option>
          <option value="march">March</option>
          <option value="april">April</option>
          <option value="may">May</option>
          <option value="june">June</option>
          <option value="july">July</option>
          <option value="august">August</option>
          <option value="september">September</option>
          <option value="october">October</option>
          <option value="november">November</option>
          <option value="december">December</option>
        </select>
      </div>
    </div>
  );
}

export default SelectBox;
