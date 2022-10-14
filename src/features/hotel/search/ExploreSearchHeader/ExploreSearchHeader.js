import exploreHeader from "./ExploreSearchHeader.module.scss";
import { memo, useContext } from "react";
import { SearchContext } from "@/utils/contexts";

function ExploreSearchHeader({ onTabChange }) {
    const { place, setPlace } = useContext(SearchContext);

    return (
        <label
            id={exploreHeader["explore-search"]}
            className={exploreHeader["advance-search__input-field"]}
            index="0"
            onFocus={onTabChange}
        >
            <h4 className={exploreHeader["heading"]}>Địa điểm</h4>
            <input
                className={exploreHeader["place-input"]}
                placeholder={"Bạn muốn đi đâu?"}
                value={place}
                onChange={(event) => {
                    setPlace(event.target.value);
                }}
            />
        </label>
    );
}

export default memo(ExploreSearchHeader);
