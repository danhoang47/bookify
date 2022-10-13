import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faMinus,
    faPlus
} from '@fortawesome/free-solid-svg-icons';
import numberPickerStyles from './NumberPicker.module.scss';
import { useEffect, useState } from "react";

function NumberPicker({ title, description, limit, value, setValue }) {
    const [isExceedLimit, setExceedLimit] = useState(false);
    const [isZero, setZero] = useState(value === 0);

    const handleDecrease = (event) => {
        event.stopPropagation();
        if (value - 1 < 0) {
            return;
        }
        setValue(value - 1);
    }

    const handleIncrease = (event) => {
        event.stopPropagation();
        if (value === limit) {
            return;
        }
        setValue(value + 1);
    }

    useEffect(() => {
        // if number less than zero
        if ( value - 1 < 0 ) {
            setZero(true);
        } else {
            setZero(false);
        }
        // if number exceed limit
        if ( value === limit) {
            setExceedLimit(true);
        } else {
            setExceedLimit(false);
        }

    //eslint-disable-next-line
    }, [value])

    return (  
        <div className={numberPickerStyles['number-picker']}>
            <div className={numberPickerStyles['picker-info']} >
                <p className={numberPickerStyles['title']}>{title}</p>
                <p className={numberPickerStyles['description']}>{description}</p>
            </div>
            <div className={numberPickerStyles['picker']}>
                <button 
                    className={[
                        numberPickerStyles['decrease-button'],
                        isZero ? numberPickerStyles['disabled'] : ''
                    ].join(' ')}
                    onClick={handleDecrease}
                >
                    <FontAwesomeIcon icon={faMinus} />
                </button>
                <div className={numberPickerStyles['value']}>
                    {value}
                </div>
                <button 
                    className={[
                        numberPickerStyles['increase-button'],
                        isExceedLimit ? numberPickerStyles['disabled'] : ''
                    ].join(' ')}
                    onClick={handleIncrease}    
                >
                    <FontAwesomeIcon icon={faPlus} />
                </button>
            </div>
        </div>
    );
}

export default NumberPicker;