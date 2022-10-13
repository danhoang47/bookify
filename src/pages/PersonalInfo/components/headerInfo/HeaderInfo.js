import HeaderInfoStyle from "./HeaderInfo.module.scss";
import { useNavigate } from "react-router-dom";

function HeaderInfo() {
  const navigate = useNavigate();

  const onClickHandler = (event) => {
    event.preventDefault();
    navigate(-1);
  };

  return (
    <>
      <ul className={HeaderInfoStyle["breadcrumb"]}>
        <li>
<<<<<<< HEAD
          <button onClick={onClickHandler}>
            Tài Khoản
          </button>
=======
          <p onClick={onClickHandler}>Tài Khoản</p>
>>>>>>> a25a1b69d065e5ec31b6e2f7825f4c405f11e042
        </li>
        <li>Thông tin cá nhân</li>
      </ul>

      <h2 className={HeaderInfoStyle["title"]}>Thông tin cá nhân</h2>
      <p className={HeaderInfoStyle["sub-title"]}>
        Esse tempor magna et nulla sunt ea excepteur tempor incididunt nisi
        labore id.
      </p>
    </>
  );
}

export default HeaderInfo;
