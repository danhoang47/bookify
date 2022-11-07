import {
  SignInModal,
  SignUpModal,
  PasswordModal,
  NewPasswordModal,
  HotelSettingModal,
} from "@/features/account";

const modalType = {
  SIGN_IN: "sign-in",
  SIGN_UP: "sign-up",
  FILTER: "filter",
  PASSWORD: "password",
  NEWPASSWORD: "newpassword",
  HOTELSETTING: "hotelsetting",
};

const getSignInModal = (payload) => {
  return {
    ...payload,
    type: modalType.SIGN_IN,
  };
};

const getSignUpModal = (payload) => {
  return {
    ...payload,
    type: modalType.SIGN_UP,
  };
};

const getFilterModal = (payload) => {
  return {
    ...payload,
    type: modalType.SIGN_IN,
  };
};
const getPasswordModal = (payload) => {
  return {
    ...payload,
    type: modalType.PASSWORD,
  };
};
const getNewPasswordModal = (payload) => {
  return {
    ...payload,
    type: modalType.NEWPASSWORD,
  };
};
const getHotelSettingModal = (payload) => {
  return { ...payload, type: modalType.HOTELSETTING };
};

const reducer = (state, modal) => {
  const newState = {
    ...state,
    isOpen: modal.isOpen,
  };
  switch (modal.type) {
    case modalType.SIGN_IN:
      return {
        ...newState,
        renderModal: () => <SignInModal animation={modal?.animation} />,
      };
    case modalType.SIGN_UP:
      return {
        ...newState,
        renderModal: () => <SignUpModal animation={modal?.animation} />,
      };
    case modalType.PASSWORD:
      return {
        ...newState,
        renderModal: () => <PasswordModal animation={modal?.animation} />,
      };
    case modalType.NEWPASSWORD:
      return {
        ...newState,
        renderModal: () => <NewPasswordModal animation={modal?.animation} />,
      };
    case modalType.HOTELSETTING:
      return {
        ...newState,
        renderModal: () => <HotelSettingModal animation={modal?.animation} />,
      };
    case modalType.FILTER:
      break;
    default:
      throw new Error("Invalid Modal Type");
  }
};
export {
  reducer,
  modalType,
  getSignInModal,
  getSignUpModal,
  getFilterModal,
  getPasswordModal,
  getNewPasswordModal,
  getHotelSettingModal,
}; 
