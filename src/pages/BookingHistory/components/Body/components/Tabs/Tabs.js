import ListStyle from "../../../../BookingHistory.module.scss";
import { useContext, useState, useEffect, useMemo } from "react";
import { HistoryContext } from "@/utils/contexts";
import { HistoryCard } from "@/features/account";

function Tabs({ category }) {
  const list = useContext(HistoryContext);
  const renderList = category
    ? list.filter((element) => {
        return Object.keys(category).every((property) => {
          return category[property] === element[property];
        });
      })
    : list;

  const [showMore, setShowMore] = useState(false);
  useEffect(() => {
    document.getElementById("tabs").scrollTo(0, 0);
  }, [showMore]);
  const handleClick = (e) => {
    e.preventDefault();
    showMore ? setShowMore(false) : setShowMore(true);
  };

  return (
    <div className={ListStyle["tabs-body"]}>
      <div className={ListStyle["input-group"]}>
        <select
          name="category"
          id="category"
          className={ListStyle["select-input"]}
        >
          <option value="">Mới nhất</option>
          <option value="">Cũ nhất</option>
        </select>
      </div>
      <div
        id="tabs"
        className={showMore ? ListStyle["tabs-active"] : ListStyle["tabs"]}
      >
        {renderList.map((list, key) => (
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
      <button className={ListStyle["show-more"]} onClick={handleClick}>
        {showMore ? "Ẩn đi" : "Hiển thị thêm"}
      </button>
    </div>
  );
}
export default Tabs;
