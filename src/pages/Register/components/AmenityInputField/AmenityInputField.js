import { useState } from "react";
import amenityStyle from "./AmenityType.module.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlus, faPencil } from "@fortawesome/free-solid-svg-icons";
import { v4 as uuid } from "uuid";

function AmenityInputField({ handleClick, addNewAmenity, amenityTypes }) {
  console.log(amenityTypes);
  const [amenity, setAmenity] = useState({
    amenity_name: "",
    type: 1,
  });

  const handleAmenityAdded = (e) => {
    if (amenity.amenity_name.length === 0) {
      return;
    } else {
      const id = uuid();
      handleClick((prev) => [
        ...prev,
        {
          ...amenity,
          amenity_id: id,
          icon: "faPencil",
        },
      ]);
      addNewAmenity((prev) => [
        ...prev,
        {
          ...amenity,
          amenity_id: id,
          icon: "faPencil",
        },
      ]);
    }
  };

  return (
    <div className={amenityStyle["add-amenity"]}>
      <div className={amenityStyle["amenity-list"]}>
        {amenityTypes.map(({ amenityTypeId, amenityTypeName }, index) => (
          <div
            key={index}
            onClick={() => {
              setAmenity((prev) => ({
                ...prev,
                type: amenityTypeId,
              }));
            }}
          >
            {amenityTypeName}
          </div>
        ))}
      </div>
      <div className={amenityStyle["amenity-input"]}>
        <input
          value={amenity.name}
          onChange={(e) => {
            setAmenity((prev) => ({
              ...prev,
              name: e.target.value,
            }));
          }}
        />
        <button>
          <FontAwesomeIcon icon={faPlus} onClick={handleAmenityAdded} />
        </button>
      </div>
    </div>
  );
}

export default AmenityInputField;
