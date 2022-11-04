import { useEffect, useState, useRef, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../contexts";
import { format } from "date-fns";
function VerifyAuth() {
  const { user, isLogin, setLogin } = useContext(UserContext);
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
          user.name = data.name ? data.name : null;
          user.account_number = data.account_number
            ? data.account_number
            : null;
          user.avatar = data.avatar ? data.avatar : null;
          user.dob = data.dob ? format(new Date(data.dob), "yyyy-MM-dd") : null;
          user.email = data.email;
          user.phone = data.phone ? data.phone : null;
          user.role = data.role ? data.role : 0;
          user.self_description = data.self_description
            ? data.self_description
            : null;
          user.subname = data.subname ? data.subname : null;
          user.user_id = data.user_id;
          user.username = data.username;
        })
        .catch((err) => {
          console.log("Login again: " + err);
          setLogin(false);
        });
    }
  }, []);

  console.log(isLogin);

  return { user, isLogin };
}

export default VerifyAuth;
