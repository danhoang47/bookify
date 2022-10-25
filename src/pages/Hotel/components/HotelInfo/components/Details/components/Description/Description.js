import DescriptionStyle from "./Description.module.scss";

function Description() {
  return (
    <div>
      <h3 className={DescriptionStyle["title"]}>
        Title for your hotel goes here
      </h3>
      <p className={DescriptionStyle["description"]}>
        Aliqua mollit adipisicing in amet magna. Ipsum veniam duis fugiat irure
        sit anim. Mollit ullamco adipisicing dolore ex non. Ipsum minim ipsum
        est qui exercitation deserunt reprehenderit. Eiusmod ullamco ullamco
        dolor esse velit commodo laborum pariatur in dolore ut Lorem. Enim sit
        proident nisi reprehenderit anim laboris. Duis consectetur reprehenderit
        reprehenderit elit sunt pariatur voluptate occaecat magna ea. Irure ad
        veniam commodo eu incididunt culpa magna. Sit minim et ut consectetur
        elit. Qui et mollit cupidatat eiusmod. Eu exercitation quis sunt quis
        voluptate magna laboris ad velit. In enim magna tempor sit. Laboris eu
        incididunt ad exercitation. Exercitation deserunt sint mollit voluptate
        nisi ex aliquip consectetur est ea tempor minim eu exercitation.
        Deserunt magna ex occaecat laborum Lorem occaecat magna cupidatat
        proident cillum.
      </p>
      <div className={DescriptionStyle["host-info"]}>
        <div className={DescriptionStyle["img-container"]}>
          <img
            src="https://cdn.vox-cdn.com/thumbor/W6YyHkPAoXd8VGz2OGMjqkWWM7E=/0x0:2370x1574/1400x1400/filters:focal(1185x787:1186x788)/cdn.vox-cdn.com/uploads/chorus_asset/file/20103707/Screen_Shot_2020_07_21_at_9.38.25_AM.png"
            alt=""
          />
        </div>
        <div className={DescriptionStyle["host"]}>
          <h4 className={DescriptionStyle["host-name"]}>Chủ nhà Le Duc</h4>
          <p className={DescriptionStyle["host-sub"]}>
            Đã tham gia vào tháng 4 năm 2014
          </p>
        </div>
      </div>
    </div>
  );
}

export default Description;
