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
          <a href="" onClick={onClickHandler}>
            Tài Khoản
          </a>
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
