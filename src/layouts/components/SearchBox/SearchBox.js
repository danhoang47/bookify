import  { Input } from '@/components'
import { useState, memo, useEffect } from 'react';
import {
    usePopup
} from '@/utils/hooks';
import searchBoxStyles from './SearchBox.module.scss';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMagnifyingGlass, faXmark } from '@fortawesome/free-solid-svg-icons';
import { SearchModal } from '@/features/hotel/search'

function SearchBox() {
    const [searchTerms, setSearchTerms] = useState('');
    const [isOpen, handleClick, containerRef] = usePopup();

    const handleOpenSearchBar = (event) => {
        handleClick(event);
    }

    return (  
        <div 
            className={searchBoxStyles['search-box']}
            ref={containerRef}            
        >
            <div 
                className={searchBoxStyles['search-bar']}
            >
                <Input 
                    style={searchBoxStyles['search-input-field']}
                    value={searchTerms}
                    onValueChange={setSearchTerms}
                    placeholder="Tìm kiếm"
                    width="f-width"
                    id="search-input-field"
                    isOpen={isOpen}
                    handleOpenSearchBar={handleOpenSearchBar}
                />
                {   isOpen || (
                    <label 
                        htmlFor='search-input-field'
                        className={searchBoxStyles['search-glass-icon']}
                    >
                        <FontAwesomeIcon icon={faMagnifyingGlass} />                    
                    </label>
                    )
                }
                {   isOpen && (
                    <label 
                        className={searchBoxStyles['search-close-icon']}
                    >
                        <FontAwesomeIcon icon={faXmark} />                    
                    </label>
                    )
                }
            </div>
            {
                (
                    <SearchModal 
                        searchTerms={searchTerms}
                    />
                )
            }
        </div>
    );
}

export default memo(SearchBox);