import barStyles from './Bar.module.scss'
import { useState } from 'react';

function Bar({ range, numberOfHotels, maxFrequency, maxHeight, currentRange }) {
    const [height, setHeight] = useState(() => {
        if (numberOfHotels === 0) {
            return 4;
        }
        else {
            return (numberOfHotels / maxFrequency) * maxHeight;
        }
    });

    return (
        <div 
            className={[
                barStyles['bar'],
                currentRange.min <= range.min ? barStyles['active'] : ''
            ].join(' ')}
            style={{
                height: `${height}px`
            }}
        >
        </div>
    )
}

export default Bar;