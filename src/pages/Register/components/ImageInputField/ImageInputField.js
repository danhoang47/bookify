import { Box } from "@mui/material";
import { useEffect, useState } from "react";
import ImageCard from "../ImageCard";
import ImageStyle from "./ImageInputField.module.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUpload } from "@fortawesome/free-solid-svg-icons";

function ViewImageInputField({ id, images, setImages, title }) {
  const [previewViewImages, setPreviewViewImages] = useState();

  const handleChange = (e) => {
    const { files } = e.target;
    const viewImagesArray = Array.from(images || []);

    console.log(files);
    if (viewImagesArray.length === 0) {
      setImages(files);
    } else {
      // merge two fileList
      const mergedFileArray = [...viewImagesArray];
      Array.from(files).forEach((newFile) => {
        let isExised = false;
        viewImagesArray.forEach((currentFile) => {
          if (currentFile.name === newFile.name) {
            isExised = true;
          }
        });
        if (!isExised) {
          mergedFileArray.push(newFile);
        }
      });

      setImages(mergedFileArray);
    }
  };

  const handleRemove = ({ url, index }) => {
    URL.revokeObjectURL(url);
    const viewImagesArray = Array.from(images || []);
    viewImagesArray.splice(index, 1);
    setImages(viewImagesArray);
  };

  useEffect(() => {
    if (!images) {
      setPreviewViewImages(null);
      return;
    }

    const objectUrls = Array.from(images).map((file, index) => ({
      url: URL.createObjectURL(file),
      index: index,
    }));
    setPreviewViewImages(objectUrls);

    return () => {
      objectUrls.forEach(({ url }) => URL.revokeObjectURL(url));
    };
  }, [images]);

  return (
    <div className={ImageStyle["image-add"]}>
      <p>{title}</p>
      <div className={ImageStyle["image-container"]}>
        <div className={ImageStyle["image-input"]}>
          <input type="file" id={id} multiple onChange={handleChange} />
          <label className={ImageStyle["image-show"]} for={id}>
            <FontAwesomeIcon icon={faUpload} />
          </label>
        </div>
        <div className={ImageStyle["image-scroll"]}>
          <Box
            sx={{
              display: "flex",
              gap: "0.4em",
              m: "1rem",
              width: "400px",
            }}
          >
            {previewViewImages?.map((objectUrl, index) => (
              <ImageCard
                objectUrl={objectUrl}
                key={index}
                handleRemove={handleRemove}
              />
            ))}
          </Box>
        </div>
      </div>
    </div>
  );
}

export default ViewImageInputField;
