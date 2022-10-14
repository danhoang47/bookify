// Component Import
import GuestsSearchField from "../GuestsSearchField";
import ExploreSearchField from "../ExploreSearchField";
import DateSearchField from "../DateSearchField";
import ExploreSearchHeader from "../ExploreSearchHeader";
import DateSearchHeader from "../DateSearchHeader";
import GuestsSearchHeader from "../GuestsSearchHeader";

// Util Import
import advanceSearchStyles from "./AdvanceSearch.module.scss";
import { useState, useCallback, useMemo } from "react";
import { SearchContext } from "@/utils/contexts";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";

const SearchField = ({ index, handler = null }) => {
    const tabs = [ExploreSearchField, DateSearchField, GuestsSearchField];
    const Component = tabs[index];
    return (
        <>
            <Component handler={handler} />
        </>
    );
};

const guestsInitial = {
    adult: 0,
    child: 0,
    infant: 0,
    pet: 0,
};

function AdvanceSearch() {
    const [currentTab, setCurrentTab] = useState(0);
    const [place, setPlace] = useState("Hà Nội");
    const [selectedDays, setSelectedDays] = useState({});
    const [guests, setGuests] = useState(guestsInitial);

    const searchContextValue = useMemo(() => {
        return {
            place,
            setPlace,
            selectedDays,
            setSelectedDays,
            guests,
            setGuests,
        };
    }, [place, guests, selectedDays]);

    const handleTabChange = useCallback((event) => {
        event.stopPropagation();
        const tabIndex = event.currentTarget.getAttribute("index");
        setCurrentTab(parseInt(tabIndex));
    }, []);

    return (
        <SearchContext.Provider value={searchContextValue}>
            <div id={advanceSearchStyles["advance-search"]}>
                <div className={advanceSearchStyles['advance-search-header']}
                >
                    <ExploreSearchHeader onTabChange={handleTabChange} />
                    <DateSearchHeader onTabChange={handleTabChange} />
                    <GuestsSearchHeader onTabChange={handleTabChange} />
                    <div
                        className={advanceSearchStyles['advance-search-button']} 
                    >   
                        <button 
                            className={advanceSearchStyles['search-button']}
                        >
                            <FontAwesomeIcon icon={faMagnifyingGlass} />
                        </button>
                    </div>
                </div>
                <SearchField index={currentTab} />
            </div>
        </SearchContext.Provider>
    );
}

export default AdvanceSearch;
