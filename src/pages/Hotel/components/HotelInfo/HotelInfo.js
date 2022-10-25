import HotelInfoStyle from "./HotelInfo.module.scss";
import InfoHeader from "./components/InfoHeader";
import Details from "./components/Details";

function HotelInfo() {
  return (
    <div>
      <div className={HotelInfoStyle["header"]}>
        <InfoHeader />
      </div>
      <div className={HotelInfoStyle["details"]}>
        <Details />
      </div>
    </div>
  );
}

export default HotelInfo;
