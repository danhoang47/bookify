import TableStyle from "./Hotel.module.scss";
import Table from "./Table";
import { useState, useContext, useCallback } from "react";
import { HotelContext } from "@/utils/contexts";
import moment from "moment";
function Hotel() {
  const [filter, setFilter] = useState(null);
  const data = useContext(HotelContext);
  console.log(data);
  const handleChange = useCallback(
    (event) => {
      console.log("push");
      const value = event.target.value;
      setFilter(value);
      data.sort((a, b) => {
        return filter
          ? Number(moment(a.Time)) - Number(moment(b.Time))
          : Number(moment(b.Time)) - Number(moment(a.Time));
      });
    },
    [filter, data]
  );
  return (
    <div className={TableStyle["container"]}>
      <div className={TableStyle["header"]}>
        <h2>
          <b>Danh sách khách sạn</b>
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
      <Table data={data} filter={filter} />
    </div>
  );
}
export default Hotel;
