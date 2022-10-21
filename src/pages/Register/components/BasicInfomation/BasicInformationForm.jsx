import { RegisterContext } from "@/utils/contexts";
import { useContext, useId, useRef } from 'react';
import InputField from "./InputField";
import SelectField from "./SelectField";
import { getHotelRegisterLabel } from "@/utils/validation";

const getInputFieldType = (key) => {
    if (key === 'name' || key === 'address') {
        return InputField;
    }
    else { return SelectField }
}

function BasicInformationForm({ className }) {
    const { basicHotelInfor, setBasicHotelInfo } = useContext(RegisterContext);

    return (  
        <div className={className}>
            {
                Object.keys(basicHotelInfor).reduce((prev, key) => {
                    const InputType = getInputFieldType(key);

                    if (InputType === SelectField) {
                        
                    }

                    if ( key === 'description' ) {
                        return [...prev]
                    } else {
                        return ([...prev, (
                            <InputType 
                                key={key}
                                id={key}
                                label={getHotelRegisterLabel(key)}
                                value={basicHotelInfor[key]}
                                onValueChange={(value) => {
                                    setBasicHotelInfo((prev) => ({
                                        ...prev,
                                        [key]: value
                                    }))
                                }}
                                selectionList={[]}
                            />
                        )])
                    }
                }, [])
            }
        </div>
    );
}

export default BasicInformationForm;