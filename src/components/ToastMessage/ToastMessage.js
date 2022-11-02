import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faXmarkCircle,
    faCircleCheck,
} from "@fortawesome/free-regular-svg-icons";
import { toastType } from "@/utils/reducers/toastMessageReducer";
import toastMessageStyles from "./ToastMessage.module.scss";
import { useClsx } from "@/utils/hooks";

const getToastType = (type) => {
    switch (type) {
        case toastType.SUCCESS:
            return faCircleCheck;
        case toastType.FAILURE:
            return faXmarkCircle;
        default:
            throw new Error("Invalid Toast Message Type");
    }
};

function ToastMessage({ type, message }) {
    return (
        <div className={useClsx(
                toastMessageStyles["toast-message"],
                toastMessageStyles[type]
            )}
        >
            <FontAwesomeIcon icon={getToastType(type)} />
            <p className={toastMessageStyles['message']}>{message}</p>
        </div>
    );
}

export default ToastMessage;
