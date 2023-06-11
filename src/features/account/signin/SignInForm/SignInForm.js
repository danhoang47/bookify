import { InputField } from "@/components";
import formStyles from "./SignInForm.module.scss";
import {
    useState,
    memo,
    useCallback,
    useEffect,
    useRef,
    useMemo,
    useContext,
} from "react";
import { faGoogle } from "@fortawesome/free-brands-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { getSuccessToastMessage } from "@/utils/reducers/toastMessageReducer";
import { useUppercase } from "@/utils/hooks";
import { accountValidation } from "@/utils/validation";
import { ToastMessageContext, UserContext } from "@/utils/contexts";
import { signIn } from "@/services/user";
import { SignIn } from "@/services-new/user";
import { CircleLoading } from "@/components";
import { format } from "date-fns";

function SignInForm({ setModalOpen }) {
    const [account, setAccount] = useState({
        username: null,
        password: null,
    });
    const [isAccountValid, setAccountValid] = useState({
        username: true,
        password: true,
    });
    const { setUser, isLogin, setLogin } = useContext(UserContext);
    const { setToastMessages } = useContext(ToastMessageContext);
    const [isLoading, setLoading] = useState(false);
    const [isRemember, setRemember] = useState(false);
    const changedKey = useRef();
    const isInformationFilled = useMemo(() => {
        const isAllFilled = Object.keys(account).every((key) => {
            return account[key] !== null;
        });
        const isAllValid = Object.keys(isAccountValid).every((key) => {
            return isAccountValid[key];
        });
        return isAllFilled && isAllValid;
    }, [account, isAccountValid]);

    // handle event functions
    const handleSubmit = async (event) => {
        event.stopPropagation();
        event.preventDefault();
        if (isLoading || !isInformationFilled) {
            return;
        } else {
            setLoading(true);
            try {
                await SignIn(account.username, account.password).then(
                    (data) => {
                        console.log(data);
                        if (data?.error) {
                            console.log("account not found : " + data.error);
                        } else {
                            // localStorage.setItem("jwt", data.token);
                            // setUser(data.user);
                            // setLogin(true);
                            // setToastMessages(
                            //     getSuccessToastMessage({
                            //         message: "Đăng nhập thành công",
                            //     })
                            // );
                            // setModalOpen(event);
                        }
                    }
                );
            } finally {
                setLoading(false);
            }
        }
    };
    const handleAccountChange = useCallback(
        (value, key) => {
            setAccount((prev) => {
                return {
                    ...prev,
                    [key]: value,
                };
            });
            changedKey.current = key;
        },
        //eslint-disable-next-line
        [account]
    );

    useEffect(() => {
        const changedField = changedKey.current;

        if (changedField) {
            setAccountValid((prev) => {
                return {
                    ...prev,
                    [changedField]: accountValidation(
                        changedField,
                        account[changedField],
                        "",
                        true
                    ),
                };
            });
        }
    }, [account]);

    return (
        <div className={formStyles["form-wrapper"]}>
            <form
                onSubmit={handleSubmit}
                className={formStyles["form"]}
                spellCheck={false}
            >
                {Object.keys(account).map((key) => (
                    <InputField
                        key={key}
                        value={account[key]}
                        id={key}
                        onValueChange={handleAccountChange}
                        isValid={isAccountValid[key]}
                        isSignIn={true}
                        // eslint-disable-next-line react-hooks/rules-of-hooks
                        label={useUppercase(key)}
                        type={key === "password" ? "password" : "text"}
                    />
                ))}

                <label htmlFor="remember-me">
                    <input
                        type="checkbox"
                        id="remember-me"
                        onChange={() => {
                            setRemember(!isRemember);
                        }}
                    />
                    Remember me
                </label>
                <p className={formStyles["reset-password-link"]}>
                    Forget your password
                </p>
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
                        {isLoading ? <CircleLoading /> : "Sign In"}
                    </button>
                    <button className={formStyles["google-sign-in-button"]}>
                        <FontAwesomeIcon icon={faGoogle} />
                        Sign In by Google
                    </button>
                </div>
            </form>
        </div>
    );
}

export default memo(SignInForm);