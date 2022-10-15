// import { useEffect } from "react";
import HistoryDataStyle from "./HistoryData.module.scss";

function HistoryData({ data }) {
  return (
    <>
      {data.map((item, index) => {
        return (
          <div className={HistoryDataStyle["report-element"]} key={index}>
            <div className={HistoryDataStyle["hotel"]}>
              <h4>{item.hotelName}</h4>
              <h4 className={HistoryDataStyle["money-remain"]}>
                -{item.price}$
              </h4>
            </div>
            <div className={HistoryDataStyle["book-detail"]}>
              <p>{item.roomType}</p>
              <p className={HistoryDataStyle["book-time"]}>{item.time}</p>
            </div>
          </div>
        );
      })}
    </>
  );
}

export default HistoryData;
