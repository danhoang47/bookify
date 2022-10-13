import "./_global.scss";
import { useMemo, useReducer, useState } from "react";
import { ModalContext, UserContext } from "@/utils/contexts";
import { reducer } from "./utils/reducers/modalReducer";
import { Modal } from "./components";

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
<<<<<<< HEAD
  const [isLogin, setLogin] = useState(true );
=======
  const [isLogin, setLogin] = useState(true);
>>>>>>> a25a1b69d065e5ec31b6e2f7825f4c405f11e042

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
