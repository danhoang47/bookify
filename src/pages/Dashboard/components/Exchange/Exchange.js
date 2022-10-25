import ExchangeStyle from "./Exchange.module.scss";
import Chart from "./Chart";
import Table from "./Table";
import { TransactionDataYears } from "./AllService";

function Exchange() {
  console.log(TransactionDataYears("2014"));

  return (
    <div className={ExchangeStyle["container"]}>
      <Chart />
      <Table />
    </div>
  );
}
export default Exchange;
