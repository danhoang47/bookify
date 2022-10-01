import { useEffect, useState, useRef } from "react";
import FormUpdateStyle from "./FormUpdate.module.scss";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";

import FileUpload from "../FileUpload";
import PersonalInput from "../PersonalInput";
import UpdateButton from "../UpdateButton";
import DatePicker from "../DatePicker";

function FormUpdate({ account }) {
  const [subname, setSubname] = useState(account.subname);
  const [name, setName] = useState(account.name);
  const [email, setEmail] = useState(account.email);
  const [phone, setPhone] = useState(account.phone);
  const [dob, setDob] = useState(account.dob);
  const [des, setDes] = useState(account.selfDes);
  const [avatar, setAvatar] = useState(account.avatar);

  const onChangeSubname = (e) => {
    setSubname(e.target.value);
  };
  const onChangeName = (e) => {
    setName(e.target.value);
  };
  const onChangeEmail = (e) => {
    setEmail(e.target.value);
  };
  const onChangePhone = (e) => {
    setPhone(e.target.value);
  };
  const onChangeDob = (e) => {
    var currentDate = new Date(e.target.value);
    console.log(currentDate);
    var month = currentDate.getMonth() + 1;
    if (month < 10) month = "0" + month;
    var dateOfMonth = currentDate.getDate();
    if (dateOfMonth < 10) dateOfMonth = "0" + dateOfMonth;
    var year = currentDate.getFullYear();
    var formattedDate = dateOfMonth + "/" + month + "/" + year;
    console.log(formattedDate);
    setDob(e.target.value);
  };

  const onAvatarUpload = (data) => {
    setAvatar(data);
  };

  const onSubmitClick = (e) => {
    e.preventDefault();
    console.log(subname, name, email, phone, dob, des, avatar);

    // const formData = new FormData();
    // formData.append("subname", subname);
    // formData.append("name", name);
    // formData.append("email", email);
    // formData.append("phone", phone);
    // formData.append("dob", dob);
    // formData.append("des", des);
    // formData.append("avatar", avatar);

    // fetch("<link>", {
    //   method: "POST",
    //   body: formData,
    // })
    //   .then((data) => {
    //     data.json();
    //   })
    //   .then((result) => {
    //     console.log(result);
    //   })
    //   .catch((error) => {
    //     console.log(error);
    //   });
  };

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
                    value={subname}
                    onChange={onChangeSubname}
                    labelContent={"Họ và tên đệm"}
                  />
                </Grid>
                <Grid item xs={12} md={4}>
                  <PersonalInput
                    type={"text"}
                    placeholder={"Điền tên bạn"}
                    name={"name"}
                    value={name}
                    onChange={onChangeName}
                    labelContent={"Tên"}
                  />
                </Grid>
              </Grid>
              <Grid container spacing={2}>
                <Grid item xs={12} md={10}>
                  <PersonalInput
                    type={"email"}
                    placeholder={"Điền Email"}
                    name={"email"}
                    value={email}
                    onChange={onChangeEmail}
                    labelContent={"Email"}
                  />
                </Grid>
              </Grid>
              <Grid container spacing={2}>
                <Grid item xs={12} md={6}>
                  <PersonalInput
                    type={"phone"}
                    placeholder={"Điền số điền thoại"}
                    name={"phone"}
                    value={phone}
                    onChange={onChangePhone}
                    labelContent={"Số điện thoại"}
                  />
                </Grid>
              </Grid>
              <Grid container spacing={2}>
                <Grid item xs={12} md={8}>
                  {/* <PersonalInput /> */}
                  <DatePicker
                    name={"dob"}
                    value={dob}
                    onChange={onChangeDob}
                    labelContent={"Ngày sinh"}
                  />
                </Grid>
              </Grid>
              <Grid container spacing={2}>
                <Grid item xs={12} md={11}>
                  <span className={FormUpdateStyle["des-input-field"]}>
                    {/* email ------------------------------- */}
                    <textarea
                      spellCheck="false"
                      rows="5"
                      placeholder="Mô tả bản thân"
                      name="des"
                      className={FormUpdateStyle["textarea-update"]}
                      onChange={(e) => {
                        setDes(e.target.value);
                      }}
                      defaultValue={des}
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
                    avatar={account.avatar}
                    onAvatarUpload={onAvatarUpload}
                  />
                </Grid>
              </Grid>
            </Grid>
          </Grid>
        </Box>
        <span className={FormUpdateStyle["button-field"]}>
          <UpdateButton content={"Lưu"} type={"save"} onClick={onSubmitClick} />
          <UpdateButton content={"Hủy"} type={"cancel"} />
        </span>
      </form>
    </div>
  );
}

export default FormUpdate;
