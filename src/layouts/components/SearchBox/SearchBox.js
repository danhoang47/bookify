import SearchInputField from "../SearchInputField";
import { useState, memo } from "react";
import { usePopup } from "@/utils/hooks";
import searchBoxStyles from "./SearchBox.module.scss";
import { SearchModal } from "@/features/hotel/search";
import { Box } from "@mui/material";

function SearchBox() {
    const [searchTerms, setSearchTerms] = useState("");
    const [isOpen, handleClick, containerRef] = usePopup();

    const handleOpenSearchBar = (event) => {
        handleClick(event);
    };

    console.log(isOpen);
    return (
        <>
            <div className={searchBoxStyles["search-box"]} ref={containerRef}>
                <div className={searchBoxStyles["search-bar"]}>
                    <SearchInputField
                        style={searchBoxStyles["search-input-field"]}
                        value={searchTerms}
                        onValueChange={setSearchTerms}
                        placeholder="Tìm kiếm"
                        width="f-width"
                        id="search-input-field"
                        isOpen={isOpen}
                        handleOpenSearchBar={handleOpenSearchBar}
                    />
                </div>
                { <SearchModal searchTerms={searchTerms} />}
            </div>
            {/* overlay */}
            {isOpen && <Box
                sx={{
                    position: "absolute",
                    left: 0,
                    top: "84.11px",
                    right: 0,
                    bottom: 0,
                    backgroundColor: "#000",
                    zIndex: "1",
                    opacity: "0.5",
                }}
            />}
        </>
    );
}

export default memo(SearchBox);
