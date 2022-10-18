import { Carousel } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "./HotelCard.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStar } from "@fortawesome/free-solid-svg-icons";
import { faHeart } from "@fortawesome/free-regular-svg-icons";

function HotelCard({
  id,
  name,
  address,
  backgroundImage,
  images,
  price,
  averagePoint,
  isBookmarked,
}) {
  const allImages = [backgroundImage, ...images];

  return (
    <div className={"hotel-card"}>
      <div className={"carousel"}>
        <Carousel controls={true} interval={null}>
          {allImages.map((src, index) => (
            <Carousel.Item key={index}>
              <img className={"carousel-image"} src={src} alt={name} />
            </Carousel.Item>
          ))}
        </Carousel>
      </div>
      <div className={"hotel-info"}>
        <div className={"basic-info"}>
          <div className={"name-and-point"}>
            <h3 className={"hotel-name"}>{name}</h3>
            <div className={"average-point"}>
              <FontAwesomeIcon icon={faStar} />
              <span>{averagePoint}</span>
            </div>
          </div>
          <p className={"hotel-address"}>{address}</p>
          <p className={"hotel-price-per-night"}>{`$${price}`}</p>
        </div>
      </div>
      <div className={"bookmark-icon"}>
        <FontAwesomeIcon icon={faHeart} style={{ zIndex: 0 }} />
      </div>
    </div>
  );
}

export default HotelCard;
