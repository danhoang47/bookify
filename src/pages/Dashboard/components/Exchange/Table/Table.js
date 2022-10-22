import TableStyle from "../Exchange.module.scss";
import moment from "moment";
import { useState, useContext, useCallback } from "react";
import { HotelContext } from "@/utils/contexts";
function Table() {
  const [filter, setFilter] = useState(null);
  const data = useContext(HotelContext);
  console.log(data);
  const handleChange = useCallback(
    (event) => {
      const value = event.target.value;
      setFilter(value);
      data.sort((a, b) => {
        return filter === "true"
          ? Number(moment(a.Time)) - Number(moment(b.Time))
          : Number(moment(b.Time)) - Number(moment(a.Time));
      });
    },
    [filter, data]
  );
  return (
    <div className={TableStyle["table"]}>
      <div className={TableStyle["header"]}>
        <h2>
          <b>Lịch sử giao dịch</b>
        </h2>
        <select
          name="category"
          id="category"
          onChange={handleChange}
          className={TableStyle["select-input"]}
        >
          <option value="" selected disabled hidden>
            Filter
          </option>
          <option value={true}>Mới nhất</option>
          <option value={false}>Cũ nhất</option>
        </select>
      </div>
      <table>
        <thead>
          <th>Tên </th>
          <th>Thời gian</th>
          <th>ID giao dịch</th>
          <th>Tổng tiền</th>
          <th>Chi tiết</th>
        </thead>
        <tbody>
          {data.map((row, key) => (
            <tr key={key}>
              <td>
                <p>{row.hotelhostName}</p>
                <p>ID:{row.ID}</p>
              </td>
              <td>
                <p>{moment(row.Time).format("MMMM dS, yyyy")}</p>
                <p>Lúc: {moment(row.Time).format("HH:MM")}</p>
              </td>
              <td>
                <p>{row.ID}</p>
              </td>
              <td>
                <p>${row.totalMoney} USD</p>
              </td>
              <td>
                <button>
                  <b>Chi tiết</b>
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
export default Table;
