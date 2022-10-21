import { Box } from "@mui/material";

function SelectField({ id, label, value, onValueChange, selectionList }) {
    return (
        <Box>
            <input id={id} type="text" value={value} onChange={onValueChange} />
            <label htmlFor={id}>{label}</label>
            <Box>
                {selectionList.map((option, index) => (
                    <div key={index}>{option}</div>
                ))}
            </Box>
        </Box>
    );
}

export default SelectField;
