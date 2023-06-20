import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { useContext, useState } from "react";
import { modalReducer, toastMessageReducer } from "@/utils/reducers";
import { Modal, ToastMessage, ToastMessageBox } from "@/components";
import {
  UserContext,
  ModalContext,
  ToastMessageContext,
} from "@/utils/contexts";
import {
  FetchUser,
  UpdateUser,
  compareCurrentPassword,
} from "@/services-new/user";
import {
  getFailureToastMessage,
  getSuccessToastMessage,
} from "@/utils/reducers/toastMessageReducer";
import {
  getNewPasswordModal,
  getChangeCard,
} from "@/utils/reducers/modalReducer";
export default function useUser({ submodal }) {
  const [userData, setUserData] = useState();
  const { dispatch } = useContext(ModalContext);
  const queryClient = useQueryClient();
  const { user } = useContext(UserContext);
  const { setToastMessages } = useContext(ToastMessageContext);
  const _id = user._id;
  const { isLoading, data } = useQuery({
    queryKey: ["fetchUser", _id],
    queryFn: () => FetchUser(_id),
    onSuccess: (data) => {
      console.log(data);
      setUserData(data);
    },
  });
  const { mutate: updateUser } = useMutation({
    mutationKey: ["update user", user._id],
    mutationFn: (userForm) => UpdateUser(userForm),
    onSuccess: async (data, variables) => {
      if (!data) {
      }
      await queryClient.refetchQueries({ queryKey: ["posts"], type: "active" });
    },
  });

  const { status: compareStatus, mutate: checkPassword } = useMutation({
    mutationKey: ["check-password-user", user._id],
    mutationFn: (password) => compareCurrentPassword(password),
    onSucess: (data) => {
      if (!data) {
        console.log("wrong password");
        setToastMessages(getFailureToastMessage({ message: "Sai mật khẩu" }));
      } else {
        if (submodal === "new password") {
          dispatch(
            getNewPasswordModal({
              isOpen: true,
              animation: "slide-in-right",
            })
          );
        } else {
          dispatch(
            getChangeCard({
              isOpen: true,
              animation: "slide-in-right",
            })
          );
        }
      }
    },
  });

  return { isLoading, updateUser, userData, setUserData, compareStatus };
}
