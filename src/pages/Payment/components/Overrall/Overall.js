import Chart from "./components/Chart";
import History from "./components/History";
import OverrallStyle from "./Overrall.module.scss";

function Overall() {
  return (
    <div>
      <div className={OverrallStyle["chart-wrapper"]}>
        <Chart />
      </div>
      <div className={OverrallStyle["history-wrapper"]}>
        <History />
      </div>
    </div>
  );
}

export default Overall;
