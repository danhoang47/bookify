import { faStar } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import About from "./About";
import Amenities from "./Amenities";
import HotelInfoStyle from "./HotelInfo.module.scss";
import Location from "./Location";
import Photos from "./Photos";

function HotelInfo() {
  return (
    <div className={HotelInfoStyle["hotelInfo-wrapper"]}>
      <div className={HotelInfoStyle["hotel-header"]}>
        <h2 className={HotelInfoStyle["hotel-name"]}>
          Khách sạn Vinpearl Nam Hội An
        </h2>
        <h4 className={HotelInfoStyle["rating-stars"]}>
          5 <FontAwesomeIcon icon={faStar} />
        </h4>
      </div>
      <div className={HotelInfoStyle["photo-section"]}>
        <Photos />
      </div>
      <div className={HotelInfoStyle["about-section"]}>
        <About />
      </div>
      <div className={HotelInfoStyle["amenities-section"]}>
        <Amenities />
      </div>
      <div className={HotelInfoStyle["location-section"]}>
        <Location />
      </div>
    </div>
  );
}

export default HotelInfo;
