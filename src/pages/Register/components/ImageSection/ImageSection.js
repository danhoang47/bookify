import { RegisterContext } from "@/utils/contexts";
import { useContext, useEffect, useState } from "react";
import ImageInputField from "../ImageInputField";

function ImageSection() {
    const {
        viewImages,
        setViewImages,
        roomImages,
        setRoomImages,
        backgroundImage,
        setBackgroundImage,
    } = useContext(RegisterContext);
    const [previewBackgroundImage, setPreviewBackgroundImage] = useState();

    const handleChange = (e) => {
        const { files } = e.target;
        setBackgroundImage(files[0]);
    };

    useEffect(() => {
        if (!backgroundImage) {
            setPreviewBackgroundImage(null);
            return;
        }
        const url = URL.createObjectURL(backgroundImage);
        setPreviewBackgroundImage(url);

        return () => URL.revokeObjectURL(url);
        
    }, [backgroundImage]);

    return (
        <div>
            <div>
                <h3>Cập nhật ảnh về khách sạn của bạn</h3>
            </div>
            <div>
                <div>
                    <p>Chọn ảnh bìa cho khách sạn của bạn</p>
                    <div>
                        <input type="file" onChange={handleChange} />
                    </div>
                    <div>
                        {previewBackgroundImage && <img src={previewBackgroundImage} alt="" /> }
                    </div>
                </div>
                <ImageInputField
                    images={viewImages}
                    setImages={setViewImages}
                    title={"Cung cấp ảnh về không gian xung quanh khách sạn"}
                />
                <ImageInputField
                    images={roomImages}
                    setImages={setRoomImages}
                    title={"Cung cấp ảnh về phòng ngủ của khách sạn"}
                />
            </div>
        </div>
    );
}

export default ImageSection;
