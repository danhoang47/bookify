import albumStyles from "./Album.module.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCameraRetro } from "@fortawesome/free-solid-svg-icons";
import { useState } from "react";
import AllImageSection from "../AllImageSection";

function Album({ backgroundImage, images }) {
    const [isAllImageOpen, setAllImageOpen] = useState(false);

    return (
        <div id={albumStyles["hotel-album"]}>
            <div className={albumStyles["initial-hotel-album"]}>
                <div className={albumStyles["left"]}>
                    <div className={albumStyles["preview-hotel-image"]}>
                        <img src={backgroundImage} alt="" />
                    </div>
                </div>
                <div className={albumStyles["right"]}>
                    <div className={albumStyles["top-right"]}>
                        <div className={albumStyles["preview-hotel-image"]}>
                            <img src={images[0].src} alt="" />
                        </div>
                    </div>
                    <div className={albumStyles["bottom-right"]}>
                        {images.reduce((prev, { id, src }, index) => {
                            if (index <= 2 && index >= 1) {
                                return [
                                    ...prev,
                                    <div
                                        key={id}
                                        className={
                                            albumStyles["preview-hotel-image"]
                                        }
                                    >
                                        <img src={src} alt="" />
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
