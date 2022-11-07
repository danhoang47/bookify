import LoginandSecurityStyle from "./LoginandSecurity.module.scss";
import HeaderInfo from "./components/Header";
import FormUpdate from "./components/Form";
import VerifyAuth from "@/utils/hooks/verifyAuth";
import { useNavigate } from "react-router-dom";
import { ToastMessageContext } from "@/utils/contexts";
import { getFailureToastMessage } from "@/utils/reducers/toastMessageReducer";
import { useEffect, useContext } from "react";

function LoginandSecurity() {
  const { user } = VerifyAuth();
  const { setToastMessages } = useContext(ToastMessageContext);
  const navigate = useNavigate();

  // useEffect(() => {
  //   if (!user || user.username === "") {
  //     navigate("/");
  //     setToastMessages(
  //       getFailureToastMessage({
  //         message: "Đăng nhập để truy cập",
  //       })
  //     );
  //   }
  // }, []);

  return (
    <div className={LoginandSecurityStyle["container"]}>
      <HeaderInfo />
      <FormUpdate />
    </div>
  );
}

export default LoginandSecurity;
