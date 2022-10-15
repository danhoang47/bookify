import SingleLineChart from "@/components/Chart/SingleLineChart";
import { useContext } from "react";
import ChartStyle from "./Chart.module.scss";
import { OverrallContext } from "../../Overall";

function Chart({ labels, data }) {
  // const [month, setMonth] = useContext(OverrallContext);

  return (
    <div>
      <SingleLineChart label="Giao dịch" labels={labels} data={data} />
    </div>
  );
}

export default Chart;
