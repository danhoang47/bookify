import "./_global.scss";
import { useEffect, useMemo, useReducer, useState } from "react";
import {
    ModalContext,
    UserContext,
    CoordinatesContext,
} from "@/utils/contexts";
import { reducer } from "./utils/reducers/modalReducer";
import { Modal } from "./components";
import { Container, Box } from "@mui/material";

const initState = {
    isOpen: false,
    isOverlay: false,
};

const user = {
    id: 123456,
    username: "Quoc Dat",
    cardNumber: "1542 - 5644 - 2545 - 2871",
    jwt: "",
    role: "",
    avatar: "",
};

function App({ children }) {
    const [modalState, dispatch] = useReducer(reducer, initState);
    const [isLogin, setLogin] = useState(false);
    const [currentCoordinates, setCurrentCoordinates] = useState();

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

    useEffect(() => {
        const nav = navigator.geolocation;
        nav.getCurrentPosition((pos) => {
            if (pos) {
                const { latitude, longitude } = pos?.coords;
                console.log(latitude, longitude);
                setCurrentCoordinates({
                    latitude,
                    longitude,
                });
            }
        });
    }, []);

    return (
        <CoordinatesContext.Provider value={currentCoordinates}>
            <UserContext.Provider value={userModifier}>
                <ModalContext.Provider value={modal}>
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
                    </Container>
                </ModalContext.Provider>
            </UserContext.Provider>
        </CoordinatesContext.Provider>
    );
}

export default App;
