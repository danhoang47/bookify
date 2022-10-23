import { InputField } from "../../components";
import formStyles from "../PasswordForm.module.scss";
import {
  useState,
  memo,
  useCallback,
  useMemo,
  useContext,
  useEffect,
} from "react";
import { ModalContext } from "@/utils/contexts";
import { getNewPasswordModal } from "@/utils/reducers/modalReducer";

function PasswordForm() {
  const { dispatch } = useContext(ModalContext);
  //user password input
  const [password, setPassword] = useState("");
  //call api check password
  const [isPasswordValid, setPasswordValid] = useState(true);
  const isInformationFilled = useMemo(() => {
    const isAllFilled = password !== null;
    const isAllValid = isPasswordValid;
    return isAllFilled && isAllValid;
  }, [password, isPasswordValid]);
  const handleSubmit = (e) => {
    e.stopPropagation();
    dispatch(
      getNewPasswordModal({
        isOpen: true,
        animation: "slide-in-right",
      })
    );
    console.log(password);
  };
  const handlePasswordChange = useCallback(
    (value) => {
      setPassword(value);
    },
    //eslint-disable-next-line
    [password]
  );
  console.log("re-render", isInformationFilled);
  return (
    <div className={formStyles["form-wrapper"]}>
      <form onSubmit={handleSubmit} className={formStyles["form"]}>
        <InputField
          value={password}
          id="password"
          onValueChange={handlePasswordChange}
          isValid={isPasswordValid}
          isSignIn={true}
          label="Mật khẩu"
          type="password"
        />
        <div className={formStyles["button-wrapper"]}>
          <button
            className={[
              formStyles["sign-in-button"],
              isInformationFilled ? "" : formStyles["button-disabled"],
            ].join(" ")}
          >
            Xác nhận
          </button>
        </div>
      </form>
    </div>
  );
}
export default memo(PasswordForm);
