import albumStyles from "./Album.module.scss";
import AllImageSection from "../AllImageSection";

function Album({ backgroundImage, images, isAllImageOpen, setAllImageOpen }) {
  return (
    <div id={albumStyles["hotel-album"]}>
      <div className={albumStyles["initial-hotel-album"]}>
        <div className={albumStyles["left"]}>
          <div className={albumStyles["preview-hotel-image"]}>
            <img src={`http://localhost:3001${backgroundImage}`} alt="" />
          </div>
        </div>
        <div className={albumStyles["right"]}>
          <div className={albumStyles["top-right"]}>
            <div className={albumStyles["preview-hotel-image"]}>
              <img
                src={`http://localhost:3001${images[0]?.imagePath}`}
                alt=""
              />
            </div>
          </div>
          <div className={albumStyles["bottom-right"]}>
            {images?.reduce((prev, { _id, imagePath }, index) => {
              if (index <= 2 && index >= 1) {
                return [
                  ...prev,
                  <div key={_id} className={albumStyles["preview-hotel-image"]}>
                    <img src={`http://localhost:3001${imagePath}`} alt="" />
                    {console.log(`http://localhost:3001${imagePath}`)}
                  </div>,
                ];
              } else {
                return prev;
              }
            }, [])}
          </div>
        </div>
        <button
          id={albumStyles["show-all-images"]}
          onClick={() => setAllImageOpen(true)}
        >
          <span>Xem tất cả ảnh</span>
        </button>
      </div>
      {isAllImageOpen && (
        <AllImageSection
          backgroundImage={backgroundImage}
          images={images}
          setAllImageOpen={setAllImageOpen}
        />
      )}
    </div>
  );
}

export default Album;
