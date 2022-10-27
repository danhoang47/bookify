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
    <div key={amenity.id} onClick={handleOnClick}>
      <FontAwesomeIcon icon={amenity.icon} />
      {amenity.title}
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
    <div id={amenityInforStyles["basic-information"]}>
      <div className={amenityInforStyles["basic-infor-header"]}>
        Hãy cho khách hàng biết về những tiện nghi của khách sạn
      </div>
      <div className={amenityInforStyles["basic-infor-body"]}>
        {displayAmenities.map((amenity, index) => (
          <AmenityCard
            amenity={amenity}
            setAmenities={setAmenities}
            key={index}
          />
        ))}
        <div>
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
