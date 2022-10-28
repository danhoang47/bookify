import { Box } from "@mui/material";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faChevronDown } from "@fortawesome/free-solid-svg-icons";
import { memo, useState, useRef, useEffect, useCallback } from "react";
import { basicHotelInforValidation } from "@/utils/validation";
import { types } from "@/utils/validation/basicHotelInforValidation";
import searchHotelTypes from "@/services/hotel/searchHotelTypes";
import searchProvinces from "@/services/hotel/searchProvinces";
import { getHotelRegisterErrorMessage } from "@/utils/validation";
import { useClsx, useDebounce } from "@/utils/hooks";
import "./SelectField.scss";

const getDebouncedFunction = (type) => {
  switch (type) {
    case types.PROVINCE:
      return searchProvinces;
    case types.DISTRICT:
      return () => {};
    case types.HOTEL_TYPE:
      return searchHotelTypes;
    default:
      return () => {};
  }
};

function SelectField({ id, label, value, setValue, setInformationValid }) {
  const [isFocus, setFocused] = useState(false);
  const inputRef = useRef();
  const [selectionList, setSelectionList] = useState([]);
  const [isSelectionListOpen, setSelectionListOpen] = useState(false);
  // eslint-disable-next-line react-hooks/exhaustive-deps
  const debouncedSearchFunction = useCallback(
    useDebounce((searchTerm) => {
      const debouncedFunction = getDebouncedFunction(id);
      debouncedFunction(searchTerm)?.then((data) => setSelectionList(data));
    }, 300),
    []
  );
  const isValid = basicHotelInforValidation(id, value, selectionList);

  const handleFocus = (e) => {
    e.stopPropagation();
    setFocused(true);
  };

  useEffect(() => {
    const inputElement = inputRef.current;
    // if element hasn't received focus
    if (!isFocus) {
      inputElement.addEventListener("focus", handleFocus);
    }

    return () => {
      inputElement.removeEventListener("focus", handleFocus);
    };
  }, [inputRef, isFocus]);

  useEffect(() => {
    debouncedSearchFunction(value);
    setInformationValid((prev) => ({
      ...prev,
      [id]: isValid,
    }));
    //eslint-disable-next-line
  }, [value]);

  return (
    <div className={useClsx("input-field select-field")}>
      <input
        id={id}
        type="text"
        value={value ?? ""}
        onChange={(e) => {
          setValue(e.target.value, id);
        }}
        ref={inputRef}
      />
      <label htmlFor={id} className={useClsx("input-label")}>
        {!isValid && isFocus ? getHotelRegisterErrorMessage(id) : label}
      </label>
      <button
        className="drop-down-button"
        onClick={() => setSelectionListOpen(true)}
      >
        <FontAwesomeIcon icon={faChevronDown} />
      </button>
      <div
        className={useClsx(
          "selection-list",
          isSelectionListOpen ? "d-block" : ""
        )}
      >
        {selectionList?.map((value) => (
          <div
            key={value.code}
            onClick={() => {
              setSelectionListOpen(false);
              setValue(value.code, id);
            }}
            className={"selection-item"}
          >
            {value.name}
          </div>
        ))}
      </div>
    </div>
  );
}

export default memo(SelectField);

/**
 * "name": "Thành phố Hà Nội",
    "code": 1,
    "matches": {
      "hà": [
        10,
        12
      ],
      "nội": [
        13,
        16
      ]
    }
*/
