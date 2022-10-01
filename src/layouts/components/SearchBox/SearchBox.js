import  { Input } from '@/components'
import { useState, memo } from 'react';
import searchBoxStyles from './SearchBox.module.scss'
import {
    useDebounce
} from '@/utils/hooks'

function SearchBox() {
    const [searchValue, setSearchValue] = useState('');

    return (  
        <div className={searchBoxStyles["search-box"]}>
            <Input 
                value={searchValue}
                onValueChange={setSearchValue}
                placeholder="Tìm kiếm"
                width="f-width"
            />
        </div>
    );
}

export default memo(SearchBox);