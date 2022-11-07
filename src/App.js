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

const appInitState = {
  isOpen: false,
  isOverlay: false,
};

const userInitState = {
  account_number: "",
  avatar: "",
  dob: "",
  email: "",
  name: "",
  phone: "",
  role: 0,
  self_description: "",
  subname: "",
  user_id: null,
  username: "",
  bank_card: ""
};

function App({ children }) {
  const [modalState, dispatch] = useReducer(modalReducer, appInitState);
  const [user, setUser] = useState(userInitState);
  const [isLogin, setLogin] = useState(true);
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
      setUser,
      isLogin,
      setLogin,
    }),
    [isLogin, user]
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

  console.log("App hereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

  useEffect(() => {
    const jwtString = JSON.stringify(localStorage.getItem("jwt"));
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
          setUser(data);
        })
        .catch((err) => {
          setLogin(false);
        });
    }
  }, []);

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
