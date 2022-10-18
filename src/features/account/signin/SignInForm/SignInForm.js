import { InputField } from "../../components";
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
import { useUppercase } from "@/utils/hooks";
import { accountValidation } from "@/utils/validation";
import { UserContext } from "@/utils/contexts";

function SignInForm() {
  let { user, isLogin, setLogin } = useContext(UserContext);

  const [account, setAccount] = useState({
    username: null,
    password: null,
  });
  const [isAccountValid, setAccountValid] = useState({
    username: true,
    password: true,
  });
  const isInformationFilled = useMemo(() => {
    const isAllFilled = Object.keys(account).every((key) => {
      return account[key] !== null;
    });
    const isAllValid = Object.keys(isAccountValid).every((key) => {
      return isAccountValid[key];
    });
    return isAllFilled && isAllValid;
  }, [account, isAccountValid]);
  const [isRemember, setRemember] = useState(false);
  const changedKey = useRef();

  const handleSubmit = (e) => {
    e.preventDefault();
    const form = new FormData();
    form.append("username", account.username);
    form.append("password", account.password);
    fetch("/rest/user_detail/login", {
      mode: "no-cors",
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: form,
    })
      .then((res) => res.json())
      .then((data) => {
        setLogin(true);
        user.id = data.user.user_id;
        user.username = data.user.username;
        user.avatar = data.user.avatar;
        user.wallet_amount = data.user.wallet_amount;
        console.log(user);
      })
      .catch((err) => {
        console.log(err);
      });
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

  // console.log("re-render ", isInformationFilled);
  return (
    <div className={formStyles["form-wrapper"]}>
      <form
        onSubmit={(e) => handleSubmit(e)}
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
            type="submit"
            className={[
              formStyles["sign-in-button"],
              isInformationFilled ? "" : formStyles["button-disabled"],
            ].join(" ")}
          >
            Sign In
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
