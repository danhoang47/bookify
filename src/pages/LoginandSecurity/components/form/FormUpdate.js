import FormUpdateStyle from "./FormUpdate.module.scss";
import {
  useState,
  useCallback,
  useEffect,
  useRef,
  useContext,
  useMemo,
} from "react";
import { ModalContext, UserContext } from "@/utils/contexts";
import Form from "../../../../components/Form";
import DropDown from "../../../../components/Input/DropDown";
import InputText from "../../../../components/Input/inputText";
import { accountValidation } from "@/utils/validation";

function FormUpdate() {
  const { user } = useContext(UserContext);
  const [inputs, setInputs] = useState({
    username: user.username,
    id: user.id,
  });
  const [account, setAccount] = useState({
    username: user.username,
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
  const changedKey = useRef();
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

  console.log(user);
  const banks = useMemo(
    () => [
      {
        title: "BIDV",
        description: "BIDV-Ngân Hàng ABC",
      },
      {
        title: "SaccomBank",
        description: "Saccombank-Ngân Hàng ABC",
      },
      {
        title: "VietcomBank",
        description: "VietcomBank-Ngân Hàng ABC",
      },
      {
        title: "TechcomBank",
        description: "Techcombank-Ngân Hàng ABC",
      },
      {
        title: "Ba mẹ Bank",
        description: "Ba mẹ Bank-Ngân Hàng uy tín vcl",
      },
    ],
    []
  );

  const handleChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setInputs((values) => ({ ...values, [name]: value }));
  };
  const clearInput = () => {
    setInputs({});
  };
  const handleForm = async (data) => {
    data.preventDefault();
    console.log(inputs);
  };
  return (
    <div className={FormUpdateStyle["container"]}>
      <form onSubmit={handleForm}>
        <div className={FormUpdateStyle["form-field"]}>
          <span className={FormUpdateStyle["text-input-field"]}>
            <label htmlFor="username">
              <b className={FormUpdateStyle["label"]}>username</b>
            </label>
            <input
              type="text"
              readOnly={true}
              placeholder="Điền username"
              name="username"
              value={user.username}
              className={FormUpdateStyle["input-update"]}
            />
          </span>
          <span className={FormUpdateStyle["text-input-field"]}>
            <label htmlFor="password">
              <b className={FormUpdateStyle["label"]}>Mật khẩu</b>
            </label>
            <input
              type="password"
              placeholder="***********"
              readOnly={true}
              name="password"
              value={inputs.password || ""}
              onChange={handleChange}
              className={FormUpdateStyle["input-update"]}
            />
          </span>
          <span className={FormUpdateStyle["text-input-field"]}>
            <label htmlFor="card-number">
              <b className={FormUpdateStyle["label"]}>Mã số thẻ</b>
            </label>
            <input
              type="text"
              readOnly={true}
              placeholder="XXXX-XXXX-XXXX-XXXX"
              name="cardNumber"
              value={user.cardNumber}
              className={FormUpdateStyle["input-update"]}
            />
          </span>
          <span className={FormUpdateStyle["text-input-field"]}>
            <label htmlFor="bank">
              <b className={FormUpdateStyle["label"]}>Ngân Hàng</b>
            </label>
            <select
              value={inputs.bank || ""}
              onChange={handleChange}
              className={FormUpdateStyle["input-update"]}
              name="bank"
            >
              {banks.map((selectOption, index) => (
                <option key={index} value={selectOption.title}>
                  {selectOption.description}
                </option>
              ))}
            </select>
          </span>
        </div>
      </form>
    </div>
  );
}

export default FormUpdate;
