import "./_global.scss";
import { useEffect, useMemo, useReducer, useState } from "react";
import {
    ModalContext,
    UserContext,
    CoordinatesContext,
    ToastMessageContext
} from "@/utils/contexts";
import { reducer } from "./utils/reducers/modalReducer";
import { Modal } from "./components";
import { Container } from "@mui/material";

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
    const [modalState, dispatch] = useReducer(reducer, initState);
    const [isLogin, setLogin] = useState(false);
    const [currentCoordinates, setCurrentCoordinates] = useState();

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
        [user]
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

    // useEffect(() => {
    //     const jwtString = JSON.stringify(localStorage.getItem("jwt"));
    //     console.log(jwtString);
    //     if (jwtString) {
    //         fetch(
    //             "http://localhost:8080/testUpload/rest/user_detail/verifyjwt",
    //             {
    //                 method: "POST",
    //                 body: jwtString,
    //             }
    //         )
    //             .then((res) => {
    //                 res.json();
    //             })
    //             .then((data) => {
    //                 console.log(data);
    //             })
    //             .catch((err) => console.log(err));
    //     }
    // }, []);

    return (
        <CoordinatesContext.Provider value={currentCoordinates}>
            <UserContext.Provider value={userContextValue}>
                <ModalContext.Provider value={modal}>
                    <ToastMessageContext.Provider>
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
                    </ToastMessageContext.Provider>
                </ModalContext.Provider>
            </UserContext.Provider>
        </CoordinatesContext.Provider>
    );
}

export default App;
