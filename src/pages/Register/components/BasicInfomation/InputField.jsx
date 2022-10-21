import { Box } from '@mui/material';
import { useEffect } from 'react';
import { memo, useRef, useState } from 'react';

function InputField({ id, label, value, onValueChange }) {
    const handleChange = (e) => onValueChange(e.target.value);
    const [isFocus, setFocused] = useState(false);
    const inputRef = useRef();

    const handleFocus = (e) => {
        e.stopPropagation();
        setFocused(true);
    }

    useEffect(() => {
        const inputElement = inputRef.current;

        // if element hasn't received focus
        if (!isFocus) {
            inputElement.addEventListener('focus', handleFocus);
        } else { return }

        return () => {
            inputElement.removeEventListener('focus', handleFocus);
        }
    }, [inputRef, isFocus])

    console.log('re-render', isFocus)
    return (  
        <Box>
            <input 
                id={id} 
                ref={inputRef}
                type="text" 
                value={value ?? ''}
                onChange={() => {}}
            />
            <label htmlFor={id}>
                {label}
            </label>
        </Box>
    );
}

export default memo(InputField);