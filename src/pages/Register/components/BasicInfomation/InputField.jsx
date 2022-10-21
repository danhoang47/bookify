import { Box } from '@mui/material';

function InputField({ id, label, value, onValueChange }) {
    return (  
        <Box>
            <input id={id} type="text" onChange={onValueChange} value={value}/>
            <label htmlFor={id}>
                {label}
            </label>
        </Box>
    );
}

export default InputField;