import { useEffect, useState, useRef, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../contexts";

function VerifyAuth() {
  const { user, setUser, isLogin, setLogin } = useContext(UserContext);
  //   const navigate = useNavigate();

  useEffect(() => {
    fetch("http://localhost:3001/user/verifyjwt", {
      credentials: 'include' , method: "POST",
    })
      .then((res) => res.json())
      .then((data) => {
        setLogin(true);
        setUser(data);
        console.log(data);
      })
      .catch((err) => {
        console.log("Login again: " + err);
        setLogin(false);
      });
  }, []);

  return { user, isLogin };
}

export default VerifyAuth;
