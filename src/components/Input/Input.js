import inputStyles from './Input.module.scss'
import { memo } from 'react';

function Input({ 
    id='',
    type= 'text',
    placeholder = '',
    value = '',
    onValueChange,
    width = '',
    isValid = true,
    style
}) {

    return (  
        <>
            <input 
                id={id}
                className={[
                    inputStyles["input-bar"],
                    inputStyles[width] ?? '',
                    isValid ? '' : inputStyles['error'],
                    style ?? ''
                ].join(' ')}
                type={type}
                value={value}
                placeholder={placeholder}
                onChange={(e) => onValueChange(e.target.value)}
            />
        </>
    );
}

export default memo(Input);