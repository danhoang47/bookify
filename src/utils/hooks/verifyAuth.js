import { useEffect, useState, useRef, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../contexts";

function VerifyAuth() {
  const { user, setUser, isLogin, setLogin } = useContext(UserContext);
  //   const navigate = useNavigate();

  useEffect(() => {
    const jwtString = JSON.stringify(localStorage.getItem("jwt"));
    const userForm = new FormData();
    userForm.append("jwt", jwtString);
    if (jwtString) {
      fetch("http://localhost:8080/bookify/api/user/verifyjwt", {
        method: "POST",
        body: userForm,
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
    }
  }, []);

  return { user, isLogin };
}

export default VerifyAuth;
