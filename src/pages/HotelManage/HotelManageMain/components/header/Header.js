import HeaderStyle from "../../HotelManage.module.scss";
function Header({ hotel }) {
  const hotelexample = {
    name: "Khách sạn Vin Pearl Nam Hội An",
  };
  return (
    <div className={HeaderStyle["header"]}>
      <div className={HeaderStyle["header-text"]}>
        <h2>{hotelexample.name}</h2>
        <p>
          Xem xét hoạt động và đánh giá mức độ yêu thích của khách hàng dành cho
          khách sạn.
        </p>
      </div>
    </div>
  );
}
export default Header;
