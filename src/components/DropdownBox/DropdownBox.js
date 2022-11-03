import dropDownStyles from "./DropdownBox.module.scss";

function DropdownBox({ children, heading, extraButtonTittle }) {
    return (
        <div className={dropDownStyles["drop-down-box"]}>
            <div className={dropDownStyles['drop-down-header']}>
                <h4 className={dropDownStyles['heading']}>
                    {heading}
                </h4>
                <button className={dropDownStyles['extra-button']}>
                    <p className={dropDownStyles['extra-button-title']}>{extraButtonTittle}</p>
                </button>
            </div>
            <div className={dropDownStyles['drop-down-content']}>
                {children}
            </div>
        </div>
    );
}

export default DropdownBox;
