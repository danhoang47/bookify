import searchModalStyles from './SearchModal.module.scss';
import GuestsSearchField from '../GuestsSearchField';
import ExploreSearchField from '../ExploreSearchField';
import DateSearchField from '../DateSearchField';

function SearchModal({
    searchTerms
}) {


    return (  
        <div className={searchModalStyles['search-modal']}>
            <DateSearchField />
        </div>
    );
}

export default SearchModal;