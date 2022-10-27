import RoomPickers from "../RoomPickers";
import RoomStyle from "./RoomInformation.module.scss";

function RoomInformation() {
  return (
    <div className={RoomStyle["container"]}>
      <h3>Thiết lập các giá trị cho phòng thuê của khách</h3>
      <RoomPickers />
    </div>
  );
}

export default RoomInformation;
