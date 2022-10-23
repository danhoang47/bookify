import checkboxStyles from "./Checkbox.module.scss";
import { useClsx } from "@/utils/hooks";

function Checkbox({ isChecked, setChecked, label }) {


    return (
        <div className={[checkboxStyles['checkbox']]} onClick={() => {}}>
            <button
                className={useClsx(
                    checkboxStyles["check-icon"],
                    isChecked ? checkboxStyles["checked"] : ""
                )}
            ></button>
            <label className={checkboxStyles['checkbox-label']}>{label}</label>
        </div>
    );
}

export default Checkbox;
