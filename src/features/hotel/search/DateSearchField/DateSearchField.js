import { DatePicker } from "@/components";
import { useState } from 'react';

function DateSearchField() {

    return (
        <DatePicker 
            numberOfMonths="2"
            mode="range"
        />
    );
}

export default DateSearchField;
