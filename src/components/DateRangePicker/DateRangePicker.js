import { DayPicker } from "react-day-picker";
import { addDays, format } from "date-fns";
import { useState } from "react";
import './DatePickerTable.scss';


const pastMonth = new Date();
function DateRangePicker() {
    const defaultSelected = {
        from: pastMonth,
        to: addDays(pastMonth, 4),
    };
    const [range, setRange] = useState(defaultSelected);

    let footer = <p>Please pick the first day.</p>;
    if (range?.from) {
        if (!range.to) {
            footer = <p>{format(range.from, "PPP")}</p>;
        } else if (range.to) {
            footer = (
                <p>
                    {format(range.from, "PPP")}–{format(range.to, "PPP")}
                </p>
            );
        }
    }

    return (
        <>
            <DayPicker
                mode="range"
                defaultMonth={pastMonth}
                selected={range}
                // footer={footer}
                modifiersClassNames={{
                    selected: 'selected_day',
                    range_end: 'range_end_day',
                    range_start: 'range_start_day',
                    range_middle: 'range_middle'
                }}
                numberOfMonths={2}
                onSelect={setRange}
            />
        </>
    );
}

export default DateRangePicker;
