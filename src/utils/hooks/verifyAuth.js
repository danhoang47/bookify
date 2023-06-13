import { useState, useRef, useContext } from "react";
import { useQuery } from "@tanstack/react-query";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../contexts";
import { VerifyJwt } from "@/services-new/user/VerifyJwt";

function VerifyAuth() {
  // const { user, setUser, isLogin, setLogin } = useContext(UserContext);
  const [firstLogin, setFirstLogin] = useState(false);
  const [userLocal, setUser] = useState();
  //   const navigate = useNavigate();
  const verifyData = useQuery({
    queryKey: ["verify"],
    queryFn: VerifyJwt,
    onSuccess: (data) => {
      // console.log(data);
      if (data.status === 500) {
        setFirstLogin(false);
        localStorage.removeItem("user");
      } else {
        setFirstLogin(true);
        console.log(data);
        setUser(data.user);
      }
      // setLogin(true);
    },
    onError: (error) => {
      console.log(error);
    },
  });

  return { userLocal, firstLogin, verifyData };
}
export default VerifyAuth;
