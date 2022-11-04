import "./_global.scss";
import { useEffect, useMemo, useReducer, useState } from "react";
import {
  ModalContext,
  UserContext,
  CoordinatesContext,
  ToastMessageContext,
} from "@/utils/contexts";
import { modalReducer, toastMessageReducer } from "./utils/reducers";
import { Modal, ToastMessage, ToastMessageBox } from "./components";
import { Container } from "@mui/material";
import { format } from "date-fns";
import { useNavigate } from "react-router-dom";

const initState = {
  isOpen: false,
  isOverlay: false,
};

const user = {
  account_number: "",
  avatar: "",
  dob: "",
  email: "",
  name: "",
  phone: "",
  role: 0,
  self_description: "",
  subname: "",
  user_id: "",
  username: "",
};

function App({ children }) {
  const [modalState, dispatch] = useReducer(modalReducer, initState);
  const [isLogin, setLogin] = useState(false);
  const [currentCoordinates, setCurrentCoordinates] = useState();
  const [toastMessages, setToastMessages] = useReducer(toastMessageReducer, []);

  const modal = useMemo(() => {
    return {
      modalState,
      dispatch,
    };
  }, [modalState]);

  const userContextValue = useMemo(
    () => ({
      user,
      isLogin,
      setLogin,
    }),
    [isLogin]
  );

  const toastMessageContextValue = useMemo(
    () => ({
      setToastMessages,
    }),
    []
  );

  useEffect(() => {
    const nav = navigator.geolocation;
    nav.getCurrentPosition((pos) => {
      if (pos) {
        const { latitude, longitude } = pos?.coords;
        setCurrentCoordinates({
          latitude,
          longitude,
        });
      }
    });
  }, []);

  useEffect(() => {
    const jwtString = JSON.stringify(localStorage.getItem("jwt"));
    console.log(jwtString);
    const userForm = new FormData();
    userForm.append("jwt", jwtString);
    if (jwtString) {
      fetch("http://localhost:8080/bookify/api/user/verifyjwt", {
        method: "POST",
        body: userForm,
      })
        .then((res) => res.json())
        .then((data) => {
          setLogin(true);
          user.name = data.name ? data.name : null;
          user.account_number = data.account_number
            ? data.account_number
            : null;
          user.avatar = data.avatar ? data.avatar : null;
          user.dob = data.dob ? format(new Date(data.dob), "yyyy-MM-dd") : null;
          user.email = data.email;
          user.phone = data.phone ? data.phone : null;
          user.role = data.role ? data.role : 0;
          user.self_description = data.self_description
            ? data.self_description
            : null;
          user.subname = data.subname ? data.subname : null;
          user.user_id = data.user_id;
          user.username = data.username;
        })
        .catch((err) => {
          console.log("Login again: " + err);
          setLogin(false);
        });
    }
  });

  return (
    <CoordinatesContext.Provider value={currentCoordinates}>
      <UserContext.Provider value={userContextValue}>
        <ModalContext.Provider value={modal}>
          <ToastMessageContext.Provider value={toastMessageContextValue}>
            <Container
              maxWidth={"sx"}
              sx={{
                position: "relative",
              }}
            >
              {children}
              {modalState.isOpen && (
                <div className="overlay">
                  <Modal>{modalState.renderModal()}</Modal>
                </div>
              )}
              <ToastMessageBox>
                {toastMessages.map(({ type, message }) => (
                  <ToastMessage type={type} message={message} />
                ))}
              </ToastMessageBox>
            </Container>
          </ToastMessageContext.Provider>
        </ModalContext.Provider>
      </UserContext.Provider>
    </CoordinatesContext.Provider>
  );
}

export default App;
