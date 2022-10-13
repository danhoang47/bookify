import searchModalStyles from "./SearchModal.module.scss";
import AdvanceSearch from "../AdvanceSearch";
import SearchResult from "../SearchResult";
import TrendingCard from "../TrendingCard";
import { Box } from "@mui/material";

// testing purpose only
const trending = ["Bể bơi", "Bãi biển", "Thiên nhiên"];

function SearchModal({ searchTerms }) {
    return (
        <div tabIndex={-1} className={searchModalStyles["search-modal"]}>
            <div className={searchModalStyles["search-section"]}>
                <div className={searchModalStyles["search-trending"]}>
                    <h4 className={searchModalStyles["heading"]}>
                        Xu hướng tìm kiếm
                    </h4>
                    <Box
                        className={searchModalStyles["search-trending-list"]}
                        sx={{
                            display: "flex",
                            gap: '0.5em'
                        }}
                    >
                        {trending.map((trend, index) => (
                            <TrendingCard 
                                key={index}
                                title={trend} 
                                src={""} 
                            />
                        ))}
                    </Box>
                </div>
            </div>
            {/* <AdvanceSearch />    */}
        </div>
    );
}

export default SearchModal;
