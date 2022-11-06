import ExchangeStyle from "./Exchange.module.scss";
import Chart from "./Chart";
import Table from "./Table";
import { TransactionDataYears } from "./AllService";
import { useEffect, useState } from "react";

function Exchange() {
  const [exchangeData, setExchangeData] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/bookify/api/dashboard/exchange")
      .then((res) => res.json())
      .then((result) => {
        setExchangeData(result);
      });
  }, []);

  return (
    <div className={ExchangeStyle["container"]}>
      <Chart exchangeData={exchangeData} />
      <Table exchangeData={exchangeData} />
    </div>
  );
}
export default Exchange;
