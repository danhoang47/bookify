import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import iconButtonStyles from './IconButton.module.scss';

function IconButton({ icon }) {
    return (  
        <button className={iconButtonStyles["icon-button"]}>
            <FontAwesomeIcon icon={icon} /> 
        </button>
    );
}

export default IconButton;