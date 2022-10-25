import { Box } from "@mui/material";

const ImageCard = ({ objectUrl, handleRemove }) => {
    return (
        <Box
            sx={{
                width: "10em",
                height: "10em",
            }}
            onClick={(e) => { handleRemove(objectUrl) }}
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