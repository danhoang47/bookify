import IntroductionStyle from "./Introduction.module.scss";
import { Grid } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { ToastMessageContext, UserContext } from "@/utils/contexts";
import { getFailureToastMessage } from "@/utils/reducers/toastMessageReducer";
import { useContext, useEffect } from "react";

function Introduction() {
  const navigate = useNavigate();

  const handleRegister = (event) => {
    event.preventDefault();
    navigate("/hosting/register");
  };

  const { user, setUser, isLogin, setLogin } = useContext(UserContext);
  const { setToastMessages } = useContext(ToastMessageContext);
  useEffect(() => {
    fetch("http://localhost:3001/user/verifyjwt", {
      credentials: 'include' , method: "POST",
    })
      .then((res) => res.json())
      .then((data) => {
        setLogin(true);
        // setUser(data);
        console.log(data);
      })
      .catch((err) => {
        setLogin(false);
      });
  }, []);

  // useEffect(() => {
  //   const jwtString = JSON.stringify(localStorage.getItem("jwt"));
  //   const userForm = new FormData();
  //   userForm.append("jwt", jwtString);
  //   if (jwtString) {
  //     fetch("http://localhost:3001/user/verifyjwt", {
  //       method: "POST",
  //       body: userForm,
  //     })
  //       .then((res) => res.json())
  //       .then((data) => {
  //         setLogin(true);
  //         setUser(data);
  //       })
  //       .catch((err) => {
  //         setUser({ role: 0 });
  //         navigate("/");
  //         setToastMessages(
  //           getFailureToastMessage({
  //             message: "Đăng nhập để truy cập",
  //           })
  //         );
  //       });
  //   } else {
  //     navigate("/");
  //     setUser({ role: 0 });
  //     setToastMessages(
  //       getFailureToastMessage({
  //         message: "Đăng nhập để truy cập",
  //       })
  //     );
  //   }
  // }, []);

  return (
    <Grid container className={IntroductionStyle["introduction"]}>
      <Grid item xs={10} justifyContent="flex-start">
        <div className={IntroductionStyle["layer1"]}>
          <img
            src={require("./components/photos/layer1.png")}
            alt=""
            className={IntroductionStyle["layer1-image"]}
          />
        </div>
        <div className={IntroductionStyle["layer2"]}>
          <div className={IntroductionStyle["layer2-wrapper"]}>
            <img
              src={require("./components/photos/layer2.png")}
              alt=""
              className={IntroductionStyle["layer2-image"]}
            />
          </div>
        </div>
        <div
          className={[
            IntroductionStyle["container"],
            IntroductionStyle["labels"],
          ].join(" ")}
        >
          <div>
            <h1>Trở thành chủ nhà</h1>
            <p>
              Explore yourself and people to provide the better looks about your
              hotel and your services
            </p>
            <button
              className={IntroductionStyle["register-button"]}
              onClick={handleRegister}
            >
              Bắt đầu ngay
            </button>
          </div>
        </div>
      </Grid>
    </Grid>
  );
}

export default Introduction;
