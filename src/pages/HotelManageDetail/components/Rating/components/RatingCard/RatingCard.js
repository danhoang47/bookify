import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStar } from "@fortawesome/free-solid-svg-icons";
import { RatingContext } from "../../Rating";
import { useContext } from "react";
import RatingCartStyle from "./RatingCard.module.scss";

function Filter({ data, rating }) {
  const [filter, setFilter] = useContext(RatingContext);

  return (
    <div className={RatingCartStyle["card-wrapper"]}>
      {rating?.map((item, index) => {
        return (
          <div className={RatingCartStyle["card"]} key={index}>
            <div className={RatingCartStyle["card-avatar"]}>
              <div className={RatingCartStyle["avatar-wrapper"]}>
                <img src={item.avatar} alt="avatar" height={30} width={30} />
              </div>
            </div>
            <div className={RatingCartStyle["card-body"]}>
              <div className={RatingCartStyle["card-title"]}>
                <h4>{item.username}</h4> -
                <span>
                  {Math.floor(
                    (item.accuracy_point +
                      item.communication_point +
                      item.location_point +
                      item.value_point) /
                      4
                  )}
                  {"  "}
                  <FontAwesomeIcon icon={faStar} />
                </span>
              </div>
              <div className={RatingCartStyle["card-content"]}>
                <p>{item.content}</p>
              </div>
              <div className={RatingCartStyle["card-time"]}>
                <p>{item.createdAt}</p>
              </div>
            </div>
          </div>
        );
      })}
    </div>
  );
}

export default Filter;
