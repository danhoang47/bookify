import guestsPickerStyles from './GuestsPicker.module.scss';
import NumberPicker from '../NumberPicker';
import { memo } from 'react';

function GuestsPicker({ guests, setGuests, limit, description = null, title = null }) {

    return (  
        <div id={guestsPickerStyles['guest-picker-field']}>
            {
                Object.keys(guests).map((type) => (
                    <NumberPicker 
                        title={title[type]}
                        description={description[type]}
                        limit={limit ?? 100}
                        value={guests[type]}
                        setValue={(value) => {
                            setGuests((prev) => {
                                return {
                                    ...prev,
                                    [type]: value
                                }
                            })
                        }}
                    />
                ))
            }
        </div>
    );
}

export default memo(GuestsPicker);