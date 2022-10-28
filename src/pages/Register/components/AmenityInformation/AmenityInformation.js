import amenityInforStyles from "./AmenityInformation.module.scss";
import AmenityInputField from "../AmenityInputField";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { RegisterContext } from "@/utils/contexts";
import { useContext, useEffect, useState } from "react";
import * as Icons from '@fortawesome/free-solid-svg-icons';

const AmenityCard = ({ amenity, setAmenities }) => {
  const handleOnClick = (e) => {
    setAmenities((prev) => {
      const isIncluded = prev.some(
        ({ id }) => id === amenity.id
      );
      if (isIncluded) {
        return prev.filter(
          ({ id }) => id !== amenity.id
        );
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
      <FontAwesomeIcon icon={Icons[amenity.icon]} />
      {amenity.name}
    </div>
  );
};

function AmenityInformation() {
  const { amenities, setAmenities } = useContext(RegisterContext);
  const [displayAmenities, setDisplayAmenities] = useState([]);
  const [displayAmenitiesType, setDisplayAmenitiesType] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/bookify/api/amenity")
      .then((res) => res.json())
      .then((amenities) => {
        setAmenities(amenities);
        setDisplayAmenities(amenities);
      });
  //eslint-disable-next-line
  }, []);

  useEffect(() => {
    fetch("http://localhost:8080/bookify/api/amenity/type")
      .then((res) => res.json())
      .then((result) => {
        setDisplayAmenitiesType(result);
      });
  }, [])


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
            amenityTypes={displayAmenitiesType}
          />
        </div>
      </div>
    </div>
  );
}

export default AmenityInformation;
