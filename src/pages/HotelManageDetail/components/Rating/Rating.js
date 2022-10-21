import { createContext, Suspense, useState } from "react";
import RatingStyle from "./Rating.module.scss";
import Filter from "./components/Filter";

import { data } from "./RatingFakeData";
import { lazy } from "react";

const RatingCard = lazy(() => import("./components/RatingCard"));

export const RatingContext = createContext();

function Rating() {
  const [filter, setFilter] = useState(0);

  return (
    <div className={RatingStyle["rating-wrapper"]}>
      <RatingContext.Provider value={[filter, setFilter]}>
        <Suspense fallback={<div>Loading....</div>}>
          <Filter />
          <RatingCard data={data} />
        </Suspense>
      </RatingContext.Provider>
    </div>
  );
}

export default Rating;
