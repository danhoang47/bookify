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
import { signIn } from "@/services/user";
import { UserContext } from "@/utils/contexts";
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
  const { user, isLogin, setLogin } = useContext(UserContext);
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
        await signIn(account.username, account.password).then((data) => {
          if (data?.error) {
            console.log("account not found : " + data.error);
          } else {
            console.log(data);
            localStorage.setItem("jwt", data.token);
            user.name = data.user.name;
            user.account_number = data.user.account_number;
            user.avatar = data.user.avatar;
            user.dob = format(new Date(data.user.dob), "yyyy-MM-dd");
            user.email = data.user.email;
            user.phone = data.user.phone;
            user.role = data.user.role;
            user.self_description = data.user.self_description;
            user.subname = data.user.subname;
            user.user_id = data.user.user_id;
            user.username = data.user.username;

            setLogin(true);
            setModalOpen(event);
          }
        });
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

  console.log(account.username, account.password);

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
