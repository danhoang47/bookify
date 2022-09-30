import React, { ReactFragment, memo } from "react";
import inputTextStyle from "./inputText.module.scss";

const InputText = ({ type, label, placeholder, name }) => {
  return (
    <React.Fragment>
      <span className={inputTextStyle["subname-input-field"]}>
        <label htmlFor={name}>
          <b className={inputTextStyle["label"]}>{label}</b>
        </label>
        <input
          type={type}
          placeholder={placeholder}
          name={name}
          className={inputTextStyle["input-update"]}
        />
      </span>
    </React.Fragment>
  );
};

export default memo(InputText);
