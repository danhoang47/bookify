import { createContext, useState } from "react";
import Chart from "./components/Chart";
import History from "./components/History";
import OverrallStyle from "./Overrall.module.scss";
import { getLabelAndData } from "./HistoryService";
import { data } from "./FakeHistoryData";

export const OverrallContext = createContext();

function Overall() {
  let date = new Date();
  const [month, setMonth] = useState(
    date.toLocaleString("default", { month: "short" })
  );
  // console.log(month);
  let { labels, dataLabel, hotelData } = getLabelAndData(month);

  return (
    <div>
      <OverrallContext.Provider value={[month, setMonth]}>
        <div className={OverrallStyle["chart-wrapper"]}>
          <Chart labels={labels} data={dataLabel} />
        </div>
        <div className={OverrallStyle["history-wrapper"]}>
          <History data={hotelData} />
        </div>
      </OverrallContext.Provider>
    </div>
  );
}

export default Overall;
