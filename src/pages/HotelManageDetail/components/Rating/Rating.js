import { createContext, Suspense, useEffect, useState } from "react";
import RatingStyle from "./Rating.module.scss";
import Filter from "./components/Filter";

import { data } from "./RatingFakeData";
import { lazy } from "react";
import { useOutletContext } from "react-router-dom";

const RatingCard = lazy(() => import("./components/RatingCard"));

export const RatingContext = createContext();

function Rating() {
  const [filter, setFilter] = useState(0);
  const [hotel, setHotel] = useOutletContext();
  const [comments, setComments] = useState([]);
  console.log(hotel);
  useEffect(() => {
    if (filter === 0) {
      fetch(
        "http://localhost:8080/bookify/api/hotel/manage/rating?hotelid=" +
          hotel.hotelId
      )
        .then((res) => res.json())
        .then((data) => {
          setComments(data);
        });
    } else {
      fetch(
        `http://localhost:8080/bookify/api/hotel/manage/rating/point?hotelid=${hotel.hotelId}&point=${filter}`
      )
        .then((res) => res.json())
        .then((data) => {
          setComments(data);
        });
    }
  }, [filter]);

  return (
    <div className={RatingStyle["rating-wrapper"]}>
      <RatingContext.Provider value={[filter, setFilter]}>
        <Suspense fallback={<div>Loading....</div>}>
          <Filter />
          <RatingCard data={data} rating={comments} />
        </Suspense>
      </RatingContext.Provider>
    </div>
  );
}

export default Rating;
