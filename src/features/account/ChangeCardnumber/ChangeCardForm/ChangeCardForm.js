import { InputField } from "@/components";
import formStyles from "./SignInForm.module.scss";
import {
    useState,
    memo,
    useCallback,
    useRef,
    useMemo,
    useContext,
} from "react";
import {
    ModalContext,
    ToastMessageContext,
    UserContext,
} from "@/utils/contexts";
import { CircleLoading } from "@/components";
import updateBankingAccount from "@/services/hotel/updateBankingAccount";
import { getChangeCard } from "@/utils/reducers/modalReducer";
import { getSuccessToastMessage } from "@/utils/reducers/toastMessageReducer";

function ChangeCardForm({ setModalOpen }) {
    const [cardNum, setCardnum] = useState("");
    const { user, setUser } = useContext(UserContext);
    const { setToastMessages } = useContext(ToastMessageContext);
    const { dispatch } = useContext(ModalContext);
    const [isLoading, setLoading] = useState(false);
    const [isError, setError] = useState(false);
    const changedKey = useRef();
    const isInformationFilled = useMemo(() => {
        const isAllFilled = cardNum !== null;
        return isAllFilled;
    }, [cardNum]);

    // handle event functions
    const handleSubmit = async (event) => {
        event.stopPropagation();
        event.preventDefault();
        if (isLoading || !isInformationFilled) {
            return;
        } else {
            setLoading(true);

            try {
                const res = await updateBankingAccount(user.user_id, cardNum);
                if (res?.success) {
                    setUser((prev) => ({
                        ...prev,
                        bankingAccountNumber: cardNum,
                    }));
                    setToastMessages(
                        getSuccessToastMessage({
                            message: "Liên kết tài khoản thành công",
                        })
                    );
                    dispatch(getChangeCard({ isOpen: false }));
                } else {
                    setError(true);
                }
            } finally {
                setLoading(false);
            }
        }
    };
    const handlecardNumChange = useCallback(
        (value, key) => {
            setCardnum(value);
            changedKey.current = key;
        },
        //eslint-disable-next-line
        [cardNum]
    );

    return (
        <div className={formStyles["form-wrapper"]}>
            <form
                onSubmit={handleSubmit}
                className={formStyles["form"]}
                spellCheck={false}
            >
                <InputField
                    value={cardNum}
                    key="CardNum"
                    id="Card Number"
                    onValueChange={handlecardNumChange}
                    isValid={!isError}
                    isSignIn={true}
                    label="Card Number"
                    type="text"
                />

                <div className={formStyles["button-wrapper"]}>
                    <button
                        className={[
                            formStyles["sign-in-button"],
                            isInformationFilled || isLoading
                                ? ""
                                : formStyles["button-disabled"],
                        ].join(" ")}
                        onClick={handleSubmit}
                    >
                        {isLoading ? <CircleLoading /> : "Save"}
                    </button>
                </div>
            </form>
        </div>
    );
}

export default memo(ChangeCardForm);
