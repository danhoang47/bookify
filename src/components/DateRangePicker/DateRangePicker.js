import { DayPicker } from "react-day-picker";
import { addDays, format } from "date-fns";
import { useState, useRef } from "react";
import './DatePickerTable.scss';
import './Cell.scss';


const pastMonth = new Date();
function DateRangePicker({ numberOfMonths, mode }) {
    const defaultSelectedDays = {
        from: new Date(),
        to: addDays(new Date(), 7)
    }
    const disabledDays = {
        from: new Date('2000/1/1'),
        to: addDays(new Date(), -1)
    }
    const [range, setRange] = useState(defaultSelectedDays);

    return (
        <>
            <DayPicker
                mode={mode}
                defaultMonth={pastMonth}
                disabled={disabledDays}
                // footer={footer}
                selected={range}
                modifiersClassNames={{
                    selected: 'selected_day',
                    range_end: 'range_end_day',
                    range_start: 'range_start_day',
                    range_middle: 'range_middle',
                    disabled: 'disabled_day'
                }}
                numberOfMonths={numberOfMonths}
                onSelect={setRange}
            />
        </>
    );
}

export default DateRangePicker;
