import ExchangeStyle from "./Exchange.module.scss";
import Chart from "./Chart";
import Table from "./Table";

function Exchange() {
 
  return (
    <div className={ExchangeStyle["container"]}>
      <Chart />
      <Table />
    </div>
  );
}
export default Exchange;
