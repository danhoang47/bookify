// Component Import
import GuestsSearchField from "../GuestsSearchField";
import DateSearchField from "../DateSearchField";
import ExploreSearchHeader from "../ExploreSearchHeader";
import DateSearchHeader from "../DateSearchHeader";
import GuestsSearchHeader from "../GuestsSearchHeader";

// Util Import
import { useState, useCallback, useMemo, lazy, Suspense } from "react";
import { SearchContext } from "@/utils/contexts";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";

// styles import
import advanceSearchStyles from "./AdvanceSearch.module.scss";
import { Box } from "@mui/material";

const ExploreSearchField = lazy(() => import('../ExploreSearchField'))
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

function AdvanceSearch({ handleChangeMode }) {
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

    const changeSearchMode = (event) => {
        event.stopPropagation();
        handleChangeMode(false);
    };

    return (
        <SearchContext.Provider value={searchContextValue}>
            <div id={advanceSearchStyles["advance-search"]}>
                <div className={advanceSearchStyles["advance-search-header"]}>
                    <div
                        className={advanceSearchStyles["search-field-nav"]}
                        onClick={changeSearchMode}
                    >
                        Tìm kiếm khách sạn
                    </div>
                    <div
                        className={[
                            advanceSearchStyles["search-field-nav"],
                            advanceSearchStyles["active"],
                        ].join(" ")}
                    >
                        Tìm kiếm nâng cao
                    </div>
                </div>
                <div
                    className={
                        advanceSearchStyles["advance-search-header-field"]
                    }
                >
                    <Box
                        sx={{
                            display: "flex",
                            borderRadius: "1.6em",
                            backgroundColor: "#ebebeb",
                            position: "relative",
                        }}
                    >
                        <ExploreSearchHeader
                            onTabChange={handleTabChange}
                            currentTab={currentTab}
                        />
                        <DateSearchHeader
                            onTabChange={handleTabChange}
                            currentTab={currentTab}
                        />
                        <GuestsSearchHeader
                            onTabChange={handleTabChange}
                            currentTab={currentTab}
                        />
                        <div
                            className={
                                advanceSearchStyles["advance-search-button"]
                            }
                        >
                            <button
                                className={advanceSearchStyles["search-button"]}
                            >
                                <FontAwesomeIcon
                                    icon={faMagnifyingGlass}
                                    style={{
                                        marginRight: "0.2em",
                                    }}
                                />
                                Tìm kiếm
                            </button>
                        </div>
                    </Box>
                </div>
                <Suspense>
                    <SearchField index={currentTab} />
                </Suspense>
            </div>
        </SearchContext.Provider>
    );
}

export default AdvanceSearch;
