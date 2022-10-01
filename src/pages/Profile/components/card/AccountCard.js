import AccountCardStyles from "./AccountCard.module.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

function AccountCard({ icon, title, description, onClick }) {
  return (
    <div className={AccountCardStyles["card"]} onClick={onClick}>
      <div className={AccountCardStyles["container"]}>
        <span className={AccountCardStyles["icon"]}>
          <FontAwesomeIcon icon={icon} />
        </span>

        <h4 className={AccountCardStyles["title"]}>
          <b>{title}</b>
        </h4>
        <p className={AccountCardStyles["description"]}>{description}</p>
      </div>
    </div>
  );
}

export default AccountCard;
