import PersonalInputStyle from "./PersonalInput.module.scss";

function PersonalInput({
  inputField,
  type,
  placeholder,
  name,
  value,
  onChange,
  labelContent,
}) {
  return (
    <span className={PersonalInputStyle["input-field"]}>
      {/* Subname ------------------------------- */}
      <input
        spellCheck="false"
        type={type}
        placeholder={placeholder}
        name={name}
        value={value}
        className={PersonalInputStyle["input-update"]}
        onChange={onChange}
      />
      <label className={PersonalInputStyle["input-label"]} htmlFor={name}>
        {labelContent}
      </label>
    </span>
  );
}

export default PersonalInput;
