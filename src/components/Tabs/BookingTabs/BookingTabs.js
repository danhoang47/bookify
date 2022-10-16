import TabStyle from "./Tabs.module.scss";
import { faCircleUser } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
function BookingTabs({ list, category }) {
  const onClick = (e) => {
    console.log("onClick");
  };
  return (
    <>
      {list.map((element) => (
        <div className={TabStyle["tab-card"]}>
          <div className={TabStyle["card-header"]}>
            <b className={TabStyle["color-blue"]}>Hôm nay</b>
            <p>
              {element.roomtype} - {element.adults} người lớn,{element.children}{" "}
              trẻ em{" "}
            </p>
          </div>
          <div className={TabStyle["card-body"]}>
            <div className={TabStyle["info"]}>
              <b>{element.username}</b>
              <p>
                From:<span> {element.checkindate}</span>
              </p>
              <p>
                To:<span> {element.checkoutdate}</span>
              </p>
            </div>
            {element.avatar ? (
              <img
                className={TabStyle["image"]}
                src={element.avatar}
                alt="avatar"
              />
            ) : (
              <FontAwesomeIcon
                className={TabStyle["image"]}
                icon={faCircleUser}
              />
            )}
          </div>
          <div className={TabStyle["button-group"]}>
            <button onClick={onClick} className={TabStyle["button-1"]}>
              Message
            </button>
            <button onClick={onClick} className={TabStyle["button-2"]}>
              Call
            </button>
          </div>
        </div>
      ))}
    </>
  );
}
export default BookingTabs;
