import amenityInforStyles from "./AmenityInformation.module.scss";
import AmenityInputField from "../AmenityInputField";
import defaultAmenities from "./defaultAmenities";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { RegisterContext } from "@/utils/contexts";
import { useContext, useEffect, useState } from "react";

const AmenityCard = ({ amenity, setAmenities }) => {
  const handleOnClick = (e) => {
    setAmenities((prev) => {
      const isIncluded = prev.some(({ id }) => id === amenity.id);
      if (isIncluded) {
        return prev.filter(({ id }) => id !== amenity.id);
      } else {
        return [...prev, amenity];
      }
    });
  };

  return (
    <div
      key={amenity.id}
      className={amenityInforStyles["amenity-card"]}
      onClick={handleOnClick}
    >
      <FontAwesomeIcon icon={amenity.icon} />
      <p>{amenity.title}</p>
    </div>
  );
};

function AmenityInformation() {
  const { amenities, setAmenities } = useContext(RegisterContext);
  const [displayAmenities, setDisplayAmenities] = useState(defaultAmenities);

  useEffect(() => {
    setAmenities(defaultAmenities);
  }, []);

  return (
    <div className={amenityInforStyles["basic-information"]}>
      <div className={amenityInforStyles["basic-infor-header"]}>
        <h3>Hãy cho khách hàng biết về những tiện nghi của khách sạn</h3>
      </div>
      <div className={amenityInforStyles["basic-infor-body"]}>
        <div className={amenityInforStyles["card-holder"]}>
          {displayAmenities.map((amenity) => (
            <AmenityCard amenity={amenity} setAmenities={setAmenities} />
          ))}
        </div>
        <div className={amenityInforStyles["input-field"]}>
          <h4>Không tìm thấy thứ bạn muốn ? Thêm vào ở bên dưới</h4>
          <AmenityInputField
            handleClick={setAmenities}
            addNewAmenity={setDisplayAmenities}
          />
        </div>
      </div>
    </div>
  );
}

export default AmenityInformation;
