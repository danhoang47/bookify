import optionListStyles from "./OptionList.module.scss";
import { ModalContext, UserContext } from "@/utils/contexts";
import { useContext, useMemo } from "react";
import { getSignUpModal, getSignInModal } from "@/utils/reducers/modalReducer";
import { useNavigate } from "react-router-dom";

function OptionList() {
  const { dispatch } = useContext(ModalContext);
  const { isLogin } = useContext(UserContext);
  const navigate = useNavigate();

  const options = useMemo(
    () => [
      {
        title: "Thông báo",
        style: "primary",
        isLoginRequired: true,
        onClickHandler: (e) => {
          console.log(e.target);
        },
      },
      {
        title: "Danh sách yêu thích",
        style: "primary",
        isLoginRequired: true,
      },
      {
        title: "Đặt phòng",
        style: "primary",
        isLoginRequired: true,
      },
      {
        title: "Tài khoản",
        style: "secondary",
        isLoginRequired: true,
        onClickHandler: (event) => {
          event.stopPropagation();
          // navigate("/profile");
        },
      },
      {
        title: "Hỗ trợ",
        style: "secondary",
        isLoginRequired: true,
      },
      {
        title: "Đăng xuất",
        style: "secondary",
        isLoginRequired: true,
      },
      {
        title: "Đăng nhập",
        style: "secondary",
        isLoginRequired: false,
        onClickHandler: (e) => {
          e.stopPropagation();
          dispatch(getSignInModal({ isOpen: true }));
        },
      },
      {
        title: "Đăng ký",
        style: "secondary",
        isLoginRequired: false,
        onClickHandler: (e) => {
          e.stopPropagation();
          dispatch(getSignUpModal({ isOpen: true }));
        },
      },
    ],
    []
  );

  return (
    <>
      <ul className={optionListStyles["option-list"]}>
        {options.reduce(
          (prev, { title, style, isLoginRequired, onClickHandler }, index) => {
            if (isLogin === isLoginRequired) {
              return [
                ...prev,
                <li
                  key={index}
                  className={optionListStyles[style]}
                  onClick={onClickHandler}
                >
                  {title}
                </li>,
              ];
            }
            return [...prev];
          },
          []
        )}
      </ul>
    </>
  );
}

export default OptionList;
