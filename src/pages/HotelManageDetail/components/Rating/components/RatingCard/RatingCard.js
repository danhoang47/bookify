import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStar } from "@fortawesome/free-solid-svg-icons";
import { RatingContext } from "../../Rating";
import { useContext } from "react";
import RatingCartStyle from "./RatingCard.module.scss";

function Filter({ data }) {
  const [filter, setFilter] = useContext(RatingContext);

  return (
    <div className={RatingCartStyle["card-wrapper"]}>
      {data
        .filter((filterData) =>
          filter === 0 ? filterData : filterData.ratingPointOverall === filter
        )
        .map((item, index) => {
          return (
            <div className={RatingCartStyle["card"]} key={index}>
              <div className={RatingCartStyle["card-avatar"]}>
                <div className={RatingCartStyle["avatar-wrapper"]}>
                  <img
                    src={item.user.avatar}
                    alt="avatar"
                    height={30}
                    width={30}
                  />
                </div>
              </div>
              <div className={RatingCartStyle["card-body"]}>
                <div className={RatingCartStyle["card-title"]}>
                  <h4>{item.user.name}</h4> -
                  <span>
                    {item.ratingPointOverall} <FontAwesomeIcon icon={faStar} />
                  </span>
                </div>
                <div className={RatingCartStyle["card-content"]}>
                  <p>{item.review.content}</p>
                </div>
                <div className={RatingCartStyle["card-time"]}>
                  <p>{item.review.createdAt}</p>
                </div>
              </div>
            </div>
          );
        })}
    </div>
  );
}

export default Filter;
