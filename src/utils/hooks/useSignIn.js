import { useMutation } from "@tanstack/react-query";
import { useState, useEffect, useContext } from "react";
import { SignIn } from "@/services-new/user";
import { ToastMessageContext, UserContext } from "@/utils/contexts";
import { getSuccessToastMessage } from "@/utils/reducers/toastMessageReducer";
export default function useSignIn() {
  const { setUser, isLogin, setLogin } = useContext(UserContext);
  const { setToastMessages } = useContext(ToastMessageContext);
  const [loginState, setLoginState] = useState();
  const { mutate: logInFn, status } = useMutation({
    mutationFn: (account) => {
      return SignIn(account.username, account.password);
    },
    onSuccess: (data) => {
      console.log("login success");
      console.log(data);
      setUser(data.user);
      setLogin(true);
      localStorage.setItem("user", JSON.stringify(data.user));
      setToastMessages(
        getSuccessToastMessage({
          message: "Đăng nhập thành công",
        })
      );

      setLoginState({ status: true, user: data.user });
    },
    onError: (error) => {
      console.log("log in error:" + error);
    },
  });
  return { status, loginState, logInFn };
}
