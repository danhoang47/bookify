import { useState } from "react";
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
        <div>
            <div>
                {amenityTypes.map(({ id, type }, index) => (
                    <div
                        key={index}
                        onClick={() => {
                            setAmenity((prev) => ({
                                ...prev,
                                type: id,
                            }));
                        }}
                    >
                        {type}
                    </div>
                ))}
            </div>
            <div>
                <input
                    value={amenity.title}
                    onChange={(e) => {
                        setAmenity((prev) => ({
                            ...prev,
                            title: e.target.value,
                        }));
                    }}
                />
            </div>
            <button>
                <FontAwesomeIcon icon={faPlus} onClick={handleAmenityAdded} />
            </button>
        </div>
    );
}

export default AmenityInputField;