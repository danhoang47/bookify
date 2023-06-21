import { useState, useContext, useEffect, useCallback, useRef } from "react";
import FormUpdateStyle from "./FormUpdate.module.scss";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import { format } from "date-fns";
import FileUpload from "../FileUpload";
import PersonalInput from "../PersonalInput";
import UpdateButton from "../UpdateButton";
import DatePicker from "../DatePicker";
import { ToastMessageContext, UserContext } from "@/utils/contexts";
import {
  getFailureToastMessage,
  getSuccessToastMessage,
} from "@/utils/reducers/toastMessageReducer";
import { useUser } from "@/utils/hooks";

function FormUpdate({ account }) {
  const { userData, isLoading, updateUser, setUserData } = useUser();
  const { user, setUser } = useContext(UserContext);
  const changedKey = useRef();
  const { setToastMessages } = useContext(ToastMessageContext);
  const [subname, setSubname] = useState();
  const [name, setName] = useState(account.name);
  const [email, setEmail] = useState(account.email);
  const [phone, setPhone] = useState(account.phone);
  const [dob, setDob] = useState(account.dob);
  const [des, setDes] = useState(account.self_description);
  const [avatar, setAvatar] = useState(account.avatar);
  const [readOnly, setReadOnly] = useState(true);
  useEffect(() => {
    console.log(userData);
  }, [userData]);
  const onChangeDob = (e) => {
    var currentDate = new Date(e.target.value);
    console.log(currentDate);
    var month = currentDate.getMonth() + 1;
    if (month < 10) month = "0" + month;
    var dateOfMonth = currentDate.getDate();
    if (dateOfMonth < 10) dateOfMonth = "0" + dateOfMonth;
    var year = currentDate.getFullYear();
    var formattedDate = dateOfMonth + "/" + month + "/" + year;
    console.log("format " + formattedDate);
    setUserData((prev) => {
      return { ...prev, ["dob"]: e.target.value };
    });
  };

  const onUpdateInput = useCallback(
    (value, key) => {
      setUserData((prev) => {
        return {
          ...prev,
          [key]: value,
        };
      });
      changedKey.current = key;
    },
    [userData]
  );
  const onSubmitClick = (e) => {
    e.preventDefault();
    updateUser(userData);
    setReadOnly(true);
  };
  if (isLoading === true) {
    return <span>Loading...</span>;
  }

  return (
    <div>
      <form action="" className={FormUpdateStyle["form"]}>
        <Box sx={{ flexGrow: 1 }}>
          <Grid container spacing={2}>
            <Grid item xs={12} md={6}>
              <Grid container spacing={2}>
                <Grid item xs={12} md={6}>
                  <PersonalInput
                    type={"text"}
                    placeholder={"Điền tên đệm của bạn"}
                    name={"subname"}
                    value={!isLoading ? userData?.subName : ""}
                    onChange={(event) =>
                      onUpdateInput(event.target.value, "subName")
                    }
                    labelContent={"Họ và tên đệm"}
                    readOnly={readOnly}
                  />
                </Grid>
                <Grid item xs={12} md={4}>
                  <PersonalInput
                    type={"text"}
                    placeholder={"Điền tên bạn"}
                    name={"name"}
                    value={!isLoading ? userData?.name : ""}
                    onChange={(event) =>
                      onUpdateInput(event.target.value, "name")
                    }
                    labelContent={"Tên"}
                    readOnly={readOnly}
                  />
                </Grid>
              </Grid>
              <Grid container spacing={2}>
                <Grid item xs={12} md={10}>
                  <PersonalInput
                    type={"email"}
                    placeholder={"Điền Email"}
                    name={"email"}
                    value={!isLoading ? userData?.email : ""}
                    onChange={(event) =>
                      onUpdateInput(event.target.value, "email")
                    }
                    labelContent={"Email"}
                    readOnly={readOnly}
                  />
                </Grid>
              </Grid>
              <Grid container spacing={2}>
                <Grid item xs={12} md={6}>
                  <PersonalInput
                    type={"phone"}
                    placeholder={"Điền số điền thoại"}
                    name={"phone"}
                    value={!isLoading ? userData?.phone : ""}
                    onChange={(event) =>
                      onUpdateInput(event.target.value, "phone")
                    }
                    labelContent={"Số điện thoại"}
                    readOnly={readOnly}
                  />
                </Grid>
              </Grid>
              <Grid container spacing={2}>
                <Grid item xs={12} md={8}>
                  {/* <PersonalInput /> */}
                  <DatePicker
                    name={"dob"}
                    value={!isLoading ? userData?.dob : ""}
                    onChange={onChangeDob}
                    labelContent={"Ngày sinh"}
                    readOnly={readOnly}
                  />
                </Grid>
              </Grid>
              <Grid container spacing={2}>
                <Grid item xs={12} md={11}>
                  <span
                    className={
                      !readOnly
                        ? FormUpdateStyle["des-input-field"]
                        : FormUpdateStyle["des-input-field-readOnly"]
                    }
                  >
                    {/* email ------------------------------- */}
                    <textarea
                      spellCheck="false"
                      rows="5"
                      placeholder="Mô tả bản thân"
                      name="des"
                      className={
                        !readOnly
                          ? FormUpdateStyle["textarea-update"]
                          : FormUpdateStyle["textarea-update-readOnly"]
                      }
                      onChange={(event) => {
                        onUpdateInput(event.target.value, "selfDescription");
                      }}
                      defaultValue={isLoading ? userData.selfDescription : ""}
                      readOnly={readOnly}
                    ></textarea>
                    <label
                      className={FormUpdateStyle["input-label"]}
                      htmlFor="des"
                    >
                      Mô tả về bản thân
                    </label>
                  </span>
                </Grid>
              </Grid>
            </Grid>
            <Grid item xs={12} md={6}>
              <Grid container spacing={2}>
                <Grid item xs={12} md={5}>
                  <FileUpload
                    avatar={
                      userData?.avatar !== "" ||
                      userData?.avatar !==
                        "http://localhost:8080/bookify/images/users/null"
                        ? userData?.avatar
                        : "http://localhost:8080/bookify/images/users/blankUser.jpg"
                    }
                    onAvatarUpload={onUpdateInput}
                    readOnly={readOnly}
                  />
                </Grid>
              </Grid>
            </Grid>
          </Grid>
        </Box>
        <span className={FormUpdateStyle["button-field"]}>
          {readOnly ? (
            <>
              <UpdateButton
                content={"Chỉnh sửa"}
                type={"save"}
                onClick={(e) => {
                  e.preventDefault();
                  setReadOnly(false);
                }}
              />
            </>
          ) : (
            <>
              <UpdateButton
                content={"Lưu"}
                type={"save"}
                onClick={onSubmitClick}
              />
              <UpdateButton
                content={"Hủy"}
                type={"cancel"}
                onClick={(e) => {
                  e.preventDefault();
                  setReadOnly(true);
                }}
              />
            </>
          )}
        </span>
      </form>
    </div>
  );
}

export default FormUpdate;
