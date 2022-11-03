import DescriptionStyle from "./Description.module.scss";

function Description({ description, hotelOwner }) {
  return (
    <div>
      <h3 className={DescriptionStyle["title"]}>Giới thiệu về khách sạn</h3>
      <p className={DescriptionStyle["description"]}>{description}</p>
      <div className={DescriptionStyle["host-info"]}>
        <div className={DescriptionStyle["img-container"]}>
          <img src={hotelOwner?.avatar} alt="" />
        </div>
        <div className={DescriptionStyle["host"]}>
          <h4 className={DescriptionStyle["host-name"]}>
            {hotelOwner?.subname + " " + hotelOwner?.name}
          </h4>
          <p className={DescriptionStyle["host-sub"]}>
            Đã tham gia vào tháng 4 năm 2014
          </p>
        </div>
      </div>
    </div>
  );
}

export default Description;
