import { useEffect, useState } from "react";
import HistoryData from "./components/HistoryData";
import MonthPicker from "./components/MonthPicker";
import HistoryStyle from "./History.module.scss";
import { data } from "./FakeHistoryData";

function History() {
  const [showmore, setShowmore] = useState(false);
  useEffect(() => {
    document.getElementById("list-history").scrollTo(0, 0);
  }, [showmore]);

  return (
    <div className={HistoryStyle["history-list"]}>
      <MonthPicker />

      <div
        className={
          showmore
            ? HistoryStyle["stat-list-active"]
            : HistoryStyle["stat-list"]
        }
        id="list-history"
      >
        <HistoryData data={data} />
      </div>
      <button
        className={HistoryStyle["show-btn"]}
        onClick={() => {
          setShowmore(!showmore);
        }}
      >
        {showmore ? "Ẩn đi" : "Hiển thị thêm"}
      </button>
    </div>
  );
}

export default History;
