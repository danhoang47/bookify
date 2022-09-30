import LoginandSecurityStyle from "./LoginandSecurity.module.scss";
import HeaderInfo from "./components/HeaderInfo";
import FormUpdate from "./components/Form";

const account = {
  username: "duc123",
  subname: "Le Quy",
  name: "Duc",
  email: "duc@gmail.com",
  phone: "129031201238",
  dob: "02/02/2002",
  selfDes:
    "Esse tempor magna et nulla sunt ea excepteur tempor incididunt nisi labore id. Eu dolor quis cupidatat occaecat laborum cillum culpa minim dolore. Aliqua est ullamco enim voluptate in. ",
};
function LoginandSecurity() {
  return (
    <div className={LoginandSecurityStyle["container"]}>
      <HeaderInfo />
      <FormUpdate account={account} />
    </div>
  );
}

export default LoginandSecurity;
