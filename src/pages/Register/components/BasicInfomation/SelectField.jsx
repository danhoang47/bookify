import { Box } from "@mui/material";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faChevronDown } from '@fortawesome/free-solid-svg-icons';
import { memo } from 'react';

function SelectField({ id, label, value, onValueChange, selectionList }) {
    const handleChange = (e) => onValueChange(e.target.value);

    return (
        <Box>
            <input id={id} type="text" value={value ?? ''} onChange={handleChange} />
            <label htmlFor={id}>
                {label}
                <button className="drop-down-button">
                    <FontAwesomeIcon icon={faChevronDown} />
                </button>
            </label>
            <Box>
                {selectionList.map((option, index) => (
                    <div key={index}>{option}</div>
                ))}
            </Box>
        </Box>
    );
}

export default memo(SelectField);
