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
import { ModalContext, UserContext } from "@/utils/contexts";
import { getNewPasswordModal } from "@/utils/reducers/modalReducer";
import { compareCurrentPassword } from "@/services/user";

function PasswordForm() {
  const { dispatch } = useContext(ModalContext);
  const { user } = useContext(UserContext);
  //user password input
  const [password, setPassword] = useState("");
  //call api check password
  const [isPasswordValid, setPasswordValid] = useState(true);
  const isInformationFilled = useMemo(() => {
    const isAllFilled = password !== null;
    const isAllValid = isPasswordValid;
    return isAllFilled && isAllValid;
  }, [password, isPasswordValid]);
  const [isLoading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    e.stopPropagation();

    if (isLoading || !isInformationFilled) {
      return;
    } else {
      setLoading(true);
      try {
        await compareCurrentPassword(user.user_id, password).then((data) => {
          if (data?.error) {
            console.log(data.error);
          } else {
            console.log(data);

            dispatch(
              getNewPasswordModal({
                isOpen: true,
                animation: "slide-in-right",
              })
            );
          }
        });
      } finally {
        setLoading(false);
      }
    }

    // if (password === "leduc") {
    //   console.log(password);
    //   dispatch(
    //     getNewPasswordModal({
    //       isOpen: true,
    //       animation: "slide-in-right",
    //     })
    //   );
    // } else {
    //   console.log("password wrong");
    // }
  };
  const handlePasswordChange = useCallback(
    (value) => {
      setPassword(value);
    },
    //eslint-disable-next-line
    [password]
  );

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
