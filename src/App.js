import DefaultLayout from "./layouts/DefaultLayout";
import "./_global.scss";
import { useMemo, useReducer, useState } from "react";
import { ModalContext, UserContext } from "@/utils/contexts";
import { reducer } from "./utils/reducers/modalReducer";
import { Modal } from "./components";
import { RouterProvider } from "react-router-dom";
import router from "./routes";
import { Outlet } from "react-router-dom";

const initState = {
  isOpen: false,
  isOverlay: false,
};

const user = {
  id: 123456,
  username: "Quoc Dat",
  cardNumber: "1542 - 5644 - 2545 - 2871",
};

function App({ children }) {
  const [modalState, dispatch] = useReducer(reducer, initState);
  const [isLogin, setLogin] = useState(false);

  const modal = useMemo(() => {
    return {
      modalState,
      dispatch,
    };
  }, [modalState]);

  const userModifier = useMemo(() => {
    return {
      user,
      isLogin,
      setLogin,
    };
  }, [isLogin]);

  return (
    <UserContext.Provider value={userModifier}>
      <ModalContext.Provider value={modal}>
        <div className="App">
          {children}
          {modalState.isOpen && (
            <div className="overlay">
              <Modal>{modalState.renderModal()}</Modal>
            </div>
          )}
        </div>
      </ModalContext.Provider>
    </UserContext.Provider>
  );
}

export default App;
