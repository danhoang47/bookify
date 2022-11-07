import DescriptionStyle from "./Description.module.scss";

function Description({ description, hotelOwner }) {
  return (
    <div>
      <h3 className={DescriptionStyle["title"]}>Giới thiệu về khách sạn</h3>
      <p className={DescriptionStyle["description"]}>{description}</p>
      <div className={DescriptionStyle["host-info"]}>
        <div className={DescriptionStyle["img-container"]}>
          <img
            src={
              hotelOwner?.avatar ||
              "https://images.assetsdelivery.com/compings_v2/tuktukdesign/tuktukdesign1606/tuktukdesign160600119.jpg"
            }
            alt=""
          />
        </div>
        <div className={DescriptionStyle["host"]}>
          <h4 className={DescriptionStyle["host-name"]}>
            {hotelOwner?.subname || hotelOwner?.name
              ? hotelOwner?.subname + " " + hotelOwner?.name
              : hotelOwner?.username}
          </h4>
          <p className={DescriptionStyle["host-sub"]}>
            Đã tham gia vào tháng {new Date(hotelOwner?.signAt).getDate()} năm{" "}
            {new Date(hotelOwner?.signAt).getFullYear()}
          </p>
        </div>
      </div>
    </div>
  );
}

export default Description;
