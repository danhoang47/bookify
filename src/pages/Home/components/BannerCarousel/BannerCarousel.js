import { Carousel } from "react-bootstrap";
import { Link } from "react-router-dom";
import bannerCarouselStyles from "./BannerCarousel.module.scss";
import "bootstrap/dist/css/bootstrap.min.css";

function BannerCarousel({ trendingHotels }) {
  return (
    <Carousel
      style={{
        zIndex: "0",
      }}
    >
      {trendingHotels.map(({ backgroundImage, name }, index) => (
        <Carousel.Item key={index} interval={3000}>
          <Link to="/profile">
            <img
              className={bannerCarouselStyles["carousel-image"]}
              src={backgroundImage}
              alt={name}
            />
          </Link>
          <Carousel.Caption>
            <h5>{name}</h5>
          </Carousel.Caption>
        </Carousel.Item>
      ))}
    </Carousel>
  );
}

export default BannerCarousel;
