import dropDownStyles from "./DropdownBox.module.scss";
import { Box } from "@mui/material";
import { useClsx } from "@/utils/hooks";
import { useContext } from 'react';
import { UserContext } from "@/utils/contexts";

function DropdownBox({
    children,
    heading,
    extraButtonTittle,
    extraButtonHandleClick,
    isScrollable,
    tabs
}) {
    const { user } = useContext(UserContext)

    return (
        <div
            className={useClsx(dropDownStyles["drop-down-box"])}
            tabIndex="-1"
        >
            <div className={dropDownStyles["drop-down-header"]}>
                <h4 className={dropDownStyles["heading"]}>{heading}</h4>
                <button className={dropDownStyles["extra-button"]}>
                    <p 
                        className={dropDownStyles["extra-button-title"]} 
                        onClick={extraButtonHandleClick}
                    >
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
