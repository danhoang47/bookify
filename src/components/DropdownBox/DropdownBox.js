import dropDownStyles from "./DropdownBox.module.scss";
import { Box } from "@mui/material";
import { useClsx } from "@/utils/hooks";

function DropdownBox({
    children,
    heading,
    extraButtonTittle,
    isScrollable,
    handleClick,
}) {
    return (
        <div
            className={useClsx(dropDownStyles["drop-down-box"])}
            onClick={handleClick}
            tabIndex="-1"
        >
            <div className={dropDownStyles["drop-down-header"]}>
                <h4 className={dropDownStyles["heading"]}>{heading}</h4>
                <button className={dropDownStyles["extra-button"]}>
                    <p className={dropDownStyles["extra-button-title"]}>
                        {extraButtonTittle}
                    </p>
                </button>
            </div>
            <div
                className={useClsx(
                    dropDownStyles["drop-down-content"],
                    isScrollable ? dropDownStyles["scroll"] : ""
                )}
                tabIndex="-1"
            >
                {children}
            </div>
            <Box
                sx={{
                    position: "absolute",
                    bottom: 0,
                    height: "1em",
                    backgroundColor: "white",
                }}
            />
        </div>
    );
}

export default DropdownBox;
