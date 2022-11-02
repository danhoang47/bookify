import { Carousel } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "./HotelCard.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStar } from "@fortawesome/free-solid-svg-icons";
import { faHeart } from "@fortawesome/free-regular-svg-icons";
import { Link } from "react-router-dom";

function HotelCard({
  hotelId,
  hotelName,
  country,
  city,
  district,
  address,
  backgroundImg,
  images,
  averagePirce,
  rating,
  isBookmarked,
}) {
  const backgroundImg2 = backgroundImg.split("/");
  const allImages = [backgroundImg2[backgroundImg2.length - 1]];
  images.forEach((image) => {
    let imgName = image.src.split("/");
    allImages.push(imgName[imgName.length - 1]);
  });

  return (
    <Link to={`hotel/${hotelId}`}>
      <div className={"hotel-card"}>
        <div className={"carousel"}>
          <Carousel controls={true} interval={null}>
            {allImages.map((src, index) => (
              <Carousel.Item key={index}>
                <img
                  className={"carousel-image"}
                  src={"http://localhost:8080/bookify/images/hotels/" + src}
                  alt={hotelName}
                />
              </Carousel.Item>
            ))}
          </Carousel>
        </div>
        <div className={"hotel-info"}>
          <div className={"basic-info"}>
            <div className={"name-and-point"}>
              <h3 className={"hotel-name"}>{hotelName}</h3>
              <div className={"average-point"}>
                <FontAwesomeIcon icon={faStar} />
                <span>{rating}</span>
              </div>
            </div>
            <p
              className={"hotel-address"}
            >{`${country}, ${city}, ${district}, ${address}`}</p>
            <p className={"hotel-price-per-night"}>{`$${averagePirce}`}</p>
          </div>
        </div>
        <div className={"bookmark-icon"}>
          <FontAwesomeIcon icon={faHeart} style={{ zIndex: 0 }} />
        </div>
      </div>
    </Link>
  );
}

export default HotelCard;
