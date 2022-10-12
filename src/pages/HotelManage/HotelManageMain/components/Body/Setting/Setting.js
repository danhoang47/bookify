import SettingStyle from "../../../HotelManage.module.scss";
import { ModalContext } from "@/utils/contexts";
import Grid from "@mui/material/Grid";
import { useContext } from "react";

import {
  getHotelSettingModal,
  getSignInModal,
} from "@/utils/reducers/modalReducer";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

function Setting({ setting }) {
  const { dispatch } = useContext(ModalContext);

  const handleClick = (e) => {
    e.preventDefault();
    dispatch(setting.modal);
  };
  console.log(setting);
  return (
    <Grid item xs={12} lg={4}>
      <div onClick={handleClick} className={SettingStyle["setting-card"]}>
        <FontAwesomeIcon
          className={SettingStyle["setting-icon"]}
          icon={setting.icon}
        />
        <h4>{setting.title}</h4>
      </div>
    </Grid>
  );
}
export default Setting;
