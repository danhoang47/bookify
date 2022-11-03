import "./AllImageSection.scss";
import { useClsx } from "@/utils/hooks";
import { useState } from "react";
import ImageCarousel from "../ImageCarousel/ImageCarousel";
import { v4 as uuid } from "uuid";
import { ImageList, ImageListItem } from "@mui/material";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faClose } from '@fortawesome/free-solid-svg-icons'

const ImageItem = ({ src, index, setActiveImageIndex }) => {
    return (
        <ImageListItem
            className={"image-item"}
            onClick={() => setActiveImageIndex(index)}
        >
            <img src={src} alt="" loading="lazy" />
        </ImageListItem>
    );
};

function AllImageSection({ backgroundImage, images, setAllImageOpen }) {
    const [activeImageIndex, setActiveImageIndex] = useState(null);

    console.log(backgroundImage, images);
    return (
        <div
            id={"all-image-section"}
            className={useClsx(activeImageIndex && "no-scroll")}
        >
            <div className="heading">
                <h2>Khách sạn Vinpearl Nam Hội An</h2>
            </div>
            <div className={useClsx(activeImageIndex && "no-scroll")}>
                <div className={useClsx("image-section")}>
                    <h3 className={useClsx("heading")}>Ảnh bìa</h3>
                    <ImageItem
                        src={backgroundImage}
                        index={0}
                        setActiveImageIndex={setActiveImageIndex}
                    />
                </div>
                <div className={useClsx("image-section")}>
                    <h3 className={useClsx("heading")}>
                        Ảnh không gian xung quanh
                    </h3>
                    <ImageList variant="mansonry" cols={3} gap={8}>
                        {images.reduce((prev, { id, src, type }, index) => {
                            if (type === 0) {
                                return [
                                    ...prev,
                                    <ImageItem
                                        key={id}
                                        src={src}
                                        id={id}
                                        index={index + 1}
                                        setActiveImageIndex={
                                            setActiveImageIndex
                                        }
                                    />,
                                ];
                            } else {
                                return prev;
                            }
                        }, [])}
                    </ImageList>
                </div>
                <div className={useClsx("image-section")}>
                    <h3 className={useClsx("heading")}>
                        Ảnh nội thất trong phòng
                    </h3>
                    <ImageList>
                        {images.reduce((prev, { id, src, type }, index) => {
                            if (type === 1) {
                                return [
                                    ...prev,
                                    <ImageItem
                                        key={id}
                                        src={src}
                                        id={id}
                                        index={index + 1}
                                        setActiveImageIndex={
                                            setActiveImageIndex
                                        }
                                    />,
                                ];
                            } else {
                                return prev;
                            }
                        }, [])}
                    </ImageList>
                </div>
            </div>
            <div>
                {activeImageIndex !== null ? (
                    <ImageCarousel
                        defaultActiveIndex={activeImageIndex}
                        images={[
                            {
                                id: uuid(),
                                src: backgroundImage,
                                type: 2,
                            },
                            ...images,
                        ]}
                        setActiveImageIndex={setActiveImageIndex}
                    />
                ): ''}
            </div>
            <button 
                className={'close-album-button'}
                onClick={() => setAllImageOpen(false)}
            >
                <FontAwesomeIcon icon={faClose} />
            </button>
        </div>
    );
}

export default AllImageSection;
