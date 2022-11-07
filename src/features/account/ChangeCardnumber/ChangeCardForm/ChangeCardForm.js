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
import { ToastMessageContext, UserContext } from "@/utils/contexts";
import { signIn } from "@/services/user";
import { CircleLoading } from "@/components";
import { format } from "date-fns";
import { validCardNumber } from "@/utils/validation";

function ChangeCardForm({ setModalOpen }) {
  const [cardNum, setCardnum] = useState("");
  const { setUser, isLogin, setLogin } = useContext(UserContext);
  console.log(setUser);
  const { setToastMessages } = useContext(ToastMessageContext);
  const [isLoading, setLoading] = useState(false);
  const [isRemember, setRemember] = useState(false);
  const changedKey = useRef();
  const isInformationFilled = useMemo(() => {
    const isAllFilled = cardNum !== null;
    return isAllFilled;
  }, [cardNum]);

  const isRegexValid = useMemo(() => {
    if (validCardNumber.test(cardNum)) return true;
    return false;
  }, [cardNum]);
  console.log(isRegexValid);
  // handle event functions
  const handleSubmit = async (event) => {
    event.stopPropagation();
    event.preventDefault();
    if (isLoading || !isInformationFilled) {
      return;
    } else {
      setLoading(true);

      try {
        if (isRegexValid) {
          //check if regex để fetch
        }
        // await signIn(cardNum.username, cardNum.password).then((data) => {
        //   if (data?.error) {
        //     console.log("cardNum not found : " + data.error);
        //   } else {
        //     localStorage.setItem("jwt", data.token);
        //     setUser(data.user);
        //     setLogin(true);
        //     setToastMessages(
        //       getSuccessToastMessage({
        //         message: "Đăng nhập thành công",
        //       })
        //     );
        //     setModalOpen(event);
        //   }
        // });
        console.log("form submit");
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

  //   useEffect(() => {
  //     const changedField = changedKey.current;

  //     if (changedField) {
  //       setCardValid((prev) => {
  //         return {
  //           ...prev,
  //           [changedField]: cardNumValidation(
  //             changedField,
  //             cardNum[changedField],
  //             "",
  //             true
  //           ),
  //         };
  //       });
  //     }
  //   }, [cardNum]);

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
          id="card"
          onValueChange={handlecardNumChange}
          isValid={true}
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
