import { useMutation } from "@tanstack/react-query";
import { useState, useEffect, useContext } from "react";
import { SignUp } from "@/services-new/user";
import {
  ToastMessageContext,
  UserContext,
  ModalContext,
} from "@/utils/contexts";
import {
  getFailureToastMessage,
  getSuccessToastMessage,
} from "@/utils/reducers/toastMessageReducer";
import { getSignInModal } from "@/utils/reducers/modalReducer";

export default function useSignUp() {
  const { dispatch } = useContext(ModalContext);
  const { setUser, isLogin, setLogin } = useContext(UserContext);
  const { setToastMessages } = useContext(ToastMessageContext);
  const [siginState, setSiginState] = useState();
  const { mutate: SignUpFn, status } = useMutation({
    mutationFn: (account) => {
      return SignUp(account.username, account.email, account.password);
    },
    onSuccess: (data) => {
      console.log("login success");
      console.log(data);
      setUser(data.user);
      setLogin(true);
      setToastMessages(
        getSuccessToastMessage({
          message: "Đăng Kí thành công",
        })
      );
      setSiginState(true);
      dispatch(getSignInModal({ isOpen: true }));
    },
    onError: (error) => {
      console.log("Sign up error:" + error);
      setToastMessages(
        getFailureToastMessage({
          message: error,
        })
      );
    },
  });
  return { status, siginState, SignUpFn };
}
