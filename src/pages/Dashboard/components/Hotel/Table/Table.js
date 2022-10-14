import TableStyle from "../Hotel.module.scss";
import moment from "moment";
function Table({ data }) {
  return (
    <table>
      <thead>
        <th>Tên chủ khách sạn</th>
        <th>Tên khách sạn</th>
        <th>Thời gian</th>
        <th>Trạng thái</th>
        <th>Chi tiết</th>
      </thead>
      <tbody>
        {data.map((row) => (
          <tr>
            <td>
              <p>{row.hotelhostName}</p>
              <p>ID:{row.ID}</p>
            </td>
            <td>{row.hotelName}</td>
            <td>
              <p>{moment(row.Time).format("MMMM dS, yyyy")}</p>
              <p>Lúc: {moment(row.Time).format("HH:MM")}</p>
            </td>
            <td>
              {row.status === 1 ? (
                <p className={TableStyle["active"]}>Hoạt động</p>
              ) : row.status === 2 ? (
                <p className={TableStyle["waiting"]}>Xét duyệt</p>
              ) : (
                <p className={TableStyle["cancel"]}>Đóng cửa</p>
              )}
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
  );
}
export default Table;
