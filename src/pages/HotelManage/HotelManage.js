import HotelManageStyle from "./HotelManage.module.scss";
import Header from "./components/header";
import Body from "./components/Body";

function HotelManage() {
  return (
    <div className={HotelManageStyle["container"]}>
      <Header />
      <Body />
    </div>
  );
}

export default HotelManage;
