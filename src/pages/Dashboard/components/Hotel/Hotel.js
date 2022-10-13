import TableStyle from "./Hotel.module.scss";
function Hotel({ data }) {
  return (
    <div className={TableStyle["container"]}>
      <h2>
        <b>Danh sách khách sạn</b>
      </h2>
      <table>
        <thead>
          <th>Tên chủ khách sạn</th>
          <th>Tên khách sạn</th>
          <th>Thời gian</th>
          <th>Trạng thái</th>
          <th>Chi tiết</th>
        </thead>
      </table>
    </div>
  );
}
export default Hotel;
