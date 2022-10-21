import PersonalInfoStyle from "./PersonalInfo.module.scss";
import HeaderInfo from "./components/HeaderInfo";
import FormUpdate from "./components/FormUpdate";
import { UserContext } from "@/utils/contexts";
import { useContext } from "react";

function PersonalInfo() {
  let { user } = useContext(UserContext);

  return (
    <div className={PersonalInfoStyle["container"]}>
      <HeaderInfo />
      <FormUpdate account={user} />
    </div>
  );
}

export default PersonalInfo;
