import styles from "./RoomAndBedRoomPicker.module.scss";
import NumberPicker from "../NumberPicker";

function RoomAndBedRoomPicker({ pickers, roomAndBedRoom, onSelect }) {
    return (
        <div id={styles["r-n-b-r-picker-section"]}>
            <h4 className={styles["picker-heading"]}>
                Phòng và Phòng ngủ{" "}
            </h4>
            <div className={styles["r-n-b-r-picker"]}>
                {pickers.map(({ title, length }) => (
                    <NumberPicker
                        key={title}
                        title={title}
                        setValue={onSelect}
                        length={length}
                        currentValue={roomAndBedRoom[title]}
                    />
                ))}
            </div>
        </div>
    );
}

export default RoomAndBedRoomPicker;
