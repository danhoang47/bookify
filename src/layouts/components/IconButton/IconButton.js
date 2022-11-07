import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import iconButtonStyles from "./IconButton.module.scss";
import { usePopup as useDropdown } from "@/utils/hooks";

function IconButton({ icon, renderChild }) {
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
            {isOpen && renderChild(handleClick)}
        </div>
    );
}

export default IconButton;
