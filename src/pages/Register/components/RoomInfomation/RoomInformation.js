import RoomPickers from "../RoomPickers";
import RoomStyle from "./RoomInformation.module.scss";

function RoomInformation() {
  return (
    <>
      <h3>Thiết lập các giá trị cho phòng thuê của khách</h3>
      <div className={RoomStyle["container"]}>
        <RoomPickers />
      </div>
    </>
  );
}

export default RoomInformation;
