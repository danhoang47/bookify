import checkboxStyles from "./Checkbox.module.scss";
import { useClsx } from "@/utils/hooks";

function Checkbox({ isChecked, setChecked, label ,id,name}) {
  return (
    <div className={[checkboxStyles["checkbox"]]} onClick={() => {}}>
      <input
        type="checkbox"
        className={useClsx(
          checkboxStyles["check-icon"],
          isChecked ? checkboxStyles["checked"] : ""
        )}
        name={name} id={id}
      />
      <label className={checkboxStyles["checkbox-label"]} for={id}>{label}</label>
    </div>
  );
}

export default Checkbox;
