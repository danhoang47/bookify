import TableStyle from "../Exchange.module.scss";
import { format } from "date-fns";
import moment from "moment/moment";
import { useState, useContext, useCallback } from "react";
import { HotelContext } from "@/utils/contexts";
function Table({ exchangeData }) {
  const [filter, setFilter] = useState(null);
  const data = useContext(HotelContext);

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
          {exchangeData?.map((row, key) => (
            <tr key={key}>
              <td>
                <p>{row.userFullName ? row.userFullName : row.username}</p>
                <p>ID:{row.user_id}</p>
              </td>
              <td>
                <p>{format(new Date(row.check_out), "MMMM dd, yyyy")}</p>
                <p>Lúc: 12:10</p>
              </td>
              <td>
                <p>{row.booking_id}</p>
              </td>
              <td>
                <p>${row.price} USD</p>
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
