import FileUploadStyle from "./FileUpload.module.scss";
import { faUser } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useEffect, useState } from "react";

function FileUpload({ avatar, onAvatarUpload, readOnly }) {
  const [selectedFile, setSelectedFile] = useState();
  const [isFilePicked, setIsFilePicked] = useState(false);
  const [preview, setPreview] = useState(avatar);

  useEffect(() => {
    if (preview === avatar && avatar) {
      setIsFilePicked(true);
      return;
    }

    if (!selectedFile) {
      setPreview(undefined);
      return;
    }

    const objectUrl = URL.createObjectURL(selectedFile);
    setPreview(objectUrl);

    // free memory when ever this component is unmounted
    return () => URL.revokeObjectURL(objectUrl);
  }, [selectedFile]);

  const onSelectFile = (e) => {
    if (!e.target.files || e.target.files.length === 0) {
      setSelectedFile(undefined);
      return;
    }

    // using the first image instead of multiple
    setSelectedFile(e.target.files[0]);
    setPreview(e.target.files[0]);
    onAvatarUpload(e.target.files[0]);
    setIsFilePicked(true);
  };
  return (
    <div className={FileUploadStyle["upload-container"]}>
      <div className={FileUploadStyle["wrapper"]}>
        <div
          className={
            !readOnly
              ? FileUploadStyle["file-upload"]
              : FileUploadStyle["file-upload-readOnly"]
          }
        >
          {isFilePicked ? (
            <img src={preview} accept="image/*" alt="" />
          ) : (
            <>
              <input
                type="file"
                name="file"
                onChange={onSelectFile}
                disabled={readOnly}
              />
              <FontAwesomeIcon icon={faUser} />
            </>
          )}
        </div>
      </div>
      <label htmlFor="" className={FileUploadStyle["upload-image"]}>
        {isFilePicked ? (
          <>
            <input
              type="file"
              name="file"
              onChange={onSelectFile}
              id={"custom-file-input"}
              style={{ display: "none" }}
              disabled={readOnly}
            />
            <label
              htmlFor="custom-file-input"
              className={
                !readOnly
                  ? FileUploadStyle["upload-label"]
                  : FileUploadStyle["upload-label-readOnly"]
              }
            >
              Cập nhật ảnh đại diện
            </label>
          </>
        ) : (
          <>Cập nhật ảnh đại diện</>
        )}
      </label>
    </div>
  );
}

export default FileUpload;
