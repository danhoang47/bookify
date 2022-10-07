import IntroductionStyle from "./Introduction.module.scss";

function Introduction() {
  return (
    <div>
      <div className={IntroductionStyle["layer1"]}>
        <img
          src={require("./components/photos/layer1.png")}
          alt=""
          className={IntroductionStyle["layer1-image"]}
        />
      </div>
      <div className={IntroductionStyle["layer2"]}>
        <div className={IntroductionStyle["layer2-wrapper"]}>
          <img
            src={require("./components/photos/layer2.png")}
            alt=""
            className={IntroductionStyle["layer2-image"]}
          />
        </div>
      </div>
      <div
        className={[
          IntroductionStyle["container"],
          IntroductionStyle["labels"],
        ].join(" ")}
      >
        <div>
          <h1>Trở thành chủ nhà</h1>
          <p>
            Explore yourself and people to provide the better looks about your
            hotel and your services
          </p>
          <button>Bắt đầu ngay</button>
        </div>
      </div>
    </div>
  );
}

export default Introduction;
