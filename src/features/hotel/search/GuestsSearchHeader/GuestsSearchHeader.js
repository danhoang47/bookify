import guestHeaderStyles from './GuestSearchHeader.module.scss';
import { SearchContext } from '@/utils/contexts';
import { useContext, useMemo } from 'react';

function GuestsSearchHeader({ onTabChange }) {
    const { guests } = useContext(SearchContext);
    const numberOfGuests = useMemo(() => {
        return Object.keys(guests).reduce((prev, type) => {
            if (type !== 'pet') {
                return prev + guests[type];
            }
            else {
                return prev;
            }
        }, 0)
    }, [guests]);

    return (  
        <div
            id={guestHeaderStyles["guest-search"]}
            className={guestHeaderStyles["advance-search__input-field"]}
            index='2'
            onClick={onTabChange}
        >
            <h4 className={guestHeaderStyles["heading"]}>Khách</h4>
            <div className={guestHeaderStyles['guests-input']}>
                { numberOfGuests === 0 ? 'Thêm số người' : `${numberOfGuests} người` }
            </div>
        </div>
    );
}

export default GuestsSearchHeader;