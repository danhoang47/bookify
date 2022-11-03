import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import iconButtonStyles from "./IconButton.module.scss";
import { usePopup as useDropdown } from "@/utils/hooks";

function IconButton({ icon, children }) {
    const [isOpen, handleClick, containerRef] = useDropdown();

    return (
        <div 
            className={iconButtonStyles["icon-button-wrapper"]}
            ref={containerRef} 
        >
            <button 
                className={iconButtonStyles["icon-button"]}
                onClick={handleClick}
            >
                <FontAwesomeIcon icon={icon} />
            </button>
            {isOpen && children}
        </div>
    );
}

export default IconButton;
