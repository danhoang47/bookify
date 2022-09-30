import DropDownStyle from "./DropDown.module.scss";
import React, { memo } from "react";

const DropDown = ({ options }) => {
  console.log(options);
  return (
    <React.Fragment>
      <span className={DropDownStyle["subname-input-field"]}>
        <label htmlFor="subname">
          <b className={DropDownStyle["label"]}>Ngân Hàng</b>
        </label>
        <select className={DropDownStyle["input-update"]} name="Bank">
          {options?.map((selectOption, index) => (
            <option key={index} value={selectOption.title}>
              {selectOption.description}
            </option>
          ))}
        </select>
      </span>
    </React.Fragment>
  );
};

export default memo(DropDown);
