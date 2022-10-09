import BodyStyle from "../../HotelManage.module.scss";
function Body({ hotel }) {
  const hotelexample = {
    name: "Khách sạn Vin Pearl Nam Hội An",
    tabs: [{}, {}, {}],
  };
  return (
    <div className={BodyStyle["body"]}>
      <div className={BodyStyle["body-header"]}>
        <h2>Lịch đặt phòng hôm nay</h2>
        <a href="">
          Tất cả các đơn <span>{hotelexample.tabs.length}</span>
        </a>
      </div>
    </div>
  );
}
export default Body;
