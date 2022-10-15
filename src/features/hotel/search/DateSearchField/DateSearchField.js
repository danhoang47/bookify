import { DatePicker } from "@/components";
import dateSearchStyles from './DateSearchField.module.scss';

function DateSearchField() {

    return (
        <div className={dateSearchStyles['date-search-field']}>
            <DatePicker 
                numberOfMonths="2"
                mode="range"
            />
        </div>
    );
}

export default DateSearchField;
