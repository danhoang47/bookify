import PersonalInfoStyle from "./PersonalInfo.module.scss";
import HeaderInfo from "./components/HeaderInfo";
import FormUpdate from "./components/FormUpdate";
import { UserContext } from "@/utils/contexts";
import { useContext, useMemo } from "react";

// const account = {
//   subname: "Le Quy",
//   name: "Duc",
//   email: "duc@gmail.com",
//   phone: "129031201238",
//   dob: "26/02/2002",
//   selfDes:
//     "Esse tempor magna et nulla sunt ea excepteur tempor incididunt nisi labore id. Eu dolor quis cupidatat occaecat laborum cillum culpa minim dolore. Aliqua est ullamco enim voluptate in. ",
//   avatar: "https://variety.com/wp-content/uploads/2021/04/Avatar.jpg",
// };

function PersonalInfo() {
  let { user } = useContext(UserContext);
  user.subname = "Le Quy";
  user.dob = "12/02/2002";
  user.name = "Duc";
  user.avatar = "https://variety.com/wp-content/uploads/2021/04/Avatar.jpg";

  return (
    <div className={PersonalInfoStyle["container"]}>
      <HeaderInfo />
      <FormUpdate account={user} />
    </div>
  );
}

export default PersonalInfo;
