import ListStyle from "../../../../BookingHistory.module.scss";
import { useContext } from "react";
import { Link } from "react-router-dom";
import { HistoryContext } from "@/utils/contexts";
import { HistoryCard } from "@/features/account";
function List(props) {
  console.log(props.category);
  console.log(props.status);
  const list = useContext(HistoryContext);
  console.log(list);
  return (
    <div>
      {/* <div className={ListStyle["input-group"]}>
        <select
          name="category"
          id="category"
          className={ListStyle["select-input"]}
        >
          <option value="">Mới nhất</option>
          <option value="">Cũ nhất</option>
        </select>
      </div> */}
      {list.map((list) => (
        <HistoryCard
          room={list.room}
          hotel={list.hotel}
          address={list.address}
          adult={list.adult}
          price={list.price}
          status={list.status}
          checkinDate={list.checkinDate}
          checkoutDate={list.checkoutDate}
        />
      ))}
    </div>
  );
}
export default List;
