import { createContext, useState } from "react";
import RatingStyle from "./Rating.module.scss";
import Filter from "./components/Filter";
import RatingCard from "./components/RatingCard";

import { data } from "./RatingFakeData";

export const RatingContext = createContext();

function Rating() {
  const [filter, setFilter] = useState(0);

  return (
    <div className={RatingStyle["rating-wrapper"]}>
      <RatingContext.Provider value={[filter, setFilter]}>
        <Filter />
        <RatingCard data={data} />
      </RatingContext.Provider>
    </div>
  );
}

export default Rating;
