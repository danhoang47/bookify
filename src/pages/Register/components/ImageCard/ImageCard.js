import { Box } from "@mui/material";
import ImageCardStyle from "./ImageCard.module.scss";

const ImageCard = ({ objectUrl, handleRemove, type }) => {
  return (
    <Box
      sx={{
        width: "10em",
        height: "10em",
      }}
      className={ImageCardStyle["card"]}
      onClick={() => {
        handleRemove(objectUrl)
      }}
    >
      <img
        src={objectUrl.url}
        alt=""
        style={{
          width: "100%",
          height: "100%",
        }}
      />
    </Box>
  );
};

export default ImageCard;
