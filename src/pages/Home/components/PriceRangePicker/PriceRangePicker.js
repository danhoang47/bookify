import Bar from "../Bar";
import priceRangePickerStyles from "./PriceRangePicker.module.scss";
import { useState, useId, useEffect } from "react";
import priceFrequencies from "./priceFrequencies";

function PriceRangePicker({ prices, setPrice }) {
  const [priceRange, setPriceRange] = useState({});
  const minInputPriceId = useId();
  const maxInputPriceId = useId();

  useEffect(() => {
    setPrice(priceRange);
  }, [priceRange]);

  return (
    <div id={priceRangePickerStyles["price-range-picker"]}>
      <h4 className={priceRangePickerStyles["heading"]}>Khoảng giá phòng</h4>
      <p className={priceRangePickerStyles["sub-heading"]}>
        Giá trung bình mỗi đêm là{" "}
        <span className={priceRangePickerStyles["average-price"]}>$200</span>
      </p>
      <div className={priceRangePickerStyles["price-inputs"]}>
        <div
          className={[
            priceRangePickerStyles["price-range-input"],
            priceRangePickerStyles["lower-limit-input"],
          ].join(" ")}
        >
          <input
            id={minInputPriceId}
            value={priceRange?.min}
            onChange={(event) => {
              setPriceRange({
                ...priceRange,
                min: event.target.value,
              });
            }}
          />
          <label
            htmlFor={minInputPriceId}
            className={priceRangePickerStyles["label-input"]}
          >
            Giá thấp nhất
          </label>
          <div className={priceRangePickerStyles["currency"]}>USD</div>
        </div>
        <span className={priceRangePickerStyles["line-separetor"]}></span>
        <div
          className={[
            priceRangePickerStyles["price-range-input"],
            priceRangePickerStyles["lower-limit-input"],
          ].join(" ")}
        >
          <input
            id={maxInputPriceId}
            value={priceRange?.max}
            onChange={(event) => {
              setPriceRange({
                ...priceRange,
                max: event.target.value,
              });
            }}
          />
          <label
            htmlFor={maxInputPriceId}
            className={priceRangePickerStyles["label-input"]}
          >
            Giá thấp nhất
          </label>
        </div>
      </div>
    </div>
  );
}

export default PriceRangePicker;
