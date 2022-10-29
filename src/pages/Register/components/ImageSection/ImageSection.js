import { RegisterContext } from "@/utils/contexts";
import ImageStyle from "./ImageSection.module.scss";
import { useContext, useEffect, useState } from "react";
import ImageInputField from "../ImageInputField";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUpload } from "@fortawesome/free-solid-svg-icons";
import { Box } from "@mui/material";

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
    <>
      <div className={ImageStyle["header"]}>
        <h3>Cập nhật ảnh về khách sạn của bạn</h3>
      </div>
      <div className={ImageStyle["body-container"]}>
        <div className={ImageStyle["body"]}>
          <div className={ImageStyle["image-background"]}>
            <p>Chọn ảnh bìa cho khách sạn của bạn</p>
            <div className={ImageStyle["image-container"]}>
              <input type="file" id="upfile" onChange={handleChange} />
            </div>
            <label className={ImageStyle["image-show"]} for="upfile">
              <div className={ImageStyle["image-placeholder"]}>
                <FontAwesomeIcon icon={faUpload} />
                <p>Tải ảnh lên</p>
              </div>
              {previewBackgroundImage && (
                <img src={previewBackgroundImage} alt="" />
              )}
            </label>
          </div>
          <ImageInputField
            id={"around"}
            images={viewImages}
            setImages={setViewImages}
            title={"Cung cấp ảnh về không gian xung quanh khách sạn"}
          />
          <ImageInputField
            id={"room"}
            images={roomImages}
            setImages={setRoomImages}
            title={"Cung cấp ảnh về phòng ngủ của khách sạn"}
          />
        </div>
      </div>
    </>
  );
}

export default ImageSection;
