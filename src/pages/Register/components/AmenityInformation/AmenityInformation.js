import amenityInforStyles from "./AmenityInformation.module.scss";
import AmenityInputField from "../AmenityInputField";
import defaultAmenities from "./defaultAmenities";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { RegisterContext } from "@/utils/contexts";
import { useContext, useEffect, useState } from "react";
import * as icons from "@fortawesome/free-solid-svg-icons";
import {
  faDumbbell,
  faSwimmingPool,
  faWifi,
  faGrill,
  faCampground,
  faFireBurner,
  faHotTub,
  faChair,
  faAirFreshener,
  faTable,
  faCouch,
  faPooBolt,
} from "@fortawesome/free-solid-svg-icons";

const AmenityCard = ({ amenity, setAmenities }) => {
  const handleOnClick = (e) => {
    setAmenities((prev) => {
      const isIncluded = prev.some(
        ({ amenity_id }) => amenity_id === amenity.amenity_id
      );
      if (isIncluded) {
        return prev.filter(
          ({ amenity_id }) => amenity_id !== amenity.amenity_id
        );
      } else {
        return [...prev, amenity];
      }
    });
  };

  return (
    <div
      key={amenity.amenity_id}
      className={amenityInforStyles["amenity-card"]}
      onClick={handleOnClick}
    >
      <FontAwesomeIcon icon={icons[amenity.icon]} />
      {amenity.amenity_name}
    </div>
  );
};

function AmenityInformation() {
  const { amenities, setAmenities } = useContext(RegisterContext);
  const [displayAmenities, setDisplayAmenities] = useState([]);
  const [displayAmenitiesType, setDisplayAmenitiesType] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/testUpload/rest/hotel/signhotel", {
      method: "GET",
    })
      .then((res) => res.json())
      .then((result) => {
        setAmenities(result.amenities);
        setDisplayAmenities(result.amenities);
        setDisplayAmenitiesType(result.amenitiesType);
      });
  }, []);

  // useEffect(() => {
  //   setAmenities(defaultAmenities);
  // }, []);

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
            amenityTypes2={displayAmenitiesType}
          />
        </div>
      </div>
    </div>
  );
}

export default AmenityInformation;
