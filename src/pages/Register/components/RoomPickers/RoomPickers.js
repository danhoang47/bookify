import { RegisterContext } from "@/utils/contexts";
import { useContext } from "react";
import NumberPicker from "@/components/NumberPicker";

const getTitle = (key) => {
    switch (key) {
        case "guests":
            return "Số khách";
        case "bedrooms":
            return "Số phòng ngủ";
        case "beds":
            return "Số giường";
        case "bathrooms":
            return "Số phòng tắm";
        case "price":
            return "Giá tiền mỗi đêm";
        case "isPrivateBathRoom":
            return "Phòng tắm là chung hay riêng";
        default:
            throw new Error("Invalid key");
    }
};

function RoomPickers() {
    const { roomInfor, setRoomInfor } = useContext(RegisterContext);
    const limitPrice = 10000;

    return (
        <div>
            {Object.keys(roomInfor).reduce((prev, key) => {
                if (key === "price" || key === "isPrivateBathRoom") {
                    return prev;
                } else {
                    return [
                        ...prev,
                        <NumberPicker
                            title={getTitle(key)}
                            description={""}
                            limit={100}
                            value={roomInfor[key]}
                            setValue={(value) => {
                                setRoomInfor((prevState) => ({
                                    ...prevState,
                                    [key]: value || 0,
                                }));
                            }}
                        />,
                    ];
                }
            }, [])}
            <div>
                <button
                    onClick={(e) =>
                        setRoomInfor((prev) => ({
                            ...prev,
                            isPrivateBathRoom: false,
                        }))
                    }
                >
                    Phòng tắm chung
                </button>
                <button
                    onClick={(e) =>
                        setRoomInfor((prev) => ({
                            ...prev,
                            isPrivateBathRoom: true,
                        }))
                    }
                >
                    Phòng tắm riêng
                </button>
            </div>
            <div>
                <p>Giá tiền mỗi đêm</p>
                <div>
                    <NumberPicker 
                        description={""}
                        limit={limitPrice}
                        value={roomInfor['price']}
                        setValue={(value) => {
                            setRoomInfor((prevState) => ({
                                ...prevState,
                                'price': value || 0,
                            }));
                        }}
                    />
                </div>
            </div>
        </div>
    );
}

export default RoomPickers;
