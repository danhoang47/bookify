import HeaderInfoStyle from "./HeaderInfo.module.scss";
import { useNavigate } from "react-router-dom";

function HeaderInfo() {
  const navigate = useNavigate();

  const onClickHandler = (event) => {
    event.stopPropagation();
    navigate("/profile");
  };

  return (
    <>
      <ul className={HeaderInfoStyle["breadcrumb"]}>
        <li>
          <button onClick={onClickHandler}>
            Tài Khoản
          </button>
        </li>
        <li>Đăng nhập và bảo mật</li>
      </ul>
      <h2 className={HeaderInfoStyle["title"]}>Đăng nhập và bảo mật</h2>
      <p className={HeaderInfoStyle["sub-title"]}>
        Esse tempor magna et nulla sunt ea excepteur tempor incididunt nisi
        labore id.
      </p>
    </>
  );
}

export default HeaderInfo;
