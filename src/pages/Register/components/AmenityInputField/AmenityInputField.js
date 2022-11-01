import { useState } from "react";
import amenityStyle from "./AmenityType.module.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlus, faChevronDown } from "@fortawesome/free-solid-svg-icons";
import { v4 as uuid } from "uuid";
import { useClsx } from "@/utils/hooks";

const amenityInitState = {
  id: ``,
  name: "",
  icon: "faPencil",
};

function AmenityInputField({
  hotelId,
  handleClick,
  addNewAmenity,
  amenityTypes,
}) {
  const [amenity, setAmenity] = useState({
    ...amenityInitState,
    hotelId: hotelId,
    type: amenityTypes[0]?.amenityTypeId,
  });
  const [isTypeListOpen, setTypeListOpen] = useState(false);

  const handleAmenityAdded = (e) => {
    if (amenity.name.length === 0) {
      return;
    } else {
      const newAmenity = {
        ...amenity,
        id: `new-${uuid()}`,
      };
      handleClick((prev) => [...prev, newAmenity]);
      addNewAmenity((prev) => [...prev, newAmenity]);
      setAmenity({
        ...amenityInitState,
        hotelId: hotelId,
        type: amenityTypes[0].amenityTypeId,
      });
    }
  };

  return (
    <div className={amenityStyle["add-amenity"]}>
      <div className={amenityStyle["amenity-input"]}>
        <div className={amenityStyle["amenity-type-select"]}>
          <p>
            {
              amenityTypes.find(
                ({ amenityTypeId }) => amenityTypeId === amenity.type
              )["amenityTypeName"]
            }
          </p>
          <button onClick={() => setTypeListOpen((prev) => !prev)}>
            <FontAwesomeIcon icon={faChevronDown} />
          </button>
          <div
            className={useClsx(
              amenityStyle["amenity-type-list"],
              isTypeListOpen ? amenityStyle["d-block"] : ""
            )}
          >
            {amenityTypes.map(({ amenityTypeId, amenityTypeName }, index) => (
              <div
                key={index}
                onClick={() => {
                  setAmenity((prev) => ({
                    ...prev,
                    type: amenityTypeId,
                  }));
                  setTypeListOpen((prev) => !prev);
                }}
                className={amenityStyle["type-item"]}
              >
                {amenityTypeName}
              </div>
            ))}
          </div>
        </div>
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
