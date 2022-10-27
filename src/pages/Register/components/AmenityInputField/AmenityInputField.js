import { useState } from "react";
import amenityStyle from "./AmenityType.module.scss";
import amenityTypes from "./amenityTypes";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlus, faPencil } from "@fortawesome/free-solid-svg-icons";
import { v4 as uuid } from "uuid";

function AmenityInputField({ handleClick, addNewAmenity }) {
  const [amenity, setAmenity] = useState({
    title: "",
    type: 1,
  });

  const handleAmenityAdded = (e) => {
    if (amenity.title.length === 0) {
      return;
    } else {
      const id = uuid();
      handleClick((prev) => [
        ...prev,
        {
          ...amenity,
          id: id,
          icon: faPencil,
        },
      ]);
      addNewAmenity((prev) => [
        ...prev,
        {
          ...amenity,
          id: id,
          icon: faPencil,
        },
      ]);
    }
  };

  return (
    <div className={amenityStyle["add-amenity"]}>
      <div className={amenityStyle["amenity-list"]}>
        <select name="" id="">
          {amenityTypes.map(({ id, type }, index) => (
            <option
              key={index}
              onClick={() => {
                setAmenity((prev) => ({
                  ...prev,
                  type: id,
                }));
              }}
            >
              {type}
            </option>
          ))}
        </select>
      </div>
      <div className={amenityStyle["amenity-input"]}>
        <input
          value={amenity.title}
          onChange={(e) => {
            setAmenity((prev) => ({
              ...prev,
              title: e.target.value,
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
