import PersonalInfoStyle from "./PersonalInfo.module.scss";
import HeaderInfo from "./components/HeaderInfo";
import FormUpdate from "./components/FormUpdate";
import { UserContext } from "@/utils/contexts";
import { useContext, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import VerifyAuth from "@/utils/hooks/verifyAuth";
import { ToastMessageContext } from "@/utils/contexts";
import { getFailureToastMessage } from "@/utils/reducers/toastMessageReducer";

function PersonalInfo() {
  let { user } = useContext(UserContext);
  const { setToastMessages } = useContext(ToastMessageContext);
  const { isLogin } = VerifyAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (isLogin === false || isLogin === undefined) {
      navigate("/");
      setToastMessages(
        getFailureToastMessage({
          message: "Đăng nhập để truy cập",
        })
      );
    }
  }, []);

  console.log(user);

  return (
    <div className={PersonalInfoStyle["container"]}>
      <HeaderInfo />
      <FormUpdate account={user} />
    </div>
  );
}

export default PersonalInfo;
