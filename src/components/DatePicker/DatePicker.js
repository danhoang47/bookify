import { DayPicker } from "react-day-picker";
import { addDays } from "date-fns";
import { useContext } from "react";
import './DatePickerTable.scss';
import './Cell.scss';
import { SearchContext } from "@/utils/contexts";

const pastMonth = new Date();
function DatePicker({ numberOfMonths, mode }) {
    const disabledDays = {
        from: new Date('2000/1/1'),
        to: addDays(new Date(), -1)
    }
    const { selectedDays, setSelectedDays } = useContext(SearchContext);

    return (
        <>
            <DayPicker
                mode={mode}
                defaultMonth={pastMonth}
                disabled={disabledDays}
                // footer={footer}
                selected={selectedDays}
                modifiersClassNames={{
                    selected: 'selected_day',
                    range_end: 'range_end_day',
                    range_start: 'range_start_day',
                    range_middle: 'range_middle',
                    disabled: 'disabled_day'
                }}
                numberOfMonths={numberOfMonths}
                onSelect={setSelectedDays}
            />
        </>
    );
}

export default DatePicker;
