import amenityInforStyles from "./AmenityInformation.module.scss";
import AmenityInputField from "../AmenityInputField";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { RegisterContext } from "@/utils/contexts";
import { useContext } from "react";
import * as Icons from "@fortawesome/free-solid-svg-icons";
import { useClsx } from "@/utils/hooks";

const AmenityCard = ({ amenity, setAmenities, isChecked }) => {
    const handleOnClick = (e) => {
        setAmenities((prev) => {
            if (isChecked) {
                return prev.filter(({ id }) => id !== amenity.id);
            } else {
                return [...prev, amenity];
            }
        });
    };

    return (
        <div
            key={amenity.id}
            className={useClsx(
                amenityInforStyles["amenity-card"],
                isChecked ? amenityInforStyles["checked"] : ""
            )}
            onClick={handleOnClick}
        >
            <FontAwesomeIcon icon={Icons[amenity.icon]} />
            {amenity.name}
        </div>
    );
};

function AmenityInformation() {
    const {
        amenities,
        setAmenities,
        displayAmenities,
        setDisplayAmenities,
        displayAmenitiesType,
    } = useContext(RegisterContext);

    return (
        <div className={amenityInforStyles["basic-information"]}>
            <div className={amenityInforStyles["basic-infor-header"]}>
                <h3>
                    Hãy cho khách hàng biết về những tiện nghi của khách sạn
                </h3>
            </div>
            <div className={amenityInforStyles["basic-infor-body"]}>
                <div className={amenityInforStyles["card-holder"]}>
                    {displayAmenities.map((amenity) => (
                        <AmenityCard
                            amenity={amenity}
                            setAmenities={setAmenities}
                            isChecked={amenities.some(
                                ({ id }) => id === amenity.id
                            )}
                        />
                    ))}
                </div>
                <div className={amenityInforStyles["input-field"]}>
                    <h4>Không tìm thấy thứ bạn muốn ? Thêm vào ở bên dưới</h4>
                    <AmenityInputField
                        handleClick={setAmenities}
                        addNewAmenity={setDisplayAmenities}
                        amenityTypes={displayAmenitiesType}
                    />
                </div>
            </div>
        </div>
    );
}

export default AmenityInformation;
