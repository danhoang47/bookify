import DatePickerStyle from "./DatePicker.module.scss";

function PersonalInput({ name, value, onChange, labelContent }) {
  const date = new Date(value);
  // const futureDate = date.getDate() + 3;
  // date.setDate(futureDate);
  const defaultValue = date.toLocaleDateString("en-CA");
  return (
    <span className={DatePickerStyle["input-field"]}>
      {/* Subname ------------------------------- */}
      <input
        spellCheck="false"
        type="date"
        placeholder="dd-mm-yyyy"
        name={name}
        value={defaultValue}
        className={DatePickerStyle["input-update"]}
        onChange={onChange}
      />
      <label className={DatePickerStyle["input-label"]} htmlFor={name}>
        {labelContent}
      </label>
    </span>
  );
}

export default PersonalInput;
