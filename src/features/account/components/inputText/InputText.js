import InputTextStyles from "./inputText.module.scss";
import React, { memo } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faWrench } from "@fortawesome/free-solid-svg-icons";
import { getPasswordModal } from "@/utils/reducers/modalReducer";
import { useContext } from "react";
import { ModalContext } from "@/utils/contexts";

function InputText({
  value,
  onValueChange,
  id,
  label,
  type,
  isValid = true,
  isSignIn = false,
  icon = null,
}) {
  console.log("input field rerender");
  const { dispatch } = useContext(ModalContext);

  return (
    <>
      <span className={InputTextStyles["input-row"]}>
        <span className={InputTextStyles["input-field"]}>
          <label htmlFor={label}>
            <b className={InputTextStyles["label"]}>{label}</b>
          </label>
          <div>
            <input
              id={id}
              className={[
                isValid ? "" : InputTextStyles["error"],
                type === "password"
                  ? InputTextStyles["input-password"]
                  : InputTextStyles["input-update"],
              ].join(" ")}
              readOnly={true}
              type={type}
              value={value}
              placeholder={label}
              onChange={(e) => onValueChange(e.target.value)}
            />
            {type === "password" ? (
              <button
                className={InputTextStyles["button"]}
                onClick={() => dispatch(getPasswordModal({ isOpen: true }))}
              >
                <FontAwesomeIcon icon={faWrench} />
              </button>
            ) : (
              ""
            )}
          </div>
        </span>
      </span>
    </>
  );
}

export default memo(InputText);
