import logoStyles from "./Logo.module.scss";
import { 
    faCircleNotch,
    faCircle
} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { memo } from 'react';

function Logo({ children }) {

  return (
    <div className={logoStyles["logo-wrapper"]}>
      <div className={logoStyles["logo"]}>
        <FontAwesomeIcon 
          className={logoStyles["fa-circle-notch"]}
          icon={faCircleNotch} 
        />
        <FontAwesomeIcon 
          className={logoStyles["fa-circle"]}
          icon={faCircle} 
        />
      </div>
      {children}
    </div>
  );
}

export default memo(Logo);
