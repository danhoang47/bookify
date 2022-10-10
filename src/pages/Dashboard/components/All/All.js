import AllStyle from "./All.module.scss";
import StaticCard from "./StaticCard";
import Chart from "./Chart";

function All() {
  return (
    <div className={AllStyle["dashboard-all"]}>
      <div className={AllStyle["time-wrapper"]}>
        <div>
          <h2>Tháng 10, năm 2022</h2>
        </div>
        <div>
          <h4>Tháng này</h4>
        </div>
      </div>
      <div className={AllStyle["static"]}>
        <StaticCard />
      </div>
      <div className={AllStyle["charts"]}>
        <Chart />
      </div>
    </div>
  );
}

export default All;
