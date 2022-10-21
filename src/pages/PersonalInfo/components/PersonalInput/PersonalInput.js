import PersonalInputStyle from "./PersonalInput.module.scss";

function PersonalInput({
  inputField,
  type,
  placeholder,
  name,
  value,
  onChange,
  labelContent,
  readOnly,
}) {
  const newValue = value ? value : "";

  return (
    <span className={PersonalInputStyle["input-field"]}>
      {/* Subname ------------------------------- */}
      <input
        spellCheck="false"
        type={type}
        placeholder={placeholder}
        name={name}
        value={newValue}
        className={
          !readOnly
            ? PersonalInputStyle["input-update"]
            : PersonalInputStyle["input-update-readOnly"]
        }
        onChange={onChange}
        readOnly={readOnly}
      />
      <label className={PersonalInputStyle["input-label"]} htmlFor={name}>
        {labelContent}
      </label>
    </span>
  );
}

export default PersonalInput;
